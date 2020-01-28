package com.sunflow.tutorialmod.config;

import java.nio.file.Path;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.Log;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;

public class TutorialModConfig {

	public static final String CATEGORY_GENERAL = "general";
	public static final String CATEGORY_MACHINES = "machines";
	public static final String SUBCATEGORY_GLOWSTONE_GENERATOR = "glowstone_generator";
	public static final String SUBCATEGORY_SINTERING_FURNACE = "sintering_furnace";
	public static final String SUBCATEGORY_ELECTRIC_SINTERING_FURNACE = "electric_sintering_furnace";
	public static final String SUBCATEGORY_CHARGER = "charger";
	public static final String SUBCATEGORY_ENERGY_ITEM = "energy_item";

	public static ForgeConfigSpec COMMON_CONFIG;
	public static ForgeConfigSpec CLIENT_CONFIG;

	public static ForgeConfigSpec.BooleanValue CONFIG_SHOW_OVERLAY;

	public static ForgeConfigSpec.IntValue GLOWSTONE_GENERATOR_GENERATE;
	public static ForgeConfigSpec.IntValue GLOWSTONE_GENERATOR_MAXPOWER;
	public static ForgeConfigSpec.IntValue GLOWSTONE_GENERATOR_TRANSFER;
	public static ForgeConfigSpec.IntValue GLOWSTONE_GENERATOR_TICKS;

	public static ForgeConfigSpec.IntValue SINTERING_FURNACE_TICKS;

	public static ForgeConfigSpec.IntValue ELECTRIC_SINTERING_FURNACE_MAXPOWER;
	public static ForgeConfigSpec.IntValue ELECTRIC_SINTERING_FURNACE_RECEIVE;
	public static ForgeConfigSpec.IntValue ELECTRIC_SINTERING_FURNACE_TICKS;
	public static ForgeConfigSpec.IntValue ELECTRIC_SINTERING_FURNACE_CONSUMPTION;

	public static ForgeConfigSpec.IntValue CHARGER_MAXPOWER;
	public static ForgeConfigSpec.IntValue CHARGER_RECEIVE;
	public static ForgeConfigSpec.IntValue CHARGER_CHARGE_RATE;

	public static ForgeConfigSpec.IntValue ENERGY_ITEM_MAXPOWER;
	public static ForgeConfigSpec.IntValue ENERGY_ITEM_CONSUME;

	static {
		commonConfig();
		clientConfig();
	}

	private static void commonConfig() {
		final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

		COMMON_BUILDER.comment("General Settings").push(CATEGORY_GENERAL);
		COMMON_BUILDER.pop();

		COMMON_BUILDER.comment("Machine Settings").push(CATEGORY_MACHINES);
		setupGlowstoneGeneratorConfig(COMMON_BUILDER);
		setupSinteringFurnaceConfig(COMMON_BUILDER);
		setupElectricSinteringFurnaceConfig(COMMON_BUILDER);
		setupChargerConfig(COMMON_BUILDER);
		setupEnergyItemConfig(COMMON_BUILDER);
		COMMON_BUILDER.pop();

		COMMON_CONFIG = COMMON_BUILDER.build();
	}

	private static void clientConfig() {
		final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
		CLIENT_BUILDER.comment("Client only settings").push("client");

		CONFIG_SHOW_OVERLAY = CLIENT_BUILDER.comment("Show the Overlay InGame")
				.define("show", false);
		CLIENT_BUILDER.pop();

		CLIENT_CONFIG = CLIENT_BUILDER.build();
	}

	private static void setupGlowstoneGeneratorConfig(Builder COMMON_BUILDER) {
		COMMON_BUILDER.comment("Glowstone Generator Settings").push(SUBCATEGORY_GLOWSTONE_GENERATOR);
		GLOWSTONE_GENERATOR_GENERATE = COMMON_BUILDER.comment("Power generation per glowstone")
				.defineInRange("generate", 256, 0, Integer.MAX_VALUE);
		GLOWSTONE_GENERATOR_MAXPOWER = COMMON_BUILDER.comment("Maximum power that can be stored")
				.defineInRange("maxPower", 10000, 0, Integer.MAX_VALUE);
		GLOWSTONE_GENERATOR_TRANSFER = COMMON_BUILDER.comment("Maximum power to transfer per tick")
				.defineInRange("transfer", 100, 0, Integer.MAX_VALUE);
		GLOWSTONE_GENERATOR_TICKS = COMMON_BUILDER.comment("Ticks per glowstone")
				.defineInRange("ticks", 20, 0, Integer.MAX_VALUE);
		COMMON_BUILDER.pop();
	}

	private static void setupSinteringFurnaceConfig(Builder COMMON_BUILDER) {
		COMMON_BUILDER.comment("Sintering Furnace Settings").push(SUBCATEGORY_SINTERING_FURNACE);
		SINTERING_FURNACE_TICKS = COMMON_BUILDER.comment("Ticks per item")
				.defineInRange("ticks", 80, 0, Integer.MAX_VALUE);
		COMMON_BUILDER.pop();
	}

	private static void setupElectricSinteringFurnaceConfig(Builder COMMON_BUILDER) {
		COMMON_BUILDER.comment("Electric Sintering Furnace Settings").push(SUBCATEGORY_ELECTRIC_SINTERING_FURNACE);
		ELECTRIC_SINTERING_FURNACE_MAXPOWER = COMMON_BUILDER.comment("Maximum power that can be stored")
				.defineInRange("maxPower", 10000, 0, Integer.MAX_VALUE);
		ELECTRIC_SINTERING_FURNACE_RECEIVE = COMMON_BUILDER.comment("Maximum power to receive per tick")
				.defineInRange("receive", 100, 0, Integer.MAX_VALUE);
		ELECTRIC_SINTERING_FURNACE_TICKS = COMMON_BUILDER.comment("Ticks per item")
				.defineInRange("ticks", 20, 0, Integer.MAX_VALUE);
		ELECTRIC_SINTERING_FURNACE_CONSUMPTION = COMMON_BUILDER.comment("Energy consumption per item")
				.defineInRange("consumption", 200, 0, Integer.MAX_VALUE);
		COMMON_BUILDER.pop();
	}

	private static void setupChargerConfig(Builder COMMON_BUILDER) {
		COMMON_BUILDER.comment("Charger Settings").push(SUBCATEGORY_CHARGER);
		CHARGER_MAXPOWER = COMMON_BUILDER.comment("Maximum power that can be stored")
				.defineInRange("maxPower", 10000, 0, Integer.MAX_VALUE);
		CHARGER_RECEIVE = COMMON_BUILDER.comment("Maximum power to transfer per tick")
				.defineInRange("receive", 100, 0, Integer.MAX_VALUE);
		CHARGER_CHARGE_RATE = COMMON_BUILDER.comment("Rate at which item get charged per tick")
				.defineInRange("chargeRate", 10, 0, Integer.MAX_VALUE);
		COMMON_BUILDER.pop();
	}

	private static void setupEnergyItemConfig(Builder COMMON_BUILDER) {
		COMMON_BUILDER.comment("Energy Item Settings").push(SUBCATEGORY_ENERGY_ITEM);
		ENERGY_ITEM_MAXPOWER = COMMON_BUILDER.comment("Maximum power that can be stored")
				.defineInRange("maxPower", 2500, 0, Integer.MAX_VALUE);
		ENERGY_ITEM_CONSUME = COMMON_BUILDER.comment("Power that is consumed per operation")
				.defineInRange("consume", 50, 0, Integer.MAX_VALUE);
		COMMON_BUILDER.pop();
	}

	public static void loadConfig(ForgeConfigSpec spec, Path path) {
		final CommentedFileConfig configData = CommentedFileConfig.builder(path)
				.sync()
				.autosave()
				.writingMode(WritingMode.REPLACE)
				.build();
		configData.load();
		spec.setConfig(configData);
	}

	@SubscribeEvent
	public static void onLoad(ModConfig.Loading event) {
		Log.debug("Loaded {} config file {}", TutorialMod.MODID, event.getConfig().getFileName());
	}

	@SubscribeEvent
	public static void onReload(ModConfig.Reloading event) {
		Log.debug("{} config just got changed on the file system!", TutorialMod.MODID);
	}
}
