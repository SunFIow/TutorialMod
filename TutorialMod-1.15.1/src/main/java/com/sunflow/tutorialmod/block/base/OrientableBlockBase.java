package com.sunflow.tutorialmod.block.base;

import com.sunflow.tutorialmod.setup.ModGroups;
import com.sunflow.tutorialmod.util.VersionUtils;

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

	public OrientableBlockBase(String name, ItemGroup group, Properties properties) {
		super(name, group, properties);
		setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
	}

	public OrientableBlockBase(String name, Properties properties) {
		this(name, ModGroups.itemGroup, properties);
	}

	public OrientableBlockBase(String name, Material material) {
		this(name, Block.Properties.create(material).hardnessAndResistance(2.5f));
	}

	public OrientableBlockBase(String name, Material material, float hardness, float resistance, ToolType tooltype, int harvestlevel, SoundType soundtype, int lightlevel) {
		this(name, Block.Properties.create(material).hardnessAndResistance(hardness, resistance).harvestTool(tooltype).harvestLevel(harvestlevel).sound(soundtype).lightValue(lightlevel));
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return getDefaultState().with(FACING, getFacingFromEntity(context.getPos(), context.getPlayer()));
	}

//
	public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
		return Direction.getFacingFromVector(VersionUtils.getX(entity) - clickedBlock.getX(), VersionUtils.getY(entity) - clickedBlock.getY(), VersionUtils.getZ(entity) - clickedBlock.getZ());
	}
}
