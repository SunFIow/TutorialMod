package com.sunflow.tutorialmod.blocks.ore;

import java.util.Random;

import com.sunflow.tutorialmod.blocks.base.BlockBase;
import com.sunflow.tutorialmod.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.ToolType;

public class RubyOre extends BlockBase {

	public RubyOre() {
		super("ruby_ore", Block.Properties.create(Material.ROCK).hardnessAndResistance(3f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).lightValue(2));
	}

	@Override
	public void onPlayerDestroy(IWorld world, BlockPos pos, BlockState state) {
		ItemStack stack = new ItemStack(ModItems.RUBY, new Random().nextInt(3));
		Block.spawnAsEntity(world.getWorld(), pos, stack);
	}
}
