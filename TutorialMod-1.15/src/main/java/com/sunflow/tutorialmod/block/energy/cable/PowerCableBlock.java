package com.sunflow.tutorialmod.block.energy.cable;

import javax.annotation.Nullable;

import com.sunflow.tutorialmod.block.energy.cable.PowerCableTile.Mode;
import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;

public class PowerCableBlock extends Block {

	public static final EnumProperty<PowerCableTile.Mode> DOWN = EnumProperty.create("down", PowerCableTile.Mode.class);
	public static final EnumProperty<PowerCableTile.Mode> UP = EnumProperty.create("up", PowerCableTile.Mode.class);
	public static final EnumProperty<PowerCableTile.Mode> NORTH = EnumProperty.create("north", PowerCableTile.Mode.class);
	public static final EnumProperty<PowerCableTile.Mode> SOUTH = EnumProperty.create("south", PowerCableTile.Mode.class);
	public static final EnumProperty<PowerCableTile.Mode> WEST = EnumProperty.create("west", PowerCableTile.Mode.class);
	public static final EnumProperty<PowerCableTile.Mode> EAST = EnumProperty.create("east", PowerCableTile.Mode.class);

	private static final VoxelShape[][][][][][] SHAPES;

	static {
		VoxelShape DOWN_SHAPE_NONE = Block.makeCuboidShape(7.0D, 5.75D, 7.0D, 9.0D, 6.0D, 9.0D);
		VoxelShape UP_SHAPE_NONE = Block.makeCuboidShape(7.0D, 10.0D, 7.0D, 9.0D, 10.25D, 9.0D);
		VoxelShape NORTH_SHAPE_NONE = Block.makeCuboidShape(7.0D, 7.0D, 5.75D, 9.0D, 9.0D, 6.0D);
		VoxelShape SOUTH_SHAPE_NONE = Block.makeCuboidShape(7.0D, 7.0D, 10.0D, 9.0D, 9.0D, 10.25D);
		VoxelShape WEST_SHAPE_NONE = Block.makeCuboidShape(5.75D, 7.0D, 7.0D, 6.0D, 9.0D, 9.0D);
		VoxelShape EAST_SHAPE_NONE = Block.makeCuboidShape(10.0D, 7.0D, 7.0D, 10.25D, 9.0D, 9.0D);

		VoxelShape DOWN_SHAPE_CABLE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D);
		VoxelShape UP_SHAPE_CABLE = Block.makeCuboidShape(6.0D, 10.0D, 6.0D, 10.0D, 16.0D, 10.0D);
		VoxelShape NORTH_SHAPE_CABLE = Block.makeCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 6.0D);
		VoxelShape SOUTH_SHAPE_CABLE = Block.makeCuboidShape(6.0D, 6.0D, 10.0D, 10.0D, 10.0D, 16.0D);
		VoxelShape WEST_SHAPE_CABLE = Block.makeCuboidShape(0.0D, 6.0D, 6.0D, 6.0D, 10.0D, 10.0D);
		VoxelShape EAST_SHAPE_CABLE = Block.makeCuboidShape(10.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);

		VoxelShape DOWN_SHAPE_CONNECTION = VoxelShapes.or(DOWN_SHAPE_CABLE, Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 1.0D, 11.0D));
		VoxelShape UP_SHAPE_CONNECTION = VoxelShapes.or(UP_SHAPE_CABLE, Block.makeCuboidShape(5.0D, 15.0D, 5.0D, 11.0D, 16.0D, 11.0D));
		VoxelShape NORTH_SHAPE_CONNECTION = VoxelShapes.or(NORTH_SHAPE_CABLE, Block.makeCuboidShape(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 1.0D));
		VoxelShape SOUTH_SHAPE_CONNECTION = VoxelShapes.or(SOUTH_SHAPE_CABLE, Block.makeCuboidShape(5.0D, 5.0D, 15.0D, 11.0D, 11.0D, 16.0D));
		VoxelShape WEST_SHAPE_CONNECTION = VoxelShapes.or(WEST_SHAPE_CABLE, Block.makeCuboidShape(0.0D, 5.0D, 5.0D, 1.0D, 11.0D, 11.0D));
		VoxelShape EAST_SHAPE_CONNECTION = VoxelShapes.or(EAST_SHAPE_CABLE, Block.makeCuboidShape(15.0D, 5.0D, 5.0D, 16.0D, 11.0D, 11.0D));

		VoxelShape[][] SINGLE_SHAPES = new VoxelShape[][] {
				{ DOWN_SHAPE_NONE, DOWN_SHAPE_CABLE, DOWN_SHAPE_CONNECTION }, // DOWN
				{ UP_SHAPE_NONE, UP_SHAPE_CABLE, UP_SHAPE_CONNECTION }, // UP
				{ NORTH_SHAPE_NONE, NORTH_SHAPE_CABLE, NORTH_SHAPE_CONNECTION }, // NORTH
				{ SOUTH_SHAPE_NONE, SOUTH_SHAPE_CABLE, SOUTH_SHAPE_CONNECTION }, // SOUTH
				{ WEST_SHAPE_NONE, WEST_SHAPE_CABLE, WEST_SHAPE_CONNECTION }, // WEST
				{ EAST_SHAPE_NONE, EAST_SHAPE_CABLE, EAST_SHAPE_CONNECTION }, // EAST
		};

		VoxelShape BASE_SHAPE = Block.makeCuboidShape(6.0D, 6.0D, 6.0D, 10.0D, 10.0D, 10.0D);

		SHAPES = new VoxelShape[3][3][3][3][3][3];

		for (int down = 0; down < 3; down++)
			for (int up = 0; up < 3; up++)
				for (int north = 0; north < 3; north++)
					for (int south = 0; south < 3; south++)
						for (int west = 0; west < 3; west++)
							for (int east = 0; east < 3; east++)

								SHAPES[down][up][north][south][west][east] = VoxelShapes.or(BASE_SHAPE,
										SINGLE_SHAPES[0][down],
										SINGLE_SHAPES[1][up],
										SINGLE_SHAPES[2][north],
										SINGLE_SHAPES[3][south],
										SINGLE_SHAPES[4][west],
										SINGLE_SHAPES[5][east]);
	}

	public PowerCableBlock() {
		super(Properties.create(Material.IRON)
				.sound(SoundType.METAL)
				.hardnessAndResistance(2.0f));
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(DOWN, PowerCableTile.Mode.MODE_NONE)
				.with(UP, PowerCableTile.Mode.MODE_NONE)
				.with(NORTH, PowerCableTile.Mode.MODE_NONE)
				.with(SOUTH, PowerCableTile.Mode.MODE_NONE)
				.with(WEST, PowerCableTile.Mode.MODE_NONE)
				.with(EAST, PowerCableTile.Mode.MODE_NONE));
	}

	@Override
	public boolean hasTileEntity(BlockState state) { return true; }

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) { return new PowerCableTile(); }

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		int down = state.get(DOWN).ordinal();
		int up = state.get(UP).ordinal();
		int north = state.get(NORTH).ordinal();
		int south = state.get(SOUTH).ordinal();
		int west = state.get(WEST).ordinal();
		int east = state.get(EAST).ordinal();

		return SHAPES[down][up][north][south][west][east];
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (player.getHeldItem(hand) == ItemStack.EMPTY)
			Log.debug("{}, {} ,{}, {}", world, player, hand, state);
		return ActionResultType.PASS;
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
		if (world.isRemote) return;
		TileEntity te = world.getTileEntity(pos);
		if (!(te instanceof PowerCableTile)) return;
		PowerCableTile tileentity = (PowerCableTile) te;

		Direction side = Direction.getFacingFromVector(
				fromPos.getX() - pos.getX(),
				fromPos.getY() - pos.getY(),
				fromPos.getZ() - pos.getZ());
		PowerCableTile.Mode newMode = getMode(world, pos, fromPos, side.getOpposite());
		tileentity.changeMode(side, newMode);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(DOWN, UP, NORTH, SOUTH, WEST, EAST);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (world.isRemote) return;
		TileEntity te = world.getTileEntity(pos);
		if (!(te instanceof PowerCableTile)) return;
		PowerCableTile tileentity = (PowerCableTile) te;

		for (Direction side : Direction.values()) {
			Mode newMode = getMode(world, pos, pos.add(side.getDirectionVec()), side.getOpposite());
			state = state.with(getProperty(side), newMode);
			tileentity.changeMode(side, newMode);
		}
		world.setBlockState(pos, state);
	}

	private PowerCableTile.Mode getMode(IWorld world, BlockPos pos, BlockPos fromPos, Direction otherSide) {
		if (world.getBlockState(fromPos).getBlock() == Registration.POWER_CABLE_BLOCK.get()) return Mode.MODE_CABLE;

		TileEntity tile = world.getTileEntity(fromPos);
		if (tile != null && tile.getCapability(CapabilityEnergy.ENERGY, otherSide).isPresent())
			return PowerCableTile.Mode.MODE_CONNECTION;

		return PowerCableTile.Mode.MODE_NONE;
	}

	@SuppressWarnings("unchecked")
	private static final EnumProperty<PowerCableTile.Mode>[] properties = (EnumProperty<Mode>[]) new EnumProperty<?>[] {
			DOWN, UP, NORTH, SOUTH, WEST, EAST };

	public static EnumProperty<PowerCableTile.Mode> getProperty(Direction side) { return properties[side.ordinal()]; }
}
