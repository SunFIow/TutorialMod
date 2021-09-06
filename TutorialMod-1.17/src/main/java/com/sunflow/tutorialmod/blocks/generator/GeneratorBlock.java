package com.sunflow.tutorialmod.blocks.generator;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

public class GeneratorBlock extends Block implements EntityBlock {

	public GeneratorBlock() {
		super(Properties.of(Material.METAL)
				.sound(SoundType.METAL)
				.lightLevel(state -> state.hasProperty(BlockStateProperties.POWERED) ? 8 : 0)
				.strength(2.0f));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new GeneratorBE(pos, state);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		if (level.isClientSide()) return null;
		return (level1, pos1, state1, be) -> {
			if (be instanceof GeneratorBE generator) {
				generator.tickServer(state1);
			}
		};
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.FACING, BlockStateProperties.POWERED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(BlockStateProperties.FACING, context.getNearestLookingDirection().getOpposite());
	}

	@Override
	public void appendHoverText(ItemStack stack, BlockGetter reader, List<Component> list, TooltipFlag flags) {
		list.add(new TranslatableComponent("message.tutorialmod.generator.tooltip"));
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (!world.isClientSide()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof GeneratorBE) {
				MenuProvider containerProvider = new MenuProvider() {
					@Override
					public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player player) {
						return new GeneratorContainer(windowId, world, pos, playerInventory, player);
					}

					@Override
					public Component getDisplayName() {
						return new TranslatableComponent("screen.tutorialmod.generator");
					}
				};
				NetworkHooks.openGui((ServerPlayer) player, containerProvider, blockEntity.getBlockPos());
			} else {
				throw new IllegalStateException("Our named container provider is missing!");
			}
		}
		return InteractionResult.SUCCESS;
	}

}
