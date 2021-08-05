package com.sunflow.tutorialmod.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
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

		if (builder == null) {
			SyncConfigPacket.Type[] enumValues = SyncConfigPacket.Type.values();
			builder = ConfigScreen.Builder.create()
//				.Boolean(ModConfig.Type.CLIENT, "options.tutorialmod.client.overlay", TutorialModConfig.CLIENT.showOverlay)
//				.Integer(ModConfig.Type.SERVER, "options.tutorialmod.server.energyitem.maxpower", TutorialModConfig.SERVER.ENERGY_ITEM_MAXPOWER, 0.0D, 20000, 1.0F)

					.Boolean(ModConfig.Type.SERVER, "boolean", SERVER.BOOLEAN_CONFIG)
					.Integer(ModConfig.Type.SERVER, "integer", SERVER.INTEGER_CONFIG, 69, 4711, 0)
					.Long(ModConfig.Type.SERVER, "long", SERVER.LONG_CONFIG, 69L, 4711L, 0)
					.Double(ModConfig.Type.SERVER, "double", SERVER.DOUBLE_CONFIG, 0D, 3D, 0)
					.Enum(ModConfig.Type.SERVER, "enum", SERVER.ENUM_CONFIG, 0, enumValues.length, 1,
							e -> {
								for (int i = 0; i < enumValues.length; i++)
									if (enumValues[i] == e) return (double) i;
								return -1D;
							},
							d -> enumValues[Math.min(d.intValue(), enumValues.length - 1)],
							e -> {
								for (int i = 0; i < enumValues.length; i++)
									if (enumValues[i] == e) return (i + 1) + "/" + enumValues.length + " " + e;
								return "-1/" + enumValues.length + " " + e;
							})
					.String(ModConfig.Type.SERVER, "string", SERVER.STRING_CONFIG, 0, 3, 0,
							s -> s == Blocks.STONE.getTranslationKey() ? 0D : s == Blocks.NETHERRACK.getTranslationKey() ? 1D : 2D,
							d -> d < 1 ? Blocks.STONE.getTranslationKey() : d < 2 ? Blocks.NETHERRACK.getTranslationKey() : Blocks.END_STONE.getTranslationKey(),
							s -> new TranslationTextComponent(s).getFormattedText())
					.List(ModConfig.Type.SERVER, SyncConfigPacket.Type.STRING, "list", SERVER.LIST_CONFIG, 0, 4, 0,
							l -> (double) l.size(),
							d -> {
								ArrayList<String> list = new ArrayList<>();
								if (d >= 1) list.add("ONE");
								if (d >= 2) list.add("TWO");
								if (d >= 3) list.add("THREE");
								return list;
							},
							l -> l.toString());
		}
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

		public final ForgeConfigSpec.IntValue WIRE_MAXPOWER;
		public final ForgeConfigSpec.IntValue WIRE_TRANSFER;

		public final ForgeConfigSpec.IntValue ENERGY_STORAGE_MAXPOWER;
		public final ForgeConfigSpec.IntValue ENERGY_STORAGE_TRANSFER;

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

		public final ForgeConfigSpec.BooleanValue BOOLEAN_CONFIG;
		public final ForgeConfigSpec.IntValue INTEGER_CONFIG;
		public final ForgeConfigSpec.LongValue LONG_CONFIG;
		public final ForgeConfigSpec.DoubleValue DOUBLE_CONFIG;
		public final ForgeConfigSpec.EnumValue<SyncConfigPacket.Type> ENUM_CONFIG;
		public final ForgeConfigSpec.ConfigValue<String> STRING_CONFIG;
//		public final ForgeConfigSpec.ConfigValue<Config> CONFIG_CONFIG;
		public final ForgeConfigSpec.ConfigValue<List<String>> LIST_CONFIG;

		Server(ForgeConfigSpec.Builder builder) {
			builder.comment("Server configuration settings")
					.push(PATH);

			// <General>
			builder.comment("General Settings")
					.push("general");
			BOOLEAN_CONFIG = builder.comment("Boolean Test Config")
					.define("boolean", true);
			INTEGER_CONFIG = builder.comment("Integer Test Config")
					.defineInRange("integer", 1337, 69, 4711);
			LONG_CONFIG = builder.comment("Long Test Config")
					.defineInRange("long", 1337L, 69L, 4711L);
			DOUBLE_CONFIG = builder.comment("Double Test Config")
					.defineInRange("double", 13.37D, 6.9D, 47.11D);
			ENUM_CONFIG = builder.comment("Enum Test Config")
					.defineEnum("enum", SyncConfigPacket.Type.LONG);
			STRING_CONFIG = builder.comment("String Test Config")
					.define("string", Blocks.STONE.getTranslationKey());
//			CONFIG_CONFIG = builder.comment("Config Value Test Config")
//					.define("config", Blocks.STONE.getTranslationKey()); 
			LIST_CONFIG = builder.comment("List Value Test Config")
					.define("list", Lists.newArrayList());
			// </General>
			builder.pop();

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

			// Energy Storage Config
			builder.comment("Energy Storage Settings")
					.push("energy_storage");
			ENERGY_STORAGE_MAXPOWER = builder.comment("Maximum power that can be stored")
					.defineInRange("maxPower", 10000, 0, Integer.MAX_VALUE);
			ENERGY_STORAGE_TRANSFER = builder.comment("Maximum power to transfer per tick")
					.defineInRange("transfer", 100, 0, Integer.MAX_VALUE);
			builder.pop();

			// Wire Config
			builder.comment("Wire Settings")
					.push("wire");
			WIRE_MAXPOWER = builder.comment("Maximum power that can be stored")
					.defineInRange("maxPower", 2000, 0, Integer.MAX_VALUE);
			WIRE_TRANSFER = builder.comment("Maximum power to transfer per tick")
					.defineInRange("transfer", 100, 0, Integer.MAX_VALUE);
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
