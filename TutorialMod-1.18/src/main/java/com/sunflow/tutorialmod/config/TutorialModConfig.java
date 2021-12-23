package com.sunflow.tutorialmod.config;

import com.sunflow.tutorialmod.TutorialMod;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;

public class TutorialModConfig {

	private TutorialModConfig() {}

	public static void init() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG);
	}

	public static final ForgeConfigSpec CLIENT_CONFIG;
	public static final Client CLIENT;
	static {
		final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
		CLIENT_CONFIG = specPair.getRight();
		CLIENT = specPair.getLeft();
	}

	public static class Client {
		public static final String COMMENT = "Client configuration settings";
		public static final String PATH = "client";

		public final ForgeConfigSpec.BooleanValue CONFIG_SHOW_OVERLAY;
		public final ForgeConfigSpec.BooleanValue CONFIG_SHOW_ARMOR;

		Client(ForgeConfigSpec.Builder builder) {
			builder.comment(COMMENT).push(PATH);

			CONFIG_SHOW_OVERLAY = builder.comment("Show the Overlay InGame")
					.define("show", false);
			CONFIG_SHOW_ARMOR = builder.comment("Show your Armor")
					.define("show", true);
			builder.pop();

		}
	}

	public static final ForgeConfigSpec SERVER_CONFIG;
	public static final Server SERVER;

	static {
		final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
		SERVER_CONFIG = specPair.getRight();
		SERVER = specPair.getLeft();
	}

	public static class Server {
		public static final String COMMENT = "Server configuration settings";
		public static final String PATH = "server";

		public static final String CATEGORY_GENERAL = "general";
		public static final String CATEGORY_MACHINES = "machines";
		public static final String SUBCATEGORY_GLOWSTONE_GENERATOR = "glowstone_generator";
		public static final String SUBCATEGORY_SINTERING_FURNACE = "sintering_furnace";
		public static final String SUBCATEGORY_ELECTRIC_SINTERING_FURNACE = "electric_sintering_furnace";
		public static final String SUBCATEGORY_CHARGER = "charger";
		public static final String SUBCATEGORY_ENERGY_ITEM = "energy_item";

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
			builder.comment(COMMENT).push(PATH);

			builder.comment("Machine Settings").push(CATEGORY_MACHINES);

			builder.comment("Glowstone Generator Settings").push(SUBCATEGORY_GLOWSTONE_GENERATOR);
			GLOWSTONE_GENERATOR_GENERATE = builder.comment("Power generation per glowstone")
					.defineInRange("generate", 256, 0, Integer.MAX_VALUE);
			GLOWSTONE_GENERATOR_MAXPOWER = builder.comment("Maximum power that can be stored")
					.defineInRange("maxPower", 10000, 0, Integer.MAX_VALUE);
			GLOWSTONE_GENERATOR_TRANSFER = builder.comment("Maximum power to transfer per tick")
					.defineInRange("transfer", 100, 0, Integer.MAX_VALUE);
			GLOWSTONE_GENERATOR_TICKS = builder.comment("Ticks per glowstone")
					.defineInRange("ticks", 20, 0, Integer.MAX_VALUE);
			builder.pop();

			builder.comment("Sintering Furnace Settings").push(SUBCATEGORY_SINTERING_FURNACE);
			SINTERING_FURNACE_TICKS = builder.comment("Ticks per item")
					.defineInRange("ticks", 80, 0, Integer.MAX_VALUE);
			builder.pop();

			builder.comment("Electric Sintering Furnace Settings").push(SUBCATEGORY_ELECTRIC_SINTERING_FURNACE);
			ELECTRIC_SINTERING_FURNACE_MAXPOWER = builder.comment("Maximum power that can be stored")
					.defineInRange("maxPower", 10000, 0, Integer.MAX_VALUE);
			ELECTRIC_SINTERING_FURNACE_RECEIVE = builder.comment("Maximum power to receive per tick")
					.defineInRange("receive", 100, 0, Integer.MAX_VALUE);
			ELECTRIC_SINTERING_FURNACE_TICKS = builder.comment("Ticks per item")
					.defineInRange("ticks", 20, 0, Integer.MAX_VALUE);
			ELECTRIC_SINTERING_FURNACE_CONSUMPTION = builder.comment("Energy consumption per tick")
					.defineInRange("consumption", 200, 0, Integer.MAX_VALUE);
			builder.pop();

			builder.comment("Charger Settings").push(SUBCATEGORY_CHARGER);
			CHARGER_MAXPOWER = builder.comment("Maximum power that can be stored")
					.defineInRange("maxPower", 10000, 0, Integer.MAX_VALUE);
			CHARGER_RECEIVE = builder.comment("Maximum power to transfer per tick")
					.defineInRange("receive", 100, 0, Integer.MAX_VALUE);
			CHARGER_CHARGE_RATE = builder.comment("Rate at which items get charged per tick")
					.defineInRange("chargeRate", 10, 0, Integer.MAX_VALUE);
			builder.pop();

			builder.comment("Energy Item Settings").push(SUBCATEGORY_ENERGY_ITEM);
			ENERGY_ITEM_MAXPOWER = builder.comment("Maximum power that can be stored")
					.defineInRange("maxPower", 2500, 0, Integer.MAX_VALUE);
			ENERGY_ITEM_CONSUME = builder.comment("Power that is consumed per operation")
					.defineInRange("consume", 50, 0, Integer.MAX_VALUE);
			builder.pop();

			builder.pop(); // Machine Settings

			builder.build();
		}
	}

	public static void onLoad(ModConfigEvent.Loading event) {
		TutorialMod.LOGGER.debug("Loaded {} config file {}", TutorialMod.MODID, event.getConfig().getFileName());
	}

	public static void onReload(ModConfigEvent.Reloading event) {
		TutorialMod.LOGGER.debug("{}({}) config just got changed on the file system!", TutorialMod.MODID, event.getConfig().getFileName());
	}
}
