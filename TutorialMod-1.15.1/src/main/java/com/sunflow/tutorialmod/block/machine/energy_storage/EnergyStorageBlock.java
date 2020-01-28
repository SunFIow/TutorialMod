package com.sunflow.tutorialmod.block.machine.energy_storage;

import com.sunflow.tutorialmod.block.base.EnergyTileBlockBase;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class EnergyStorageBlock extends EnergyTileBlockBase {

	public EnergyStorageBlock() {
		super(Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f)
				.lightValue(14));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new EnergyStorageTile();
	}
}