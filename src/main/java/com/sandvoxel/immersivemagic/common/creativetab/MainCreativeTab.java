package com.sandvoxel.immersivemagic.common.creativetab;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.common.blocks.Blocks;
import com.sandvoxel.immersivemagic.common.items.Items;
import com.sandvoxel.immersivemagic.common.items.lib.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MainCreativeTab extends CreativeTabs {
    public MainCreativeTab() {
        super(Reference.MOD_ID+"-tab");
    }
    protected ItemStack tabIcon = Items.COSMIC_ITEM.getStack(1);
    @Override
    public ItemStack getTabIconItem() {
        return tabIcon;
    }
}
