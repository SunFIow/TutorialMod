package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags.IOptionalNamedTag;

public class ModTags {
	public static class Items {
		public static final IOptionalNamedTag<Item> TUTORIAL = makeTag(TutorialMod.MODID, "tutorial");

		private static IOptionalNamedTag<Item> makeTag(String modid, String name) { return ItemTags.createOptional(new ResourceLocation(modid, name)); }
	}

	public static class Blocks {
		public static final IOptionalNamedTag<Block> TUTORIAL = makeTag(TutorialMod.MODID, "tutorial");

		private static IOptionalNamedTag<Block> makeTag(String modid, String name) { return BlockTags.createOptional(new ResourceLocation(modid, name)); }
	}
}
