package com.sunflow.tutorialmod.blocks.tree;

import com.sunflow.tutorialmod.blocks.base.BlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class CustomPlanksBlock extends BlockBase {

	public CustomPlanksBlock(String name, MaterialColor color) {
		super(name, Block.Properties.create(Material.WOOD, color).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
	}

}
