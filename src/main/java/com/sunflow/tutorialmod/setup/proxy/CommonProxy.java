package com.sunflow.tutorialmod.setup.proxy;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.init.ModEnchantments;
import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.setup.CommonRegistrations;
import com.sunflow.tutorialmod.util.Config;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

public abstract class CommonProxy {

	public void preSetup() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(TutorialMod.getInstance()::setup);
		modEventBus.register(CommonRegistrations.class);
		MinecraftForge.EVENT_BUS.addListener(TutorialMod.getInstance()::serverStarting);

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
		modEventBus.register(Config.class);

		Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("tutorialmod-client.toml"));
		Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("tutorialmod-common.toml"));

		Networking.registerMessages();
	}

	public void setup() {
		final IEventBus eventBus = MinecraftForge.EVENT_BUS;
		eventBus.register(ModEnchantments.ENCHANTMENT_MULTIJUMP);
//		eventBus.register(EnchantmentMultiJump.class);
	}

	public abstract World getClientWorld();

	public abstract PlayerEntity getClientPlayer();
}
