package com.sunflow.tutorialmod.block.machine.glowstone_generator;

import com.sunflow.tutorialmod.block.base.EnergyTileBlockBase;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class GlowstoneGeneratorBlock extends EnergyTileBlockBase {
	private final int lightAmountPowered;
	private final int lightAmountStorage;

	public GlowstoneGeneratorBlock() {
		super(Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f)
				.lightValue(14));
		lightAmountPowered = lightValue / 2;
		lightAmountStorage = (lightValue - lightAmountPowered) / 15;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) { return new GlowstoneGeneratorTile(); }

	@Override
	public int getLightValue(BlockState state) {
		return state.get(FILLLEVEL) * lightAmountStorage
				+ (state.get(POWERED) ? lightAmountPowered : 0);
	}
}
