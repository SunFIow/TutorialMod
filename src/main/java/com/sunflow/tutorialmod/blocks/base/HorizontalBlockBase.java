package com.sunflow.tutorialmod.blocks.base;

import com.sunflow.tutorialmod.TutorialMod;

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

	public HorizontalBlockBase(String name, ItemGroup group, Properties properties) {
		super(name, group, properties);
		setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
	}

	public HorizontalBlockBase(String name, Material material) {
		this(name, Block.Properties.create(material).hardnessAndResistance(2.5f));
	}

	public HorizontalBlockBase(String name, Properties properties) {
		this(name, TutorialMod.setup.itemGroup, properties);
	}

	public HorizontalBlockBase(String name, Material material, float hardness, float resistance, ToolType tooltype, int harvestlevel, SoundType soundtype, int lightlevel) {
		this(name, Block.Properties.create(material).hardnessAndResistance(hardness, resistance).harvestTool(tooltype).harvestLevel(harvestlevel).sound(soundtype).lightValue(lightlevel));
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
		return getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
}
