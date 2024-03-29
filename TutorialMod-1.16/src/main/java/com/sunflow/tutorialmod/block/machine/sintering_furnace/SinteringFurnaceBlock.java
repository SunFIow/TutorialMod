package com.sunflow.tutorialmod.block.machine.sintering_furnace;

import com.sunflow.tutorialmod.block.base.PoweredTileBlockBase;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class SinteringFurnaceBlock extends PoweredTileBlockBase {

	public SinteringFurnaceBlock() {
		super(Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f)
				.setLightLevel((state) -> state.get(POWERED) ? 14 : 0));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new SinteringFurnaceTile();
	}
}
