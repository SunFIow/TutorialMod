package com.sunflow.tutorialmod.util.handlers;

import java.util.ArrayList;

import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.ExplodePacket;
import com.sunflow.tutorialmod.util.enums.KeyBindings;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KeyBindingHandler {

	@SubscribeEvent
	public static void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
		if (KeyBindings.EXPLODE.isPressed()) Networking.sendToServer(new ExplodePacket(4711));
		if (KeyBindings.OVERLAY.isPressed()) {
			TutorialModConfig.CONFIG_SHOW_OVERLAY.set(!TutorialModConfig.CONFIG_SHOW_OVERLAY.get());
			TutorialModConfig.CONFIG_SHOW_OVERLAY.save();
		}
	}

	@SubscribeEvent
	public static void handleKeyBindingZoom(FOVUpdateEvent event) {
		if (!(event.getEntity() instanceof PlayerEntity)) return;
		ArrayList<KeyBindings> keyList = KeyBindings.getDownKeys();
		if (!keyList.isEmpty()) for (KeyBindings key : keyList) if (key == KeyBindings.ZOOM) event.setNewfov(event.getNewfov() / 4);
	}
}
