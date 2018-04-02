package com.sandvoxel.immersivemagic.common.spells.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SpellEntityBase extends EntityThrowable {

    protected EnumParticleTypes spellParticle = EnumParticleTypes.SPELL;
    protected Double impactPartVel = 1.0D;
    protected Double projPartVel = 1.0D;
    protected Double fizzlePartVel = 1.0D;
    protected boolean hasGravity = false;


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
