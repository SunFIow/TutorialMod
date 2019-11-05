package com.sunflow.tutorialmod.util.handlers;

import java.util.ArrayList;

import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.ExplodePacket;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.enums.KeyBindings;

import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KeyBindingHandler {

	public static void setup() {
		Log.debug("I am going to register the keybindings now senpai.");
		KeyBindings.register();
	}

//	public KeyBindingHandler() {
//		TutorialMod.LOGGER.info("I am going to register the keybindings now senpai.");
//		KeyBindings.register();
//	}

	@SubscribeEvent
	public static void handleKeyInputEvent(KeyInputEvent event) {
		ArrayList<KeyBindings> keyList = KeyBindings.getPressedKey();
		if (!keyList.isEmpty()) {
			for (KeyBindings key : keyList) {
				switch (key) {
					case EXPLODE:
						Log.info("Boom!");
						Networking.sendToServer(new ExplodePacket(4711));
						break;
				}
			}
		}
	}
}
