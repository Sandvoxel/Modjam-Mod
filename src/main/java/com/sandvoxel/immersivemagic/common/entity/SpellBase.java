package com.sandvoxel.immersivemagic.common.entity;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.Refrence;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.fixes.EntityId;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class SpellBase extends EntityThrowable {
    private String internalName = "test";
    public SpellBase(World worldIn) {
        super(worldIn);

    }

    public SpellBase(World world, EntityLivingBase entityLivingBase){
        super(world,entityLivingBase);
    }

    public String getInternalName() {
        return internalName;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        this.isDead = true;
        ImmersiveMagic.LOGGER.info(result.entityHit);
    }

    public static void addSpellToRegistry(){
        ResourceLocation resourceLocation = new ResourceLocation(Refrence.MOD_ID+":yesr");
        EntityRegistry.registerModEntity(resourceLocation,SpellBase.class,"spell",32184985,Refrence.MOD_ID,64,10,true);
    }

}
