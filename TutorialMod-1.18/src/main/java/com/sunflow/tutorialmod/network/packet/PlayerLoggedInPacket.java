package com.sunflow.tutorialmod.network.packet;

import com.sunflow.tutorialmod.network.Networking;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraftforge.network.NetworkEvent;

public class PlayerLoggedInPacket extends BasePacket {
	public PlayerLoggedInPacket() {}

	public PlayerLoggedInPacket(FriendlyByteBuf buf) {}

	@Override
	protected boolean action(NetworkEvent.Context ctx) {
		ServerPlayer player = ctx.getSender();
		PlayerList list = player.getServer().getPlayerList();
		for (ServerPlayer spe : list.getPlayers()) {
			CompoundTag tag = spe.getPersistentData();
			if (spe != player && tag.contains("skin")) {
				ResourceLocation location = new ResourceLocation(tag.getString("skin"));
				Networking.sendToPlayer(() -> player, new PlayerSkinPacket(spe.getUUID(), location));
			}
		}
		CompoundTag tag = player.getPersistentData();
		if (tag.contains("skin")) {
			ResourceLocation location = new ResourceLocation(tag.getString("skin"));
			Networking.sendToConnected(new PlayerSkinPacket(player.getUUID(), location));
		}

		return true;
	}
}
