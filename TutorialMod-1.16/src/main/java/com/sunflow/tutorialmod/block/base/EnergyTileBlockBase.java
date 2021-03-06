package com.sunflow.tutorialmod.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class EnergyTileBlockBase extends PoweredTileBlockBase {
	public static final IntegerProperty FILLLEVEL = BlockStateProperties.POWER_0_15;

	public EnergyTileBlockBase(Properties properties) {
		super(properties);
		setDefaultState(getDefaultState().with(FILLLEVEL, Integer.valueOf(0)));
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(BlockState state, World world, BlockPos pos) {
		return state.get(FILLLEVEL);
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FILLLEVEL);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context).with(FILLLEVEL, Integer.valueOf(0));
	}
}
