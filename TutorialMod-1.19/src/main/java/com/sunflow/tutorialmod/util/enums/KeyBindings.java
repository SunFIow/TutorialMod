package com.sunflow.tutorialmod.util.enums;

import java.util.ArrayList;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

public enum KeyBindings {
	ZOOM("zoom", 70, "Zoom"),
	EXPLODE("explode", 71, "Create an Explosion"),
	OVERLAY("overlay", 74, "Toggle Overlay"),
	NOARMOR("noarmor", 75, "Toggle Armor");

	public static final String CATERGORY = "key.categories." + TutorialMod.MODID;
	private final KeyMapping keybinding;
	private final String translationName;

	private KeyBindings(String keyName, int defaultKeyCode, String translationName) {
		keybinding = new KeyMapping("key." + TutorialMod.MODID + "." + keyName, defaultKeyCode, CATERGORY);
		this.translationName = translationName;
	}

	public String getName() { return keybinding.getName(); }

	public String getTranslation() { return translationName; }

	public boolean consumeClick() { return keybinding.consumeClick(); }

	public boolean isDown() { return keybinding.isDown(); }

	public static void register() {
		for (KeyBindings key : KeyBindings.values()) {
			ClientRegistry.registerKeyBinding(key.keybinding);
		}
	}
}
