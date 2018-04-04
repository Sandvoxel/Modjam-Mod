package com.sandvoxel.immersivemagic.common.spells;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.blocks.Blocks;
import com.sandvoxel.immersivemagic.common.blocks.LiquefactedBlock;
import com.sandvoxel.immersivemagic.common.magicdata.Affinities;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import com.sandvoxel.immersivemagic.common.spells.lib.SpellBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class MiningSpell extends SpellBase {
    protected AffinityTypes spellAffType = AffinityTypes.EARTH;
    protected int baseManaCost = 100;
    private int ticksAfterCast;
    private BlockPos castingPos;

    public MiningSpell() {
        super("spell_mining", "spell_earth", SpellTypes.DIRECT_SPELL, null, AffinityTypes.EARTH, 100);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        successfulCast = true;
        castingPos = pos;
        IAffinities affinities = playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null);

        int i = 0;
        if (!worldIn.isRemote) {
            int manaCost;

            for (BlockPos affectedBlock : BlockPos.getAllInBox(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
                Block blockInQuestion = worldIn.getBlockState(affectedBlock).getBlock();
                manaCost = blockInQuestion.getHarvestLevel(worldIn.getBlockState(affectedBlock)) * baseManaCost;
                if (affinities.canCast(manaCost, spellAffType)) {
                    if(blockInQuestion.getHarvestLevel(worldIn.getBlockState(affectedBlock)) != -1.0f && blockInQuestion.getMaterial(worldIn.getBlockState(affectedBlock)) == Material.ROCK) {
                        ticksAfterCast = 5;
                        affinities.addXp(blockInQuestion.getHarvestLevel(worldIn.getBlockState(affectedBlock)), spellAffType);
                        worldIn.destroyBlock(affectedBlock, true);
                        worldIn.notifyNeighborsOfStateChange(affectedBlock, net.minecraft.init.Blocks.AIR, true);
                        i++;
                    } else {
                        dispWrongType(playerIn);
                    }
                } else if (affinities.hasAffinity(spellAffType)) {
                    dispOutOfMana(playerIn, spellAffType.getMeta(), affinities.getAffinityMana(spellAffType), manaCost);
                    break;
                } else {
                    dispNoAffinity(playerIn, spellAffType.getMeta());
                    break;
                }
            }
            if(i>0){
                return EnumActionResult.PASS;
            }
        } else if (successfulCast){
            return EnumActionResult.PASS;
        }
        return EnumActionResult.FAIL;
    }

    protected void dispWrongType(EntityPlayer playerIn) {
        successfulCast = false;
        playerIn.sendStatusMessage(new TextComponentTranslation("This spell only works on stone-type blocks!", new Object[0]), true);
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
        if (player.world.isRemote) {
            if (ticksAfterCast > 1) {
                ticksAfterCast--;
            } else if (ticksAfterCast == 1) {
                for (BlockPos affectedBlock : BlockPos.getAllInBox(castingPos.add(-1, -1, -1), castingPos.add(1, 1, 1))) {
                    player.world.updateBlockTick(affectedBlock, player.world.getBlockState(affectedBlock).getBlock(), 1, 1);
                }
                ticksAfterCast = 0;
            }
        }
        super.onUsingTick(stack, player, count);
    }
}
