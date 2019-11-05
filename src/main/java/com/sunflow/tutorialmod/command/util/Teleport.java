package com.sunflow.tutorialmod.command.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;

public class Teleport {

	public static void teleportToDimension(PlayerEntity player, int dimension, double x, double y, double z) {
		player.changeDimension(DimensionType.getById(dimension));
//		player.setPositionAndUpdate(x, y, z);
//		player.teleportKeepLoaded(x, y, z);
	}

	public static void teleportToDimension(ServerPlayerEntity player, int dimensionID, BlockPos pos) {
		final ServerWorld world = player.getServer().getWorld(DimensionType.getById(dimensionID));

		world.getChunkProvider().func_217228_a(TicketType.POST_TELEPORT, new ChunkPos(pos), 1, player.getEntityId());
		if (player.isSleeping()) {
			player.wakeUpPlayer(true, true, false);
		}
		player.detach();
		if (world == player.world) {
			player.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), player.rotationYaw, player.rotationPitch);
		} else {
			player.teleport(world, pos.getX(), pos.getY(), pos.getZ(), player.rotationYaw, player.rotationPitch);
		}
		player.setRotationYawHead(player.rotationYaw);
	}
}
