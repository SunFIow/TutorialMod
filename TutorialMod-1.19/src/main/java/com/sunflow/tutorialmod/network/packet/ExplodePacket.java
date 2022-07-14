package com.sunflow.tutorialmod.network.packet;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public class ExplodePacket extends BasePacket {
	private int posX;

	public ExplodePacket(int posX) {
		this.posX = posX;
	}

	public ExplodePacket(FriendlyByteBuf buf) {
		this.posX = buf.readInt();
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeInt(posX);
	}

	@Override
	protected boolean action(NetworkEvent.Context ctx) {
		if (ctx.getDirection().getOriginationSide() == LogicalSide.SERVER) handleClientSide(ctx.getSender());
		else handleServerSide(ctx.getSender());

		return true;
	}

	public void handleServerSide(ServerPlayer sender) {
		TutorialMod.LOGGER.warn("handleServerSide");
		TutorialMod.LOGGER.info(sender);
		TutorialMod.LOGGER.info(this);
		TutorialMod.LOGGER.info("");
	}

	public void handleClientSide(ServerPlayer sender) {
		TutorialMod.LOGGER.warn("handleClientSide");
		TutorialMod.LOGGER.info(sender);
		TutorialMod.LOGGER.info(this);
		TutorialMod.LOGGER.info("");
	}

}
