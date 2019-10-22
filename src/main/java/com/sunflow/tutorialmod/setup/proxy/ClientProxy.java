package com.sunflow.tutorialmod.setup.proxy;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.blocks.tile.CopperChestTile;
import com.sunflow.tutorialmod.entity.CentaurEntity;
import com.sunflow.tutorialmod.entity.WeirdMobEntity;
import com.sunflow.tutorialmod.entity.renderer.CentaurRenderer;
import com.sunflow.tutorialmod.entity.renderer.CopperChestTileRenderer;
import com.sunflow.tutorialmod.entity.renderer.WeirdMobRenderer;
import com.sunflow.tutorialmod.init.ModEnchantments;
import com.sunflow.tutorialmod.setup.ClientRegistrations;
import com.sunflow.tutorialmod.util.handlers.CustomHandlers;
import com.sunflow.tutorialmod.util.handlers.KeyBindingHandler;
import com.sunflow.tutorialmod.util.handlers.PlayerSkinHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy extends CommonProxy {

	@Override
	public void preSetup() {
		TutorialMod.LOGGER.info("Welcome home senpai.");
//		TutorialMod.LOGGER.info("Gimme just a moment senpai while I get some things to tinker with...");
		TutorialMod.LOGGER.info("Would you like to have dinner first?");
		TutorialMod.LOGGER.info("Or would you rather take a bath?");
		TutorialMod.LOGGER.info("Or...");

		super.preSetup();

		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.register(ClientRegistrations.class);
	}

	@Override
	public void setup() {
		TutorialMod.LOGGER.info("perhaps you would like...");

		super.setup();

		ClientRegistrations.registerScreens();

		RenderingRegistry.registerEntityRenderingHandler(WeirdMobEntity.class, WeirdMobRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(CentaurEntity.class, CentaurRenderer::new);

		try {
			KeyBindingHandler.class.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		ClientRegistry.bindTileEntitySpecialRenderer(CopperChestTile.class, new CopperChestTileRenderer<CopperChestTile>());

		final IEventBus eventBus = MinecraftForge.EVENT_BUS;
		eventBus.register(KeyBindingHandler.class);
		eventBus.register(ModEnchantments.class);
		eventBus.register(PlayerSkinHandler.class);
		eventBus.register(CustomHandlers.class);

		TutorialMod.LOGGER.info("m...");
		TutorialMod.LOGGER.info("mm...");
		TutorialMod.LOGGER.info("me? ");
		TutorialMod.LOGGER.info("*giggling*");

		TutorialMod.LOGGER.info("I am ready now senpai. Shall we begin?");
	}

	@Override
	public World getClientWorld() {
		return Minecraft.getInstance().world;
	}

	@Override
	public PlayerEntity getClientPlayer() {
		return Minecraft.getInstance().player;
	}
}
