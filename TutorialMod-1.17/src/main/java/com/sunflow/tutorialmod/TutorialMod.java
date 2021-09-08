package com.sunflow.tutorialmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sunflow.tutorialmod.datagen.DataGenerators;
import com.sunflow.tutorialmod.setup.ClientSetup;
import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.versions.mcp.MCPVersion;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TutorialMod.MODID)
public class TutorialMod {
	// Directly reference a log4j logger.
	public static final Logger LOGGER = LogManager.getLogger();

	public static final String MODID = "tutorialmod";
	public static final String NAME = "Tutorial Mod";
	public static final String VERSION = "0.0.1";
	public static final String ACCEPTED_VERSION = "[1.17.1,1.18)";

	public static final CreativeModeTab TAB_TUTORIALMOD = tutTab();
	public static final int TAB_TUTORIALMOD_ID = CreativeModeTab.getGroupCountSafe() - 1;

//	public static CommonProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

	public TutorialMod() {
		Log.info("{} loading, version {}, accepted for {}, for MC {} with MCP {}", NAME, VERSION, ACCEPTED_VERSION, MCPVersion.getMCVersion(), MCPVersion.getMCPVersion());
//		Log.info("Loading Network data for {} net version: {}", NAME, Networking.getVersion());

		Log.info("Welcome home senpai.");
//	Log.info("Gimme just a moment senpai while I get some things to tinker with...");
		Log.info("Would you like to have dinner first?");
		Log.info("Or would you rather take a bath?");
		Log.info("Or...");

//		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TutorialModConfig.CLIENT_CONFIG);
//		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TutorialModConfig.COMMON_CONFIG);

//		proxy.registerEvents();

		IEventBus bus_mod = FMLJavaModLoadingContext.get().getModEventBus();
		bus_mod.addListener(this::setup);
		bus_mod.addListener(ClientSetup::setup);

		Registration.init(bus_mod);
		bus_mod.addListener(DataGenerators::gatherData);

//		IEventBus bus_forge = MinecraftForge.EVENT_BUS;
//		bus_forge.addListener(this::onServerStarting);
	}

	private static final CreativeModeTab tutTab() {
		int index = CreativeModeTab.getGroupCountSafe();
		CreativeModeTab newGroup = (new CreativeModeTab(index, "tutorialmod") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(Registration.TUTORIAL_ITEM.get());
			}
		});
		CreativeModeTab[] tmp = new CreativeModeTab[index + 1];
		System.arraycopy(CreativeModeTab.TABS, 0, tmp, 0, CreativeModeTab.TABS.length);
		CreativeModeTab.TABS = tmp;
		CreativeModeTab.TABS[index] = newGroup;
		return newGroup;
	}

	private void setup(final FMLCommonSetupEvent event) {

	}
}
