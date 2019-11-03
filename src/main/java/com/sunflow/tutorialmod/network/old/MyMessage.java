package com.sunflow.tutorialmod.network.old;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.util.Log;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

public class MyMessage {
	private int posX;

	public MyMessage(int posX) {

		this.posX = posX;
	}

	public static void encode(MyMessage msg, PacketBuffer buf) {
		buf.writeInt(msg.posX);
	}

	public static MyMessage decode(PacketBuffer buf) {
		return new MyMessage(buf.readInt());
	}

	public static void onMessage(MyMessage msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			// Work that needs to be threadsafe (most work)
			ServerPlayerEntity sender = ctx.get().getSender(); // the client that sent this packet
			// do stuff
			sender.sendMessage(new StringTextComponent("posX: " + msg.posX));
			Log.info(msg.posX);
		});
		ctx.get().setPacketHandled(true);
	}
}