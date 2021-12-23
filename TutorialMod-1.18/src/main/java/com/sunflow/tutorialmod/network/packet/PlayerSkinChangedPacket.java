package com.sunflow.tutorialmod.network.packet;

import java.util.UUID;

import com.sunflow.tutorialmod.util.NBTUtils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class PlayerSkinChangedPacket extends BasePacket {

	private UUID uuid;
	private ResourceLocation location;

	public PlayerSkinChangedPacket(UUID uuid, ResourceLocation location) { this.uuid = uuid; this.location = location; }

	public PlayerSkinChangedPacket(FriendlyByteBuf buf) { uuid = buf.readUUID(); location = buf.readResourceLocation(); }

	@Override
	public void encode(FriendlyByteBuf buf) { buf.writeUUID(uuid); buf.writeResourceLocation(location); }

	@Override
	protected boolean action(NetworkEvent.Context ctx) {
		ServerPlayer player = ctx.getSender().getServer().getPlayerList().getPlayer(uuid);
		CompoundTag tag = NBTUtils.getModTag(player);
		tag.putString("skin", location.toString());
		return true;
	}
}
