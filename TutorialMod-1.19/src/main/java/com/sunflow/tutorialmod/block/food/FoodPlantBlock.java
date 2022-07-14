package com.sunflow.tutorialmod.block.food;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FoodPlantBlock<S extends ItemLike, C extends ItemLike> extends CropBlock {
	private static final VoxelShape[] SHAPES_2_4_6_8_10_10_10_12 = new VoxelShape[] {
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D) };

	private final float chanceSeed;
	private final float chanceCrop;
	private final Supplier<S> seed;
	private final Supplier<C> crop;
	private final VoxelShape[] shape;

	public FoodPlantBlock(Supplier<S> seed, Supplier<C> crop) {
		this(seed, crop, 2f, 1f);
	}

	public FoodPlantBlock(Supplier<S> seed, Supplier<C> crop, float chanceSeed, float chanceCrop) {
		this(seed, crop, chanceSeed, chanceCrop, SHAPES_2_4_6_8_10_10_10_12);
	}

	public FoodPlantBlock(Supplier<S> seed, Supplier<C> crop, float chanceSeed, float chanceCrop, VoxelShape[] shape) {
		super(Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP));
		this.chanceSeed = chanceSeed;
		this.chanceCrop = chanceCrop;
		this.seed = seed;
		this.crop = crop;
		this.shape = shape;
	}

	public FoodPlantBlock(Supplier<S> seed, Supplier<C> crop, float chanceSeed, float chanceCrop, double[] shapeVals) {
		this(seed, crop, chanceSeed, chanceCrop, new VoxelShape[] {
				Block.box(shapeVals[8], 0.0D, shapeVals[8], 16.0D - shapeVals[8], shapeVals[0], 16.0D - shapeVals[8]),
				Block.box(shapeVals[8], 0.0D, shapeVals[8], 16.0D - shapeVals[8], shapeVals[1], 16.0D - shapeVals[8]),
				Block.box(shapeVals[8], 0.0D, shapeVals[8], 16.0D - shapeVals[8], shapeVals[2], 16.0D - shapeVals[8]),
				Block.box(shapeVals[8], 0.0D, shapeVals[8], 16.0D - shapeVals[8], shapeVals[3], 16.0D - shapeVals[8]),
				Block.box(shapeVals[8], 0.0D, shapeVals[8], 16.0D - shapeVals[8], shapeVals[4], 16.0D - shapeVals[8]),
				Block.box(shapeVals[8], 0.0D, shapeVals[8], 16.0D - shapeVals[8], shapeVals[5], 16.0D - shapeVals[8]),
				Block.box(shapeVals[8], 0.0D, shapeVals[8], 16.0D - shapeVals[8], shapeVals[6], 16.0D - shapeVals[8]),
				Block.box(shapeVals[8], 0.0D, shapeVals[8], 16.0D - shapeVals[8], shapeVals[7], 16.0D - shapeVals[8]) });
	}

	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		if (!pLevel.isClientSide() && this.isMaxAge(pState)) {
			float cSeed = pLevel.getRandom().nextFloat() * chanceSeed;
			float cCrop = pLevel.getRandom().nextFloat() * chanceCrop;
			for (int i = 0; i < cSeed + 1; i++) {
				Block.popResourceFromFace(pLevel, pPos, Direction.UP, new ItemStack(getBaseSeedId(), 1));
			}
			for (int i = 0; i < cCrop; i++) {
				Block.popResourceFromFace(pLevel, pPos, Direction.UP, new ItemStack(getCropItem(), 1));
			}

			pLevel.setBlock(pPos, getStateForAge(0), 3);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}

	@Override
	protected S getBaseSeedId() { return seed.get(); }

	protected C getCropItem() { return crop.get(); }

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return shape[pState.getValue(this.getAgeProperty())];
	}
}
