package com.sandvoxel.immersivemagic.common.blocks;

import com.google.common.collect.Maps;
import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.common.blocks.LIb.BlockBase;
import jline.internal.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

/**
 * Created by CrazyGrape on 4/1/2018.
 */
public class LiquefactedBlock extends BlockBase{

    protected static final PropertyEnum<LiqBlockTypes> LIQ_BLOCK_TYPE = PropertyEnum.create("liq_block_type", LiqBlockTypes.class);

    public LiquefactedBlock() {
        super(Material.GROUND, "");
        setInternalName("liquefacted_block");
        setTickRandomly(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        super.getSubBlocks(itemIn, items);
        for(LiqBlockTypes liqType : LiqBlockTypes.values()) {
            items.add(new ItemStack(this, liqType.getMeta()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(LIQ_BLOCK_TYPE, LiqBlockTypes.byMeta(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        LiqBlockTypes type = state.getValue(LIQ_BLOCK_TYPE);
        return type.getMeta();
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        worldIn.setBlockState(pos, LiqBlockTypes.byMeta(this.getMetaFromState(state)).getDrop().getDefaultState());
        super.updateTick(worldIn, pos, state, rand);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LIQ_BLOCK_TYPE);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(LIQ_BLOCK_TYPE, LiqBlockTypes.byMeta(this.getMetaFromState(state)));
    }

    public enum LiqBlockTypes implements IStringSerializable {
        DIRT(Blocks.DIRT, "dirt"),
        DIRT_COARSE(Blocks.DIRT, "dirt_c"),
        SAND(Blocks.SAND, "sand"),
        RED_SAND(Blocks.SAND, "sand_r"),
        GRAVEL(Blocks.GRAVEL, "gravel"),
        CLAY(Blocks.CLAY, "clay"),
        SOULSAND(Blocks.SOUL_SAND, "soulsand");

        private static final LiqBlockTypes[] META_LOOKUP = new LiqBlockTypes[values().length];
        private int ID;
        private String Name;
        private Block drop;

        static {
            for (LiqBlockTypes type : values()) {
                META_LOOKUP[type.ordinal()] = type;
            }
        }

        LiqBlockTypes(Block drops, String name) {
            this.drop = drops;
            Name = name;
        }

        public String getName() {
            return Name;
        }

        public Block getDrop(){
            return drop;
        }

        public static int getBlockID(LiqBlockTypes blockTypes){
            return blockTypes.ID;
        }

        public int getMeta() {
            return this.ordinal();
        }


        public static LiqBlockTypes byMeta(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length ) {
                meta = 0;
            }
            return META_LOOKUP[meta];
        }

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockRenderer() {
        final String resourcePath = String.format("%s:%s-", Reference.MOD_ID, this.resourcePath);
        final String badPath = String.format("%s:badblock", Reference.MOD_ID);

        ModelLoader.setCustomStateMapper(this, new DefaultStateMapper() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                Map<IProperty<?>, Comparable<?>> blockStates = Maps.newLinkedHashMap(state.getProperties());

                if (!Arrays.asList(LiqBlockTypes.values()).contains(blockStates.get(LIQ_BLOCK_TYPE)))
                    return new ModelResourceLocation(badPath, "");

                if (blockStates.containsKey(LIQ_BLOCK_TYPE))
                    blockStates.remove(LIQ_BLOCK_TYPE);

                return new ModelResourceLocation(resourcePath + state.getValue(LIQ_BLOCK_TYPE).getName(), getPropertyString(blockStates));
            }
        });
    }

}
