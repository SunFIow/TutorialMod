package com.sunflow.tutorialmod.block.tree;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.material.MaterialColor;

public class CustomLogBlock extends RotatedPillarBlock {

	public CustomLogBlock(MaterialColor color, Properties properties) {
		super(properties.color(color));
	}

}
