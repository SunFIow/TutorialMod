package com.sunflow.tutorialmod.network.packet;

import java.util.UUID;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.util.handlers.PlayerSkinHandler;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlayerSkinPacket extends BasePacket {
	private static final ResourceLocation dummy = new ResourceLocation("dummy", "dummy");

	private UUID uuid;
	private ResourceLocation location = dummy;
	private String path = "";

	public PlayerSkinPacket(UUID uuid, ResourceLocation location) { this.uuid = uuid; this.location = location; }

	public PlayerSkinPacket(UUID uuid, String path) { this.uuid = uuid; this.path = path; }

	public PlayerSkinPacket(PacketBuffer buf) { uuid = buf.readUniqueId(); location = buf.readResourceLocation(); path = buf.readString(32767); }

	@Override
	public void encode(PacketBuffer buf) { buf.writeUniqueId(uuid); buf.writeResourceLocation(location); buf.writeString(path); }

	@Override
	protected boolean action(NetworkEvent.Context ctx) {
		if (ctx.getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			Networking.sendToConnected(this);
			return true;
		}
		AbstractClientPlayerEntity cpe = (AbstractClientPlayerEntity) TutorialMod.proxy.getClientWorld().getPlayerByUuid(uuid); // Player who changed his Skin
		if (!path.equals("")) location = new ResourceLocation(TutorialMod.MODID, path + "_" + cpe.getSkinType() + ".png");
		PlayerSkinHandler.changePlayerSkin(cpe, location);
		return true;
	}
}
