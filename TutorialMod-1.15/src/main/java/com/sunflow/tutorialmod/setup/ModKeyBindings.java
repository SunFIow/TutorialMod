package com.sunflow.tutorialmod.setup;

import java.util.ArrayList;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public enum ModKeyBindings {
	ZOOM("zoom", 70),
	EXPLODE("explode", 71),
	OVERLAY("overlay", 74);

	private final KeyBinding keybinding;

	private ModKeyBindings(String keyName, int defaultKeyCode) {
		keybinding = new KeyBinding("key." + TutorialMod.MODID + "." + keyName, defaultKeyCode, "key.categories." + TutorialMod.MODID);
	}

	public KeyBinding getKeybind() { return keybinding; }

	public boolean isPressed() { return keybinding.isPressed(); }

	public boolean isDown() { return keybinding.isKeyDown(); }

	public static ArrayList<ModKeyBindings> getPressedKey() {
		ArrayList<ModKeyBindings> pressedKeys = new ArrayList<ModKeyBindings>();
		for (ModKeyBindings key : ModKeyBindings.values()) if (key.isPressed()) pressedKeys.add(key);
		return pressedKeys;
	}

	public static ArrayList<ModKeyBindings> getDownKeys() {
		ArrayList<ModKeyBindings> downKeys = new ArrayList<ModKeyBindings>();
		for (ModKeyBindings key : ModKeyBindings.values()) if (key.isDown()) downKeys.add(key);
		return downKeys;
	}

	public static void register() {
		for (ModKeyBindings key : ModKeyBindings.values()) ClientRegistry.registerKeyBinding(key.getKeybind());
	}
}
