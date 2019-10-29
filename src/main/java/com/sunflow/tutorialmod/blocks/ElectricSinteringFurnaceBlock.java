package com.sunflow.tutorialmod.blocks;

import com.sunflow.tutorialmod.blocks.base.EnergyTileBlockBase;
import com.sunflow.tutorialmod.blocks.tile.ElectricSinteringFurnaceTile;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ElectricSinteringFurnaceBlock extends EnergyTileBlockBase {
	public ElectricSinteringFurnaceBlock() {
		super("electric_sintering_furnace", Material.ROCK, 2.0F, 14);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new ElectricSinteringFurnaceTile();
	}
}
