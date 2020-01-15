package com.sunflow.tutorialmod.block.machine.firstblock;

import com.sunflow.tutorialmod.block.base.EnergyTileBlockBase;
import com.sunflow.tutorialmod.setup.ModGroups;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class FirstBlock extends EnergyTileBlockBase {
	public FirstBlock() {
		super("firstblock", ModGroups.itemGroup2, Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f)
				.lightValue(14));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new FirstBlockTile();
	}
}
