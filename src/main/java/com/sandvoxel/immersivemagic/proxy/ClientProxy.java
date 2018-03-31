package com.sandvoxel.immersivemagic.proxy;

import com.sandvoxel.immersivemagic.Refrence;
import com.sandvoxel.immersivemagic.common.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @SubscribeEvent
    public static void RegisterModels(ModelRegistryEvent event) {
        for (Block block : RegistryHelper.getBlocks()) {
            RegistryHelper.initItemBlocks(Refrence.MOD_ID, block);
        }
    }
}
