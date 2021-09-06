package com.sunflow.tutorialmod.datagen;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TagsProvider extends BlockTagsProvider {

	public TagsProvider(DataGenerator generator, ExistingFileHelper fileHelper) { super(generator, TutorialMod.MODID, fileHelper); }

	@Override
	protected void addTags() {
		tag(BlockTags.MINEABLE_WITH_PICKAXE)
				.add(Registration.GENERATOR_BLOCK.get());
		tag(BlockTags.NEEDS_IRON_TOOL)
				.add(Registration.GENERATOR_BLOCK.get());
	}

	@Override
	public String getName() {
		return TutorialMod.MODID + " " + super.getName();
	}

}
