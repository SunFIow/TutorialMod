package com.sunflow.tutorialmod.block.tree;

import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class CustomLogBlock extends LogBlock {

	public CustomLogBlock(MaterialColor color) {
		super(color, Block.Properties.create(Material.WOOD, color).hardnessAndResistance(2F).sound(SoundType.WOOD));
	}

}
