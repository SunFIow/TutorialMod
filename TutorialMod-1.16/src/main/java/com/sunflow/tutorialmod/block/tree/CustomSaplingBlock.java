package com.sunflow.tutorialmod.block.tree;

import com.google.common.base.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.IPlantable;

public class CustomSaplingBlock extends SaplingBlock {

//	public CustomSaplingBlock() {
//		super(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid());
//	}

	public CustomSaplingBlock(Block leaves, Block log, Supplier<IPlantable> sapling) {
		super(new CustomTree(log, leaves, sapling),
				Properties.create(Material.PLANTS)
						.doesNotBlockMovement()
						.tickRandomly()
						.zeroHardnessAndResistance()
						.sound(SoundType.PLANT));
	}
}
