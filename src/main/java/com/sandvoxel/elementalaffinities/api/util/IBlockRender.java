package com.sandvoxel.elementalaffinities.api.util;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IBlockRender {
    @SideOnly(Side.CLIENT)
    void registerBlockRenderer();

}
