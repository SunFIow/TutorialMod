package com.sunflow.tutorialmod.util.handlers;

import java.util.ArrayList;

import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.ExplodePacket;
import com.sunflow.tutorialmod.setup.ModKeyBindings;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KeyBindingHandler {

	@SubscribeEvent
	public static void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
		if (ModKeyBindings.EXPLODE.isPressed()) Networking.sendToServer(new ExplodePacket(4711));
		if (ModKeyBindings.OVERLAY.isPressed()) {
			TutorialModConfig.CLIENT.showOverlay.set(!TutorialModConfig.CLIENT.showOverlay.get());
			TutorialModConfig.CLIENT.showOverlay.save();
		}
	}

	@SubscribeEvent
	public static void handleKeyBindingZoom(FOVUpdateEvent event) {
		if (!(event.getEntity() instanceof PlayerEntity)) return;
		ArrayList<ModKeyBindings> keyList = ModKeyBindings.getDownKeys();
		if (!keyList.isEmpty()) for (ModKeyBindings key : keyList) if (key == ModKeyBindings.ZOOM) event.setNewfov(event.getNewfov() / 4);
	}
}
