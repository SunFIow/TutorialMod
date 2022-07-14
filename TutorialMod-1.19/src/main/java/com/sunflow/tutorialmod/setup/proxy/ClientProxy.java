package com.sunflow.tutorialmod.setup.proxy;

import com.sunflow.tutorialmod.enchantment.EnchantmentMultiJump;
import com.sunflow.tutorialmod.rendering.RenderMobPositions;
import com.sunflow.tutorialmod.rendering.RenderModOverlay;
import com.sunflow.tutorialmod.rendering.RenderNoArmor;
import com.sunflow.tutorialmod.rendering.RenderTileOverlays;
import com.sunflow.tutorialmod.setup.ClientModEvents;
import com.sunflow.tutorialmod.util.handlers.KeyBindingHandler;
import com.sunflow.tutorialmod.util.handlers.PlayerSkinHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
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
		eventBus.addListener(RenderNoArmor::renderPre);
		eventBus.addListener(RenderNoArmor::renderPost);
		eventBus.addListener(RenderTileOverlays::render);

		eventBus.addListener(EnchantmentMultiJump::enchantmentFunction);
	}

	@Override
	public void registerModEvents(IEventBus eventBus) {
		super.registerModEvents(eventBus);
		eventBus.addListener(ClientModEvents::setup);
		eventBus.addListener(ClientModEvents::onModelRegistryEvent);
		eventBus.addListener(ClientModEvents::onTextureStitch);
		eventBus.addListener(ClientModEvents::onRegisterLayers);
		eventBus.addListener(ClientModEvents::onRegisterRenderers);
	}

	@SuppressWarnings("resource")
	@Override
	public Level getClientWorld() { return Minecraft.getInstance().level; }

	@SuppressWarnings("resource")
	@Override
	public Player getClientPlayer() { return Minecraft.getInstance().player; }

	@Override
	public Minecraft getMinecraft() { return Minecraft.getInstance(); }

	@Override
	public boolean isClient() { return true; }

	@Override
	public boolean isServer() { return false; }

}
