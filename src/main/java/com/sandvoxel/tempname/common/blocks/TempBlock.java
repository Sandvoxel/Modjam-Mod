package com.sandvoxel.tempname.common.blocks;

import com.sandvoxel.tempname.Refrence;
import com.sandvoxel.tempname.common.blocks.LIb.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class TempBlock extends BlockBase {
    public TempBlock() {
        super(Material.WOOD, "");
        setInternalName("tempblock");
        setCreativeTab(CreativeTabs.TRANSPORTATION);

    }
}
