package com.sunflow.tutorialmod.block.tree;

import java.util.Random;

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
//	public static final CustomTree COPPER = new CustomTree(ModBlocks.COPPER_LOG, ModBlocks.COPPER_LEAVES, ModBlocks.COPPER_SAPLING);
//	public static final CustomTree ALUMINIUM = new CustomTree(ModBlocks.ALUMINIUM_LOG, ModBlocks.ALUMINIUM_LEAVES, ModBlocks.ALUMINIUM_SAPLING);
//	public final HugeTreeFeatureConfig configBig;
//	public final TreeFeatureConfig configSmall;

	private final BlockState leaves;
	private final BlockState log;
	private final IPlantable sapling;

	public CustomTree(Block log, Block leaves, IPlantable sapling) {
		Log.warn("CustomTree");
		this.log = log.getDefaultState();
		Log.warn(this.log);
		this.leaves = leaves.getDefaultState();
		Log.warn(this.leaves);
		this.sapling = sapling;
		Log.warn(this.sapling);
//		this.configSmall = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(this.log), new SimpleBlockStateProvider(this.leaves), new BlobFoliagePlacer(0, 0))).setSapling(this.sapling).func_225568_b_();
//		this.configBig = (new HugeTreeFeatureConfig.Builder(new SimpleBlockStateProvider(this.log), new SimpleBlockStateProvider(this.leaves))).func_225569_d_(6).setSapling(this.sapling).func_225568_b_();
	}

//	@Override
//	public AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
//		return random.nextInt(10) == 0
//				? new CustomBigTreeFeature(NoFeatureConfig::deserialize, true, log, leaves).setSapling(sapling)
////				: new TreeFeature(NoFeatureConfig::deserialize, true, 4 + random.nextInt(7), log, leaves, true).setSapling(sapling);
//				: new CustomTreeFeature(NoFeatureConfig::deserialize, true, true, 4, random.nextInt(7), sapling, log, leaves);
//	}

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
		return (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(this.log), new SimpleBlockStateProvider(this.leaves), new BlobFoliagePlacer(0, 0))).setSapling(this.sapling).func_225568_b_();
	}

	public HugeTreeFeatureConfig configBig() {
		return (new HugeTreeFeatureConfig.Builder(new SimpleBlockStateProvider(this.log), new SimpleBlockStateProvider(this.leaves))).func_225569_d_(6).setSapling(this.sapling).func_225568_b_();
	}
}