package com.sandvoxel.immersivemagic.common.blocks;

import com.sandvoxel.immersivemagic.Refrence;
import com.sandvoxel.immersivemagic.common.blocks.LIb.BlockBase;
import com.sandvoxel.immersivemagic.common.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;


public enum Blocks {
    TEMP_BLOCK(TempBlock.class),
    SPELL_LIGHT(SpellLightBlock.class);

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

    public ItemBlock getItemBlock(){
        return new ItemBlock(this.block);
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
