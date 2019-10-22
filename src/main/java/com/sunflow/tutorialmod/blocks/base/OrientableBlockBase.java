package com.sunflow.tutorialmod.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.ToolType;

public class OrientableBlockBase extends BlockBase {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	public OrientableBlockBase(String name, Material material) {
		super(name, material);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	public OrientableBlockBase(String name, Properties properties) {
		super(name, properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	public OrientableBlockBase(String name, Material material, float hardness, float resistance, ToolType tooltype, int harvestlevel, SoundType soundtype, int lightlevel) {
		super(name, material, hardness, resistance, tooltype, harvestlevel, soundtype, lightlevel);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	public OrientableBlockBase(String name, ItemGroup group, Properties properties) {
		super(name, group, properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

//	@Override
//	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
//		if (placer != null) {
//			worldIn.setBlockState(pos, state.with(FACING, getFacingFromEntity(pos, placer)), 2);
//		}
//	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
//		return this.getDefaultState().with(FACING, context.getNearestLookingDirection().getOpposite());
		return this.getDefaultState().with(FACING, getFacingFromEntity(context.getPos(), context.getPlayer()));
	}

//
	public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
		return Direction.getFacingFromVector(entity.posX - clickedBlock.getX(), entity.posY - clickedBlock.getY(), entity.posZ - clickedBlock.getZ());
	}
}
