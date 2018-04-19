package com.sandvoxel.elementalaffinities.common.blocks;

import com.sandvoxel.elementalaffinities.ElementalAffinities;
import com.sandvoxel.elementalaffinities.common.blocks.LIb.BlockBase;
import net.minecraft.block.material.Material;

public class SpellCrafter extends BlockBase {
    public SpellCrafter() {
        super(Material.ROCK, "");
        this.setCreativeTab(ElementalAffinities.tabimmmag);
    }
}
