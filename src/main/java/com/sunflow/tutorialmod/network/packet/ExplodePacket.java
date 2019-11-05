package com.sunflow.tutorialmod.network.packet;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.util.Log;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public class ExplodePacket {
	private int posX;

	public ExplodePacket(int posX) {
		this.posX = posX;
	}

	public ExplodePacket(PacketBuffer buf) {
		this.posX = buf.readInt();
	}

	public void encode(PacketBuffer buf) {
		buf.writeInt(posX);
	}

	public void onMessage(Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			if (ctx.get().getDirection().getOriginationSide() == LogicalSide.SERVER) {
				handleServerSide(ctx.get().getSender());
			} else {
				handleClientSide(ctx.get().getSender());
			}
		});
		ctx.get().setPacketHandled(true);
	}

	public void handleServerSide(ServerPlayerEntity sender) {
		Log.warn("handleServerSide");
		Log.info(sender);
		Log.info(this);
		Log.info("");
	}

	public void handleClientSide(ServerPlayerEntity sender) {
		Log.warn("handleClientSide");
		Log.info(sender);
		Log.info(this);
		Log.info("");
	}

}
