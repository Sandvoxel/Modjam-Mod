package com.sandvoxel.immersivemagic.proxy;

import com.sandvoxel.immersivemagic.api.util.IBlockRender;
import com.sandvoxel.immersivemagic.api.util.IItemRender;
import com.sandvoxel.immersivemagic.client.render.SpellDefault;
import com.sandvoxel.immersivemagic.common.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        SpellDefault.registerRender();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }


    @SubscribeEvent
    public static void RegisterModels(ModelRegistryEvent event) {
        for (Block block : RegistryHelper.getBlocks()) {
            if (block instanceof IBlockRender) {
                ((IBlockRender) block).registerBlockRenderer();
            }
            RegistryHelper.initItemBlocks(block);
        }
        for(Item items : RegistryHelper.getItemBlocks()){
            if(items instanceof IItemRender){
                ((IItemRender)items).registerItemRenderer();
            }
        }

    }
}
