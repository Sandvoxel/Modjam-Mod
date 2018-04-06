package com.sandvoxel.immersivemagic.common.spells.entity;

import com.sandvoxel.immersivemagic.common.blocks.Blocks;
import com.sandvoxel.immersivemagic.common.blocks.LiquefactedBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Created by CrazyGrape on 3/31/2018.
 */
public class SpellLiquefact extends SpellEntityBase {

    //Necessary
    public SpellLiquefact(World worldIn) {
        super(worldIn);
        spellParticleType = EnumParticleTypes.CRIT;
        impactPartVel = 4.0D;
    }

    public SpellLiquefact(World world, EntityLivingBase entityLivingBase){
        super(world, entityLivingBase, true, EnumParticleTypes.CRIT);
        noClip = false;
        setGlowing(false);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getBlockPos()!=null) {
            if (!world.isRemote) {
                //Compare all blocks withing the XYZ radius (cubic) to determine whether or not blocks should be converted into their liquedfacted forms
                //Bug: ignores metadata
                BlockPos pos = result.getBlockPos();
                for (BlockPos affectedBlock : BlockPos.getAllInBox(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
                    for (LiquefactedBlock.LiqBlockTypes type : LiquefactedBlock.LiqBlockTypes.values()) {
                        Block blockInQuestion = world.getBlockState(affectedBlock).getBlock();
                        if (blockInQuestion == type.getDrop() || blockInQuestion == net.minecraft.init.Blocks.GRASS) {
                            world.setBlockState(affectedBlock, Blocks.LIQ_BLOCK.getBlocks().getDefaultState().withProperty(LiquefactedBlock.getProperty(), LiquefactedBlock.LiqBlockTypes.byMeta(type.getMeta())));
                        }
                    }
                }
            } else {
                BlockPos pos = result.getBlockPos();
                for (BlockPos affectedBlock : BlockPos.getAllInBox(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
                    world.getBlockState(affectedBlock);
                }
            }
        }
        impactDeathHandling(result, 16, spellParticleType);
    }
}