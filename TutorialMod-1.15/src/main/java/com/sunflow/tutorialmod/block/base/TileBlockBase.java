package com.sunflow.tutorialmod.block.base;

import java.util.function.Supplier;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public abstract class TileBlockBase extends OrientableBlockBase {
	private Supplier<TileEntity> tile;

	public TileBlockBase(Properties properties, Supplier<TileEntity> tile) { super(properties); this.tile = tile; }

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return tile.get();
	}
}
