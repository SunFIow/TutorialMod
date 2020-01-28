package com.sunflow.tutorialmod.util.handlers;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.ModCommands;
import com.sunflow.tutorialmod.setup.registration.Registration;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.MyWorldData;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid = TutorialMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEventHandlers {
	@SubscribeEvent
	public static void serverStarting(FMLServerStartingEvent event) {
		Log.info("Preparing the server for u senpai.");

		Registration.COPPER.get().register();
		ModCommands.register(event.getCommandDispatcher());
		TutorialMod.data = event.getServer().getWorld(DimensionType.OVERWORLD).getSavedData().getOrCreate(MyWorldData::new, MyWorldData.ID_GENERAL);
	}

	@SubscribeEvent
	public static void onDimensionRegistry(RegisterDimensionsEvent event) {
		Log.info("-registerDimensions-");

		Registration.BADLANDS_TYPE = DimensionManager.registerOrGetDimension(new ResourceLocation(TutorialMod.MODID, "badlands"), Registration.BADLANDS.get(), null, true);
	}
}