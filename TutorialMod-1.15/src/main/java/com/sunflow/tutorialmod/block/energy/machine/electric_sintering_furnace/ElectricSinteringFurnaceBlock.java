package com.sunflow.tutorialmod.block.energy.machine.electric_sintering_furnace;

import com.sunflow.tutorialmod.block.base.EnergyTileBlockBase;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;

public class ElectricSinteringFurnaceBlock extends EnergyTileBlockBase {
	private final int lightAmountPowered;
	private final int lightAmountStorage;

	public ElectricSinteringFurnaceBlock() {
		super(Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f)
				.lightValue(14),
				ElectricSinteringFurnaceTile::new);
		lightAmountPowered = lightValue / 2;
		lightAmountStorage = (lightValue - lightAmountPowered) / 15;
	}

	@Override
	public int getLightValue(BlockState state) {
		return state.get(FILLLEVEL) * lightAmountStorage
				+ (state.get(POWERED) ? lightAmountPowered : 0);
	}
}
