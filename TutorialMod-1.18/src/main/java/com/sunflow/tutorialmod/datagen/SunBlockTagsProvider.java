package com.sunflow.tutorialmod.datagen;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SunBlockTagsProvider extends BlockTagsProvider {

	public SunBlockTagsProvider(DataGenerator generator, ExistingFileHelper fileHelper) { super(generator, TutorialMod.MODID, fileHelper); }

	@Override
	protected void addTags() {
		tag(BlockTags.MINEABLE_WITH_PICKAXE)
				.add(Registration.SUN_ORE_OVERWORLD.block())
				.add(Registration.SUN_ORE_DEEPSLATE.block())
				.add(Registration.SUN_ORE_NETHER.block())
				.add(Registration.SUN_ORE_END.block())
				.add(Registration.RUBY_ORE.block())
				.add(Registration.RUBY_BLOCK.block());
		tag(BlockTags.NEEDS_IRON_TOOL)
				.add(Registration.SUN_ORE_OVERWORLD.block())
				.add(Registration.SUN_ORE_DEEPSLATE.block())
				.add(Registration.SUN_ORE_NETHER.block())
				.add(Registration.SUN_ORE_END.block())
				.add(Registration.RUBY_ORE.block())
				.add(Registration.RUBY_BLOCK.block());
		tag(Tags.Blocks.ORES)
				.add(Registration.SUN_ORE_OVERWORLD.block())
				.add(Registration.SUN_ORE_DEEPSLATE.block())
				.add(Registration.SUN_ORE_NETHER.block())
				.add(Registration.SUN_ORE_END.block())
				.add(Registration.RUBY_ORE.block());
	}

	@Override
	public String getName() { return TutorialMod.MODID + " " + super.getName(); }

}
