package com.sandvoxel.immersivemagic.common.util;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.common.blocks.LIb.BlockBase;
import com.sandvoxel.immersivemagic.common.items.Items;
import com.sandvoxel.immersivemagic.common.items.lib.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class RegistryHelper {

    private static List<Block> blocks = new ArrayList<>();
    private static List<Item> items = new ArrayList<>();



    public static Block addBlockToRegistry(String modid, Class<? extends Block> blockClass) {
        Block block = null;
        ItemBlock itemBlock;
        String internalName;

        try {
            block = blockClass.getConstructor().newInstance();
            itemBlock = new ItemBlock(block);

            internalName = ((BlockBase) block).getInternalName();

            if (!internalName.equals(internalName.toLowerCase(Locale.US)))
                throw new IllegalArgumentException(String.format("InternalName values need to be all lowercase! Item: %s", internalName));

            if (internalName.isEmpty())
                throw new IllegalArgumentException(String.format("InternalName cannot be blank! Item: %s", blockClass.getCanonicalName()));


            block.setRegistryName(modid, internalName);
            block.setUnlocalizedName(internalName);
            itemBlock.setRegistryName(Objects.requireNonNull(block.getRegistryName()));

            blocks.add(block);
            items.add(itemBlock);

        } catch (Exception e) {

            ImmersiveMagic.LOGGER.error(String.format("Block %s has had a error : %s", blockClass.getCanonicalName(), e));
        }

        return block;
    }

    public static Item addItemsToRegistry(String modid, Class<?extends ItemBase> itemClass){
        Item item = null;
        String internalName;

        try {
            item = itemClass.getConstructor().newInstance();

            internalName = ((ItemBase)item).getInteneralName();

            if (!internalName.equals(internalName.toLowerCase(Locale.US)))
                throw new IllegalArgumentException(String.format("InternalName values need to be all lowercase! Item: %s", internalName));

            if (internalName.isEmpty())
                throw new IllegalArgumentException(String.format("InternalName cannot be blank! Item: %s", itemClass.getCanonicalName()));

            item.setUnlocalizedName(internalName);
            item.setRegistryName(modid,internalName);

            items.add(item);
        }catch (Exception e){
            ImmersiveMagic.LOGGER.error(String.format("Item %s has had a error : %s", itemClass.getCanonicalName(), e));
        }

        return item;
    }

    public static void initItemBlocks(String modid, Block block) {

        try {
            Item itemBlock = Item.getItemFromBlock(block);
            ModelResourceLocation model = new ModelResourceLocation(String.format("%s", block.getRegistryName()));
            ModelBakery.registerItemVariants(itemBlock, model);
            ItemMeshDefinition meshDefinition = stack -> model;
            ModelLoader.setCustomMeshDefinition(itemBlock, meshDefinition);

        } catch (Exception e) {

            ImmersiveMagic.LOGGER.error(String.format("Failed to initialize ItemBlock for: %s || %s", block.getUnlocalizedName(), e));
        }
    }



    public static List<Block> getBlocks() {
        return blocks;
    }

    public static List<Item> getItemBlocks() {
        return items;
    }
}
