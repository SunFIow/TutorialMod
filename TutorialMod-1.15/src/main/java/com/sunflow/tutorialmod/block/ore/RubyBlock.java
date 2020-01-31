package com.sunflow.tutorialmod.block.ore;

import com.sunflow.tutorialmod.block.base.BlockBase;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.ToolType;

public class RubyBlock extends BlockBase {

	public RubyBlock() { super(Material.IRON, 5.0f, 5.0f, ToolType.PICKAXE, 2, SoundType.METAL, 5); }

	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) { return true; }
}
