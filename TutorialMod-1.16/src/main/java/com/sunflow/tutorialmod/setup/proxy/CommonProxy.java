package com.sunflow.tutorialmod.setup.proxy;

import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.setup.CommonForgeEvents;
import com.sunflow.tutorialmod.setup.CommonModEvents;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public abstract class CommonProxy {

	public final void registerEvents() {
		registerModEvents(FMLJavaModLoadingContext.get().getModEventBus());
		registerForgeEvents(MinecraftForge.EVENT_BUS);
	}

	public void registerForgeEvents(final IEventBus eventBus) {
		Log.debug("Registering the Mod Events for u senpai.");

		eventBus.addListener(CommonForgeEvents::serverStarting);
//		eventBus.addListener(CommonForgeEvents::onDimensionRegistry);
	}

	public void registerModEvents(final IEventBus eventBus) {
		Log.debug("Registering the Forge Events for u senpai.");

		eventBus.addListener(CommonModEvents::setup);

//		eventBus.addListener(DataGenerators::gatherData);
		eventBus.addListener(TutorialModConfig::onLoad);
		eventBus.addListener(TutorialModConfig::onReload);
	}

	public abstract World getClientWorld();

	public abstract PlayerEntity getClientPlayer();

	public abstract Minecraft getMinecraft();

	public abstract boolean isClient();

	public abstract boolean isServer();

}
