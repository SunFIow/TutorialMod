package com.sunflow.tutorialmod.block.tree;

import com.sunflow.tutorialmod.setup.ModBlocks;
import com.sunflow.tutorialmod.setup.ModGroups;
import com.sunflow.tutorialmod.setup.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class CustomLeavesBlock extends LeavesBlock {

	public CustomLeavesBlock(String name) {
		super(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).func_226896_b_());
		this.setRegistryName(name);

		RenderTypeLookup.setRenderLayer(this, RenderType.func_228643_e_());

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new BlockItem(this, new Item.Properties().group(ModGroups.itemGroup)).setRegistryName(name));
	}
}
