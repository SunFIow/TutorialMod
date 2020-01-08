package com.sunflow.tutorialmod.block.tree;

import java.util.Random;

import com.sunflow.tutorialmod.world.gen.feature.tree.CustomBigTreeFeature;
import com.sunflow.tutorialmod.world.gen.feature.tree.CustomTreeFeature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.IPlantable;

public class CustomTree extends Tree {
	private final BlockState leaves;
	private final BlockState log;
	private final IPlantable sapling;

	public CustomTree(Block log, Block leaves, IPlantable sapling) {
		this.log = log.getDefaultState();
		this.leaves = leaves.getDefaultState();
		this.sapling = sapling;
	}

	@Override
	public AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		return random.nextInt(10) == 0
				? new CustomBigTreeFeature(NoFeatureConfig::deserialize, true, log, leaves).setSapling(sapling)
//				: new TreeFeature(NoFeatureConfig::deserialize, true, 4 + random.nextInt(7), log, leaves, true).setSapling(sapling);
				: new CustomTreeFeature(NoFeatureConfig::deserialize, true, true, 4, random.nextInt(7), sapling, log, leaves);
	}
}