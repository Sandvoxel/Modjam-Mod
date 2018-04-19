package com.sandvoxel.elementalaffinities.common.gui;

import com.sandvoxel.elementalaffinities.common.tileentitys.TileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by CrazyGrape on 4/2/2018.
 */
public class GuiHandler implements IGuiHandler {

    public GuiHandler(Object modInstance) {
        NetworkRegistry.INSTANCE.registerGuiHandler(modInstance, this);
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        return (tileEntity instanceof TileEntityBase) ? ((TileEntityBase) tileEntity).getServerGuiElement(id, player) : null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));


        return (tileEntity instanceof TileEntityBase) ? ((TileEntityBase) tileEntity).getClientGuiElement(id, player) : null;
    }
}