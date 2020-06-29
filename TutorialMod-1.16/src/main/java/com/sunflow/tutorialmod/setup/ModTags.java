package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class ModTags {
	public static class Items {
		public static final ITag<Item> TUTORIAL = makeTag(TutorialMod.MODID, "tutorial");

		private static ITag<Item> makeTag(String modid, String name) { return ItemTags.makeWrapperTag(modid + ":" + name); }
	}

	public static class Blocks {
		public static final ITag<Block> TUTORIAL = makeTag(TutorialMod.MODID, "tutorial");

		private static ITag<Block> makeTag(String modid, String name) { return BlockTags.makeWrapperTag(modid + ":" + name); }
	}
}
