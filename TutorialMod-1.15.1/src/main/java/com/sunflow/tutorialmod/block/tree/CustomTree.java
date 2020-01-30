package com.sunflow.tutorialmod.block.tree;

import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraftforge.common.IPlantable;

public class CustomTree extends BigTree {

	private final BlockState leaves;
	private final BlockState log;
	private final Supplier<IPlantable> sapling;

	public CustomTree(Block log, Block leaves, Supplier<IPlantable> sapling) {
		this.log = log.getDefaultState();
		this.leaves = leaves.getDefaultState();
		this.sapling = sapling;
	}

	@Override
	public ConfiguredFeature<TreeFeatureConfig, ?> func_225546_b_(Random random, boolean p_225546_2_) {
		return random.nextInt(10) == 0
				? Feature.FANCY_TREE.withConfiguration(configSmall())
				: Feature.NORMAL_TREE.withConfiguration(configSmall());
	}

	@Override
	public ConfiguredFeature<HugeTreeFeatureConfig, ?> func_225547_a_(Random random) {
		return Feature.DARK_OAK_TREE.withConfiguration(configBig());
	}

	public TreeFeatureConfig configSmall() {
		return (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(log), new SimpleBlockStateProvider(leaves), new BlobFoliagePlacer(0, 0))).setSapling(sapling.get()).build();
	}

	public HugeTreeFeatureConfig configBig() {
		return (new HugeTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log), new SimpleBlockStateProvider(leaves))).baseHeight(6).setSapling(sapling.get()).build();
	}
}