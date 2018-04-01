package com.sandvoxel.immersivemagic.common.spells.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SpellEntityBase extends EntityThrowable {
    public SpellEntityBase(World worldIn) {
        super(worldIn);
    }

    public SpellEntityBase(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public SpellEntityBase(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {

    }
}
