package com.sunflow.tutorialmod.block;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.PushReaction;

public class MultiBlock extends Block {
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

	public MultiBlock() {
		super(Properties.of(Material.STONE)
				.strength(2.0f)
				.lightLevel(state -> 14)
				.noOcclusion());
		registerDefaultState(this.getStateDefinition().any().setValue(HALF, DoubleBlockHalf.UPPER));
	}

	@Override
	public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
		DoubleBlockHalf doubleblockhalf = pState.getValue(HALF);
		if (pFacing.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.UPPER == (pFacing == Direction.DOWN)) {
			return pFacingState.is(this) && pFacingState.getValue(HALF) != doubleblockhalf ? pState : Blocks.AIR.defaultBlockState();
		} else {
			return doubleblockhalf == DoubleBlockHalf.UPPER && pFacing == Direction.UP && !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
		}
	}

	/**
	* Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
	* this block
	*/
	@Override
	public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
		if (!pLevel.isClientSide && pPlayer.isCreative()) {
			preventCreativeDropFromUpperPart(pLevel, pPos, pState, pPlayer);
		}

		super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
	}

	protected static void preventCreativeDropFromUpperPart(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
		DoubleBlockHalf doubleblockhalf = pState.getValue(HALF);
		if (doubleblockhalf == DoubleBlockHalf.LOWER) {
			BlockPos blockpos = pPos.above();
			BlockState blockstate = pLevel.getBlockState(blockpos);
			if (blockstate.is(pState.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.UPPER) {
				BlockState blockstate1 = blockstate.hasProperty(BlockStateProperties.WATERLOGGED) && blockstate.getValue(BlockStateProperties.WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
				pLevel.setBlock(blockpos, blockstate1, 35);
				pLevel.levelEvent(pPlayer, 2001, blockpos, Block.getId(blockstate));
			}
		}

	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		BlockPos blockpos = pContext.getClickedPos();
		Level level = pContext.getLevel();
		if (blockpos.getY() > 0 && level.getBlockState(blockpos.below()).canBeReplaced(pContext)) {
			return this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER);
		} else {
			return null;
		}
	}

	@Override
	public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack) {
		pLevel.setBlock(pPos.below(), pState.setValue(HALF, DoubleBlockHalf.LOWER), 3);
	}

	@Override
	public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
		BlockPos blockpos = pPos.above();
		BlockState blockstate = pLevel.getBlockState(blockpos);
		return pState.getValue(HALF) == DoubleBlockHalf.UPPER ? blockstate.isFaceSturdy(pLevel, blockpos, Direction.DOWN) : blockstate.is(this);
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState pState) { return PushReaction.DESTROY; } // FIXME

	@Override
	public long getSeed(BlockState pState, BlockPos pPos) {
		return Mth.getSeed(pPos.getX(), pPos.below(pState.getValue(HALF) == DoubleBlockHalf.UPPER ? 0 : 1).getY(), pPos.getZ());
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(HALF);
	}
}
