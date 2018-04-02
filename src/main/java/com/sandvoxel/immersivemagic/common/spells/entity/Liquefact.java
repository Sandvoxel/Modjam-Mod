package com.sandvoxel.immersivemagic.common.spells.entity;

import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.common.blocks.Blocks;
import com.sandvoxel.immersivemagic.common.blocks.LiquefactedBlock;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;

/**
 * Created by CrazyGrape on 3/31/2018.
 */
public class Liquefact extends SpellEntityBase {

    public Liquefact(World worldIn) {
        super(worldIn);
        noClip = false;


    }

    public Liquefact(World world, EntityLivingBase entityLivingBase){
        super(world,entityLivingBase);
        noClip = false;
        setGlowing(false);
    }


    @Override
    public void onEntityUpdate() {
        if (this.world.isRemote)
        {
           this.world.spawnParticle(EnumParticleTypes.CRIT,
                    this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width,
                    this.posY + this.rand.nextDouble() * (double)this.height - 0.25D,
                    this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width,
                    (this.rand.nextDouble() - 0.5D) * -motionX,
                    (this.rand.nextDouble() - 0.5D) * -motionY,
                    (this.rand.nextDouble() - 0.5D) * -motionZ);
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {

        if (!world.isRemote && result.getBlockPos()!=null){
            BlockPos pos = result.getBlockPos();
            BlockPos affectedBlock = pos;

            for(int x = pos.getX() - 1; x <= pos.getX() + 1; x++ ){
                for (int y = pos.getY() - 1; y <= pos.getY() + 1; y++){
                    for (int z = pos.getZ() - 1; z <= pos.getZ() + 1; z++) {
                        affectedBlock = new BlockPos(x, y, z);
                        for (LiquefactedBlock.LiqBlockTypes type : LiquefactedBlock.LiqBlockTypes.values()){
                            Block blockInQuestion = world.getBlockState(affectedBlock).getBlock();
                            if (blockInQuestion == type.getDrop() || blockInQuestion == net.minecraft.init.Blocks.GRASS){
                                world.setBlockState(affectedBlock, Blocks.LIQ_BLOCK.getBlocks().getDefaultState().withProperty(LiquefactedBlock.getProperty(), LiquefactedBlock.LiqBlockTypes.byMeta(type.getMeta())));
                            }
                        }
                    }
                }

            }
        } else {
            for (int i = 0; i < 16; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.CRIT,
                        this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width,
                        this.posY + this.rand.nextDouble() * (double)this.height - 0.25D,
                        this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width,
                        (this.rand.nextDouble() - 0.5D) * 2.5D,
                        (this.rand.nextDouble() * 2.0D),
                        (this.rand.nextDouble() - 0.5D) * 2.5D);
            }
        }
        if(result.entityHit==null)
            this.isDead = true;
    }

    public static void addSpellToRegistry(String spellName){
        ResourceLocation resourceLocation = new ResourceLocation(Reference.MOD_ID+":spell_" + spellName);
        EntityRegistry.registerModEntity(resourceLocation,SpellLight.class,"spell",4864152, Reference.MOD_ID,64,10,true);
    }

}