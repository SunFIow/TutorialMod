package com.sunflow.tutorialmod.block;

import com.sunflow.tutorialmod.command.util.TeleportationTools;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class TeleporterBlock extends Block {

	private int dimensionID;

	public TeleporterBlock(int dimensionID) {
		super(Properties.of(Material.STONE));
		this.dimensionID = dimensionID;
	}

	public TeleporterBlock() { this(0); }

	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		if (!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND) {
			if (pPlayer.isCrouching()) {
				dimensionID++;
				if (dimensionID > 1) {
					dimensionID = -1;
				}
				pPlayer.displayClientMessage(new TextComponent("Linked to Dimension " + dimensionID), true);
			} else {
				if (pPlayer instanceof ServerPlayer serverPlayer) {
					TeleportationTools.teleportToDimension(serverPlayer, mapIntToDim(this.dimensionID), serverPlayer.blockPosition());
				} else {
					Log.warn("You are not an ServerPlayerEntity");
				}
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}

	private static ResourceKey<Level> mapIntToDim(int dimensionID) {
		switch (dimensionID) {
			case -1:
				return Level.NETHER;
			case 0:
				return Level.OVERWORLD;
			case 1:
				return Level.END;
			default:
				return null;
		}
	}
}
