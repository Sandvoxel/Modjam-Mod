package com.sandvoxel.immersivemagic.common.blocks;

import com.sandvoxel.immersivemagic.common.blocks.LIb.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class TempBlock extends BlockBase {
    public TempBlock() {
        super(Material.WOOD, "");
        setInternalName("tempblock");
        setCreativeTab(CreativeTabs.TRANSPORTATION);

    }
}
