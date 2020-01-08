package com.sunflow.tutorialmod.setup;

import java.util.ArrayList;
import java.util.List;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ModSounds {
	public static final List<SoundEvent> SOUNDS = new ArrayList<>();

	public static SoundEvent ENTITY_CENTAUR_AMBIENT = makeSound("entity.centaur.ambient");
	public static SoundEvent ENTITY_CENTAUR_HURT = makeSound("entity.centaur.hurt");
	public static SoundEvent ENTITY_CENTAUR_DEATH = makeSound("entity.centaur.death");

	private static SoundEvent makeSound(String name) {
		ResourceLocation location = new ResourceLocation(TutorialMod.MODID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		SOUNDS.add(event);
		return event;
	}
}
