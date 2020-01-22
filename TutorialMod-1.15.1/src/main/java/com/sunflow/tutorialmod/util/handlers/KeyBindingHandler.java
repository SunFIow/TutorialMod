package com.sunflow.tutorialmod.util.handlers;

import java.util.ArrayList;

import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.ExplodePacket;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.config.Config;
import com.sunflow.tutorialmod.util.enums.KeyBindings;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KeyBindingHandler {

	public static void setup() {
		Log.debug("I am going to register the keybindings now senpai.");
		KeyBindings.register();
	}

	@SubscribeEvent
	public static void handleKeyInputEvent(KeyInputEvent event) {
		if (KeyBindings.EXPLODE.isPressed()) Networking.sendToServer(new ExplodePacket(4711));
		if (KeyBindings.OVERLAY.isPressed()) {
			Config.CONFIG_SHOW_OVERLAY.set(!Config.CONFIG_SHOW_OVERLAY.get());
			Config.CONFIG_SHOW_OVERLAY.save();
		}
	}

	@SubscribeEvent
	public static void handleKeyBindingZoom(FOVUpdateEvent event) {
		if (!(event.getEntity() instanceof PlayerEntity)) return;
		ArrayList<KeyBindings> keyList = KeyBindings.getDownKeys();
		if (!keyList.isEmpty()) for (KeyBindings key : keyList) if (key == KeyBindings.ZOOM) event.setNewfov(event.getNewfov() / 4);
	}
}
