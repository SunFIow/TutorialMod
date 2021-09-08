package com.sunflow.tutorialmod.blocks.poweruser;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;

public class PowerUserBlock extends Block implements EntityBlock {

	public PowerUserBlock() {
		super(Properties.of(Material.METAL)
				.sound(SoundType.METAL)
				.noOcclusion()
				.lightLevel(state -> state.hasProperty(BlockStateProperties.POWERED) ? 8 : 0)
				.strength(2.0f));
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.POWERED);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new PowerUserBE(pos, state);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		if (level.isClientSide()) return (level1, pos1, state1, be) -> {
			if (be instanceof PowerUserBE poweruser) {
				poweruser.tickClient(state1);
			}
		};
		else return (level1, pos1, state1, be) -> {
			if (be instanceof PowerUserBE poweruser) {
				poweruser.tickServer(state1);
			}
		};
	}

	@Override
	public void appendHoverText(ItemStack stack, BlockGetter reader, List<Component> list, TooltipFlag flags) {
		list.add(new TranslatableComponent("message.tutorialmod.poweruser.tooltip"));
	}

}
