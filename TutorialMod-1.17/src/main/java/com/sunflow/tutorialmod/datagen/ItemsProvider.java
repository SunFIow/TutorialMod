package com.sunflow.tutorialmod.datagen;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.ClientSetup;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemsProvider extends ItemModelProvider {

	public ItemsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) { super(generator, TutorialMod.MODID, existingFileHelper); }

	@Override
	protected void registerModels() {
//		registerSingleTexture(Registration.TUTORIAL_ITEM.get());
		getBuilder(Registration.TUTORIAL_ITEM.get().getRegistryName().getPath())
				.parent(getExistingFile(mcLoc("item/handheld")))
				.texture("layer0", "item/tutorial_item")
				.override().predicate(ClientSetup.DISTANCE_PROPERTY, 0).model(createTutorialModel(0)).end()
				.override().predicate(ClientSetup.DISTANCE_PROPERTY, 1).model(createTutorialModel(1)).end()
				.override().predicate(ClientSetup.DISTANCE_PROPERTY, 2).model(createTutorialModel(2)).end()
				.override().predicate(ClientSetup.DISTANCE_PROPERTY, 3).model(createTutorialModel(3)).end();

		registerBlockItem(Registration.GENERATOR_BLOCK.get());
		registerBlockItem(Registration.POWERUSER_BLOCK.get());
	}

	private void registerSingleTexture(Item item) {
		String name = item.getRegistryName().getPath();
		singleTexture(name, mcLoc("item/handheld"), "layer0", modLoc("item/" + name));
	}

	private ItemModelBuilder createTutorialModel(int index) {
		return getBuilder("tutorial_item" + index)
				.parent(getExistingFile(mcLoc("item/handheld")))
				.texture("layer0", "item/tutorial_item" + index);
	}

	private void registerBlockItem(Block block) {
		String name = block.getRegistryName().getPath();
		withExistingParent(name, new ResourceLocation(TutorialMod.MODID, "block/" + name));

	}

	@Override
	public String getName() {
		return TutorialMod.MODID + " " + super.getName();
	}
}
