package com.sunflow.tutorialmod.setup;

import org.apache.commons.lang3.tuple.Pair;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.Log;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;

public class TutorialModConfig {

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

		public final ForgeConfigSpec.DoubleValue POWERUSER_PARTICLE_YPSPEED;

		Client(ForgeConfigSpec.Builder builder) {
			builder.comment(COMMENT).push(PATH);

			builder.comment("Power User Settings")
					.push("poweruser");
			POWERUSER_PARTICLE_YPSPEED = builder
					.comment("The vertical speed of the particles")
					.defineInRange("yspeed", 0.0, -1_000.0, 1_000.0);
			builder.pop();

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

		public final ForgeConfigSpec.IntValue GENERATOR_PERTICK;
		public final ForgeConfigSpec.IntValue GENERATOR_SENDPERTICK;
		public final ForgeConfigSpec.IntValue GENERATOR_CAPACITY;

		public final ForgeConfigSpec.IntValue POWERUSER_PERTICK;
		public final ForgeConfigSpec.IntValue POWERUSER_CAPACITY;

		Server(ForgeConfigSpec.Builder builder) {
			builder.comment(COMMENT).push(PATH);

			builder.comment("Generator Settings")
					.push("generator");
			GENERATOR_PERTICK = builder
					.comment("How much FE the generator can generate per tick")
					.defineInRange("generatePerTick", 50, 1, Integer.MAX_VALUE);
			GENERATOR_SENDPERTICK = builder
					.comment("How much FE the generator can send to adjacent blocks per tick")
					.defineInRange("sendPerTick", 1000, 1, Integer.MAX_VALUE);
			GENERATOR_CAPACITY = builder
					.comment("How much FE the generator can store")
					.defineInRange("capacity", 100_000, 1, Integer.MAX_VALUE);
			builder.pop();

			builder.comment("Power User Settings")
					.push("poweruser");
			POWERUSER_PERTICK = builder
					.comment("How much FE the poweruser will use per tick")
					.defineInRange("usePerTick", 20, 1, Integer.MAX_VALUE);
			POWERUSER_CAPACITY = builder
					.comment("How much FE the poweruser can store")
					.defineInRange("capacity", 1_000, 1, Integer.MAX_VALUE);
			builder.pop();

			builder.pop();
		}
	}

	@SubscribeEvent
	public static void onLoad(ModConfigEvent.Loading event) {
		Log.debug("Loaded {} config file {}", TutorialMod.MODID, event.getConfig().getFileName());
	}

	@SubscribeEvent
	public static void onReload(ModConfigEvent.Reloading event) {
		Log.debug("{} config just got changed on the file system!", TutorialMod.MODID);
	}

}
