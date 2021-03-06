package com.sunflow.tutorialmod.block.food;

import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class FoodPlantBlock extends CropsBlock {
	private static final VoxelShape[] SHAPES_2_4_6_8_10_10_10_12 = new VoxelShape[] {
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D) };

	private final float chanceSeed;
	private final float chanceCrop;
	private final Supplier<Item> seed;
	private final Supplier<Item> crop;
	private final VoxelShape[] shape;

	public FoodPlantBlock(Supplier<Item> seed, Supplier<Item> crop, float chanceSeed, float chanceCrop, VoxelShape[] shape) {
		super(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.CROP));
		this.chanceSeed = chanceSeed;
		this.chanceCrop = chanceCrop;
		this.seed = seed;
		this.crop = crop;
		this.shape = shape;
	}

	public FoodPlantBlock(Supplier<Item> seed, Supplier<Item> crop, float chanceSeed, float chanceCrop, double[] shapeVals) {
		this(seed, crop, chanceSeed, chanceCrop, new VoxelShape[] {
				Block.makeCuboidShape(shapeVals[8], 0.0D, shapeVals[8], 16.0D - shapeVals[8], shapeVals[0], 16.0D - shapeVals[8]),
				Block.makeCuboidShape(shapeVals[8], 0.0D, shapeVals[8], 16.0D - shapeVals[8], shapeVals[1], 16.0D - shapeVals[8]),
				Block.makeCuboidShape(shapeVals[8], 0.0D, shapeVals[8], 16.0D - shapeVals[8], shapeVals[2], 16.0D - shapeVals[8]),
				Block.makeCuboidShape(shapeVals[8], 0.0D, shapeVals[8], 16.0D - shapeVals[8], shapeVals[3], 16.0D - shapeVals[8]),
				Block.makeCuboidShape(shapeVals[8], 0.0D, shapeVals[8], 16.0D - shapeVals[8], shapeVals[4], 16.0D - shapeVals[8]),
				Block.makeCuboidShape(shapeVals[8], 0.0D, shapeVals[8], 16.0D - shapeVals[8], shapeVals[5], 16.0D - shapeVals[8]),
				Block.makeCuboidShape(shapeVals[8], 0.0D, shapeVals[8], 16.0D - shapeVals[8], shapeVals[6], 16.0D - shapeVals[8]),
				Block.makeCuboidShape(shapeVals[8], 0.0D, shapeVals[8], 16.0D - shapeVals[8], shapeVals[7], 16.0D - shapeVals[8]) });
	}

	public FoodPlantBlock(Supplier<Item> seed, Supplier<Item> crop, float chanceSeed, float chanceCrop) {
		this(seed, crop, chanceSeed, chanceCrop, SHAPES_2_4_6_8_10_10_10_12);
	}

	public FoodPlantBlock(Supplier<Item> seed, Supplier<Item> crop) {
		this(seed, crop, 2f, 1f);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isRemote) {
			if (this.isMaxAge(state)) {
				float cSeed = new Random().nextFloat() * chanceSeed, cCrop = new Random().nextFloat() * chanceCrop;
				for (int i = 0; i < cSeed + 1; i++) {
					world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(getSeedsItem(), 1)));
				}
				for (int i = 0; i < cCrop; i++) {
					world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(getCropItem(), 1)));
				}

				world.setBlockState(pos, withAge(0));
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.FAIL;
	}

	@Override
	protected IItemProvider getSeedsItem() { return seed.get(); }

	protected IItemProvider getCropItem() { return crop.get(); }

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return shape[state.get(this.getAgeProperty())];
	}
}
