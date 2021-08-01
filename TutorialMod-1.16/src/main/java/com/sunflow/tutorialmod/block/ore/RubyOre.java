package com.sunflow.tutorialmod.block.ore;

import java.util.Random;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class RubyOre extends CustomOreBlock {

	public RubyOre() {
		super(Block.Properties.create(Material.ROCK)
				.harvestTool(ToolType.PICKAXE)
				.sound(SoundType.STONE)
				.hardnessAndResistance(3)
				.harvestLevel(2)
				.setLightLevel(state -> 0));
	}

//	@Override
//	public void onPlayerDestroy(IWorld world, BlockPos pos, BlockState state) {
//		ItemStack stack = new ItemStack(Registration.RUBY.get(), new Random().nextInt(3));
//		Block.spawnAsEntity(world.getWorld(), pos, stack);
//	}

//	@Override
//	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
//		super.onBlockHarvested(world, pos, state, player);
//		ItemStack stack = new ItemStack(Registration.RUBY.get(), new Random().nextInt(3));
//		Block.spawnAsEntity(world, pos, stack);
//	}

	@Override
	public void spawnAdditionalDrops(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
		ItemStack stac = new ItemStack(Registration.RUBY.get(), new Random().nextInt(3));
		Block.spawnAsEntity(world, pos, stac);
	}
}
