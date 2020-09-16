package com.sunflow.tutorialmod.block.energy.machine.energy_storage;

import com.sunflow.tutorialmod.block.base.EnergyTileBlockBase;

import net.minecraft.block.material.Material;

public class EnergyStorageBlock extends EnergyTileBlockBase {

	public EnergyStorageBlock() {
		super(Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f)
				.lightValue(14),
				EnergyStorageTile::new);
	}

}