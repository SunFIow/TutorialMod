package com.sunflow.tutorialmod.block.tree;

import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class CustomLeavesBlock extends LeavesBlock {

	public CustomLeavesBlock() {
		super(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid());
	}
}
