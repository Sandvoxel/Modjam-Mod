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
        super(world,entityLivingBase, true, EnumParticleTypes.CRIT);
        noClip = false;
        setGlowing(false);
    }


    @Override
    public void onEntityUpdate() {

    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!world.isRemote && result.getBlockPos()!=null) {
            BlockPos pos = result.getBlockPos();
            for (BlockPos affectedBlock : BlockPos.getAllInBox(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
                for (LiquefactedBlock.LiqBlockTypes type : LiquefactedBlock.LiqBlockTypes.values()) {
                    Block blockInQuestion = world.getBlockState(affectedBlock).getBlock();
                    if (blockInQuestion == type.getDrop() || blockInQuestion == net.minecraft.init.Blocks.GRASS) {
                        world.setBlockState(affectedBlock, Blocks.LIQ_BLOCK.getBlocks().getDefaultState().withProperty(LiquefactedBlock.getProperty(), LiquefactedBlock.LiqBlockTypes.byMeta(type.getMeta())));
                    }
                }
            }
        }
        impactDeathHandling(result, 16);
    }
}