package com.sandvoxel.elementalaffinities;

import com.google.common.base.Stopwatch;
import com.sandvoxel.elementalaffinities.common.creativetab.MainCreativeTab;
import com.sandvoxel.elementalaffinities.common.exception.OutdatedJavaException;
import com.sandvoxel.elementalaffinities.common.util.Logger;
import com.sandvoxel.elementalaffinities.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.SystemUtils;

import java.util.concurrent.TimeUnit;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class ElementalAffinities {

    public static Logger LOGGER = new Logger(Reference.MOD_NAME);
    public static Stopwatch STOPWATCH = Stopwatch.createUnstarted();

    public static MainCreativeTab tabimmmag = new MainCreativeTab();


    @Mod.Instance(Reference.MOD_ID)
    public static ElementalAffinities instance;

    @SidedProxy(clientSide = "com.sandvoxel.elementalaffinities.proxy.ClientProxy", serverSide = "com.sandvoxel.elementalaffinities.proxy.ServerProxy")
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        STOPWATCH.start();
        LOGGER.info("[Pre-Init] Started");

        if (!SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_8)) {
            throw new OutdatedJavaException(String.format("%s requires Java 8 or newer, Please update your java", Reference.MOD_NAME));
        }

        proxy.preInit(event);

        LOGGER.info("[Pre-Init] Finished <" + STOPWATCH.elapsed(TimeUnit.MILLISECONDS) + "ms>");
        STOPWATCH.reset();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        STOPWATCH.start();
        LOGGER.info("[Init] Started");

        proxy.init(event);

        LOGGER.info("[Init] Finished <" + STOPWATCH.elapsed(TimeUnit.MILLISECONDS) + "ms>");
        STOPWATCH.reset();
    }

    @Mod.EventHandler
    public void preInit(FMLPostInitializationEvent event) {
        STOPWATCH.start();
        LOGGER.info("[Post-Init] Started");

        proxy.postInit(event);

        LOGGER.info("[Post-Init] Finished <" + STOPWATCH.elapsed(TimeUnit.MILLISECONDS) + "ms>");
        STOPWATCH.reset();
    }



}
