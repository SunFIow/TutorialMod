package com.sunflow.tutorialmod.network.packet;

import java.util.UUID;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlayerSkinChangedPacket extends BasePacket {

	private UUID uuid;
	private ResourceLocation location;

	public PlayerSkinChangedPacket(UUID uuid, ResourceLocation location) { this.uuid = uuid; this.location = location; }

	public PlayerSkinChangedPacket(PacketBuffer buf) { uuid = buf.readUniqueId(); location = buf.readResourceLocation(); }

	@Override
	public void encode(PacketBuffer buf) { buf.writeUniqueId(uuid); buf.writeResourceLocation(location); }

	@Override
	protected boolean action(NetworkEvent.Context ctx) {
		ServerPlayerEntity player = ctx.getSender().getServer().getPlayerList().getPlayerByUUID(uuid);
		CompoundNBT tag = player.getPersistentData();
		tag.putString("skin", location.toString());
		return true;
	}
}
