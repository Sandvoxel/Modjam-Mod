package com.sandvoxel.elementalaffinities.common.blocks.LIb;

import com.sandvoxel.elementalaffinities.Reference;
import com.sandvoxel.elementalaffinities.api.blocks.IBlock;
import com.sandvoxel.elementalaffinities.api.util.IBlockRender;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by CrazyGrape on 4/1/2018.
 */
public class BlockFallingBase extends BlockFalling implements IBlockRender, IBlock {

    protected String resourcePath;
    private String internalName = "";
    protected boolean isInventory = false;


    public BlockFallingBase(Material blockMaterialIn, String resourcePath) {
        super(blockMaterialIn);
        this.resourcePath = resourcePath + internalName;
    }

    @Override
    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    @Override
    public String getUnlocalizedName() {
        String blockName = getUnwrappedUnlocalizedName(super.getUnlocalizedName());

        return String.format("tile.%s.%s", Reference.MOD_ID, blockName);
    }

    private String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        return willHarvest || super.removedByPlayer(state, world, pos, player, false);
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        worldIn.setBlockToAir(pos);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockRenderer() {
        final String resourcePath = String.format("%s:%s", Reference.MOD_ID, this.resourcePath);
        ModelLoader.setCustomStateMapper(this, new DefaultStateMapper() {
            @SideOnly(Side.CLIENT)
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(resourcePath, getPropertyString(state.getProperties()));
            }
        });
    }
}
