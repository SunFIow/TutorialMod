package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.MyWorldData;

import net.minecraft.world.level.Level;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;

public class CommonForgeEvents {

	public static void serverStarting(ServerStartingEvent event) {
		TutorialMod.LOGGER.info("Preparing the server for u senpai.");

		// Registration.COPPER.get().register();
		TutorialMod.data = event.getServer().getLevel(Level.OVERWORLD).getDataStorage()
				.computeIfAbsent(MyWorldData::new, MyWorldData::new, MyWorldData.ID_GENERAL);
	}

	public static void onCommandsRegister(RegisterCommandsEvent event) {
		ModCommands.register(event.getDispatcher());
	}

	// public static void onDimensionRegistry(RegisterDimensionsEvent event) {
	// Log.debug("Registering the dimensions for u senpai.");
	//
	// net.minecraft.world.DimensionType.field_235999_c_;
	// Registration.BADLANDS_TYPE = DimensionManager.registerOrGetDimension(Registration.BADLANDS_ID, Registration.BADLANDS.get(), null, true);
	// }

}