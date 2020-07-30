package com.sunflow.tutorialmod.config;

import org.apache.commons.lang3.tuple.Pair;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class TutorialModConfig {

//	public static ConfigScreen createScreen(Screen parent, ConfigScreen.Category category) {
//		return new ConfigScreen(new StringTextComponent(TutorialMod.NAME), parent, category, ConfigScreen.Builder.create()
//				.Boolean(Category.CLIENT, "options.tutorialmod.client.overlay", TutorialModConfig.CLIENT.showOverlay)
//				.Integer(Category.SERVER, "options.tutorialmod.server.energyitem.maxpower", TutorialModConfig.SERVER.ENERGY_ITEM_MAXPOWER, 0.0D, 20000, 1.0F));
//	}

	private static ConfigScreen.Builder builder;

	private static ConfigScreen.Builder getOrCreateBuilder() {
		if (builder == null) builder = ConfigScreen.Builder.create()
				.Boolean(ModConfig.Type.CLIENT, "options.tutorialmod.client.overlay", TutorialModConfig.CLIENT.showOverlay)
				.Integer(ModConfig.Type.SERVER, "options.tutorialmod.server.energyitem.maxpower", TutorialModConfig.SERVER.ENERGY_ITEM_MAXPOWER, 0.0D, 20000, 1.0F);
		return builder;
	}

	public static ConfigScreen createScreen(Screen parent, ModConfig.Type type) {
		return new ConfigScreen(new StringTextComponent(TutorialMod.NAME), parent, type, getOrCreateBuilder());
	}

//	private static final ForgeConfigSpec commonSpec;
//	public static final Common COMMON;
//	static {
//		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
//		commonSpec = specPair.getRight();
//		COMMON = specPair.getLeft();
//	}

//	public static class Common {
//		Common(ForgeConfigSpec.Builder builder) {
//			builder.comment("Common configuration settings")
//					.push("common");
//			builder.pop();
//		}
//	}

	public static final ForgeConfigSpec clientSpec;
	public static final Client CLIENT;
	static {
		final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
		clientSpec = specPair.getRight();
		CLIENT = specPair.getLeft();
	}

	public static class Client {
		public static final String PATH = "client";
		public final ForgeConfigSpec.BooleanValue showOverlay;

		Client(ForgeConfigSpec.Builder builder) {
			builder.comment("Client configuration settings")
					.push(PATH);

			showOverlay = builder.comment("Show the Overlay InGame")
					.define("show", false);

			builder.pop();
		}

	}

	public static final ForgeConfigSpec serverSpec;
	public static final Server SERVER;
	static {
		final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
		serverSpec = specPair.getRight();
		SERVER = specPair.getLeft();
	}

	public static class Server {
		public static final String PATH = "server";

		public final ForgeConfigSpec.IntValue GLOWSTONE_GENERATOR_GENERATE;
		public final ForgeConfigSpec.IntValue GLOWSTONE_GENERATOR_MAXPOWER;
		public final ForgeConfigSpec.IntValue GLOWSTONE_GENERATOR_TRANSFER;
		public final ForgeConfigSpec.IntValue GLOWSTONE_GENERATOR_TICKS;

		public final ForgeConfigSpec.IntValue SINTERING_FURNACE_TICKS;

		public final ForgeConfigSpec.IntValue ELECTRIC_SINTERING_FURNACE_MAXPOWER;
		public final ForgeConfigSpec.IntValue ELECTRIC_SINTERING_FURNACE_RECEIVE;
		public final ForgeConfigSpec.IntValue ELECTRIC_SINTERING_FURNACE_TICKS;
		public final ForgeConfigSpec.IntValue ELECTRIC_SINTERING_FURNACE_CONSUMPTION;

		public final ForgeConfigSpec.IntValue CHARGER_MAXPOWER;
		public final ForgeConfigSpec.IntValue CHARGER_RECEIVE;
		public final ForgeConfigSpec.IntValue CHARGER_CHARGE_RATE;

		public final ForgeConfigSpec.IntValue ENERGY_ITEM_MAXPOWER;
		public final ForgeConfigSpec.IntValue ENERGY_ITEM_CONSUME;

		Server(ForgeConfigSpec.Builder builder) {
			builder.comment("Server configuration settings")
					.push(PATH);

			// <Machines>
			builder.comment("Machine Settings")
					.push("machines");

			// Glowstone Generator Config
			builder.comment("Glowstone Generator Settings")
					.push("glowstone_generator");
			GLOWSTONE_GENERATOR_GENERATE = builder.comment("Power generation per glowstone")
					.defineInRange("generate", 256, 0, Integer.MAX_VALUE);
			GLOWSTONE_GENERATOR_MAXPOWER = builder.comment("Maximum power that can be stored")
					.defineInRange("maxPower", 10000, 0, Integer.MAX_VALUE);
			GLOWSTONE_GENERATOR_TRANSFER = builder.comment("Maximum power to transfer per tick")
					.defineInRange("transfer", 100, 0, Integer.MAX_VALUE);
			GLOWSTONE_GENERATOR_TICKS = builder.comment("Ticks per glowstone")
					.defineInRange("ticks", 20, 0, Integer.MAX_VALUE);
			builder.pop();

			// Sintering Furnace Config
			builder.comment("Sintering Furnace Settings")
					.push("sintering_furnace");
			SINTERING_FURNACE_TICKS = builder.comment("Ticks per item")
					.defineInRange("ticks", 80, 0, Integer.MAX_VALUE);
			builder.pop();

			// Electric Sintering Furnace Config
			builder.comment("Electric Sintering Furnace Settings")
					.push("electric_sintering_furnace");
			ELECTRIC_SINTERING_FURNACE_MAXPOWER = builder.comment("Maximum power that can be stored")
					.defineInRange("maxPower", 10000, 0, Integer.MAX_VALUE);
			ELECTRIC_SINTERING_FURNACE_RECEIVE = builder.comment("Maximum power to receive per tick")
					.defineInRange("receive", 100, 0, Integer.MAX_VALUE);
			ELECTRIC_SINTERING_FURNACE_TICKS = builder.comment("Ticks per item")
					.defineInRange("ticks", 20, 0, Integer.MAX_VALUE);
			ELECTRIC_SINTERING_FURNACE_CONSUMPTION = builder.comment("Energy consumption per item")
					.defineInRange("consumption", 200, 0, Integer.MAX_VALUE);
			builder.pop();

			// Charger Config
			builder.comment("Charger Settings")
					.push("charger");
			CHARGER_MAXPOWER = builder.comment("Maximum power that can be stored")
					.defineInRange("maxPower", 10000, 0, Integer.MAX_VALUE);
			CHARGER_RECEIVE = builder.comment("Maximum power to transfer per tick")
					.defineInRange("receive", 100, 0, Integer.MAX_VALUE);
			CHARGER_CHARGE_RATE = builder.comment("Rate at which items get charged per tick")
					.defineInRange("chargeRate", 10, 0, Integer.MAX_VALUE);
			builder.pop();

			// Energy Item Config
			builder.comment("Energy Item Settings")
					.push("energy_item");
			ENERGY_ITEM_MAXPOWER = builder.comment("Maximum power that can be stored")
					.defineInRange("maxPower", 2500, 0, 20000);
			ENERGY_ITEM_CONSUME = builder.comment("Power that is consumed per operation")
					.defineInRange("consume", 50, 0, Integer.MAX_VALUE);
			builder.pop();

			// </Machines>
			builder.pop();

			builder.pop();
		}
	}

//	public static void loadConfig(ForgeConfigSpec spec, Path path) {
//		final CommentedFileConfig configData = CommentedFileConfig.builder(path)
//				.sync()
//				.autosave()
//				.writingMode(WritingMode.REPLACE)
//				.build();
//		configData.load();
//		spec.setConfig(configData);
//	}

	public static void onLoad(ModConfig.Loading event) {
		Log.debug("Loaded {} config file {}", TutorialMod.MODID, event.getConfig().getFileName());
	}

	public static void onReload(ModConfig.Reloading event) {
		Log.debug("{} config just got changed on the file system!", TutorialMod.MODID);
	}
}
