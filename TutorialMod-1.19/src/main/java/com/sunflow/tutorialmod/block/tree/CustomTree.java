package com.sunflow.tutorialmod.block.tree;

import java.util.OptionalInt;
import java.util.Random;

import com.google.common.collect.ImmutableList;

import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class CustomTree extends AbstractMegaTreeGrower {

	private final BlockState leaves;
	private final BlockState log;

	public CustomTree(Block log, Block leaves) {
		this.log = log.defaultBlockState();
		this.leaves = leaves.defaultBlockState();
	}

	@Override
	protected ConfiguredFeature<?, ?> getConfiguredFeature(Random pRandom, boolean pLargeHive) {
		return Feature.TREE.configured(pRandom.nextInt(10) == 0
				? configFancy().build()
				: configSmall().build());
	}

	@Override
	protected ConfiguredFeature<?, ?> getConfiguredMegaFeature(Random pRandom) {
		return Feature.TREE.configured(configBig().build());
	}

	public TreeConfigurationBuilder configFancy() {
		return new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(Blocks.OAK_LOG),
				new FancyTrunkPlacer(3, 11, 0),
				BlockStateProvider.simple(Blocks.OAK_LEAVES),
				new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
				new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines();
	}

	public TreeConfigurationBuilder configSmall() {
		return new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(log),
				new StraightTrunkPlacer(4, 2, 0),
				BlockStateProvider.simple(leaves),
				new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
				new TwoLayersFeatureSize(1, 0, 1))
						.ignoreVines();
	}

	public TreeConfigurationBuilder configBig() {
		return (new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(log),
				new GiantTrunkPlacer(13, 2, 14),
				BlockStateProvider.simple(leaves),
				new MegaPineFoliagePlacer(ConstantInt.of(0),
						ConstantInt.of(0), UniformInt.of(13, 17)),
				new TwoLayersFeatureSize(1, 1, 2)))
						.decorators(ImmutableList.of(new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL))));
	}

}