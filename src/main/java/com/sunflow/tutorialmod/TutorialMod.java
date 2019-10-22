package com.sunflow.tutorialmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sunflow.tutorialmod.init.ModCommands;
import com.sunflow.tutorialmod.setup.ModSetup;
import com.sunflow.tutorialmod.setup.proxy.ClientProxy;
import com.sunflow.tutorialmod.setup.proxy.CommonProxy;
import com.sunflow.tutorialmod.setup.proxy.ServerProxy;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.server.command.ConfigCommand;
import net.minecraftforge.versions.mcp.MCPVersion;

@Mod(value = TutorialMod.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = TutorialMod.MODID)
public class TutorialMod {
	public static final String MODID = "tutorialmod";
	public static final String NAME = "Tutorial Mod";
	public static final String VERSION = "1.0.4";
	public static final String ACCEPTED_VERSION = "[1.14.4,)";

	public static final Logger LOGGER = LogManager.getLogger(NAME);
//  private static final Marker FORGEMOD = MarkerManager.getMarker("FORGEMOD");

	private static TutorialMod INSTANCE;

	public static TutorialMod getInstance() {
		return INSTANCE;
	}

	public static CommonProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

	public static final ModSetup setup = new ModSetup();

	public TutorialMod() {
		LOGGER.info("{} loading, version {}, accepted for {}, for MC {} with MCP {}", NAME, VERSION, ACCEPTED_VERSION, MCPVersion.getMCVersion(), MCPVersion.getMCPVersion());

		LOGGER.info("Welcome home senpai.");
//		LOGGER.info("Gimme just a moment senpai while I get some things to tinker with...");
		LOGGER.info("Would you like to have dinner first?");
		LOGGER.info("Or would you rather take a bath?");
		LOGGER.info("Or...");

		INSTANCE = this;
		TutorialMod.proxy.preSetup();

//		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
//		modEventBus.addListener(this::setup);
//		modEventBus.addListener(this::clientSetup);
//		modEventBus.register(CommonRegistrations.class);
//		MinecraftForge.EVENT_BUS.addListener(this::serverStarting);
//
//		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
//		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
//		modEventBus.register(Config.class);
//
//		Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("tutorialmod-client.toml"));
//		Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("tutorialmod-common.toml"));

//		ModSounds.registerSounds();
	}

	public void setup(FMLCommonSetupEvent event) {
//		LOGGER.info("Almost ready senpai ...");
		LOGGER.info("perhaps you would like...");

		TutorialMod.setup.init();
		TutorialMod.proxy.setup();
	}

//	public void clientSetup(FMLClientSetupEvent event) {
//		LOGGER.info("m...");
//		LOGGER.info("mm...");
//		LOGGER.info("me? ");
//		LOGGER.info("*giggling*");
//
////		final IEventBus eventBus = MinecraftForge.EVENT_BUS;
////		eventBus.register(KeyBindingHandler.class);
////		eventBus.register(ModEnchantments.class);
////		eventBus.register(PlayerSkinHandler.class);
////		eventBus.register(CustomHandlers.class);
//
////		LOGGER.info("I am ready now senpai. Shall we begin?");
//	}
//
//	public void serverSetup(FMLDedicatedServerSetupEvent event) {}

	public void serverStarting(FMLServerStartingEvent event) {
		LOGGER.info("Preparing the server for u senpai.");

		new ModCommands(event.getCommandDispatcher());
		ConfigCommand.register(event.getCommandDispatcher());
	}
}
