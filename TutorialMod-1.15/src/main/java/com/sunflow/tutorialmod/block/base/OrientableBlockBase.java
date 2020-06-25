package com.sunflow.tutorialmod.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class OrientableBlockBase extends Block {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	public OrientableBlockBase(Properties properties) {
		super(properties);
		setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return getDefaultState().with(FACING, getFacingFromEntity(context.getPos(), context.getPlayer()));
	}

//
	public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
		return Direction.getFacingFromVector(entity.getPosX() - clickedBlock.getX(), entity.getPosY() - clickedBlock.getY(), entity.getPosZ() - clickedBlock.getZ());
	}
}
