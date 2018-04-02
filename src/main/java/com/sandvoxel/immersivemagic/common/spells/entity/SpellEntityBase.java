package com.sandvoxel.immersivemagic.common.spells.entity;

import com.sandvoxel.immersivemagic.common.blocks.Blocks;
import com.sandvoxel.immersivemagic.common.blocks.LiquefactedBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SpellEntityBase extends EntityThrowable {

    protected EnumParticleTypes spellParticleType = EnumParticleTypes.SPELL;

    protected Double projPartVel = 1.0D;
    protected Double impactPartVel = 2.5D;
    protected Double fizzlePartVel = 2.0D;
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

    public SpellEntityBase(World worldIn, EntityLivingBase throwerIn, boolean hasGravity, EnumParticleTypes spellParticle) {
        super(worldIn, throwerIn);
        this.hasGravity = hasGravity;
        this.spellParticleType = spellParticle;
    }

    public SpellEntityBase(World worldIn, EntityLivingBase throwerIn, boolean hasGravity, EnumParticleTypes spellParticle, double impactPartVel, double projectilePartVel, double fizzlePartVel) {
        super(worldIn, throwerIn);
        this.hasGravity = hasGravity;
        this.spellParticleType = spellParticle;
        this.impactPartVel = impactPartVel;
        this.projPartVel = projectilePartVel;
        this.fizzlePartVel = fizzlePartVel;
    }

    @Override
    public void onEntityUpdate() {
        if (this.world.isRemote)
        {
            this.world.spawnParticle(spellParticleType,
                    this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width,
                    this.posY + this.rand.nextDouble() * (double)this.height - 0.25D,
                    this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width,
                    (this.rand.nextDouble() - 0.5D) * -motionX * projPartVel,
                    (this.rand.nextDouble() - 0.5D) * -motionY * projPartVel,
                    (this.rand.nextDouble() - 0.5D) * -motionZ * projPartVel);
        }
        if (!hasGravity) {
            if(Math.abs(motionX) < 0.05 && Math.abs(motionY) < 0.05 && Math.abs(motionZ) < 0.05){
                isDead = true;
                if (this.world.isRemote)
                {
                    for (int i = 0; i < 400; ++i)
                    {
                        this.world.spawnParticle(spellParticleType,
                                this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width,
                                this.posY + this.rand.nextDouble() * (double)this.height - 0.25D,
                                this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width,
                                (this.rand.nextDouble() - 0.5D) * fizzlePartVel,
                                (this.rand.nextDouble() - 0.5D) * fizzlePartVel,
                                (this.rand.nextDouble() - 0.5D) * fizzlePartVel);
                    }
                }
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        impactDeathHandling(result, 4);
    }

    protected void impactDeathHandling(RayTraceResult result, int numberOfParticles) {
        if(!world.isRemote) {
            for (int i = 0; i < numberOfParticles; ++i) {
                this.world.spawnParticle(spellParticleType,
                        this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width,
                        this.posY + this.rand.nextDouble() * (double) this.height - 0.25D,
                        this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width,
                        (this.rand.nextDouble() - 0.5D) * impactPartVel,
                        (this.rand.nextDouble() - 0.5D) * impactPartVel,
                        (this.rand.nextDouble() - 0.5D) * impactPartVel);
            }
        }
        if(result.entityHit==null)
            this.isDead = true;
    }

    protected void impactDeathHandling(RayTraceResult result, int numberOfParticles, EnumParticleTypes particleType) {
        if(!world.isRemote) {
            for (int i = 0; i < numberOfParticles; ++i) {
                this.world.spawnParticle(particleType,
                        this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width,
                        this.posY + this.rand.nextDouble() * (double) this.height - 0.25D,
                        this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width,
                        (this.rand.nextDouble() - 0.5D) * impactPartVel,
                        (this.rand.nextDouble() * 2.0D),
                        (this.rand.nextDouble() - 0.5D) * impactPartVel);
            }
        }
        if(result.entityHit==null)
            this.isDead = true;
    }

//----- Getters and Setters -----

    public void setSpellParticleType(EnumParticleTypes particleType){
        spellParticleType = particleType;
    }

    public EnumParticleTypes getSpellParticleType() {
        return spellParticleType;
    }

    /***Multiplier on particle velocity on spell impact*/
    public void setImpactPartVel(double velocity) {
        impactPartVel = velocity;
    }

    public double getImpactPartVel() {
        return impactPartVel;
    }

    /***Multiplier on projectile particle velocity*/
    public void setProjectilePartVel(double velocity) {
        projPartVel = velocity;
    }

    public double getProjectilePartVel() {
        return projPartVel;
    }

    /***Multiplier on particle velocity when the spell fizzles out in the air*/
    public void setFizzlePartVel(double velocity) {
        fizzlePartVel = velocity;
    }

    public double getFizzlePartVel() {
        return fizzlePartVel;
    }
}
