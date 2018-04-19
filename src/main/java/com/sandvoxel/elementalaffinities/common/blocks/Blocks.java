package com.sandvoxel.elementalaffinities.common.blocks;

import com.sandvoxel.elementalaffinities.Reference;
import com.sandvoxel.elementalaffinities.common.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;


public enum Blocks {
    TEMP_BLOCK(AffinitySelector.class),
    SPELL_LIGHT(SpellLightBlock.class),
    LIQ_BLOCK(LiquefactedBlock.class);

    private final Class<? extends Block> blockClass;
    private Block block;

    Blocks(Class<? extends Block> blockClass) {
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
        block = RegistryHelper.addBlockToRegistry(Reference.MOD_ID, blockClass);
    }

    public String getName() {
        return blockClass.getSimpleName();
    }
}
