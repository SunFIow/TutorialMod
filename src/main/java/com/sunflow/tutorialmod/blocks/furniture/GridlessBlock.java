package com.sunflow.tutorialmod.blocks.furniture;

import com.sunflow.tutorialmod.blocks.base.BlockBase;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class GridlessBlock extends BlockBase {

	private static final VoxelShape GRIDLESS_AABB = Block.makeCuboidShape(4D, 0, 4D, 12D, 8D, 12D);
	private double xOffset, zOffset;

	public GridlessBlock() {
		super("gridless", Block.Properties.create(Material.CLAY));
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
//		state.with(property, value)
		return GRIDLESS_AABB.withOffset(xOffset, 0, zOffset);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		if (!context.getWorld().isRemote && context.getFace() == Direction.UP) {
			Vec3d hit = context.getHitVec();
			BlockPos pos = context.getPos();
			Vec3d diff = hit.subtract(pos.getX(), pos.getY(), pos.getZ());
			xOffset = diff.x - 0.5;
			zOffset = diff.z - 0.5;
			Log.warn(hit);
			Log.info(pos);
			Log.info(diff);
//			return this.getDefaultState();
		}

		return super.getStateForPlacement(context);
	}
}
