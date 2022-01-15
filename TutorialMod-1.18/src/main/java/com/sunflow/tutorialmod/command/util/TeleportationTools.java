package com.sunflow.tutorialmod.command.util;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

public class TeleportationTools {

	private TeleportationTools() {}

	public static void teleportToDimension(ServerPlayer player, ResourceKey<Level> dimensionType, BlockPos pos) {
		final ServerLevel level = player.getServer().getLevel(dimensionType);

		level.getChunkSource().addRegionTicket(TicketType.POST_TELEPORT, new ChunkPos(pos), 1, player.getId());
		if (player.isSleeping()) player.stopSleeping();
		player.stopRiding();
		if (level == player.level) player.teleportTo(pos.getX(), pos.getY(), pos.getZ());
		else player.teleportTo(level, pos.getX(), pos.getY(), pos.getZ(), player.getYRot(), player.getYRot());
		//		player.setRotationYawHead(player.rotationYaw);
	}

}
