package com.sandvoxel.tempname.common.blocks.LIb;

import com.sandvoxel.tempname.Refrence;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockBase extends Block {

    private String resourcePath;
    private String internalName = "";
    protected boolean isInventory = false;


    public BlockBase(Material blockMaterialIn, String resourcePath) {
        super(blockMaterialIn);
        this.resourcePath = resourcePath;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    @Override
    public String getUnlocalizedName() {
        String blockName = getUnwrappedUnlocalizedName(super.getUnlocalizedName());

        return String.format("tile.%s.%s", Refrence.MOD_ID, blockName);
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

}
