package com.sunflow.tutorialmod.block.ore;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class RubyOre extends CustomOreBlock {

	public RubyOre() {
		super(Block.Properties.of(Material.STONE)
				.sound(SoundType.STONE)
				.strength(3)
				.requiresCorrectToolForDrops()
				.lightLevel(state -> 0));
	}

	@Override
	public void spawnAfterBreak(BlockState pState, ServerLevel pLevel, BlockPos pPos, ItemStack pStack) {
		ItemStack stack = new ItemStack(Registration.RUBY.get(), pLevel.getRandom().nextInt(3));
		Block.popResource(pLevel, pPos, stack);
	}
}
