package com.sunflow.tutorialmod.block.energy.machine.sintering_furnace;

import com.sunflow.tutorialmod.block.base.PoweredTileBlockBase;

import net.minecraft.block.material.Material;

public class SinteringFurnaceBlock extends PoweredTileBlockBase {

	public SinteringFurnaceBlock() {
		super(Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f)
				.lightValue(14),
				SinteringFurnaceTile::new);
	}

}
