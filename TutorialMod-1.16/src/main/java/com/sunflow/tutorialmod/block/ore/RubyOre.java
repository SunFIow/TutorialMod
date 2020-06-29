package com.sunflow.tutorialmod.block.ore;

import java.util.Random;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.ToolType;

public class RubyOre extends CustomOreBlock {

	public RubyOre() {
		super(Block.Properties.create(Material.ROCK)
				.harvestTool(ToolType.PICKAXE)
				.sound(SoundType.STONE)
				.hardnessAndResistance(3)
				.harvestLevel(2)
				.func_235838_a_(state -> 0));
	}

	@Override
	public void onPlayerDestroy(IWorld world, BlockPos pos, BlockState state) {
		ItemStack stack = new ItemStack(Registration.RUBY.get(), new Random().nextInt(3));
		Block.spawnAsEntity(world.getWorld(), pos, stack);
	}
}
