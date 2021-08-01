package com.sunflow.tutorialmod.block.teston;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MovingPistonBlock;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.PistonType;
import net.minecraft.tileentity.PistonTileEntity;
//import net.minecraft.tileentity.PistonTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class TEstonBlock extends PistonBlock {
	/** This piston is the sticky one? */
	private final boolean isSticky;

	public static Supplier<TEstonBlock> create(boolean sticky) {
		AbstractBlock.IPositionPredicate abstractblock$ipositionpredicate = (state, reader, pos) -> !state.get(TEstonBlock.EXTENDED);
		return () -> new TEstonBlock(sticky, AbstractBlock.Properties.create(Material.PISTON)
				.hardnessAndResistance(1.5F)
				.setOpaque((BlockState state, IBlockReader reader, BlockPos pos) -> false)
				.setSuffocates(abstractblock$ipositionpredicate)
				.setBlocksVision(abstractblock$ipositionpredicate));
	}

	public TEstonBlock(boolean sticky, AbstractBlock.Properties properties) {
		super(sticky, properties);
		this.isSticky = sticky;
	}

	private void checkForMove(World worldIn, BlockPos pos, BlockState state) {
		Direction direction = state.get(FACING);
		boolean flag = this.shouldBeExtended(worldIn, pos, direction);
		if (flag && !state.get(EXTENDED)) {
			if ((new TEstonBlockStructureHelper(worldIn, pos, direction, true)).canMove()) {
				worldIn.addBlockEvent(pos, this, 0, direction.getIndex());
			}
		} else if (!flag && state.get(EXTENDED)) {
			BlockPos blockpos = pos.offset(direction, 2);
			BlockState blockstate = worldIn.getBlockState(blockpos);
			int i = 1;
			if (blockstate.isIn(Blocks.MOVING_PISTON) && blockstate.get(FACING) == direction) {
				TileEntity tileentity = worldIn.getTileEntity(blockpos);
				if (tileentity instanceof PistonTileEntity) {
					PistonTileEntity pistontileentity = (PistonTileEntity) tileentity;
					if (pistontileentity.isExtending() && (pistontileentity.getProgress(0.0F) < 0.5F || worldIn.getGameTime() == pistontileentity.getLastTicked() || ((ServerWorld) worldIn).isInsideTick())) {
						i = 2;
					}
				}
			}
			worldIn.addBlockEvent(pos, this, i, direction.getIndex());
		}
	}

	/**
	 * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	 */
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (!worldIn.isRemote) this.checkForMove(worldIn, pos, state);
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if (!worldIn.isRemote) this.checkForMove(worldIn, pos, state);
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
	}

	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (!oldState.isIn(state.getBlock())) if (!worldIn.isRemote && worldIn.getTileEntity(pos) == null)
			this.checkForMove(worldIn, pos, state);
	}

	private boolean shouldBeExtended(World worldIn, BlockPos pos, Direction facing) {
		for (Direction direction : Direction.values())
			if (direction != facing && worldIn.isSidePowered(pos.offset(direction), direction)) return true;

		if (worldIn.isSidePowered(pos, Direction.DOWN)) return true;

		BlockPos blockpos = pos.up();

		for (Direction direction1 : Direction.values())
			if (direction1 != Direction.DOWN && worldIn.isSidePowered(blockpos.offset(direction1), direction1)) return true;

		return false;
	}

	/**
	 * Called on server when World#addBlockEvent is called. If server returns true, then also called on the client. On
	 * the Server, this may perform additional changes to the world, like pistons replacing the block with an extended
	 * base. On the client, the update may involve replacing tile entities or effects such as sounds or particles
	 * 
	 * @deprecated call via {@link IBlockState#onBlockEventReceived(World,BlockPos,int,int)} whenever possible.
	 *             Implementing/overriding is fine.
	 */
	@Override
	@Deprecated
	public boolean eventReceived(BlockState state, World worldIn, BlockPos pos, int id, int param) {
		Direction direction = state.get(FACING);
		if (!worldIn.isRemote) {
			boolean flag = this.shouldBeExtended(worldIn, pos, direction);
			if (flag && (id == 1 || id == 2)) {
				worldIn.setBlockState(pos, state.with(EXTENDED, Boolean.valueOf(true)), 2);
				return false;
			}

			if (!flag && id == 0) return false;

		}

		if (id == 0) {
			if (net.minecraftforge.event.ForgeEventFactory.onPistonMovePre(worldIn, pos, direction, true)) return false;
			if (!this.doMove(worldIn, pos, direction, true)) return false;

			worldIn.setBlockState(pos, state.with(EXTENDED, Boolean.valueOf(true)), 67);
			worldIn.playSound((PlayerEntity) null, pos, SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 0.5F, worldIn.rand.nextFloat() * 0.25F + 0.6F);
		} else if (id == 1 || id == 2) {
			if (net.minecraftforge.event.ForgeEventFactory.onPistonMovePre(worldIn, pos, direction, false)) return false;
			TileEntity tileentity1 = worldIn.getTileEntity(pos.offset(direction));
			if (tileentity1 instanceof PistonTileEntity) ((PistonTileEntity) tileentity1).clearPistonTileEntity();

			BlockState blockstate = Blocks.MOVING_PISTON.getDefaultState().with(MovingPistonBlock.FACING, direction).with(MovingPistonBlock.TYPE, this.isSticky ? PistonType.STICKY : PistonType.DEFAULT);
			worldIn.setBlockState(pos, blockstate, 20);
//			worldIn.setTileEntity(pos, MovingPistonBlock.createTilePiston(this.getDefaultState().with(FACING, Direction.byIndex(param & 7)), direction, false, true));
// ------------------------------------------------------------------------------------
			worldIn.setTileEntity(pos, new TEstonTileEntity(this.getDefaultState().with(FACING, Direction.byIndex(param & 7)), direction, false, true, null));
// ------------------------------------------------------------------------------------
			worldIn.func_230547_a_(pos, blockstate.getBlock());
			blockstate.updateNeighbours(worldIn, pos, 2);
			if (this.isSticky) {
				BlockPos blockpos = pos.add(direction.getXOffset() * 2, direction.getYOffset() * 2, direction.getZOffset() * 2);
				BlockState blockstate1 = worldIn.getBlockState(blockpos);
				boolean flag1 = false;
				if (blockstate1.isIn(Blocks.MOVING_PISTON)) {
					TileEntity tileentity = worldIn.getTileEntity(blockpos);
					if (tileentity instanceof PistonTileEntity) {
						PistonTileEntity pistontileentity = (PistonTileEntity) tileentity;
						if (pistontileentity.getFacing() == direction && pistontileentity.isExtending()) {
							pistontileentity.clearPistonTileEntity();
							flag1 = true;
						}
					}
				}

				if (!flag1) {
					if (id != 1 ||
							blockstate1.isAir() ||
							!canPush(blockstate1, worldIn, blockpos, direction.getOpposite(), false, direction) ||
							blockstate1.getPushReaction() != PushReaction.NORMAL &&
									!blockstate1.isIn(Blocks.PISTON) &&
									!blockstate1.isIn(Blocks.STICKY_PISTON) &&
									!blockstate1.isIn(Registration.TESTON_BLOCK.get()) &&
									!blockstate1.isIn(Registration.STICKY_TESTON_BLOCK.get())) {
						worldIn.removeBlock(pos.offset(direction), false);
					} else {
						this.doMove(worldIn, pos, direction, false);
					}
				}
			} else {
				worldIn.removeBlock(pos.offset(direction), false);
			}

			worldIn.playSound((PlayerEntity) null, pos, SoundEvents.BLOCK_PISTON_CONTRACT, SoundCategory.BLOCKS, 0.5F, worldIn.rand.nextFloat() * 0.15F + 0.6F);
		}

		net.minecraftforge.event.ForgeEventFactory.onPistonMovePost(worldIn, pos, direction, (id == 0));
		return true;
	}

	/**
	 * Checks if the piston can push the given BlockState.
	 */
	public static boolean canPush(BlockState blockStateIn, World worldIn, BlockPos pos, Direction facing, boolean destroyBlocks, Direction direction) {
		if (pos.getY() >= 0 && pos.getY() <= worldIn.getHeight() - 1 && worldIn.getWorldBorder().contains(pos)) {
			if (blockStateIn.isAir()) return true;
			if (!blockStateIn.isIn(Blocks.OBSIDIAN) && !blockStateIn.isIn(Blocks.CRYING_OBSIDIAN) && !blockStateIn.isIn(Blocks.RESPAWN_ANCHOR)) {
				if (facing == Direction.DOWN && pos.getY() == 0) return false;
				if (facing == Direction.UP && pos.getY() == worldIn.getHeight() - 1) return false;
				if (!blockStateIn.isIn(Blocks.PISTON) && !blockStateIn.isIn(Blocks.STICKY_PISTON) &&
						!blockStateIn.isIn(Registration.TESTON_BLOCK.get()) && !blockStateIn.isIn(Registration.STICKY_TESTON_BLOCK.get())) {
					if (blockStateIn.getBlockHardness(worldIn, pos) == -1.0F) return false;
					switch (blockStateIn.getPushReaction()) {
						case BLOCK:
							return false;
						case DESTROY:
							return destroyBlocks;
						case PUSH_ONLY:
							return facing == direction;
					}
				} else if (blockStateIn.get(EXTENDED)) return false;
//				return !blockStateIn.hasTileEntity();
// ------------------------------------------------------------------------------------
				return true;
// ------------------------------------------------------------------------------------
			}
		}
		return false;
	}

	private boolean doMove(World worldIn, BlockPos pos, Direction directionIn, boolean extending) {
		BlockPos blockpos = pos.offset(directionIn);
		if (!extending && worldIn.getBlockState(blockpos).isIn(Registration.TESTON_HEAD_BLOCK.get())) {
			worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 20);
		}

		TEstonBlockStructureHelper testonblockstructurehelper = new TEstonBlockStructureHelper(worldIn, pos, directionIn, extending);
		if (!testonblockstructurehelper.canMove()) return false;

		Map<BlockPos, BlockState> map = Maps.newHashMap();
		List<BlockPos> blockToMove = testonblockstructurehelper.getBlocksToMove();
		List<BlockState> blocksToMoveStates = Lists.newArrayList();

		for (int i = 0; i < blockToMove.size(); ++i) {
			BlockPos blockpos1 = blockToMove.get(i);
			BlockState blockstate = worldIn.getBlockState(blockpos1);
			blocksToMoveStates.add(blockstate);
			map.put(blockpos1, blockstate);
		}

		List<BlockPos> blocksToDestroy = testonblockstructurehelper.getBlocksToDestroy();
		BlockState[] ablockstate = new BlockState[blockToMove.size() + blocksToDestroy.size()];
		Direction direction = extending ? directionIn : directionIn.getOpposite();
		int j = 0;

		for (int k = blocksToDestroy.size() - 1; k >= 0; --k) {
			BlockPos blockpos2 = blocksToDestroy.get(k);
			BlockState blockstate1 = worldIn.getBlockState(blockpos2);
			TileEntity tileentity = blockstate1.hasTileEntity() ? worldIn.getTileEntity(blockpos2) : null;
			spawnDrops(blockstate1, worldIn, blockpos2, tileentity);
			worldIn.setBlockState(blockpos2, Blocks.AIR.getDefaultState(), 18);
			ablockstate[j++] = blockstate1;
		}

		for (int l = blockToMove.size() - 1; l >= 0; --l) {
			BlockPos blockpos3 = blockToMove.get(l);
			BlockState blockstate5 = worldIn.getBlockState(blockpos3);
			blockpos3 = blockpos3.offset(direction);
			map.remove(blockpos3);
			worldIn.setBlockState(blockpos3, Blocks.MOVING_PISTON.getDefaultState().with(FACING, directionIn), 68);
//				worldIn.setTileEntity(blockpos3, MovingPistonBlock.createTilePiston(blocksToMoveStates.get(l), directionIn, extending, false));
// ------------------------------------------------------------------------------------
			BlockPos btmbp = blockToMove.get(l);
			BlockState btmbs = blocksToMoveStates.get(l);
			if (!btmbs.hasTileEntity()) worldIn.setTileEntity(blockpos3, new TEstonTileEntity(btmbs, directionIn, extending, false, null));
			else {
				worldIn.setTileEntity(blockpos3, new TEstonTileEntity(btmbs, directionIn, extending, false,
						worldIn.getTileEntity(btmbp).serializeNBT()));
				worldIn.removeTileEntity(btmbp);
			}
// ------------------------------------------------------------------------------------
			ablockstate[j++] = blockstate5;
		}

		if (extending) {
			PistonType pistontype = this.isSticky ? PistonType.STICKY : PistonType.DEFAULT;
			BlockState blockstate4 = Registration.TESTON_HEAD_BLOCK.get().getDefaultState().with(TEstonHeadBlock.FACING, directionIn).with(TEstonHeadBlock.TYPE, pistontype);
			BlockState blockstate6 = Blocks.MOVING_PISTON.getDefaultState().with(MovingPistonBlock.FACING, directionIn).with(MovingPistonBlock.TYPE, pistontype);
			map.remove(blockpos);
			worldIn.setBlockState(blockpos, blockstate6, 68);
//				worldIn.setTileEntity(blockpos, MovingPistonBlock.createTilePiston(blockstate4, directionIn, true, true));
// ------------------------------------------------------------------------------------				
			worldIn.setTileEntity(blockpos, new TEstonTileEntity(blockstate4, directionIn, true, true, null));
// ------------------------------------------------------------------------------------
		}

		BlockState blockstate3 = Blocks.AIR.getDefaultState();

		for (BlockPos blockpos4 : map.keySet()) {
			worldIn.setBlockState(blockpos4, blockstate3, 82);
		}

		for (Entry<BlockPos, BlockState> entry : map.entrySet()) {
			BlockPos blockpos5 = entry.getKey();
			BlockState blockstate2 = entry.getValue();
			blockstate2.updateDiagonalNeighbors(worldIn, blockpos5, 2);
			blockstate3.updateNeighbours(worldIn, blockpos5, 2);
			blockstate3.updateDiagonalNeighbors(worldIn, blockpos5, 2);
		}

		j = 0;

		for (int i1 = blocksToDestroy.size() - 1; i1 >= 0; --i1) {
			BlockState blockstate7 = ablockstate[j++];
			BlockPos blockpos6 = blocksToDestroy.get(i1);
			blockstate7.updateDiagonalNeighbors(worldIn, blockpos6, 2);
			worldIn.notifyNeighborsOfStateChange(blockpos6, blockstate7.getBlock());
		}

		for (int j1 = blockToMove.size() - 1; j1 >= 0; --j1) {
			worldIn.notifyNeighborsOfStateChange(blockToMove.get(j1), ablockstate[j++].getBlock());
		}

		if (extending) {
			worldIn.notifyNeighborsOfStateChange(blockpos, Registration.TESTON_HEAD_BLOCK.get());
		}

		return true;

	}

}
