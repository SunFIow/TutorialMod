package com.sunflow.tutorialmod.data.generator.provider;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.ModTags;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ModItemTagsProvider extends ItemTagsProvider {

	public ModItemTagsProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerTags() {
		this.copy(ModTags.Blocks.TUTORIAL, ModTags.Items.TUTORIAL);
		this.getBuilder(ModTags.Items.TUTORIAL).add(Registration.FIRSTITEM.get());
		for (RegistryObject<Item> item : Registration.SKIN) this.getBuilder(ModTags.Items.TUTORIAL).add(item.get());

	}

	@Override
	public String getName() { return TutorialMod.NAME + " " + super.getName(); }

}
