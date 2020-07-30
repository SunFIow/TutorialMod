package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.data.MyWorldData;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class CommonForgeEvents {

	public static void serverStarting(FMLServerStartingEvent event) {
		Log.info("Preparing the server for u senpai.");

//		Registration.COPPER_BIOME.get().register();
		ModCommands.register(event.getCommandDispatcher());
		TutorialMod.data = event.getServer().getWorld(DimensionType.OVERWORLD).getSavedData().getOrCreate(MyWorldData::new, MyWorldData.ID_GENERAL);
	}

	public static void onDimensionRegistry(RegisterDimensionsEvent event) {
		Log.debug("Registering the dimensions for u senpai.");

		Registration.BADLANDS_TYPE = DimensionManager.registerOrGetDimension(Registration.BADLANDS_ID, Registration.BADLANDS.get(), null, true);
	}
}