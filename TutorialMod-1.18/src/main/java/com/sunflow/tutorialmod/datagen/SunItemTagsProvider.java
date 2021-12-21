package com.sunflow.tutorialmod.datagen;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SunItemTagsProvider extends ItemTagsProvider {

	public SunItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper fileHelper) { super(generator, blockTags, TutorialMod.MODID, fileHelper); }

	@Override
	protected void addTags() {
		tag(Tags.Items.ORES)
				.add(Registration.SUN_ORE_OVERWORLD.item())
				.add(Registration.SUN_ORE_DEEPSLATE.item())
				.add(Registration.SUN_ORE_NETHER.item())
				.add(Registration.SUN_ORE_END.item());
	}

	@Override
	public String getName() { return TutorialMod.MODID + " " + super.getName(); }

}
