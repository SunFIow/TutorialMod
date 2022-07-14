package com.sunflow.tutorialmod.block.base;

import com.mojang.math.Vector3d;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.furniture.fancyblock.FancyBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BakedBlockBase extends Block {

	protected VoxelShape shape;
	protected Vector3d offset;
	protected Vector3d length;
	protected ResourceLocation location;

	public BakedBlockBase(String name, Properties properties, VoxelShape shape) {
		super(properties);
		this.shape = shape;

		AABB bb = shape.bounds();
		offset = new Vector3d(bb.minX, bb.minY, bb.minZ);
		length = new Vector3d(bb.maxX - bb.minX, bb.maxY - bb.minY, bb.maxZ - bb.minZ);

		location = new ResourceLocation(TutorialMod.MODID, "block/" + name);
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
		if (blockEntity instanceof FancyBlockEntity fancyEntity) {
			Vector3d offset = fancyEntity.getOffset();
			if (offset != null) return shape.move(offset.x, offset.y, offset.z);
		}
		return shape.move(offset.x, offset.y, offset.z);
	}

	public Vector3d offset() { return offset; }

	public Vector3d length() { return length; }

	public ResourceLocation location() { return location; }
}
