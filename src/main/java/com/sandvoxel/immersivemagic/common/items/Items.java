package com.sandvoxel.immersivemagic.common.items;

import com.sandvoxel.immersivemagic.Reference;
import com.sandvoxel.immersivemagic.common.items.lib.ItemBase;
import com.sandvoxel.immersivemagic.common.items.spellcraft.SpellItems;
import com.sandvoxel.immersivemagic.common.util.RegistryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum Items {
    SPELL_CRAFTING_ITEMS(SpellItems.class),
    COSMIC_ITEM(cosmicItem.class),
    CRYSTAL_BALL(crystalBall.class);

    protected Class<? extends ItemBase> itemClass;
    protected Item item;

    Items(Class<? extends ItemBase> itemClass) {
        this.itemClass = itemClass;
    }


    public static void registerItems() {
        for (Items item : Items.values()) {
            item.registerItem();
        }
    }

    public Item getItem() {
        return this.item;
    }

    public ItemStack getStack() {
        return new ItemStack(item);
    }

    public ItemStack getStack(int size) {
        return new ItemStack(item, size);
    }

    public ItemStack getStack(int size, int damage) {
        return new ItemStack(item, size, damage);
    }

    private void registerItem() {
        item = RegistryHelper.addItemsToRegistry(Reference.MOD_ID, itemClass);
    }
}
