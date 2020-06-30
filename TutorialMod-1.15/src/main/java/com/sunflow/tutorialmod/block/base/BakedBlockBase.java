package com.sunflow.tutorialmod.block.base;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShape;

public class BakedBlockBase extends Block {

	protected VoxelShape shape;
	protected Vec3d offset;
	protected Vec3d length;
	protected ResourceLocation location;

	public BakedBlockBase(String name, Properties properties, VoxelShape shape) {
		super(properties);
		this.shape = shape;

		AxisAlignedBB bb = shape.getBoundingBox();
		offset = new Vec3d(bb.minX, bb.minY, bb.minZ);
		length = new Vec3d(bb.maxX - bb.minX, bb.maxY - bb.minY, bb.maxZ - bb.minZ);

		location = new ResourceLocation(TutorialMod.MODID, "block/" + name);
	}

	public Vec3d offset() { return offset; }

	public Vec3d length() { return length; }

	public ResourceLocation location() { return location; }
}
