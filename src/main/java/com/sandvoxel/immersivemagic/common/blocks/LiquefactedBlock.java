package com.sandvoxel.immersivemagic.common.blocks;

import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.common.blocks.LIb.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Created by CrazyGrape on 4/1/2018.
 */
public class LiquefactedBlock extends BlockBase{

    public LiquefactedBlock() {
        super(Material.GROUND, "");
        setInternalName("liquefacted_block");

    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);
    }

    public enum LiqBlockTypes{
        DIRT, DIRT_COARSE, SAND, SAND_RED, GRAVEL, CLAY, SOULSAND;

        private static final LiqBlockTypes[] ID_LOOKUP = new LiqBlockTypes[values().length];
        private int ID;
        private String Name;

        static {
            for (LiqBlockTypes tier : values()) {
                ID_LOOKUP[tier.ID] = tier;
            }
        }

       
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockItemRenderer() {
        final String resourcePath = String.format("%s:%s-", Reference.MOD_ID, this.getInternalName());
        final String badPath = String.format("%s:badblock", Reference.MOD_ID);

        for (MachineTier machineTier : MachineTier.values()) {
            if (!Arrays.asList(machineTiers).contains(machineTier)) {
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), machineTier.getMeta(), new ModelResourceLocation(badPath, "inventory"));
            } else {
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), machineTier.getMeta(), new ModelResourceLocation(resourcePath + machineTier.getName(), "inventory"));
            }
        }
    }

}
