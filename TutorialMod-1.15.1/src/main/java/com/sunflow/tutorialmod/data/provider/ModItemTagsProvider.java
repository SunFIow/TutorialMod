package com.sunflow.tutorialmod.data.provider;

import com.sunflow.tutorialmod.setup.ModItems;
import com.sunflow.tutorialmod.setup.ModTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;

public class ModItemTagsProvider extends ItemTagsProvider {

	public ModItemTagsProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerTags() {
		this.copy(ModTags.Blocks.TUTORIAL, ModTags.Items.TUTORIAL);
		this.getBuilder(ModTags.Items.TUTORIAL).add(ModItems.FIRSTITEM);
		this.getBuilder(ModTags.Items.TUTORIAL).add(ModItems.SKIN);
	}

	@Override
	public String getName() {
		return "TutorialMod " + super.getName();
	}

}
