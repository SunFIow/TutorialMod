package com.sunflow.tutorialmod.network.packet;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class MultiJumpPacket extends BasePacket {

	public MultiJumpPacket(PacketBuffer buf) {}

	public MultiJumpPacket() {}

	@Override
	public boolean action(NetworkEvent.Context ctx) {
		ctx.getSender().fallDistance = -2.0F;
		return true;
	}
}
