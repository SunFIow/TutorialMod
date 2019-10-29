package com.sunflow.tutorialmod.blocks;

import com.sunflow.tutorialmod.blocks.base.EnergyTileBlockBase;
import com.sunflow.tutorialmod.blocks.tile.EnergyStorageTile;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

public class EnergyStorageBlock extends EnergyTileBlockBase {

	public EnergyStorageBlock() {
		super("energy_storage", Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f)
				.lightValue(14));
	}

	@Override
	public TileEntity getTileEntity() {
		return new EnergyStorageTile();
	}
}