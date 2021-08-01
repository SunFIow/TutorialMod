package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.entity.centaur.CentaurEntity;
import com.sunflow.tutorialmod.entity.weirdmob.WeirdMobEntity;
import com.sunflow.tutorialmod.network.Networking;

import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonModEvents {

	public static void setup(final FMLCommonSetupEvent event) {
		Networking.registerMessages();
		event.enqueueWork(() -> {
			GlobalEntityTypeAttributes.put(Registration.CENTAUR.get(), CentaurEntity.prepareAttributes().create());
			GlobalEntityTypeAttributes.put(Registration.WEIRDMOB.get(), WeirdMobEntity.prepareAttributes().create());
		});
	}
}
