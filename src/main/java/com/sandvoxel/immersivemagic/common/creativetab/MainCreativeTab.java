package com.sandvoxel.immersivemagic.common.creativetab;

import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.common.blocks.Blocks;
import com.sandvoxel.immersivemagic.common.items.Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class MainCreativeTab extends CreativeTabs {
    public MainCreativeTab() {
        super(Reference.MOD_ID+"-tab");
    }

    @Override
    public ItemStack getTabIconItem() {
        return Blocks.SPELL_LIGHT.getStack();
    }
}
