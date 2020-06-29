package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.MyWorldData;

import net.minecraft.world.World;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class CommonForgeEvents {

	public static void serverStarting(FMLServerStartingEvent event) {
		Log.info("Preparing the server for u senpai.");

//		Registration.COPPER.get().register();
		ModCommands.register(event.getCommandDispatcher());
		TutorialMod.data = event.getServer().getWorld(World.field_234918_g_).getSavedData().getOrCreate(MyWorldData::new, MyWorldData.ID_GENERAL);
	}

//	public static void onDimensionRegistry(RegisterDimensionsEvent event) {
//		Log.debug("Registering the dimensions for u senpai.");
//
//		net.minecraft.world.DimensionType.field_235999_c_;
//		Registration.BADLANDS_TYPE = DimensionManager.registerOrGetDimension(Registration.BADLANDS_ID, Registration.BADLANDS.get(), null, true);
//	}
}