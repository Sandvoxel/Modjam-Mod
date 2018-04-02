package com.sandvoxel.immersivemagic.common.spells.entity;

import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.common.blocks.Blocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;


public class SpellLight extends SpellEntityBase {

    //Necessary
    public SpellLight(World worldIn) {
        super(worldIn);
    }

    public SpellLight(World world, EntityLivingBase entityLivingBase){
        super(world, entityLivingBase, false, EnumParticleTypes.FIREWORKS_SPARK);
    }


    @Override
    protected void onImpact(RayTraceResult result) {
        if (!world.isRemote && result.getBlockPos()!=null){
            world.setBlockState(result.getBlockPos().add(result.sideHit.getDirectionVec()),Blocks.SPELL_LIGHT.getBlocks().getDefaultState());
        }
        impactDeathHandling(result, 32);
    }
}
