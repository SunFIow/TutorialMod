package com.sunflow.tutorialmod.block.base;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class PoweredTileBlockBase extends ContainerBlockBase {
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	public PoweredTileBlockBase(Properties properties, Supplier<TileEntity> tile) {
		super(properties, tile);
		setDefaultState(getDefaultState().with(POWERED, false));
	}

	@Override
	public int getLightValue(BlockState state) {
		return state.get(POWERED) ? lightValue : 0;
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(POWERED);
	}

	@Override
	public boolean eventReceived(BlockState state, World worldIn, BlockPos pos, int id, int param) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return tileentity == null ? false : tileentity.receiveClientEvent(id, param);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context).with(POWERED, false);
	}
}
