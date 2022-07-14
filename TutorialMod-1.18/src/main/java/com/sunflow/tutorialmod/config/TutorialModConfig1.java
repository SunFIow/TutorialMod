package com.sunflow.tutorialmod.config;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.sunflow.tutorialmod.TutorialMod;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class TutorialModConfig1 {

	public static ConfigScreen createScreen(Screen parent, ModConfig.Type type) { return new ConfigScreen(new TextComponent(TutorialMod.NAME), parent, type, getOrCreateBuilder()); }

	private static ConfigScreen.Builder builder;

	private static ConfigScreen.Builder getOrCreateBuilder() {
		builder = null;
		if (builder == null) builder = getBuilder();
		return builder;
	}

	public static ConfigScreen.Builder getBuilder() {
		SyncConfigPacket.Type[] enumValues = SyncConfigPacket.Type.values();
		builder = ConfigScreen.Builder.create()
				.Boolean(ModConfig.Type.CLIENT, "overlay", "Overlay", TutorialModConfig.CLIENT.CONFIG_SHOW_OVERLAY)
				.Integer(ModConfig.Type.SERVER, "energyitem.maxpower", "Maximum Power the EnergyItem can store", TutorialModConfig.SERVER.ENERGY_ITEM_MAXPOWER, 0.0D, 20000, 1.0F)

				.Boolean(ModConfig.Type.SERVER, "boolean", "Boolean Config Value", SERVER.BOOLEAN_CONFIG)
				.Integer(ModConfig.Type.SERVER, "integer", "Integer Config Value", SERVER.INTEGER_CONFIG, 69, 4711, 0)
				.Long(ModConfig.Type.SERVER, "long", "Long Config Value", SERVER.LONG_CONFIG, 69L, 4711L, 0)
				.Double(ModConfig.Type.SERVER, "double", "Double Config Value", SERVER.DOUBLE_CONFIG, 0D, 3D, 0)
				.Enum(ModConfig.Type.SERVER, "enum", "Enum Config Value", SERVER.ENUM_CONFIG, 0, enumValues.length, 1,
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
				.String(ModConfig.Type.SERVER, "string", "String Config Value", SERVER.STRING_CONFIG, 0, 3, 0,
						s -> s.equals(Blocks.STONE.getDescriptionId()) ? 0D : s.equals(Blocks.NETHERRACK.getDescriptionId()) ? 1D : 2D,
						d -> d < 1 ? Blocks.STONE.getDescriptionId() : d < 2 ? Blocks.NETHERRACK.getDescriptionId() : Blocks.END_STONE.getDescriptionId(),
						s -> new TranslatableComponent(s).getString())
				.List(ModConfig.Type.SERVER, SyncConfigPacket.Type.STRING, "list", "List Config Value", SERVER.LIST_CONFIG, 0, 4, 0,
						l -> (double) l.size(),
						d -> {
							ArrayList<String> list = new ArrayList<>();
							if (d >= 1) list.add("ONE");
							if (d >= 2) list.add("TWO");
							if (d >= 3) list.add("THREE");
							return list;
						},
						l -> l.toString());
		return builder;
	}

	public static final ForgeConfigSpec clientSpec;
	public static final Client CLIENT;
	static {
		final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
		clientSpec = specPair.getRight();
		CLIENT = specPair.getLeft();
	}

	public static class Client {
		public static final String COMMENT = "Client configuration settings";
		public static final String PATH = "client";

		Client(ForgeConfigSpec.Builder builder) {}
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

		public final ForgeConfigSpec.BooleanValue BOOLEAN_CONFIG;
		public final ForgeConfigSpec.IntValue INTEGER_CONFIG;
		public final ForgeConfigSpec.LongValue LONG_CONFIG;
		public final ForgeConfigSpec.DoubleValue DOUBLE_CONFIG;
		public final ForgeConfigSpec.EnumValue<SyncConfigPacket.Type> ENUM_CONFIG;
		public final ForgeConfigSpec.ConfigValue<String> STRING_CONFIG;
		// public final ForgeConfigSpec.ConfigValue<Config> CONFIG_CONFIG;
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
					.define("string", Blocks.STONE.getDescriptionId());
			// CONFIG_CONFIG = builder.comment("Config Value Test Config")
			// .define("config", Blocks.STONE.getTranslationKey());
			LIST_CONFIG = builder.comment("List Value Test Config")
					.define("list", Lists.newArrayList());
			// </General>
			builder.pop();

			builder.pop();
		}
	}
}
