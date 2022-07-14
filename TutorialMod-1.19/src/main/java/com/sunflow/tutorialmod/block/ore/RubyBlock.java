package com.sunflow.tutorialmod.block.ore;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.IPlantable;

public class RubyBlock extends Block {

	public RubyBlock() {
		super(Properties.of(Material.STONE)
				.strength(5.0f)
				.requiresCorrectToolForDrops()
				.sound(SoundType.METAL)
				.lightLevel(state -> 0));
	}

	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) { return true; }
}
