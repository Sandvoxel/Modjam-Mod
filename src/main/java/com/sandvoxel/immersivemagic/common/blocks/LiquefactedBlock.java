package com.sandvoxel.immersivemagic.common.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.common.blocks.LIb.BlockBase;
import jline.internal.Nullable;
import net.minecraft.block.*;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

/**
 * Created by CrazyGrape on 4/1/2018.
 */
public class LiquefactedBlock extends BlockBase {

    protected static final PropertyEnum<LiqBlockTypes> LIQ_BLOCK_TYPE = PropertyEnum.create("liq_block_type", LiqBlockTypes.class);

    public LiquefactedBlock() {
        super(Material.GROUND, "block_liquefacted");
        setInternalName("block_liquefacted");
        setTickRandomly(true);
        setSoundType(SoundType.GROUND);
        setHardness(1.5f);
        //setCreativeTab(ImmersiveMagic.tabimmmag);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        if(itemIn != null)
            return;
        for(LiqBlockTypes liqType : LiqBlockTypes.values()) {
            items.add(new ItemStack(this, 1, liqType.getMeta()));
        }
    }

    public static PropertyEnum getProperty(){
        return LIQ_BLOCK_TYPE;
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
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.IGNORE;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.motionX *= 0.35f;
        if(entityIn.motionY <= 0) {
            entityIn.motionY *= 0.01f;
        } else {
            entityIn.motionY *= 0.9f;
        }
        entityIn.motionZ *= 0.35f;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        int blockToPlace = LiqBlockTypes.getBlockID(LiqBlockTypes.byMeta(this.getMetaFromState(state)));
        switch(blockToPlace) {
            case 1:
                worldIn.setBlockState(pos, Blocks.DIRT.getStateFromMeta(1));
            case 3:
                worldIn.setBlockState(pos, Blocks.SAND.getStateFromMeta(1));
            default:
                worldIn.setBlockState(pos, LiqBlockTypes.byMeta(this.getMetaFromState(state)).getDrop().getDefaultState());
        }
        super.updateTick(worldIn, pos, state, rand);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LIQ_BLOCK_TYPE);
    }

    @javax.annotation.Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isRemote){
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(LIQ_BLOCK_TYPE, LiqBlockTypes.byMeta(stack.getMetadata())));
        }
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(LIQ_BLOCK_TYPE, LiqBlockTypes.byMeta(this.getMetaFromState(state)));
    }

    public enum LiqBlockTypes implements IStringSerializable {
        DIRT(Blocks.DIRT, "dirt", 0),
        DIRT_COARSE(Blocks.DIRT, "dirt_c", 1),
        SAND(Blocks.SAND, "sand", 2),
        RED_SAND(Blocks.SAND, "sand_r", 3),
        GRAVEL(Blocks.GRAVEL, "gravel", 4),
        SOULSAND(Blocks.SOUL_SAND, "soulsand", 5);

        private static final LiqBlockTypes[] META_LOOKUP = new LiqBlockTypes[values().length];
        private int ID;
        private String Name;
        private Block drop;

        static {
            for (LiqBlockTypes type : values()) {
                META_LOOKUP[type.ID] = type;
            }
        }

        LiqBlockTypes(Block drops, String name, int ID) {
            this.drop = drops;
            this.ID = ID;
            Name = name;
        }

        public String getName() {
            return Name;
        }

        public Block getDrop(){
            return drop;
        }

        public int getIDFromDrop(Block drop){
            for (LiqBlockTypes type : values()) {
                META_LOOKUP[type.ID] = type;
                if (drop == type.getDrop()){
                    return type.ID;
                }
            }
            if (drop == Blocks.GRASS || drop == Blocks.MYCELIUM || drop == Blocks.GRASS_PATH){
                return 0;
            }
            return 0;
        }

        public static int getBlockID(LiqBlockTypes blockTypes){
            return blockTypes.ID;
        }

        public int getMeta() {
            return this.ID;
        }


        public static LiqBlockTypes byMeta(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length ) {
                meta = 0;
            }
            return META_LOOKUP[meta];
        }

    }
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockRenderer() {
        final String resourcePath = String.format("%s:%s", Reference.MOD_ID, this.resourcePath);
        final String badPath = String.format("%s:badblock", Reference.MOD_ID);

        ModelLoader.setCustomStateMapper(this, new DefaultStateMapper() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                Map<IProperty<?>, Comparable<?>> blockStates = Maps.newLinkedHashMap(state.getProperties());

                if (!Arrays.asList(LiqBlockTypes.values()).contains(blockStates.get(LIQ_BLOCK_TYPE)))
                    return new ModelResourceLocation(badPath, "");

                if (blockStates.containsKey(LIQ_BLOCK_TYPE))
                    blockStates.remove(LIQ_BLOCK_TYPE);

                return new ModelResourceLocation(resourcePath, getPropertyString(blockStates));
            }
        });
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        if (plantable instanceof BlockBush)
        {
            return true;
        }
        return false;
    }

}
