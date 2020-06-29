package com.sunflow.tutorialmod.block.tree;

import java.util.OptionalInt;
import java.util.Random;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.MegaPineFoliagePlacer;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.GiantTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
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
	public ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random random, boolean p_225546_2_) {
		return random.nextInt(10) == 0
				? Feature.field_236291_c_.withConfiguration(configFancy())
				: Feature.field_236291_c_.withConfiguration(configSmall());
	}

	@Override
	public ConfiguredFeature<BaseTreeFeatureConfig, ?> getHugeTreeFeature(Random random) {
		return Feature.field_236291_c_.withConfiguration(configBig());
	}

	public BaseTreeFeatureConfig configFancy() {
		return (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log), new SimpleBlockStateProvider(leaves), new FancyFoliagePlacer(2, 0, 4, 0, 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).func_236700_a_().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build();
//		return (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log), new SimpleBlockStateProvider(leaves), new BlobFoliagePlacer(0, 0))).setSapling(sapling.get()).build();
	}

	public BaseTreeFeatureConfig configSmall() {
		return (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log), new SimpleBlockStateProvider(leaves), new BlobFoliagePlacer(2, 0, 0, 0, 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1))).func_236700_a_().build();
//		return (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log), new SimpleBlockStateProvider(leaves), new BlobFoliagePlacer(0, 0))).setSapling(sapling.get()).build();
	}

	public BaseTreeFeatureConfig configBig() {
		return (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log), new SimpleBlockStateProvider(leaves), new MegaPineFoliagePlacer(0, 0, 0, 0, 4, 3), new GiantTrunkPlacer(13, 2, 14), new TwoLayerFeature(1, 1, 2))).func_236703_a_(ImmutableList.of(new AlterGroundTreeDecorator(new SimpleBlockStateProvider(Blocks.PODZOL.getDefaultState())))).build();
//		return (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log), new SimpleBlockStateProvider(leaves))).baseHeight(6).setSapling(sapling.get()).build();
	}
}