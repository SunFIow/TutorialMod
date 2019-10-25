package com.sunflow.tutorialmod.blocks.ore;

import java.util.Random;

import com.sunflow.tutorialmod.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class RubyOre extends CustomOreBlock {

	public RubyOre() {
		super("ruby_ore", 3f, 2, 2);
	}

	@Override
	public void onPlayerDestroy(IWorld world, BlockPos pos, BlockState state) {
		ItemStack stack = new ItemStack(ModItems.RUBY, new Random().nextInt(3));
		Block.spawnAsEntity(world.getWorld(), pos, stack);
	}
}
