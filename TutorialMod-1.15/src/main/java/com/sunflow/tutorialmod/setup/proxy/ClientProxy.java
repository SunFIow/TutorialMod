package com.sunflow.tutorialmod.setup.proxy;

import com.sunflow.tutorialmod._testing.rendering.RenderPlayerFire;
import com.sunflow.tutorialmod.config.InGameConfig;
import com.sunflow.tutorialmod.enchantment.EnchantmentMultiJump;
import com.sunflow.tutorialmod.rendering.RenderMobPositions;
import com.sunflow.tutorialmod.rendering.RenderModOverlay;
import com.sunflow.tutorialmod.rendering.RenderTileOverlays;
import com.sunflow.tutorialmod.setup.ClientModEvents;
import com.sunflow.tutorialmod.util.handlers.KeyBindingHandler;
import com.sunflow.tutorialmod.util.handlers.PlayerSkinHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.IEventBus;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerForgeEvents(IEventBus eventBus) {
		super.registerForgeEvents(eventBus);

		eventBus.addListener(KeyBindingHandler::handleKeyInputEvent);
		eventBus.addListener(KeyBindingHandler::handleKeyBindingZoom);

		eventBus.addListener(PlayerSkinHandler::playerJoined);

		eventBus.addListener(RenderMobPositions::render);
		eventBus.addListener(RenderModOverlay::render);
		eventBus.addListener(RenderTileOverlays::render);
		eventBus.addListener(RenderPlayerFire::render);
		eventBus.addListener(RenderPlayerFire::renderOverlay);

		eventBus.addListener(EnchantmentMultiJump::enchantmentFunction);
	}

	@Override
	public void registerModEvents(IEventBus eventBus) {
		super.registerModEvents(eventBus);
		eventBus.addListener(ClientModEvents::setup);
		eventBus.addListener(ClientModEvents::onItemColor);
		eventBus.addListener(ClientModEvents::onTextureStitch);

		eventBus.addListener(InGameConfig::RegisterExtensionPoint);
	}

	@SuppressWarnings("resource")
	@Override
	public World getClientWorld() { return Minecraft.getInstance().world; }

	@SuppressWarnings("resource")
	@Override
	public PlayerEntity getClientPlayer() { return Minecraft.getInstance().player; }

	@Override
	public Minecraft getMinecraft() { return Minecraft.getInstance(); }

	@Override
	public boolean isClient() { return true; }

	@Override
	public boolean isServer() { return false; }

}
