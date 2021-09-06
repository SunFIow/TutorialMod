package com.sunflow.tutorialmod.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public class DataGenerators {

	public static void gatherData(GatherDataEvent event) {
		if (event.includeClient()) gatherClientData(event, event.getGenerator(), event.getExistingFileHelper());
		if (event.includeServer()) gatherServerData(event, event.getGenerator(), event.getExistingFileHelper());
	}

	private static void gatherClientData(GatherDataEvent event, DataGenerator generator, ExistingFileHelper fileHelper) {
		generator.addProvider(new BlockStatesProvider(generator, fileHelper));
		generator.addProvider(new ItemsProvider(generator, fileHelper));

	}

	private static void gatherServerData(GatherDataEvent event, DataGenerator generator, ExistingFileHelper fileHelper) {
		generator.addProvider(new RecipesProvider(generator));
		generator.addProvider(new LootTabelsProvider(generator));
		generator.addProvider(new TagsProvider(generator, fileHelper));
	}
}
