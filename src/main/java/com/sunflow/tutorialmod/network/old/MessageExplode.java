package com.sunflow.tutorialmod.network.old;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.util.Log;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageExplode {
	private int posX;

	public MessageExplode(int posX) {
		this.posX = posX;
	}

	public static void onMessage(MessageExplode msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			if (ctx.get().getDirection().getOriginationSide() == LogicalSide.SERVER) {
				handleServerSide(msg, ctx.get().getSender());
			} else {
				handleClientSide(msg, ctx.get().getSender());
			}
		});
		ctx.get().setPacketHandled(true);
	}

	public static void encode(MessageExplode msg, PacketBuffer buf) {
		buf.writeInt(msg.posX);
	}

	public static MessageExplode decode(PacketBuffer buf) {
		return new MessageExplode(buf.readInt());
	}

	public static void handleServerSide(MessageExplode msg, ServerPlayerEntity sender) {
		Log.warn("handleServerSide");
		Log.info(sender);
		Log.info(msg);
		Log.info("");
	}

	public static void handleClientSide(MessageExplode msg, ServerPlayerEntity sender) {
		Log.warn("handleClientSide");
		Log.info(sender);
		Log.info(msg);
		Log.info("");
	}

}
