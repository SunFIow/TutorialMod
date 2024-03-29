package com.sunflow.tutorialmod;

import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.config.TutorialModConfig1;
import com.sunflow.tutorialmod.data.MySaveData;
import com.sunflow.tutorialmod.data.TutorialData;
import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.setup.proxy.ClientProxy;
import com.sunflow.tutorialmod.setup.proxy.CommonProxy;
import com.sunflow.tutorialmod.setup.proxy.ServerProxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.WorldData;
import net.minecraftforge.client.ConfigGuiHandler.ConfigGuiFactory;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.internal.WorldPersistenceHooks;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TutorialMod.MODID)
public class TutorialMod {

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "tutorialmod";
    public static final String NAME = "Tutorial Mod";
    public static final String VERSION = "0.0.1";
    public static final String ACCEPTED_VERSION = "[1.18,)";

    public static CommonProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static MySaveData data;

    public TutorialMod() {
        Registration.init();

        TutorialModConfig.init();

        WorldPersistenceHooks.addHook(new TutorialData());

        proxy.registerEvents();

        ModLoadingContext.get().registerExtensionPoint(ConfigGuiFactory.class, () -> new ConfigGuiFactory((mc, parent) -> TutorialModConfig1.createScreen(parent, ModConfig.Type.CLIENT)));
    }
}
