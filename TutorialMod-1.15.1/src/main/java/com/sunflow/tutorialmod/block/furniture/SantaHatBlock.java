package com.sunflow.tutorialmod.block.furniture;

import com.sunflow.tutorialmod.block.base.HorizontalBlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class SantaHatBlock extends HorizontalBlockBase {

	private static final VoxelShape SANTA_HAT_AABB = Block.makeCuboidShape(2D, 0, 2D, 14D, 13D, 14D);

	public SantaHatBlock() {
		super("santa_hat", Block.Properties.create(Material.WOOL).sound(SoundType.CLOTH));
		RenderTypeLookup.setRenderLayer(this, RenderType.func_228643_e_());
	}

//	@Override
//	public BlockRenderLayer getRenderLayer() {
//		return BlockRenderLayer.CUTOUT;
//	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SANTA_HAT_AABB;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.down()).getMaterial().isSolid();
	}
}
