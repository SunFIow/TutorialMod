package com.sunflow.tutorialmod.blocks;

import com.sunflow.tutorialmod.blocks.base.BlockBase;
import com.sunflow.tutorialmod.init.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BakedBlockBase extends BlockBase {
	private final VoxelShape shape; // = Block.makeCuboidShape(2.0D, 2.0D, 2.0D, 14.0D, 14.0D, 14.0D);

	public BakedBlockBase(String name, ItemGroup group, Properties properties, VoxelShape shape) {
		super(name, group, properties);
		this.shape = shape;

		ModBlocks.BAKEDMODELBLOCKS.add(this);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return shape;
	}
}
