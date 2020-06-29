package com.sunflow.tutorialmod.util.enums;

import java.util.ArrayList;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public enum KeyBindings {
	ZOOM("zoom", 70),
	EXPLODE("explode", 71),
	OVERLAY("overlay", 74);

	private final KeyBinding keybinding;

	private KeyBindings(String keyName, int defaultKeyCode) {
		keybinding = new KeyBinding("key." + TutorialMod.MODID + "." + keyName, defaultKeyCode, "key.categories." + TutorialMod.MODID);
	}

	public KeyBinding getKeybind() { return keybinding; }

	public boolean isPressed() { return keybinding.isPressed(); }

	public boolean isDown() { return keybinding.isKeyDown(); }

	public static ArrayList<KeyBindings> getPressedKey() {
		ArrayList<KeyBindings> pressedKeys = new ArrayList<KeyBindings>();
		for (KeyBindings key : KeyBindings.values()) if (key.isPressed()) pressedKeys.add(key);
		return pressedKeys;
	}

	public static ArrayList<KeyBindings> getDownKeys() {
		ArrayList<KeyBindings> downKeys = new ArrayList<KeyBindings>();
		for (KeyBindings key : KeyBindings.values()) if (key.isDown()) downKeys.add(key);
		return downKeys;
	}

	public static void register() {
		for (KeyBindings key : KeyBindings.values()) ClientRegistry.registerKeyBinding(key.getKeybind());
	}
}
