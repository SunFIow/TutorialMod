package com.sunflow.tutorialmod.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class MultiJumpPacket extends BasePacket {

	public MultiJumpPacket(FriendlyByteBuf buf) {}

	public MultiJumpPacket() {}

	@Override
	public boolean action(NetworkEvent.Context ctx) {
		ctx.getSender().fallDistance = -2.0F;
		return true;
	}
}
