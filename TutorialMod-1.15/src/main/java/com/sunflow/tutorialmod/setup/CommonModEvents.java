package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.capability.CapabilityItemEnergy;
import com.sunflow.tutorialmod.capability.CapabilityProcessor;
import com.sunflow.tutorialmod.network.Networking;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonModEvents {

	public static void setup(final FMLCommonSetupEvent event) {
		CapabilityProcessor.register();
		CapabilityItemEnergy.register();
		Networking.registerMessages();
	}
}
