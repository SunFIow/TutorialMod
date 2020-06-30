package com.sunflow.tutorialmod;

import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.setup.proxy.ClientProxy;
import com.sunflow.tutorialmod.setup.proxy.CommonProxy;
import com.sunflow.tutorialmod.setup.proxy.ServerProxy;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.MyWorldData;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.versions.mcp.MCPVersion;

@Mod(value = TutorialMod.MODID)
//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = TutorialMod.MODID)
public class TutorialMod {
	public static final String MODID = "tutorialmod";
	public static final String NAME = "Tutorial Mod";
	public static final String VERSION = "1.3.0";
	public static final String ACCEPTED_VERSION = "[1.15.2]";

//	public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
	public static CommonProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

	public static MyWorldData data;

	public TutorialMod() {
		Log.info("{} loading, version {}, accepted for {}, for MC {} with MCP {}", NAME, VERSION, ACCEPTED_VERSION, MCPVersion.getMCVersion(), MCPVersion.getMCPVersion());
		Log.info("Loading Network data for {} net version: {}", NAME, Networking.getVersion());

		Log.info("Welcome home senpai.");
//		Log.info("Gimme just a moment senpai while I get some things to tinker with...");
		Log.info("Would you like to have dinner first?");
		Log.info("Or would you rather take a bath?");
		Log.info("Or...");

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TutorialModConfig.CLIENT_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, TutorialModConfig.SERVER_CONFIG);
//		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TutorialModConfig.COMMON_CONFIG);

		proxy.registerEvents();

//		FMLJavaModLoadingContext.get().getModEventBus().addListener(CommonSetup::setup);
//		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::setup));

		Log.warn("Registration#init");
		Registration.registerAll(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
