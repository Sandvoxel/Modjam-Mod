package com.sandvoxel.immersivemagic.common.tileentitys;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.client.gui.TestScreen;
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
