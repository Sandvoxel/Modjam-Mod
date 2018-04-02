package com.sandvoxel.immersivemagic.common.spells.entity;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.common.blocks.Blocks;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Collection;

/**
 * Created by CrazyGrape on 4/1/2018.
 */
public class SpellNova extends SpellEntityBase {

    protected int explosionRadius = 3;
    protected int age = 0;
    private EnumParticleTypes spellNovaParticle = EnumParticleTypes.FLAME;

    //Necessary
    public SpellNova(World worldIn) {
        super(worldIn);
        spellParticleType = spellNovaParticle;
        impactPartVel = 4.0D;
    }

    public SpellNova(World world, EntityLivingBase entityLivingBase){
        super(world, entityLivingBase, false, EnumParticleTypes.LAVA);
        setGlowing(true);
        deathParticleNum = 400;
    }

    @Override
    public void onEntityUpdate() {
        spawnParticleTrail(spellNovaParticle);
        age++;
        if(Math.abs(motionX) < 0.05 && Math.abs(motionY) < 0.05 && Math.abs(motionZ) < 0.05){
            isDead = true;
            if (this.world.isRemote)
            {
                for (int i = 0; i < deathParticleNum; ++i)
                {
                    this.world.spawnParticle(EnumParticleTypes.LAVA,
                            this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width,
                            this.posY + this.rand.nextDouble() * (double)this.height - 0.25D,
                            this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width,
                            (this.rand.nextDouble() - 0.5D) * fizzlePartVel,
                            (this.rand.nextDouble() - 0.5D) * fizzlePartVel,
                            (this.rand.nextDouble() - 0.5D) * fizzlePartVel);
                }
            } else {
                this.explode();
            }
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        if(age >= 50) {
            isDead = true;
            if (this.world.isRemote) {
                for (int i = 0; i < deathParticleNum; ++i) {
                    this.world.spawnParticle(EnumParticleTypes.LAVA,
                            this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width,
                            this.posY + this.rand.nextDouble() * (double) this.height - 0.25D,
                            this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width,
                            (this.rand.nextDouble() - 0.5D) * fizzlePartVel,
                            (this.rand.nextDouble() - 0.5D) * fizzlePartVel,
                            (this.rand.nextDouble() - 0.5D) * fizzlePartVel);
                }
            } else {
                age = 0;
                this.explode();
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!world.isRemote && result.getBlockPos()!=null){
            this.explode();
        }
        impactDeathHandling(result, 32, EnumParticleTypes.LAVA);
    }

    private void explode()
    {
        if (!this.world.isRemote)
        {
            this.world.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius, true);
            this.setDead();
        }
    }
}
