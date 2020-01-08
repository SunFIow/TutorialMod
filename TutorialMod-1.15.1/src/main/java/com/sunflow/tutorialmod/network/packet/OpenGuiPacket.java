package com.sunflow.tutorialmod.network.packet;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.command.spawner.SpawnerScreen;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class OpenGuiPacket {

	public OpenGuiPacket(PacketBuffer buf) {}

	public OpenGuiPacket() {}

	public void encode(PacketBuffer buf) {}

	public void onMessage(Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(SpawnerScreen::open);
		ctx.get().setPacketHandled(true);
	}
}
