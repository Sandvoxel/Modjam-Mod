package com.sandvoxel.immersivemagic.common.spells;

import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import com.sandvoxel.immersivemagic.common.spells.lib.SpellBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class MiningSpell extends SpellBase {
    protected AffinityTypes spellAffType = AffinityTypes.EARTH;
    protected int baseManaCost = 100;
    private BlockPos castingPos;

    public MiningSpell() {
        super("spell_mining", "spell_earth", SpellTypes.DIRECT_SPELL, null, AffinityTypes.EARTH, 100);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        castingPos = pos;

        int i = 0;
        boolean foundBlock = false;
        int manaCost = 0;

        for (BlockPos affectedBlock : BlockPos.getAllInBox(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
            Block blockInQuestion = worldIn.getBlockState(affectedBlock).getBlock();
            manaCost = (blockInQuestion.getHarvestLevel(worldIn.getBlockState(affectedBlock)) + 1) * baseManaCost;
            if (playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null).canCast(manaCost, spellAffType)) {
                if(blockInQuestion.getHarvestLevel(worldIn.getBlockState(affectedBlock)) != -1.0f && blockInQuestion.getMaterial(worldIn.getBlockState(affectedBlock)) == Material.ROCK) {
                    foundBlock = true;
                    playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null).addXp(blockInQuestion.getHarvestLevel(worldIn.getBlockState(affectedBlock)), spellAffType);
                    worldIn.destroyBlock(affectedBlock, true);
                    worldIn.notifyNeighborsOfStateChange(affectedBlock, net.minecraft.init.Blocks.AIR, true);
                    i++;
                }
            }
        }

        if(i>0){
            return EnumActionResult.PASS;
        }

        if (playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null).hasAffinity(spellAffType)) {
            if (foundBlock) {
                dispOutOfMana(playerIn, spellAffType.getMeta(), playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null).getAffinityMana(spellAffType), manaCost);
                return EnumActionResult.FAIL;
            } else {
                dispWrongType(playerIn);
            }
        } else {
            dispNoAffinity(playerIn, spellAffType.getMeta());
            return EnumActionResult.FAIL;
        }
        //This statement is here so intelliJ doesn't get mad at me, saying that it's missing a return statement
        return EnumActionResult.FAIL;
    }

    protected void dispWrongType(EntityPlayer playerIn) {
        playerIn.sendStatusMessage(new TextComponentTranslation("This spell only works on stone-type blocks!", new Object[0]), true);
    }

}
