package com.sunflow.tutorialmod.block;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.EnergyTileBlockBase;
import com.sunflow.tutorialmod.block.tile.FirstBlockTile;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class FirstBlock extends EnergyTileBlockBase {
	public FirstBlock() {
		super("firstblock", TutorialMod.groups.itemGroup2, Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f)
				.lightValue(14));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new FirstBlockTile();
	}
}
