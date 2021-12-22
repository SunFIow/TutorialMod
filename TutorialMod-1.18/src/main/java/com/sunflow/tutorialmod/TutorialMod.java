package com.sunflow.tutorialmod;

import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.setup.proxy.ClientProxy;
import com.sunflow.tutorialmod.setup.proxy.CommonProxy;
import com.sunflow.tutorialmod.setup.proxy.ServerProxy;
import com.sunflow.tutorialmod.util.MyWorldData;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TutorialMod.MODID)
public class TutorialMod {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger(TutorialMod.class);
    public static final String MODID = "tutorialmod";
    public static final String NAME = "Tutorial Mod";
    public static final String VERSION = "0.0.1";
    public static final String ACCEPTED_VERSION = "[1.18,)";

    public static CommonProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static MyWorldData data;

    public TutorialMod() {
        Registration.init();

        TutorialModConfig.init();

        proxy.registerEvents();
    }
}
