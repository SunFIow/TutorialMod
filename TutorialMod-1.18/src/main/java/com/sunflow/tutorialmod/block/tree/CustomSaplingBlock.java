package com.sunflow.tutorialmod.block.tree;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class CustomSaplingBlock extends SaplingBlock {

	public CustomSaplingBlock(Block leaves, Block log) {
		super(new CustomTree(log, leaves),
				Properties.of(Material.PLANT)
						.noCollission()
						.randomTicks()
						.instabreak()
						.sound(SoundType.GRASS));
	}
}
