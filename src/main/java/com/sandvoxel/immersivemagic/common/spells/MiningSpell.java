package com.sandvoxel.immersivemagic.common.spells;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.blocks.Blocks;
import com.sandvoxel.immersivemagic.common.blocks.LiquefactedBlock;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import com.sandvoxel.immersivemagic.common.spells.lib.SpellBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class MiningSpell extends SpellBase {
    public MiningSpell() {
        super("spell_mining", "spell_earth", SpellTypes.DIRECT_SPELL, null);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IAffinities affinities = playerIn.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null);

        int i = 0;

        for (BlockPos affectedBlock : BlockPos.getAllInBox(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {

            Block blockInQuestion = worldIn.getBlockState(affectedBlock).getBlock();
            if(affinities.canCast(blockInQuestion.getHarvestLevel(worldIn.getBlockState(affectedBlock))*100,AffinityTypes.EARTH) && blockInQuestion.getHarvestLevel(worldIn.getBlockState(affectedBlock) )!= -1.0f && blockInQuestion.getMaterial(worldIn.getBlockState(affectedBlock))==Material.ROCK){
                affinities.addXp(blockInQuestion.getHarvestLevel(worldIn.getBlockState(affectedBlock)),AffinityTypes.EARTH);
                worldIn.destroyBlock(affectedBlock,true);
                i++;
            }
        }
        if(i>0){
         return EnumActionResult.PASS;
        }
        if (affinities.hasAffinity(AffinityTypes.FIRE)) {
            dispOutOfMana(playerIn);
        } else {
            dispNoAffinity(playerIn);
        }
        return EnumActionResult.FAIL;
    }
}
