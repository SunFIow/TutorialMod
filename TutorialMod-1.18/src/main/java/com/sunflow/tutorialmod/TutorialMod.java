package com.sunflow.tutorialmod;

import com.sunflow.tutorialmod.datagen.DataGenerators;
import com.sunflow.tutorialmod.setup.ClientSetup;
import com.sunflow.tutorialmod.setup.ModSetup;
import com.sunflow.tutorialmod.setup.Registration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TutorialMod.MODID)
public class TutorialMod {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "tutorialmod";

    public TutorialMod() {
        Registration.init();

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modBus.addListener(ClientSetup::init));

        modBus.addListener(DataGenerators::gatherData);

        // IEventBus forgeBus = MinecraftForge.EVENT_BUS;
    }

}
