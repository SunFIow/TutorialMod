package com.sunflow.tutorialmod.network.packet;

import com.sunflow.tutorialmod.network.Networking;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlayerLoggedInPacket extends BasePacket {
	public PlayerLoggedInPacket() {}

	public PlayerLoggedInPacket(PacketBuffer buf) {}

	@Override
	protected boolean action(NetworkEvent.Context ctx) {
		ServerPlayerEntity player = ctx.getSender();
		PlayerList list = player.getServer().getPlayerList();
		for (ServerPlayerEntity spe : list.getPlayers()) {
			CompoundNBT tag = spe.getPersistentData();
			if (spe != player && tag.contains("skin")) {
				ResourceLocation location = new ResourceLocation(tag.getString("skin"));
				Networking.sendToPlayer(() -> player, new PlayerSkinPacket(spe.getUniqueID(), location));
			}
		}
		CompoundNBT tag = player.getPersistentData();
		if (tag.contains("skin")) {
			ResourceLocation location = new ResourceLocation(tag.getString("skin"));
			Networking.sendToConnected(new PlayerSkinPacket(player.getUniqueID(), location));
		}

		return true;
	}
}
