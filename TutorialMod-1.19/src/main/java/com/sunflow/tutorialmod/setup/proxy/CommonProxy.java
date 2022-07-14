package com.sunflow.tutorialmod.setup.proxy;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.data.DataGenerators;
import com.sunflow.tutorialmod.setup.CommonForgeEvents;
import com.sunflow.tutorialmod.setup.CommonModEvents;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public abstract class CommonProxy {

	public final void registerEvents() {
		registerModEvents(FMLJavaModLoadingContext.get().getModEventBus());
		registerForgeEvents(MinecraftForge.EVENT_BUS);
	}

	public void registerForgeEvents(final IEventBus eventBus) {
		TutorialMod.LOGGER.debug("Registering the Mod Events for u senpai.");

		eventBus.addListener(CommonForgeEvents::serverStarting);
		eventBus.addListener(CommonForgeEvents::onCommandsRegister);
		// eventBus.addListener(CommonForgeEvents::onDimensionRegistry);
	}

	public void registerModEvents(final IEventBus eventBus) {
		TutorialMod.LOGGER.debug("Registering the Forge Events for u senpai.");

		eventBus.addListener(CommonModEvents::setup);
		eventBus.addListener(CommonModEvents::onEntityAttributeCreation);

		eventBus.addListener(DataGenerators::gatherData);
		eventBus.addListener(TutorialModConfig::onLoad);
		eventBus.addListener(TutorialModConfig::onReload);
	}

	public abstract Level getClientWorld();

	public abstract Player getClientPlayer();

	public abstract Minecraft getMinecraft();

	public abstract boolean isClient();

	public abstract boolean isServer();

}
