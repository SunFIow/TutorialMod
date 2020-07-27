package com.sunflow.tutorialmod.block.tree;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.IPlantable;

public class CustomSaplingBlock extends SaplingBlock {

	public CustomSaplingBlock(Block log, Block leaves, Supplier<IPlantable> sapling) {
		super(new CustomTree(log, leaves, sapling), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT));
	}
}
