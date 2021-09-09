package com.sunflow.tutorialmod.network.packets;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

public abstract class BasePacket {

	protected BasePacket(FriendlyByteBuf buf) {}

	protected BasePacket() {}

	public void encode(FriendlyByteBuf buf) {}

	public final void onMessage(Supplier<NetworkEvent.Context> ctx) { ctx.get().enqueueWork(() -> ctx.get().setPacketHandled(action(ctx.get()))); }

	protected abstract boolean action(NetworkEvent.Context ctx);

}
