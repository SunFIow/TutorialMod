package com.sunflow.tutorialmod.setup.registration;

import com.sunflow.tutorialmod.network.Networking;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonSetup {
	@SubscribeEvent
	public static void setup(FMLCommonSetupEvent event) {
		Networking.registerMessages();
	}
}
