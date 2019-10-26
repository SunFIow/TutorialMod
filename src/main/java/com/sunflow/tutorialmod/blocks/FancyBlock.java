package com.sunflow.tutorialmod.blocks;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.blocks.tile.FancyBlockTile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class FancyBlock extends BakedBlockBase {
	private static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 2.0D, 2.0D, 14.0D, 14.0D, 14.0D);

	public FancyBlock() {
		super("fancyblock", TutorialMod.setup.itemGroup, Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f), SHAPE);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new FancyBlockTile();
	}

	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		ItemStack stack = player.getHeldItem(hand);
		if (!stack.isEmpty() && stack.getItem() instanceof BlockItem) {
			if (!world.isRemote) {
				TileEntity tile = world.getTileEntity(pos);
				if (tile instanceof FancyBlockTile) {
					BlockState mimicState = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
					((FancyBlockTile) tile).setMimic(mimicState);
				}
			}
			return true;
		}
		return super.onBlockActivated(state, world, pos, player, hand, hit);
	}

}
