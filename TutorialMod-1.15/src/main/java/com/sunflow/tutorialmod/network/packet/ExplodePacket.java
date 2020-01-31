package com.sunflow.tutorialmod.network.packet;

import com.sunflow.tutorialmod.util.Log;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public class ExplodePacket extends BasePacket {
	private int posX;

	public ExplodePacket(int posX) {
		this.posX = posX;
	}

	public ExplodePacket(PacketBuffer buf) {
		this.posX = buf.readInt();
	}

	@Override
	public void encode(PacketBuffer buf) {
		buf.writeInt(posX);
	}

	@Override
	protected boolean action(NetworkEvent.Context ctx) {
		if (ctx.getDirection().getOriginationSide() == LogicalSide.SERVER) handleClientSide(ctx.getSender());
		else handleServerSide(ctx.getSender());

		return true;
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
