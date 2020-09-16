package com.sunflow.tutorialmod.block.energy.cable;

import java.util.ArrayList;

import javax.annotation.Nullable;

import com.sunflow.tutorialmod.block.energy.cable.PowerCableTile.Mode;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class PowerCableBlock extends Block {

	public static final EnumProperty<PowerCableTile.Mode> NORTH = EnumProperty.create("north", PowerCableTile.Mode.class);
	public static final EnumProperty<PowerCableTile.Mode> SOUTH = EnumProperty.create("south", PowerCableTile.Mode.class);
	public static final EnumProperty<PowerCableTile.Mode> WEST = EnumProperty.create("west", PowerCableTile.Mode.class);
	public static final EnumProperty<PowerCableTile.Mode> EAST = EnumProperty.create("east", PowerCableTile.Mode.class);
	public static final EnumProperty<PowerCableTile.Mode> UP = EnumProperty.create("up", PowerCableTile.Mode.class);
	public static final EnumProperty<PowerCableTile.Mode> DOWN = EnumProperty.create("down", PowerCableTile.Mode.class);

//	private static final VoxelShape RENDER_SHAPE = VoxelShapes.create(0.1, 0.1, 0.1, 0.9, 0.9, 0.9);

	private static final VoxelShape BASE_SHAPE = Block.makeCuboidShape(6.0D, 6.0D, 6.0D, 10.0D, 10.0D, 10.0D);

	private static final VoxelShape NORTH_SHAPE_NONE = Block.makeCuboidShape(7.0D, 7.0D, 5.75D, 9.0D, 9.0D, 6.0D);
	private static final VoxelShape SOUTH_SHAPE_NONE = Block.makeCuboidShape(7.0D, 7.0D, 10.0D, 9.0D, 9.0D, 10.25D);
	private static final VoxelShape WEST_SHAPE_NONE = Block.makeCuboidShape(5.75D, 7.0D, 7.0D, 6.0D, 9.0D, 9.0D);
	private static final VoxelShape EAST_SHAPE_NONE = Block.makeCuboidShape(10.0D, 7.0D, 7.0D, 10.25D, 9.0D, 9.0D);
	private static final VoxelShape UP_SHAPE_NONE = Block.makeCuboidShape(7.0D, 10.0D, 7.0D, 9.0D, 10.25D, 9.0D);
	private static final VoxelShape DOWN_SHAPE_NONE = Block.makeCuboidShape(7.0D, 5.75D, 7.0D, 9.0D, 6.0D, 9.0D);

	private static final VoxelShape NORTH_SHAPE_CABLE = Block.makeCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 6.0D);
	private static final VoxelShape SOUTH_SHAPE_CABLE = Block.makeCuboidShape(6.0D, 6.0D, 10.0D, 10.0D, 10.0D, 16.0D);
	private static final VoxelShape WEST_SHAPE_CABLE = Block.makeCuboidShape(0.0D, 6.0D, 6.0D, 6.0D, 10.0D, 10.0D);
	private static final VoxelShape EAST_SHAPE_CABLE = Block.makeCuboidShape(10.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
	private static final VoxelShape UP_SHAPE_CABLE = Block.makeCuboidShape(6.0D, 10.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	private static final VoxelShape DOWN_SHAPE_CABLE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D);

	private static final VoxelShape NORTH_SHAPE_CONNECTION = VoxelShapes.or(NORTH_SHAPE_CABLE, Block.makeCuboidShape(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 1.0D));
	private static final VoxelShape SOUTH_SHAPE_CONNECTION = VoxelShapes.or(SOUTH_SHAPE_CABLE, Block.makeCuboidShape(5.0D, 5.0D, 15.0D, 11.0D, 11.0D, 16.0D));
	private static final VoxelShape WEST_SHAPE_CONNECTION = VoxelShapes.or(WEST_SHAPE_CABLE, Block.makeCuboidShape(0.0D, 5.0D, 5.0D, 1.0D, 11.0D, 11.0D));
	private static final VoxelShape EAST_SHAPE_CONNECTION = VoxelShapes.or(EAST_SHAPE_CABLE, Block.makeCuboidShape(15.0D, 5.0D, 5.0D, 16.0D, 11.0D, 11.0D));
	private static final VoxelShape UP_SHAPE_CONNECTION = VoxelShapes.or(UP_SHAPE_CABLE, Block.makeCuboidShape(5.0D, 15.0D, 5.0D, 11.0D, 16.0D, 11.0D));
	private static final VoxelShape DOWN_SHAPE_CONNECTION = VoxelShapes.or(DOWN_SHAPE_CABLE, Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 1.0D, 11.0D));

	public PowerCableBlock() {
		super(Properties.create(Material.IRON)
				.sound(SoundType.METAL)
				.hardnessAndResistance(2.0f));
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(NORTH, PowerCableTile.Mode.MODE_NONE)
				.with(SOUTH, PowerCableTile.Mode.MODE_NONE)
				.with(WEST, PowerCableTile.Mode.MODE_NONE)
				.with(EAST, PowerCableTile.Mode.MODE_NONE)
				.with(UP, PowerCableTile.Mode.MODE_NONE)
				.with(DOWN, PowerCableTile.Mode.MODE_NONE));
	}

	@Override
	public boolean hasTileEntity(BlockState state) { return true; }

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) { return new PowerCableTile(); }

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
//		Log.debug("getShape: " + pos);
		ArrayList<VoxelShape> shapes = new ArrayList<>();
		if (state.get(NORTH) == PowerCableTile.Mode.MODE_NONE) shapes.add(NORTH_SHAPE_NONE);
		else if (state.get(NORTH) == PowerCableTile.Mode.MODE_CABLE) shapes.add(NORTH_SHAPE_CABLE);
		else shapes.add(NORTH_SHAPE_CONNECTION);

		if (state.get(SOUTH) == PowerCableTile.Mode.MODE_NONE) shapes.add(SOUTH_SHAPE_NONE);
		else if (state.get(SOUTH) == PowerCableTile.Mode.MODE_CABLE) shapes.add(SOUTH_SHAPE_CABLE);
		else shapes.add(SOUTH_SHAPE_CONNECTION);

		if (state.get(WEST) == PowerCableTile.Mode.MODE_NONE) shapes.add(WEST_SHAPE_NONE);
		else if (state.get(WEST) == PowerCableTile.Mode.MODE_CABLE) shapes.add(WEST_SHAPE_CABLE);
		else shapes.add(WEST_SHAPE_CONNECTION);

		if (state.get(EAST) == PowerCableTile.Mode.MODE_NONE) shapes.add(EAST_SHAPE_NONE);
		else if (state.get(EAST) == PowerCableTile.Mode.MODE_CABLE) shapes.add(EAST_SHAPE_CABLE);
		else shapes.add(EAST_SHAPE_CONNECTION);

		if (state.get(UP) == PowerCableTile.Mode.MODE_NONE) shapes.add(UP_SHAPE_NONE);
		else if (state.get(UP) == PowerCableTile.Mode.MODE_CABLE) shapes.add(UP_SHAPE_CABLE);
		else shapes.add(UP_SHAPE_CONNECTION);

		if (state.get(DOWN) == PowerCableTile.Mode.MODE_NONE) shapes.add(DOWN_SHAPE_NONE);
		else if (state.get(DOWN) == PowerCableTile.Mode.MODE_CABLE) shapes.add(DOWN_SHAPE_CABLE);
		else shapes.add(DOWN_SHAPE_CONNECTION);

//		if (state.get(NORTH) != PowerCableTile.Mode.MODE_NONE) shapes.add(NORTH_SHAPE_CABLE);
//		if (state.get(SOUTH) != PowerCableTile.Mode.MODE_NONE) shapes.add(SOUTH_SHAPE_CABLE);
//		if (state.get(WEST) != PowerCableTile.Mode.MODE_NONE) shapes.add(WEST_SHAPE_CABLE);
//		if (state.get(EAST) != PowerCableTile.Mode.MODE_NONE) shapes.add(EAST_SHAPE_CABLE);
//		if (state.get(UP) != PowerCableTile.Mode.MODE_NONE) shapes.add(UP_SHAPE_CABLE);
//		if (state.get(DOWN) != PowerCableTile.Mode.MODE_NONE) shapes.add(DOWN_SHAPE_CABLE);

		if (!shapes.isEmpty()) return VoxelShapes.or(BASE_SHAPE, shapes.toArray(new VoxelShape[0]));
		return BASE_SHAPE;
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
		if (!world.isRemote) {
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof PowerCableTile) {
				PowerCableTile tileentity = (PowerCableTile) te;
				tileentity.changeMode(result.getFace(), Mode.MODE_NONE);
			}
		}
		return ActionResultType.PASS;
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
		if (!world.isRemote) {
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof PowerCableTile) {
				PowerCableTile tileentity = (PowerCableTile) te;
				Direction side = Direction.getFacingFromVector(
						fromPos.getX() - pos.getX(),
						fromPos.getY() - pos.getY(),
						fromPos.getZ() - pos.getZ());
				PowerCableTile.Mode newMode = getMode(world, pos, fromPos, side.getOpposite());
				tileentity.changeMode(side, newMode);
			}
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(NORTH, SOUTH, WEST, EAST, DOWN, UP);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState state = super.getStateForPlacement(context);
		for (Direction side : Direction.values()) {
			Mode mode = getMode(context.getWorld(), context.getPos(), context.getPos().add(side.getDirectionVec()), side.getOpposite());
			state = state.with(getProp(side), mode);
		}
		return state;
	}

	private PowerCableTile.Mode getMode(World world, BlockPos pos, BlockPos fromPos, Direction otherSide) {
		PowerCableTile.Mode mode = PowerCableTile.Mode.MODE_NONE;
		if (world.getBlockState(fromPos).getBlock() == Registration.POWER_CABLE_BLOCK.get())
			mode = Mode.MODE_CABLE;
		else {
			TileEntity tile = world.getTileEntity(fromPos);
			if (tile != null) {
				LazyOptional<IEnergyStorage> storage = tile.getCapability(CapabilityEnergy.ENERGY, otherSide);
				if (storage.isPresent()) mode = PowerCableTile.Mode.MODE_CONNECTION;
			}
		}
		return mode;
	}

	public static EnumProperty<PowerCableTile.Mode> getProp(Direction side) {
		switch (side) {
			case NORTH:
				return PowerCableBlock.NORTH;
			case SOUTH:
				return PowerCableBlock.SOUTH;
			case WEST:
				return PowerCableBlock.WEST;
			case EAST:
				return PowerCableBlock.EAST;
			case UP:
				return PowerCableBlock.UP;
			case DOWN:
			default:
				return PowerCableBlock.DOWN;
		}
	}
}
