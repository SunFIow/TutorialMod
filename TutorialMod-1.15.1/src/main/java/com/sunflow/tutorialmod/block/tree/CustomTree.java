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
	public ConfiguredFeature<TreeFeatureConfig, ?> func_225546_b_(Random random) {
		return random.nextInt(10) == 0
				? Feature.FANCY_TREE.func_225566_b_(configSmall())
				: Feature.NORMAL_TREE.func_225566_b_(configSmall());
	}

	@Override
	public ConfiguredFeature<HugeTreeFeatureConfig, ?> func_225547_a_(Random random) {
		return Feature.DARK_OAK_TREE.func_225566_b_(configBig());
	}

	public TreeFeatureConfig configSmall() {
		return (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(log), new SimpleBlockStateProvider(leaves), new BlobFoliagePlacer(0, 0))).setSapling(sapling.get()).func_225568_b_();
	}

	public HugeTreeFeatureConfig configBig() {
		return (new HugeTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log), new SimpleBlockStateProvider(leaves))).func_225569_d_(6).setSapling(sapling.get()).func_225568_b_();
	}
}