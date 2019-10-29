package com.sunflow.tutorialmod.blocks;

import com.sunflow.tutorialmod.blocks.base.PoweredTileBlockBase;
import com.sunflow.tutorialmod.blocks.tile.SinteringFurnaceTile;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class SinteringFurnaceBlock extends PoweredTileBlockBase {

	public SinteringFurnaceBlock() {
		super("sintering_furnace", Material.ROCK, 2.0f, 14);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new SinteringFurnaceTile();
	}
}
