package com.sunflow.tutorialmod.block.magicblock;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class MagicBlockTile extends BlockEntity {
	public MagicBlockTile(BlockPos pos, BlockState state) { super(Registration.MAGICBLOCK.blockEntity(), pos, state); }

	@Override
	public AABB getRenderBoundingBox() {
		return new AABB(getBlockPos(), getBlockPos().offset(1, 3, 1));
	}
}
