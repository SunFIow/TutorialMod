package com.sunflow.tutorialmod.block.magicblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MagicBlock extends Block implements EntityBlock {
	private static final VoxelShape RENDER_SHAPE = Shapes.empty();

	public MagicBlock() {
		super(Properties.of(Material.STONE)
				// .noOcclusion()
				.sound(SoundType.METAL)
				.strength(2.0f));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) { return new MagicBlockEntity(pPos, pState); }

	@Override
	public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
		return RENDER_SHAPE;
	}

	@Override
	public RenderShape getRenderShape(BlockState state) { return RenderShape.INVISIBLE; }

}
