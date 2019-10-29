package com.sunflow.tutorialmod.blocks.base;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class EnergyTileBlockBase extends PoweredTileBlockBase {
	public static final IntegerProperty FILLLEVEL = BlockStateProperties.POWER_0_15;

	public EnergyTileBlockBase(String name, ItemGroup group, Properties properties) {
		super(name, group, properties);
		setDefaultState(getDefaultState().with(FILLLEVEL, Integer.valueOf(0)));
	}

	public EnergyTileBlockBase(String name, Properties properties) {
		this(name, TutorialMod.setup.itemGroup, properties);
	}

	public EnergyTileBlockBase(String name, Material material, SoundType sound, float hardness, float resistance) {
		this(name, Properties.create(material)
				.sound(sound)
				.hardnessAndResistance(hardness, resistance));
	}

	public EnergyTileBlockBase(String name, Material material, float hardnessAndResistance, int lightValue) {
		this(name, Properties.create(material).hardnessAndResistance(hardnessAndResistance).lightValue(lightValue));
	}

	public EnergyTileBlockBase(String name, Material material, float hardnessAndResistance) {
		this(name, Properties.create(material)
				.hardnessAndResistance(hardnessAndResistance));
	}

	public EnergyTileBlockBase(String name) {
		this(name, Material.ROCK, 2.0f);
	}

	@Override
	public int getLightValue(BlockState state) {
		return state.get(POWERED) ? (int) (state.get(FILLLEVEL) / 15F * lightValue) : 0;
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(BlockState state, World world, BlockPos pos) {
		return state.get(FILLLEVEL);
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FILLLEVEL);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context).with(FILLLEVEL, Integer.valueOf(0));
	}
}
