package com.sunflow.tutorialmod.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class PoweredTileBlockBase extends TileBlockBase {
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	public PoweredTileBlockBase(Properties properties) {
		super(properties);
		setDefaultState(getDefaultState().with(POWERED, false));
	}

	public PoweredTileBlockBase(Material material, SoundType sound, float hardness, float resistance) {
		this(Properties.create(material)
				.sound(sound)
				.hardnessAndResistance(hardness, resistance));
	}

	public PoweredTileBlockBase(Material material, float hardnessAndResistance, int lightValue) {
		this(Properties.create(material).hardnessAndResistance(hardnessAndResistance).lightValue(lightValue));
	}

	public PoweredTileBlockBase(Material material, float hardnessAndResistance) {
		this(Properties.create(material)
				.hardnessAndResistance(hardnessAndResistance));
	}

	public PoweredTileBlockBase() { this(Material.ROCK, 2.0f); }

	@Override
	public int getLightValue(BlockState state) {
		return state.get(POWERED) ? lightValue : 0;
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(POWERED);
	}

	@Override
	public boolean eventReceived(BlockState state, World worldIn, BlockPos pos, int id, int param) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return tileentity == null ? false : tileentity.receiveClientEvent(id, param);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context).with(POWERED, false);
	}
}
