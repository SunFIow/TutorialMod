package com.sunflow.tutorialmod.block.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SantaHatBlock extends HorizontalDirectionalBlock {

	private static final VoxelShape SANTA_HAT_AABB = Block.box(2D, 0, 2D, 14D, 13D, 14D);

	public SantaHatBlock() {
		super(Block.Properties.of(Material.WOOL).sound(SoundType.WOOL));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SANTA_HAT_AABB;
	}

	@Override
	public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
		return pLevel.getBlockState(pPos.below()).getMaterial().isSolid();
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(FACING);
	}

}
