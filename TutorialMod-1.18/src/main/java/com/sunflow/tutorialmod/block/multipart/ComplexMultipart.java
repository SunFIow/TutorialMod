package com.sunflow.tutorialmod.block.multipart;

import java.util.List;

import com.sunflow.tutorialmod.data.provider.SunLanguageProvider;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ComplexMultipart extends Block implements EntityBlock {

	public static final TranslatableComponent TOOLTIP = new TranslatableComponent(SunLanguageProvider.getTranslationKeyMessage("complex_multipart.tooltip"));

	public static final EnumProperty<ComplexMultipartEntity.Mode> NORTH = EnumProperty.create("north", ComplexMultipartEntity.Mode.class);
	public static final EnumProperty<ComplexMultipartEntity.Mode> SOUTH = EnumProperty.create("south", ComplexMultipartEntity.Mode.class);
	public static final EnumProperty<ComplexMultipartEntity.Mode> WEST = EnumProperty.create("west", ComplexMultipartEntity.Mode.class);
	public static final EnumProperty<ComplexMultipartEntity.Mode> EAST = EnumProperty.create("east", ComplexMultipartEntity.Mode.class);
	public static final EnumProperty<ComplexMultipartEntity.Mode> UP = EnumProperty.create("up", ComplexMultipartEntity.Mode.class);
	public static final EnumProperty<ComplexMultipartEntity.Mode> DOWN = EnumProperty.create("down", ComplexMultipartEntity.Mode.class);

	private static final VoxelShape RENDER_SHAPE = Block.box(0.1, 0.1, 0.1, 0.9, 0.9, 0.9);

	public ComplexMultipart() {
		super(Properties.of(Material.METAL)
				.sound(SoundType.METAL)
				.strength(2.0f));
		registerDefaultState(this.getStateDefinition().any()
				.setValue(NORTH, ComplexMultipartEntity.Mode.MODE_NONE)
				.setValue(SOUTH, ComplexMultipartEntity.Mode.MODE_NONE)
				.setValue(WEST, ComplexMultipartEntity.Mode.MODE_NONE)
				.setValue(EAST, ComplexMultipartEntity.Mode.MODE_NONE)
				.setValue(UP, ComplexMultipartEntity.Mode.MODE_NONE)
				.setValue(DOWN, ComplexMultipartEntity.Mode.MODE_NONE));
	}

	@Override
	public void appendHoverText(ItemStack pStack, BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
		pTooltip.add(TOOLTIP);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new ComplexMultipartEntity(pPos, pState);
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
		return RENDER_SHAPE;
	}

	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		if (!pLevel.isClientSide()) {
			BlockEntity be = pLevel.getBlockEntity(pPos);
			if (be instanceof ComplexMultipartEntity complexEntity) {
				complexEntity.toggleMode(pHit.getDirection());
			}
		}
		return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		super.createBlockStateDefinition(pBuilder);
		pBuilder.add(NORTH, SOUTH, WEST, EAST, DOWN, UP);
	}
}
