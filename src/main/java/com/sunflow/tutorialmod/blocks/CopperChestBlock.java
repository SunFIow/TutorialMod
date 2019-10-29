package com.sunflow.tutorialmod.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.blocks.tile.CopperChestTile;
import com.sunflow.tutorialmod.init.ModBlocks;
import com.sunflow.tutorialmod.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CopperChestBlock extends ContainerBlock implements IWaterLoggable {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
	public static final TranslationTextComponent containerName = new TranslationTextComponent("container.copper_chest");

	public CopperChestBlock() {
		super(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD));
		setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)));
		setRegistryName("copper_chest");

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new BlockItem(this, new Item.Properties().group(TutorialMod.setup.itemGroup)).setRegistryName("copper_chest"));

	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new CopperChestTile();
	};

	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isRemote) {
			CopperChestTile tile = getTile(state, world, pos, false);
			if (tile != null) {
				player.openContainer(tile);
			}
		}
		return true;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean hasCustomBreakingProgress(BlockState state) {
		return true;
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		if (state.get(WATERLOGGED)) {
			world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		return state;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction direction = context.getPlacementHorizontalFacing().getOpposite();
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());

		return this.getDefaultState().with(FACING, direction).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	}

	@Override
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : Fluids.EMPTY.getDefaultState();
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (stack.hasDisplayName()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof CopperChestTile) {
				((CopperChestTile) tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof IInventory) {
				InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
				worldIn.updateComparatorOutputLevel(pos, this);
			}

			if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
				worldIn.removeTileEntity(pos);
			}
		}
	}

	@Nullable
	public static CopperChestTile getTile(BlockState state, IWorld world, BlockPos pos, boolean allowBlocked) {
		TileEntity tileentity = world.getTileEntity(pos);
		if (!(tileentity instanceof CopperChestTile)) {
			return null;
		} else if (!allowBlocked && isBlocked(world, pos)) {
			return null;
		} else {
			CopperChestTile chesttileentity = (CopperChestTile) tileentity;
			return chesttileentity;
		}
	}

	private static boolean isBlocked(IWorld world, BlockPos pos) {
		return isBelowSolidBlock(world, pos) || isCatSittingOn(world, pos);
	}

	private static boolean isBelowSolidBlock(IBlockReader world, BlockPos pos) {
		BlockPos blockpos = pos.up();
		return world.getBlockState(blockpos).isNormalCube(world, blockpos);
	}

	private static boolean isCatSittingOn(IWorld world, BlockPos pos) {
		List<CatEntity> list = world.getEntitiesWithinAABB(CatEntity.class, new AxisAlignedBB(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1));
		if (!list.isEmpty()) {
			for (CatEntity catentity : list) {
				if (catentity.isSitting()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
		return Container.calcRedstoneFromInventory(getTile(blockState, worldIn, pos, false));
	}

//	@Nullable
//	public static IInventory getInventory(BlockState state, World world, BlockPos pos, boolean allowBlocked) {
//		return getTile(state, world, pos, allowBlocked);
//	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}

	@Override
	public boolean allowsMovement(BlockState state, IBlockReader world, BlockPos pos, PathType type) {
		return false;
	}
}
