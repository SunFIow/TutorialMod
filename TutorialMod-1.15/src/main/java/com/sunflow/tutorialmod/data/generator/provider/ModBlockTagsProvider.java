package com.sunflow.tutorialmod.data.generator.provider;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.ModTags;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

public class ModBlockTagsProvider extends BlockTagsProvider {

	public ModBlockTagsProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerTags() {
		this.getBuilder(ModTags.Blocks.TUTORIAL).add(Registration.GLOWSTONE_GENERATOR_BLOCK.get(), Registration.RUBY_ORE.get());
		this.getBuilder(BlockTags.LOGS).add(Registration.COPPER_LOG.get(), Registration.ALUMINIUM_LOG.get());
		this.getBuilder(BlockTags.LEAVES).add(Registration.COPPER_LEAVES.get(), Registration.ALUMINIUM_LEAVES.get());
		this.getBuilder(BlockTags.PLANKS).add(Registration.COPPER_PLANKS.get(), Registration.ALUMINIUM_PLANKS.get());
		this.getBuilder(BlockTags.SAPLINGS).add(Registration.COPPER_SAPLING.get(), Registration.ALUMINIUM_SAPLING.get());
	}

	@Override
	public String getName() { return TutorialMod.NAME + " " + super.getName(); }
}
