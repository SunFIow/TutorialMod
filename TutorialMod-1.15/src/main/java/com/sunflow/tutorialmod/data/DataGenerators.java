package com.sunflow.tutorialmod.data;

import com.sunflow.tutorialmod.data.provider.ModBlockTagsProvider;
import com.sunflow.tutorialmod.data.provider.ModItemTagsProvider;
import com.sunflow.tutorialmod.data.provider.ModLootTabelProvider;
import com.sunflow.tutorialmod.data.provider.ModRecipesProvider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

//@Mod.EventBusSubscriber(modid = TutorialMod.MODID, bus = Bus.MOD)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		final DataGenerator generator = event.getGenerator();
		if (event.includeServer()) {
			generator.addProvider(new ModBlockTagsProvider(generator));
			generator.addProvider(new ModItemTagsProvider(generator));
			generator.addProvider(new ModRecipesProvider(generator));
			generator.addProvider(new ModLootTabelProvider(generator));
		}
	}
}
