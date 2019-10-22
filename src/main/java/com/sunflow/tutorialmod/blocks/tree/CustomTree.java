package com.sunflow.tutorialmod.blocks.tree;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;

public class CustomTree extends Tree {
	private final BlockState leaves;
	private final BlockState log;
	private final SaplingBlock sapling;

	public CustomTree(Block leaves, Block log, Block sapling) {
		this.leaves = leaves.getDefaultState();
		this.log = log.getDefaultState();
		this.sapling = (SaplingBlock) sapling;
	}

	@Override
	@Nullable
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		return random.nextInt(10) == 0
				? new CustomBigTreeFeature(NoFeatureConfig::deserialize, true, log, leaves).setSapling(sapling)
				: new TreeFeature(NoFeatureConfig::deserialize, true, 4 + random.nextInt(7), log, leaves, true).setSapling(sapling);
	}
}