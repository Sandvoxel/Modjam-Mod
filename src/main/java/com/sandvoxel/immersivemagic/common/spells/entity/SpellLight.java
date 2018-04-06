package com.sandvoxel.immersivemagic.common.spells.entity;

import com.sandvoxel.immersivemagic.common.blocks.Blocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;


public class SpellLight extends SpellEntityBase {

    private EnumParticleTypes spellLightParticle = EnumParticleTypes.FIREWORKS_SPARK;

    //Necessary
    public SpellLight(World worldIn) {
        super(worldIn);
        spellParticleType = spellLightParticle;
        impactPartVel = 1.5D;
    }

    public SpellLight(World world, EntityLivingBase entityLivingBase){
        super(world, entityLivingBase, false, EnumParticleTypes.FIREWORKS_SPARK);
    }


    @Override
    protected void onImpact(RayTraceResult result) {
        BlockPos pos = result.getBlockPos();
        if (!world.isRemote && result.getBlockPos()!=null && world.getBlockState(pos.add(result.sideHit.getDirectionVec())).getBlock() == net.minecraft.init.Blocks.AIR){
            world.setBlockState(result.getBlockPos().add(result.sideHit.getDirectionVec()),Blocks.SPELL_LIGHT.getBlocks().getDefaultState());
        }
        impactDeathHandling(result, 24, spellLightParticle);
    }
}
