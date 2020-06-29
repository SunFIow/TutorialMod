package com.sunflow.tutorialmod.block.machine.glowstone_generator;

import com.sunflow.tutorialmod.block.base.EnergyTileBlockBase;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class GlowstoneGeneratorBlock extends EnergyTileBlockBase {
	public GlowstoneGeneratorBlock() {
		super(Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f)
				.func_235838_a_(state -> state.get(FILLLEVEL) * 7 / 15
						+ (state.get(POWERED) ? 7 : 0)));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) { return new GlowstoneGeneratorTile(); }

}
