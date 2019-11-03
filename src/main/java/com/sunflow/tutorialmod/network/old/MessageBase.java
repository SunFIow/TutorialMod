package com.sunflow.tutorialmod.network.old;

import java.util.function.Supplier;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public abstract class MessageBase {

	public void onMessage(MessageBase msg, Supplier<NetworkEvent.Context> ctx) {
		if (ctx.get().getDirection().getOriginationSide() == LogicalSide.SERVER) {
			handleServerSide(msg, ctx.get().getSender());
		} else {
			handleClientSide(msg, ctx.get().getSender());
		}
//		ctx.get().setPacketHandled(true);
	}

	public static void encode(MessageBase msg, PacketBuffer buf) {}

	public static MessageBase decode(PacketBuffer buf) {
		return null;
	}

	public abstract void handleServerSide(MessageBase msg, ServerPlayerEntity sender);

	public abstract void handleClientSide(MessageBase msg, ServerPlayerEntity sender);
}