package com.sandvoxel.tempname.proxy;

import com.sandvoxel.tempname.TempName;
import com.sandvoxel.tempname.common.blocks.Blocks;
import com.sandvoxel.tempname.common.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        Blocks.registerBlocks();
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }


    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        for (Block block : RegistryHelper.getBlocks()) {
            TempName.LOGGER.info(block.getUnlocalizedName());
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Item item : RegistryHelper.getItems()) {
            TempName.LOGGER.info(item.getUnlocalizedName());
            event.getRegistry().register(item);
        }
    }
}
