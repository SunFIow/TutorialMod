package com.sunflow.tutorialmod.network.packet;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class MultiJumpPacket {

	public MultiJumpPacket(PacketBuffer buf) {}

	public MultiJumpPacket() {}

	public void encode(PacketBuffer buf) {}

	public void onMessage(Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> ctx.get().getSender().fallDistance = -2.0F);
		ctx.get().setPacketHandled(true);
	}
}
