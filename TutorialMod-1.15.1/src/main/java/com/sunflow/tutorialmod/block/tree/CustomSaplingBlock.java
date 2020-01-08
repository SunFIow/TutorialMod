package com.sunflow.tutorialmod.block.tree;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.ModBlocks;
import com.sunflow.tutorialmod.setup.ModItems;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.block.Block;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.Tree;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.IPlantable;

public class CustomSaplingBlock extends SaplingBlock {

	public CustomSaplingBlock(String name, Block leaves, Block log, IPlantable sapling) {
		super(getTree(log, leaves, sapling), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT));
		this.setRegistryName(name);

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new BlockItem(this, new Item.Properties().group(TutorialMod.groups.itemGroup)).setRegistryName(name));
	}

	private static Tree getTree(Block log, Block leaves, IPlantable sapling) {
		Log.warn(log);
		Log.warn(leaves);
		Log.warn(sapling);
		Tree tree = new CustomTree(log, leaves, sapling);
		Log.warn(tree);
		return tree;
	}

}
