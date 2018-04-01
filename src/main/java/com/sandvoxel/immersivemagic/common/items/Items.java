package com.sandvoxel.immersivemagic.common.items;

import com.sandvoxel.immersivemagic.Refrence;
import com.sandvoxel.immersivemagic.common.items.lib.ItemBase;
import com.sandvoxel.immersivemagic.common.items.spellcraft.SpellItems;
import com.sandvoxel.immersivemagic.common.util.RegistryHelper;
import net.minecraft.item.Item;

public enum Items {
    SPELL_CRAFTINE_ITEMS(SpellItems.class);

    private Class<? extends ItemBase> itemClass;
    private Item item;

    Items(Class<? extends ItemBase> itemClass) {
        this.itemClass = itemClass;
    }
    public static void registerItems() {
        for (Items item : Items.values()) {
            item.registerItem();
        }
    }
    private void registerItem() {
        item = RegistryHelper.addItemsToRegistry(Refrence.MOD_ID, itemClass);
    }
}
