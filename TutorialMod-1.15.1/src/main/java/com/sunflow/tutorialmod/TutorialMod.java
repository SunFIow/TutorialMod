package com.sunflow.tutorialmod;

import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.setup.proxy.ClientProxy;
import com.sunflow.tutorialmod.setup.proxy.CommonProxy;
import com.sunflow.tutorialmod.setup.proxy.ServerProxy;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.MyWorldData;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.versions.mcp.MCPVersion;

@Mod(value = TutorialMod.MODID)
//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = TutorialMod.MODID)
public class TutorialMod {
	public static final String MODID = "tutorialmod";
	public static final String NAME = "Tutorial Mod";
	public static final String VERSION = "1.0.6";
	public static final String ACCEPTED_VERSION = "[1.15.1,)";

//	public static CommonProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
	public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

	public static MyWorldData data;

	public TutorialMod() {
		Log.info("{} loading, version {}, accepted for {}, for MC {} with MCP {}", NAME, VERSION, ACCEPTED_VERSION, MCPVersion.getMCVersion(), MCPVersion.getMCPVersion());
		Log.info("Loading Network data for {} net version: {}", NAME, Networking.init());

		proxy.preSetup();
	}
}
