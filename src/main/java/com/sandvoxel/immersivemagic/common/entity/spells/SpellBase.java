package com.sandvoxel.immersivemagic.common.entity.spells;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.Refrence;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.blocks.Blocks;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityObject;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class SpellBase extends EntityThrowable {
    public SpellBase(World worldIn) {
        super(worldIn);
        noClip = false;


    }

    public SpellBase(World world, EntityLivingBase entityLivingBase){
        super(world,entityLivingBase);
        noClip = false;
        setGlowing(false);
        setNoGravity(true);
    }


    @Override
    public void onEntityUpdate() {
        if (this.world.isRemote)
        {
            for (int i = 0; i < 2; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {

        if (!world.isRemote && result.getBlockPos()!=null){
            world.setBlockState(result.getBlockPos().add(result.sideHit.getDirectionVec()),Blocks.SPELL_LIGHT.getBlocks().getDefaultState());
        }
        if(result.entityHit==null)
        this.isDead = true;
    }

    public static void addSpellToRegistry(String spellName){
        ResourceLocation resourceLocation = new ResourceLocation(Refrence.MOD_ID+":spell_" + spellName);
        EntityRegistry.registerModEntity(resourceLocation,SpellBase.class,"spell",32184985,Refrence.MOD_ID,64,10,true);
    }

}
