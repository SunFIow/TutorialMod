package com.sunflow.tutorialmod.blocks.tree;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.init.ModBlocks;
import com.sunflow.tutorialmod.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class CustomSaplingBlock extends SaplingBlock {

	public CustomSaplingBlock(String name, Block leaves, Block log, Block sapling) {
		super(new CustomTree(leaves, log, sapling), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT));
		this.setRegistryName(name);

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new BlockItem(this, new Item.Properties().group(TutorialMod.groups.itemGroup)).setRegistryName(name));
	}

}
