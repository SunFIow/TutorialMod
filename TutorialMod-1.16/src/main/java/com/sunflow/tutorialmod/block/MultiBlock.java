package com.sunflow.tutorialmod.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MultiBlock extends Block {
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

	public MultiBlock() {
		super(Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f)
//				.lightValue(14)
				.func_235838_a_(state -> 14)
				.notSolid());
		this.setDefaultState(this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.UPPER));
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		DoubleBlockHalf doubleblockhalf = stateIn.get(HALF);
		if (facing.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.UPPER == (facing == Direction.DOWN)) {
			return facingState.getBlock() == this && facingState.get(HALF) != doubleblockhalf ? stateIn : Blocks.AIR.getDefaultState();
		} else {
			return doubleblockhalf == DoubleBlockHalf.UPPER && facing == Direction.UP && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
		}
	}

//	@Override
//	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
////		super.harvestBlock(worldIn, player, pos, state, te, stack);
//		super.harvestBlock(worldIn, player, pos, Blocks.AIR.getDefaultState(), te, stack);
//	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
//		DoubleBlockHalf doubleblockhalf = state.get(HALF);
//		BlockPos blockpos = doubleblockhalf == DoubleBlockHalf.UPPER ? pos.down() : pos.up();
//		BlockState blockstate = worldIn.getBlockState(blockpos);
//		if (blockstate.getBlock() == this && blockstate.get(HALF) != doubleblockhalf) {
//			worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
//			worldIn.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
//			ItemStack itemstack = player.getHeldItemMainhand();
////			if (!worldIn.isRemote && !player.isCreative() && player.canHarvestBlock(blockstate)) {
//			if (!worldIn.isRemote && !player.isCreative() && player.func_234569_d_(blockstate)) {
//				Block.spawnDrops(state, worldIn, pos, (TileEntity) null, player, itemstack);
//				Block.spawnDrops(blockstate, worldIn, blockpos, (TileEntity) null, player, itemstack);
//			}
//		}
//
//		super.onBlockHarvested(worldIn, pos, state, player);

		if (!worldIn.isRemote && player.isCreative()) {
//			DoublePlantBlock.func_241471_b_(worldIn, pos, state, player);
			func_241471_b_(worldIn, pos, state, player);
		}

		super.onBlockHarvested(worldIn, pos, state, player);
	}

	protected static void func_241471_b_(World p_241471_0_, BlockPos p_241471_1_, BlockState p_241471_2_, PlayerEntity p_241471_3_) {
		DoubleBlockHalf doubleblockhalf = p_241471_2_.get(HALF);
		if (doubleblockhalf == DoubleBlockHalf.UPPER) {
			BlockPos blockpos = p_241471_1_.down();
			BlockState blockstate = p_241471_0_.getBlockState(blockpos);
			if (blockstate.getBlock() == p_241471_2_.getBlock() && blockstate.get(HALF) == DoubleBlockHalf.LOWER) {
				p_241471_0_.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
				p_241471_0_.playEvent(p_241471_3_, 2001, blockpos, Block.getStateId(blockstate));
			}
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockPos blockpos = context.getPos();
		if (blockpos.getY() > 0 && context.getWorld().getBlockState(blockpos.down()).isReplaceable(context)) {
			return this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER);
		} else {
			return null;
		}
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		worldIn.setBlockState(pos.down(), state.with(HALF, DoubleBlockHalf.LOWER), 3);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.up();
		BlockState blockstate = worldIn.getBlockState(blockpos);
//		if (state.get(HALF) == DoubleBlockHalf.UPPER) {
//			return blockstate.isSolidSide(worldIn, blockpos, Direction.DOWN);
//		} else {
//			return blockstate.getBlock() == this;
//		}
		return state.get(HALF) == DoubleBlockHalf.UPPER
				? blockstate.isSolidSide(worldIn, blockpos, Direction.DOWN)
				: blockstate.isIn(this);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public long getPositionRandom(BlockState state, BlockPos pos) {
		return MathHelper.getCoordinateRandom(pos.getX(), pos.up(state.get(HALF) == DoubleBlockHalf.UPPER ? 0 : 1).getY(), pos.getZ());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HALF);
	}
}
