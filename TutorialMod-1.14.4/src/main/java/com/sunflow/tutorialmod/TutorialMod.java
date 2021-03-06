package com.sunflow.tutorialmod;

import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.setup.ModCommands;
import com.sunflow.tutorialmod.setup.ModGroups;
import com.sunflow.tutorialmod.setup.proxy.ClientProxy;
import com.sunflow.tutorialmod.setup.proxy.CommonProxy;
import com.sunflow.tutorialmod.setup.proxy.ServerProxy;
import com.sunflow.tutorialmod.util.Log;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.versions.mcp.MCPVersion;

@Mod(value = TutorialMod.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = TutorialMod.MODID)
public class TutorialMod {
	public static final String MODID = "tutorialmod";
	public static final String NAME = "Tutorial Mod";
	public static final String VERSION = "1.0.4";
	public static final String ACCEPTED_VERSION = "[1.14.4,)";

//	public static final Logger LOGGER = LogManager.getLogger(NAME);

//  private static final Marker FORGEMOD = MarkerManager.getMarker("FORGEMOD");

	private static TutorialMod INSTANCE;

	public static TutorialMod getInstance() {
		return INSTANCE;
	}

	public static CommonProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

	public static final ModGroups groups = new ModGroups();

	public TutorialMod() {
		Log.info("{} loading, version {}, accepted for {}, for MC {} with MCP {}", NAME, VERSION, ACCEPTED_VERSION, MCPVersion.getMCVersion(), MCPVersion.getMCPVersion());
		Log.info("Loading Network data for TutorialMod net version: {}", Networking.init());

		INSTANCE = this;

		TutorialMod.proxy.preSetup();
	}

	public void setup(FMLCommonSetupEvent event) {
//		LOGGER.info("Almost ready senpai ...");

		TutorialMod.proxy.setup();
	}

	public void serverStarting(FMLServerStartingEvent event) {
		Log.info("Preparing the server for u senpai.");

		ModCommands.register(event.getCommandDispatcher());
	}
}
