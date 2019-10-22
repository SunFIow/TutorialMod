package com.sunflow.tutorialmod.blocks.tree;

import java.util.Random;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.init.ModBlocks;
import com.sunflow.tutorialmod.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class CustomLeavesBlock extends LeavesBlock {

	public CustomLeavesBlock(String name) {
		super(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT));
		this.setRegistryName(name);

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new BlockItem(this, new Item.Properties().group(TutorialMod.setup.itemGroup)).setRegistryName(name));
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		int i = getDistance(facingState) + 1;
		if (i != 1 || stateIn.get(DISTANCE) != i) {
			worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
		}

		return stateIn;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return updateDistance(this.getDefaultState().with(PERSISTENT, Boolean.valueOf(true)), context.getWorld(), context.getPos());
	}

	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		worldIn.setBlockState(pos, updateDistance(state, worldIn, pos), 3);
	}

	private static BlockState updateDistance(BlockState state, IWorld worldIn, BlockPos pos) {
		int i = 7;

		try (BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain()) {
			for (Direction direction : Direction.values()) {
				blockpos$pooledmutableblockpos.setPos(pos).move(direction);
				i = Math.min(i, getDistance(worldIn.getBlockState(blockpos$pooledmutableblockpos)) + 1);
				if (i == 1) {
					break;
				}
			}
		}

		return state.with(DISTANCE, Integer.valueOf(i));
	}

	private static int getDistance(BlockState neighbor) {
		if (BlockTags.LOGS.contains(neighbor.getBlock())) {
			return 0;
		} else if (neighbor.getBlock() instanceof CustomLogBlock) {
			return 0;
		} else {
			return neighbor.getBlock() instanceof LeavesBlock ? neighbor.get(DISTANCE) : 7;
		}
	}
}
