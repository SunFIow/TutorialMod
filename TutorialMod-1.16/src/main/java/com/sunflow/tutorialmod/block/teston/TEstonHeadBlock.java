package com.sunflow.tutorialmod.block.teston;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.PistonHeadBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.PistonType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class TEstonHeadBlock extends PistonHeadBlock {

	public TEstonHeadBlock() { super(AbstractBlock.Properties.create(Material.PISTON).hardnessAndResistance(1.5F).noDrops()); }

	private boolean isExtended(BlockState baseState, BlockState extendedState) {
		Block block = (baseState.get(TYPE) == PistonType.DEFAULT ? Registration.TESTON_BLOCK : Registration.STICKY_TESTON_BLOCK).get();
		return extendedState.isIn(block) && extendedState.get(PistonBlock.EXTENDED) && extendedState.get(FACING) == baseState.get(FACING);
	}

	/**
	 * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
	 * this block
	 */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!worldIn.isRemote && player.abilities.isCreativeMode) {
			BlockPos blockpos = pos.offset(state.get(FACING).getOpposite());
			if (this.isExtended(state, worldIn.getBlockState(blockpos))) {
				worldIn.destroyBlock(blockpos, false);
			}
		}

		super.onBlockHarvested(worldIn, pos, state, player);
	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.isIn(newState.getBlock())) {
			super.onReplaced(state, worldIn, pos, newState, isMoving);
			BlockPos blockpos = pos.offset(state.get(FACING).getOpposite());
			if (this.isExtended(state, worldIn.getBlockState(blockpos))) {
				worldIn.destroyBlock(blockpos, true);
			}

		}
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos.offset(state.get(FACING).getOpposite()));
		return this.isExtended(state, blockstate) || blockstate.isIn(Blocks.MOVING_PISTON) && blockstate.get(FACING) == state.get(FACING);
	}

	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack((state.get(TYPE) == PistonType.DEFAULT ? Registration.TESTON_BLOCK : Registration.STICKY_TESTON_BLOCK).get());
	}

}
