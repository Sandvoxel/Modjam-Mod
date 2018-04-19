package com.sandvoxel.elementalaffinities.api.inventory;

import com.sandvoxel.elementalaffinities.common.inventory.InventoryOperation;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IInventoryHandler {
    void saveChanges();

    void onChangeInventory(IInventory inv, int slot, InventoryOperation inventoryoperation, ItemStack removedStack, ItemStack newStack);
}
