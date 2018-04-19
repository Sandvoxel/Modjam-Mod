package com.sandvoxel.elementalaffinities.common.tileentitys;

import com.sandvoxel.elementalaffinities.client.gui.TestScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TestTile extends TileEntityBase {


    @SideOnly(Side.CLIENT)
    @Override
    public Object getClientGuiElement(int guiId, EntityPlayer player) {
        if(guiId == 0){
            return new TestScreen(player);
        }
        return null;
    }

    @Override
    public Object getServerGuiElement(int guiId, EntityPlayer player) {
        return null;
    }
}
