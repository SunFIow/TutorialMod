package com.sunflow.tutorialmod.util.handlers;

import com.sunflow.tutorialmod.setup.ModDimensions;
import com.sunflow.tutorialmod.util.Log;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CommonForgeEventHandlers {
	@SubscribeEvent
	public static void onDimensionRegistry(RegisterDimensionsEvent event) {
		Log.info("-registerDimensions-");

		ModDimensions.DIMENSIONS.forEach((dim) -> dim.setDimensionType(DimensionManager.registerOrGetDimension(dim.getRegistryName(), dim, dim.getData(), dim.hasSkyLight())));
	}
}