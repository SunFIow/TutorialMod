package com.sunflow.tutorialmod.block.base;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.furniture.fancyblock.FancyBlockTile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;

public class BakedBlockBase extends Block {

	protected VoxelShape shape;
	protected Vector3d offset;
	protected Vector3d length;
	protected ResourceLocation location;

	public BakedBlockBase(String name, Properties properties, VoxelShape shape) {
		super(properties);
		this.shape = shape;

		AxisAlignedBB bb = shape.getBoundingBox();
		offset = new Vector3d(bb.minX, bb.minY, bb.minZ);
		length = new Vector3d(bb.maxX - bb.minX, bb.maxY - bb.minY, bb.maxZ - bb.minZ);

		location = new ResourceLocation(TutorialMod.MODID, "block/" + name);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof FancyBlockTile) {
			Vector3d offset = ((FancyBlockTile) tile).getOffset();
			if (offset != null) return shape.withOffset(offset.x, offset.y, offset.z);
		}
		return shape.withOffset(offset.x, offset.y, offset.z);
	}

	public Vector3d offset() { return offset; }

	public Vector3d length() { return length; }

	public ResourceLocation location() { return location; }
}
