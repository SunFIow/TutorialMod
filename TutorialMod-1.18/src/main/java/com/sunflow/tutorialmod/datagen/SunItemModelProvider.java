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

public class SunItemModelProvider extends ItemModelProvider {

	public SunItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) { super(generator, TutorialMod.MODID, existingFileHelper); }

	@Override
	protected void registerModels() {
		registerBlockItem(Registration.SUN_ORE_OVERWORLD.block());
		registerBlockItem(Registration.SUN_ORE_DEEPSLATE.block());
		registerBlockItem(Registration.SUN_ORE_NETHER.block());
		registerBlockItem(Registration.SUN_ORE_END.block());
	}

	private void registerSingleTexture(Item item) {
		String name = item.getRegistryName().getPath();
		singleTexture(name, mcLoc("item/handheld"), "layer0", modLoc("item/" + name));
	}

	private void registerBlockItem(Block block) {
		String name = block.getRegistryName().getPath();
		withExistingParent(name, modLoc("block/" + name));
	}

	@Override
	public String getName() { return TutorialMod.MODID + " " + super.getName(); }
}
