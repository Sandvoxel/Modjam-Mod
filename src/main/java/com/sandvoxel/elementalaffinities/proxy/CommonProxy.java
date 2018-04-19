package com.sandvoxel.elementalaffinities.proxy;

import com.sandvoxel.elementalaffinities.ElementalAffinities;
import com.sandvoxel.elementalaffinities.Reference;
import com.sandvoxel.elementalaffinities.api.magic.IAffinities;
import com.sandvoxel.elementalaffinities.common.blocks.Blocks;
import com.sandvoxel.elementalaffinities.common.gui.GuiHandler;
import com.sandvoxel.elementalaffinities.common.items.Items;
import com.sandvoxel.elementalaffinities.common.magicdata.Affinities;
import com.sandvoxel.elementalaffinities.common.magicdata.AffinitiesProvider;
import com.sandvoxel.elementalaffinities.common.magicdata.AffinitiesStorage;
import com.sandvoxel.elementalaffinities.common.magicdata.AffinityObject;
import com.sandvoxel.elementalaffinities.common.network.lib.Network;
import com.sandvoxel.elementalaffinities.common.spells.Spells;
import com.sandvoxel.elementalaffinities.common.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Iterator;


@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        Blocks.registerBlocks();
        Items.registerItems();
        Spells.registerSpells();
        CapabilityManager.INSTANCE.register(IAffinities.class, new AffinitiesStorage(),Affinities.class);
        new GuiHandler(ElementalAffinities.instance);

    }

    public void init(FMLInitializationEvent event) {
        Network.init();

    }

    public void postInit(FMLPostInitializationEvent event) {

    }


    public static final ResourceLocation AFFINITIES_CAPABILITY = new ResourceLocation(Reference.MOD_ID, "affinitys");

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof EntityPlayer)) return;
        final Affinities maxHealth = new Affinities((EntityPlayer) event.getObject());
        event.addCapability(AFFINITIES_CAPABILITY, AffinitiesProvider.createpoider(maxHealth));
    }

    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone event) {
        IAffinities affinities = event.getOriginal().getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY,null);
        IAffinities affinities1 = event.getEntity().getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY,null);
        affinities1.setPlayerAffinities(affinities.getPlayerAffinities());
    }

    @SubscribeEvent
    public static void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event){
        if(event.getEntity() instanceof EntityPlayer){
            if(event.getEntity().world.isRemote)
                return;
            EntityPlayer player = (EntityPlayer)event.getEntity();
            IAffinities affinities = player.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY,null);
            Iterator<AffinityObject> iter = affinities.getPlayerAffinities().iterator();

            for(AffinityObject affinityObject : affinities.getPlayerAffinities()){
                if(affinityObject.getManaCap() > affinityObject.getAffinityMana()){
                    affinityObject.setAffinityMana(affinityObject.getAffinityMana()+1);
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        for (Block block : RegistryHelper.getBlocks()) {
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Item item : RegistryHelper.getItemBlocks()) {
            event.getRegistry().register(item);
        }
    }

    public void registerGUIs() {
        new GuiHandler(ElementalAffinities.instance);
    }
}
