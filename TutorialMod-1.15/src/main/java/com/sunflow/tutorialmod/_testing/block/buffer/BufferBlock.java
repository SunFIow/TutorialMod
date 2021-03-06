package com.sunflow.tutorialmod._testing.block.buffer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BufferBlock extends Block {

	public BufferBlock() {
		super(Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new BufferTileEntity();
	}

}
