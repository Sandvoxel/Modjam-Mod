package com.sandvoxel.immersivemagic.common.blocks;

import com.sandvoxel.immersivemagic.common.blocks.LIb.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class SpellLightBlock extends BlockBase {
    public SpellLightBlock() {
        super(Material.GLASS, "");
        setInternalName("spelllight");
        lightValue = 16;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        return 15;
    }
}
