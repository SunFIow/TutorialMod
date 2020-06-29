package com.sunflow.tutorialmod.block.ore;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.ToolType;

public class RubyBlock extends Block {

	public RubyBlock() {
		super(Properties.create(Material.IRON)
				.hardnessAndResistance(5.0f)
				.harvestTool(ToolType.PICKAXE)
				.harvestLevel(2)
				.sound(SoundType.METAL)
				.func_235838_a_(state -> 0));
	}

	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) { return true; }
}
