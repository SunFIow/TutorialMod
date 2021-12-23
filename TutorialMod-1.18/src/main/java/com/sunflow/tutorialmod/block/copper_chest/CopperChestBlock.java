package com.sunflow.tutorialmod.block.copper_chest;

import java.util.Random;

import javax.annotation.Nullable;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType.MenuSupplier;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.AbstractChestBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.EnderChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CopperChestBlock extends AbstractChestBlock<CopperChestEntity> implements SimpleWaterloggedBlock {

	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
	public static final Component CONTAINER_TITLE = new TranslatableComponent("container.copper_chest");

	public CopperChestBlock() {
		super(Block.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD), Registration.COPPER_CHEST::blockEntity);
		registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> combine(BlockState state, Level level, BlockPos pos, boolean bool) {
		return DoubleBlockCombiner.Combiner::acceptNone;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext ctx) {
		return SHAPE;
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext p_53128_) {
		FluidState fluidstate = p_53128_.getLevel().getFluidState(p_53128_.getClickedPos());
		return this.defaultBlockState().setValue(FACING, p_53128_.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	public static class BaseMenuProvider implements MenuProvider {
		private final Component title;
		private final MenuSupplier<AbstractContainerMenu> menuSupplier;

		public BaseMenuProvider(MenuSupplier<AbstractContainerMenu> menuSupplier, Component title) {
			this.title = title;
			this.menuSupplier = menuSupplier;
		}

		@Override
		public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
			return menuSupplier.create(id, playerInventory);
		}

		@Override
		public Component getDisplayName() { return title; }

	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		BlockEntity blockentity = level.getBlockEntity(pos);
		if (blockentity instanceof CopperChestEntity) {
			BlockPos blockpos = pos.above();
			if (level.getBlockState(blockpos).isRedstoneConductor(level, blockpos)) {
				return InteractionResult.sidedSuccess(level.isClientSide);
			} else if (level.isClientSide) {
				return InteractionResult.SUCCESS;
			} else {
				CopperChestEntity copperchestentity = (CopperChestEntity) blockentity;
				player.openMenu(new BaseMenuProvider(copperchestentity::createMenu, CONTAINER_TITLE));
				player.awardStat(Stats.CUSTOM.get(Stats.OPEN_CHEST));
				return InteractionResult.CONSUME;
			}
		} else {
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new CopperChestEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153199_, BlockState p_153200_, BlockEntityType<T> entityType) {
		return p_153199_.isClientSide ? createTickerHelper(entityType, blockEntityType.get(), CopperChestEntity::lidAnimateTick) : null;
	}

	@Override
	public void animateTick(BlockState p_53144_, Level p_53145_, BlockPos p_53146_, Random p_53147_) {
		for (int i = 0; i < 3; ++i) {
			int j = p_53147_.nextInt(2) * 2 - 1;
			int k = p_53147_.nextInt(2) * 2 - 1;
			double d0 = (double) p_53146_.getX() + 0.5D + 0.25D * (double) j;
			double d1 = (double) ((float) p_53146_.getY() + p_53147_.nextFloat());
			double d2 = (double) p_53146_.getZ() + 0.5D + 0.25D * (double) k;
			double d3 = (double) (p_53147_.nextFloat() * (float) j);
			double d4 = ((double) p_53147_.nextFloat() - 0.5D) * 0.125D;
			double d5 = (double) (p_53147_.nextFloat() * (float) k);
			p_53145_.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
		}

	}

	@Override
	public BlockState rotate(BlockState p_53157_, Rotation p_53158_) {
		return p_53157_.setValue(FACING, p_53158_.rotate(p_53157_.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState p_53154_, Mirror p_53155_) {
		return p_53154_.rotate(p_53155_.getRotation(p_53154_.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_53167_) {
		p_53167_.add(FACING, WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState p_53177_) {
		return p_53177_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_53177_);
	}

	@Override
	public BlockState updateShape(BlockState p_53160_, Direction p_53161_, BlockState p_53162_, LevelAccessor p_53163_, BlockPos p_53164_, BlockPos p_53165_) {
		if (p_53160_.getValue(WATERLOGGED)) {
			p_53163_.scheduleTick(p_53164_, Fluids.WATER, Fluids.WATER.getTickDelay(p_53163_));
		}

		return super.updateShape(p_53160_, p_53161_, p_53162_, p_53163_, p_53164_, p_53165_);
	}

	@Override
	public boolean isPathfindable(BlockState p_53132_, BlockGetter p_53133_, BlockPos p_53134_, PathComputationType p_53135_) {
		return false;
	}

	@Override
	public void tick(BlockState p_153203_, ServerLevel p_153204_, BlockPos p_153205_, Random p_153206_) {
		BlockEntity blockentity = p_153204_.getBlockEntity(p_153205_);
		if (blockentity instanceof EnderChestBlockEntity) {
			((EnderChestBlockEntity) blockentity).recheckOpen();
		}

	}
}
