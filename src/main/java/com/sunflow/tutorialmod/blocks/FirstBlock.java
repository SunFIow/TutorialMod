package com.sunflow.tutorialmod.blocks;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.blocks.base.EnergyTileBlockBase;
import com.sunflow.tutorialmod.blocks.tile.FirstBlockTile;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

public class FirstBlock extends EnergyTileBlockBase {
	public FirstBlock() {
		super("firstblock", TutorialMod.setup.itemGroup2, Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f)
				.lightValue(14));
	}

	@Override
	public TileEntity getTileEntity() {
		return new FirstBlockTile();
	}
}
