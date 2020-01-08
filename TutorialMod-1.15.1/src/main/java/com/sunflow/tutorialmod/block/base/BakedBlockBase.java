package com.sunflow.tutorialmod.block.base;

import com.sunflow.tutorialmod.block.furniture.fancyblock.FancyBlockTile;
import com.sunflow.tutorialmod.setup.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BakedBlockBase extends BlockBase {
	protected VoxelShape shape;

	public Vec3d offset;
	public Vec3d length;

	public BakedBlockBase(String name, ItemGroup group, Properties properties, VoxelShape shape) {
		super(name, group, properties);
		this.shape = shape;
		AxisAlignedBB bb = shape.getBoundingBox();
		offset = new Vec3d(bb.minX, bb.minY, bb.minZ);
		length = new Vec3d(bb.maxX - bb.minX, bb.maxY - bb.minY, bb.maxZ - bb.minZ);

		ModBlocks.BAKEDMODELBLOCKS.add(this);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof FancyBlockTile) {
			Vec3d offset = ((FancyBlockTile) tile).getOffset();
			if (offset != null)
				return shape.withOffset(offset.x, offset.y, offset.z);
		}
		return shape.withOffset(offset.x, offset.y, offset.z);
	}
}
