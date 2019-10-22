package com.sunflow.tutorialmod.blocks.tree;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.init.ModBlocks;
import com.sunflow.tutorialmod.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class CustomLogBlock extends LogBlock {

	public CustomLogBlock(String name, MaterialColor color) {
		super(color, Block.Properties.create(Material.WOOD, color).hardnessAndResistance(2F).sound(SoundType.WOOD));
		this.setRegistryName(name);

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new BlockItem(this, new Item.Properties().group(TutorialMod.setup.itemGroup)).setRegistryName(name));
	}

}
