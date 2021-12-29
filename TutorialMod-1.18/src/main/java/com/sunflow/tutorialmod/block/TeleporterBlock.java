package com.sunflow.tutorialmod.block;

import com.sunflow.tutorialmod.util.Log;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
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
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) { // TODO Auto-generated method stub
		if (!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND) {
			if (pPlayer.isCrouching()) {
				dimensionID++;
				if (dimensionID > 1) {
					dimensionID = -1;
				}
				pPlayer.displayClientMessage(new TextComponent("Linked to Dimension " + dimensionID), true);
			} else {
				if (pPlayer instanceof ServerPlayer) {
					// TeleportationTools.teleportToDimension((ServerPlayerEntity) player, this.dimensionID, player.func_233580_cy_());
					// player.attemptTeleport(p_213373_1_, p_213373_3_, p_213373_5_, p_213373_7_)
					// ((ServerPlayerEntity) player).teleport(ServerWorld
					// 		/*     */ dimensionID == -1 ? World.field_234919_h_
					// 				: dimensionID == +0 ? World.field_234918_g_
					// 						/*       */ : World.field_234920_i_,
					// 		player.getPosX(), player.getPosY(), player.getPosZ(), player.cameraYaw, player.rotationPitch);
				} else {
					Log.warn("You are not an ServerPlayerEntity");
				}
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}
}
