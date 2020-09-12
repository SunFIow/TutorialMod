package com.sunflow.tutorialmod.block.tree;

import java.util.Random;
import java.util.function.Supplier;

import com.sunflow.tutorialmod.util.Log;

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

	private TreeFeatureConfig smallConfig;
	private HugeTreeFeatureConfig bigConfig;

	public CustomTree(Block log, Block leaves, Supplier<IPlantable> sapling) {
		this.log = log.getDefaultState();
		this.leaves = leaves.getDefaultState();
		this.sapling = sapling;
	}

	@Override
	public ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean p_225546_2_) {
		return random.nextInt(10) == 0
				? Feature.FANCY_TREE.withConfiguration(configSmall())
				: Feature.NORMAL_TREE.withConfiguration(configSmall());
	}

	@Override
	public ConfiguredFeature<HugeTreeFeatureConfig, ?> getHugeTreeFeature(Random random) {
		return Feature.DARK_OAK_TREE.withConfiguration(configBig());
	}

	public TreeFeatureConfig configSmall() {
		Log.info("configSmall");
		return smallConfig != null
				? smallConfig
				: (smallConfig = (new TreeFeatureConfig.Builder(
						new SimpleBlockStateProvider(log),
						new SimpleBlockStateProvider(leaves),
						new BlobFoliagePlacer(2, 1)))
								.baseHeight(5)
								.heightRandA(2)
//										 .trunkTopOffset(1)
								.foliageHeight(3)
//										 .foliageHeightRandom(1)
								.ignoreVines()
								.setSapling(sapling.get())
								.build());
	}

	public HugeTreeFeatureConfig configBig() {
		Log.info("configBig");
		return bigConfig != null
				? bigConfig
				: (bigConfig = (new HugeTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log), new SimpleBlockStateProvider(leaves))).baseHeight(6).setSapling(sapling.get()).build());
	}

}