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

//	@Override
//	public int getLightValue(BlockState state) {
//		return (int) (state.get(POWER) / 15F * lightValue);
//	}

//	@Override
//	public int getLightValue(BlockState state) {
//		return lightValue;
//	}
//	public static void updatePower(BlockState state, World world, BlockPos pos) {
//		boolean powered = state.get(FILLLEVEL) > 0;
//		if (state.get(POWERED) != powered) {
//			world.setBlockState(pos, state.with(POWERED, powered), 3);
//		}
//	}

	@Override
	public TileEntity getTileEntity() {
		return new EnergyStorageTile();
	}
}