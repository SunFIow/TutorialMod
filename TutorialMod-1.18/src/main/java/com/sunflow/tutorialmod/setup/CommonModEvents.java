package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.network.Networking;

import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonModEvents {

	public static void setup(final FMLCommonSetupEvent event) {
		Networking.registerMessages();
		// event.enqueueWork(() -> {
		// GlobalEntityTypeAttributes.put(Registration.CENTAUR.get(), CentaurEntity.prepareAttributes().create());
		// GlobalEntityTypeAttributes.put(Registration.WEIRDMOB.get(), WeirdMobEntity.prepareAttributes().create());
		// });
	}

	public static void addAttributes(EntityAttributeModificationEvent event) {
		// event.add(Registration.CENTAUR.get(), CentaurEntity.prepareAttributes().create());
		// event.add(Registration.WEIRDMOB.get(), WeirdMobEntity.prepareAttributes().create());
	}
}
