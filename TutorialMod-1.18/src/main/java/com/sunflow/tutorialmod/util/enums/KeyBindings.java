package com.sunflow.tutorialmod.util.enums;

import java.util.ArrayList;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

public enum KeyBindings {
	ZOOM("zoom", 70),
	EXPLODE("explode", 71),
	OVERLAY("overlay", 74);

	private final KeyMapping keybinding;

	private KeyBindings(String keyName, int defaultKeyCode) {
		keybinding = new KeyMapping("key." + TutorialMod.MODID + "." + keyName, defaultKeyCode, "key.categories." + TutorialMod.MODID);
	}

	public KeyMapping getKeybind() { return keybinding; }

	public boolean consumeClick() { return keybinding.consumeClick(); }

	public boolean isDown() { return keybinding.isDown(); }

	public static void register() {
		for (KeyBindings key : KeyBindings.values()) ClientRegistry.registerKeyBinding(key.getKeybind());
	}
}
