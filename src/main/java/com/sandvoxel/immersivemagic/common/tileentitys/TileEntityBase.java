package com.sandvoxel.tempname.common.tileentitys;

import com.sandvoxel.tempname.api.util.IOrientable;
import com.sandvoxel.tempname.common.util.TileHelper;
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
}
