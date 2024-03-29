package com.sunflow.tutorialmod.block.machine.electric_sintering_furnace;

import com.sunflow.tutorialmod.block.base.EnergyTileBlockBase;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ElectricSinteringFurnaceBlock extends EnergyTileBlockBase {

	public ElectricSinteringFurnaceBlock() {
		super(Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f)
				.setLightLevel(state -> state.get(FILLLEVEL) * 7 / 15
						+ (state.get(POWERED) ? 7 : 0)));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new ElectricSinteringFurnaceTile();
	}

}
