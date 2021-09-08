package com.sunflow.tutorialmod.setup;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Config {
	public static ForgeConfigSpec CLIENT_CONFIG;
	public static ForgeConfigSpec SERVER_CONFIG;

	public static ForgeConfigSpec.IntValue GENERATOR_PERTICK;
	public static ForgeConfigSpec.IntValue GENERATOR_SENDPERTICK;
	public static ForgeConfigSpec.IntValue GENERATOR_CAPACITY;

	public static ForgeConfigSpec.IntValue POWERUSER_PERTICK;
	public static ForgeConfigSpec.IntValue POWERUSER_CAPACITY;
	public static ForgeConfigSpec.DoubleValue POWERUSER_PARTICLE_YPSPEED;

	public static void init() {
		initClient();
		initServer();

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG);
	}

	private static void initClient() {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

		builder.comment("Power User Settings")
				.push("poweruser");
		POWERUSER_PARTICLE_YPSPEED = builder
				.comment("The vertical speed of the particles")
				.defineInRange("yspeed", 0.0, -1_000.0, 1_000.0);
		builder.pop();

		CLIENT_CONFIG = builder.build();
	}

	private static void initServer() {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

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

		SERVER_CONFIG = builder.build();
	}
}
