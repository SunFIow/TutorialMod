package com.sunflow.tutorialmod.data.provider;

import com.sunflow.tutorialmod.init.ModBlocks;
import com.sunflow.tutorialmod.init.ModTags;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

public class ModBlockTagsProvider extends BlockTagsProvider {

	public ModBlockTagsProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerTags() {
		this.getBuilder(ModTags.Blocks.TUTORIAL).add(ModBlocks.FIRSTBLOCK, ModBlocks.RUBY_ORE);
		this.getBuilder(BlockTags.LOGS).add(ModBlocks.COPPER_LOG, ModBlocks.ALUMINIUM_LOG);
		this.getBuilder(BlockTags.LEAVES).add(ModBlocks.COPPER_LEAVES, ModBlocks.ALUMINIUM_LEAVES);
		this.getBuilder(BlockTags.PLANKS).add(ModBlocks.COPPER_PLANKS, ModBlocks.ALUMINIUM_PLANKS);
		this.getBuilder(BlockTags.SAPLINGS).add(ModBlocks.COPPER_SAPLING, ModBlocks.ALUMINIUM_SAPLING);
	}

	@Override
	public String getName() {
		return "TutorialMod " + super.getName();
	}
}
