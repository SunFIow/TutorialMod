package com.sunflow.tutorialmod.data.generator;

import com.sunflow.tutorialmod.data.generator.provider.ModBlockStatesProvider;
import com.sunflow.tutorialmod.data.generator.provider.ModBlockTagsProvider;
import com.sunflow.tutorialmod.data.generator.provider.ModItemTagsProvider;
import com.sunflow.tutorialmod.data.generator.provider.ModLootTabelsProvider;
import com.sunflow.tutorialmod.data.generator.provider.ModRecipesProvider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

//@Mod.EventBusSubscriber(modid = TutorialMod.MODID, bus = Bus.MOD)
//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
//	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		if (event.includeServer()) {
			generator.addProvider(new ModBlockTagsProvider(generator));
			generator.addProvider(new ModItemTagsProvider(generator));
			generator.addProvider(new ModRecipesProvider(generator));
			generator.addProvider(new ModLootTabelsProvider(generator));
		}
		if (event.includeClient()) {
			generator.addProvider(new ModBlockStatesProvider(generator, event.getExistingFileHelper()));
		}
	}
}
