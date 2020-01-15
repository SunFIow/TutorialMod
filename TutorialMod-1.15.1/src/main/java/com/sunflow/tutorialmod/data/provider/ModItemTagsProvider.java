package com.sunflow.tutorialmod.data.provider;

import com.sunflow.tutorialmod.setup.ModItems;
import com.sunflow.tutorialmod.setup.ModTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.ItemTags;

public class ModItemTagsProvider extends ItemTagsProvider {

	public ModItemTagsProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerTags() {
		this.copy(ModTags.Blocks.TUTORIAL, ModTags.Items.TUTORIAL);
		this.getBuilder(ModTags.Items.TUTORIAL).add(ModItems.FIRSTITEM);
		this.getBuilder(ModTags.Items.TUTORIAL).add(ModItems.SKIN);
		this.getBuilder(ItemTags.ARROWS).add(ModItems.CARROW);
	}

	@Override
	public String getName() { return "TutorialMod " + super.getName(); }

}
