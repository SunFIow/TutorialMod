package com.sunflow.tutorialmod.setup.proxy;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.ModCommands;
import com.sunflow.tutorialmod.setup.ModFluids;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.MyWorldData;
import com.sunflow.tutorialmod.util.VersionUtils;
import com.sunflow.tutorialmod.util.config.Config;
import com.sunflow.tutorialmod.util.handlers.CommonForgeEventHandlers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public abstract class CommonProxy {

	public void preSetup() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		MinecraftForge.EVENT_BUS.addListener(this::serverStarting);

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

//		ModBiomes.registerBiomes();
		ModFluids.init();
	}

	public void setup(FMLCommonSetupEvent event) {
		final IEventBus eventBus = MinecraftForge.EVENT_BUS;
		eventBus.register(CommonForgeEventHandlers.class);
//		eventBus.register(ModEnchantments.ENCHANTMENT_MULTIJUMP);
	}

	public void serverStarting(FMLServerStartingEvent event) {
		Log.info("Preparing the server for u senpai.");

		ModCommands.register(event.getCommandDispatcher());
		TutorialMod.data = VersionUtils.getWorld(event.getServer(), DimensionType.OVERWORLD).getSavedData().getOrCreate(MyWorldData::new, MyWorldData.ID_GENERAL);
	}

	public abstract World getClientWorld();

	public abstract PlayerEntity getClientPlayer();

	public abstract Minecraft getMinecraft();
}
