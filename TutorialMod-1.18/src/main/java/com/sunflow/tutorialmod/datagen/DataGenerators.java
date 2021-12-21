package com.sunflow.tutorialmod.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public class DataGenerators {

	public static void gatherData(GatherDataEvent event) {
		if (event.includeServer()) gatherServerData(event, event.getGenerator(), event.getExistingFileHelper());
		if (event.includeClient()) gatherClientData(event, event.getGenerator(), event.getExistingFileHelper());
	}

	private static void gatherServerData(GatherDataEvent event, DataGenerator generator, ExistingFileHelper fileHelper) {
		generator.addProvider(new SunRecipesProvider(generator));
		generator.addProvider(new SunLootTabelsProvider(generator));
		BlockTagsProvider blockTags = new SunBlockTagsProvider(generator, fileHelper);
		generator.addProvider(blockTags);
		generator.addProvider(new SunItemTagsProvider(generator, blockTags, fileHelper));
	}

	private static void gatherClientData(GatherDataEvent event, DataGenerator generator, ExistingFileHelper fileHelper) {
		generator.addProvider(new SunBlockStatesProvider(generator, fileHelper));
		generator.addProvider(new SunItemModelProvider(generator, fileHelper));
		generator.addProvider(new SunLanguageProvider(generator, "en_us"));
	}
}
