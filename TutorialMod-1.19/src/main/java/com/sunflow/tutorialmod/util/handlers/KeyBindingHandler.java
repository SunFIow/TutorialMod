package com.sunflow.tutorialmod.util.handlers;

import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.ExplodePacket;
import com.sunflow.tutorialmod.util.enums.KeyBindings;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.FOVModifierEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KeyBindingHandler {

	@SubscribeEvent
	public static void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
		if (KeyBindings.EXPLODE.consumeClick()) Networking.sendToServer(new ExplodePacket(4711));
		if (KeyBindings.OVERLAY.consumeClick()) {
			TutorialModConfig.CLIENT.CONFIG_SHOW_OVERLAY.set(!TutorialModConfig.CLIENT.CONFIG_SHOW_OVERLAY.get());
			TutorialModConfig.CLIENT.CONFIG_SHOW_OVERLAY.save();
		}
		if (KeyBindings.NOARMOR.consumeClick()) {
			TutorialModConfig.CLIENT.CONFIG_SHOW_OVERLAY.set(!TutorialModConfig.CLIENT.CONFIG_SHOW_OVERLAY.get());
			TutorialModConfig.CLIENT.CONFIG_SHOW_OVERLAY.save();
		}
	}

	@SubscribeEvent
	public static void handleKeyBindingZoom(FOVModifierEvent event) {
		if (!(event.getEntity() instanceof Player)) return;
		if (KeyBindings.ZOOM.isDown()) event.setNewfov(event.getNewfov() / 4);
	}
}
