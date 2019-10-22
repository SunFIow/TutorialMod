package com.sunflow.tutorialmod.blocks.furniture;

import com.sunflow.tutorialmod.blocks.base.HorizontalBlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class SantaHatBlock extends HorizontalBlockBase {

//	private static final AxisAlignedBB SANTA_HAT_AABB = new AxisAlignedBB(2D / 16D, 0, 2D / 16D, 1D - 2D / 16D, 13D / 16D, 1D - 2D / 16D);
	private static final VoxelShape SANTA_HAT_AABB = Block.makeCuboidShape(2D, 0, 2D, 14D, 13D, 14D);

	public SantaHatBlock(String name) {
		super(name, Block.Properties.create(Material.WOOL).sound(SoundType.CLOTH));
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SANTA_HAT_AABB;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.down()).getMaterial().isSolid();
	}
}
