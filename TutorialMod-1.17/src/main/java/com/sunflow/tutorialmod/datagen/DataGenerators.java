package com.sunflow.tutorialmod.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public class DataGenerators {

	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		if (event.includeServer()) {
			generator.addProvider(new RecipesProvider(generator));
		}
		if (event.includeClient()) {
			generator.addProvider(new ItemsProvider(generator, event.getExistingFileHelper()));
		}
	}
}
