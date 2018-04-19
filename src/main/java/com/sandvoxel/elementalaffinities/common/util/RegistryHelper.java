package com.sandvoxel.elementalaffinities.common.util;

import com.sandvoxel.elementalaffinities.ElementalAffinities;
import com.sandvoxel.elementalaffinities.Reference;
import com.sandvoxel.elementalaffinities.api.blocks.IBlock;
import com.sandvoxel.elementalaffinities.common.items.lib.ItemBase;
import com.sandvoxel.elementalaffinities.common.spells.SpellTypes;
import com.sandvoxel.elementalaffinities.common.spells.lib.SpellBase;
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
        String internalName = "";

        try {
            block = blockClass.getConstructor().newInstance();
            itemBlock = new ItemBlock(block);

            if (block instanceof IBlock) {
                internalName = ((IBlock) block).getInternalName();
            }

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

            ElementalAffinities.LOGGER.error(String.format("Block %s has had a error : %s", blockClass.getCanonicalName(), e));
        }

        return block;
    }

    public static Item addItemsToRegistry(String modid, Class<?extends ItemBase> itemClass){
        Item item = null;
        String internalName = null;

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
            ElementalAffinities.LOGGER.error(String.format("Item %s has had a error : %s", itemClass.getCanonicalName(), e));
        }

        return item;
    }

    public static void initItemBlocks(Block block) {
        try {
            Item itemBlock = Item.getItemFromBlock(block);
            ModelResourceLocation model = new ModelResourceLocation(String.format("%s", block.getRegistryName()));
            ModelBakery.registerItemVariants(itemBlock, model);
            ItemMeshDefinition meshDefinition = stack -> model;
            ModelLoader.setCustomMeshDefinition(itemBlock, meshDefinition);

        } catch (Exception e) {
            ElementalAffinities.LOGGER.error(String.format("Failed to initialize ItemBlock for: %s || %s", block.getUnlocalizedName(), e));
        }
    }

    public static Item spellRegstration(Class<? extends SpellBase> spellClass,int id){
        Item item = null;
        String internalName;

        try {
            item = spellClass.getConstructor().newInstance();
            internalName = ((SpellBase)item).getInteneralName();

            ElementalAffinities.LOGGER.info(spellClass);

            if (!internalName.equals(internalName.toLowerCase(Locale.US)))
                throw new IllegalArgumentException(String.format("InternalName values need to be all lowercase! Item: %s", internalName));

            if (internalName.isEmpty())
                throw new IllegalArgumentException(String.format("InternalName cannot be blank! Item: %s", spellClass.getCanonicalName()));

            item.setUnlocalizedName(internalName);
            item.setRegistryName(Reference.MOD_ID,internalName);

            items.add(item);
            ElementalAffinities.LOGGER.info("Added spell Item " + item);

            if (((SpellBase)item).getSpellType() == SpellTypes.THROWABLE_SPELL){
                ((SpellBase)item).RegisterSpellEntity(id);
            }


        }catch (Exception e){
            ElementalAffinities.LOGGER.error(String.format("Failed to initialize Spell for: %s || %s", spellClass.getCanonicalName(), e));

        }
        return item;
    }





    public static List<Block> getBlocks() {
        return blocks;
    }

    public static List<Item> getItemBlocks() {
        return items;
    }
}
