package com.sunflow.tutorialmod.network.packet;

import java.util.UUID;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.util.handlers.PlayerSkinHandler;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public class PlayerSkinPacket extends BasePacket {
	private static final ResourceLocation dummy = new ResourceLocation("dummy", "dummy");

	private UUID uuid;
	private ResourceLocation location = dummy;
	private String path = "";

	public PlayerSkinPacket(UUID uuid, ResourceLocation location) { this.uuid = uuid; this.location = location; }

	public PlayerSkinPacket(UUID uuid, String path) { this.uuid = uuid; this.path = path; }

	public PlayerSkinPacket(FriendlyByteBuf buf) { uuid = buf.readUUID(); location = buf.readResourceLocation(); path = buf.readUtf(); }

	@Override
	public void encode(FriendlyByteBuf buf) { buf.writeUUID(uuid); buf.writeResourceLocation(location); buf.writeUtf(path); }

	@Override
	protected boolean action(NetworkEvent.Context ctx) {
		if (ctx.getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			Networking.sendToConnected(this);
			return true;
		}

		AbstractClientPlayer cpe = (AbstractClientPlayer) TutorialMod.proxy.getClientWorld().getPlayerByUUID(uuid); // Player who changed his Skin
		if (!path.equals("")) location = new ResourceLocation(TutorialMod.MODID, path + "_" + cpe.getModelName() + ".png");
		PlayerSkinHandler.changePlayerSkin(cpe, location);
		return true;
	}
}
