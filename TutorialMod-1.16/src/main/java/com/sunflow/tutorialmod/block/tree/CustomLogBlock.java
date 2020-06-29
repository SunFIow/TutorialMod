package com.sunflow.tutorialmod.block.tree;

import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.MaterialColor;

public class CustomLogBlock extends RotatedPillarBlock {

//	public CustomLogBlock(MaterialColor color) {
//		super(Block.Properties.create(Material.WOOD, color)
//				.hardnessAndResistance(2F)
//				.sound(SoundType.WOOD));
//	}

	public CustomLogBlock(MaterialColor color, Properties properties) {
		super(properties);
	}

}
