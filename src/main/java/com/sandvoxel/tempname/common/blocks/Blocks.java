package com.sandvoxel.tempname.common.blocks;

import com.sandvoxel.tempname.Refrence;
import com.sandvoxel.tempname.TempName;
import com.sandvoxel.tempname.common.blocks.LIb.BlockBase;
import com.sandvoxel.tempname.common.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;


public enum Blocks {
    TEMP_BLOCK(TempBlock.class);

    private final Class<? extends BlockBase> blockClass;
    private Block block;

    Blocks(Class<? extends BlockBase> blockClass) {
        this.blockClass = blockClass;
    }

    public static void registerBlocks() {
        for (Blocks blocks : Blocks.values()) {
            blocks.registerBlock();
        }
    }

    public ItemStack getStack() {
        return new ItemStack(block);
    }


    public ItemStack getStack(int size, int meta) {
        return new ItemStack(block, size, meta);
    }

    public Block getBlocks() {
        return this.block;
    }

    private void registerBlock() {
        block = RegistryHelper.addBlockToRegistry(Refrence.MOD_ID, blockClass);
    }

    public String getName() {
        return blockClass.getSimpleName();
    }
}
