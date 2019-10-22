package com.sunflow.tutorialmod.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ToolType;

public class HorizontalBlockBase extends BlockBase {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public HorizontalBlockBase(String name, Material material) {
		super(name, material);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	public HorizontalBlockBase(String name, Properties properties) {
		super(name, properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	public HorizontalBlockBase(String name, Material material, float hardness, float resistance, ToolType tooltype, int harvestlevel, SoundType soundtype, int lightlevel) {
		super(name, material, hardness, resistance, tooltype, harvestlevel, soundtype, lightlevel);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	public HorizontalBlockBase(String name, ItemGroup group, Properties properties) {
		super(name, group, properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

//	@Override
//	public BlockState rotate(BlockState state, Rotation rot) {
//		return state.with(FACING, rot.rotate(state.get(FACING)));
//	}

//	@Override
//	public BlockState mirror(BlockState state, Mirror mirrorIn) {
//		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
//	}

//	@Override
//	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
//		worldIn.setBlockState(pos, state.with(FACING, placer.getHorizontalFacing().getOpposite()), 2);
//	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
}
