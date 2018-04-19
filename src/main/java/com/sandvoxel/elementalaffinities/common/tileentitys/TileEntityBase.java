package com.sandvoxel.elementalaffinities.common.tileentitys;

import com.sandvoxel.elementalaffinities.api.util.IOrientable;
import com.sandvoxel.elementalaffinities.common.util.TileHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityBase extends TileEntity implements IOrientable {

        private EnumFacing forward = EnumFacing.NORTH;

        public void dropItems() {
            TileHelper.DropItems(this);
        }

        @Override
        public boolean canBeRotated() {
            return false;
        }

        @Override
        public EnumFacing getForward() {
            return forward;
        }

        @Override
        public void setOrientation(EnumFacing forward) {
            this.forward = forward;
        }

    /**
     * Returns a Server side Container to be displayed to the user.
     *
     * @param guiId  the GUI ID mumber
     * @param player the player currently interacting with your block/tile entity
     * @return A GuiScreen/Container to be displayed to the user, null if none.
     */
    public Object getServerGuiElement(int guiId, EntityPlayer player) {
        return null;
    }

    /**
     * Returns a Container to be displayed to the user. On the client side, this
     * needs to return a instance of GuiScreen On the server side, this needs to
     * return a instance of Container
     *
     * @param guiId  the GUI ID mumber
     * @param player the player currently interacting with your block/tile entity
     * @return A GuiScreen/Container to be displayed to the user, null if none.
     */
    public Object getClientGuiElement(int guiId, EntityPlayer player) {
        return null;
    }
}
