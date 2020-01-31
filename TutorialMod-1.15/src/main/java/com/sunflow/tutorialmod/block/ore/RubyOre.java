package com.sunflow.tutorialmod.block.ore;

import java.util.Random;

import com.sunflow.tutorialmod.setup.registration.Registration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class RubyOre extends CustomOreBlock {

	public RubyOre() { super(3f, 2, 2); }

	@Override
	public void onPlayerDestroy(IWorld world, BlockPos pos, BlockState state) {
		ItemStack stack = new ItemStack(Registration.RUBY.get(), new Random().nextInt(3));
		Block.spawnAsEntity(world.getWorld(), pos, stack);
	}
}
