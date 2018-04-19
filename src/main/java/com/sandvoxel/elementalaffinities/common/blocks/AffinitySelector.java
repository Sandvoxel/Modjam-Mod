package com.sandvoxel.elementalaffinities.common.blocks;

import com.google.common.collect.Maps;
import com.sandvoxel.elementalaffinities.ElementalAffinities;
import com.sandvoxel.elementalaffinities.Reference;
import com.sandvoxel.elementalaffinities.common.blocks.LIb.BlockTileBase;
import com.sandvoxel.elementalaffinities.common.tileentitys.TestTile;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Map;

public class AffinitySelector extends BlockTileBase {

    protected static final AxisAlignedBB CRAFTER_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D);

    public AffinitySelector() {
        super(Material.ROCK, "");
        setInternalName("affinity_selector");
        setCreativeTab(ElementalAffinities.tabimmmag);
        setTileEntity(TestTile.class);
        this.setSoundType(SoundType.STONE);
        this.setHardness(5.0f);

        setLightOpacity(0);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        playerIn.openGui(ElementalAffinities.instance,0,worldIn,pos.getX(),pos.getY(),pos.getZ());


        return true;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return CRAFTER_AABB;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CRAFTER_AABB;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
    }



    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockRenderer() {
        final String resourcePath = String.format("%s:%s", Reference.MOD_ID, this.resourcePath);
        ModelLoader.setCustomStateMapper(this, new DefaultStateMapper() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                Map<IProperty<?>, Comparable<?>> blockStates = Maps.newLinkedHashMap(state.getProperties());
                return new ModelResourceLocation(resourcePath + getInternalName(), getPropertyString(blockStates));
            }
        });
    }
}
