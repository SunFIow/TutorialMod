package com.sunflow.tutorialmod.util.handlers;

import java.util.Map;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.PlayerLoggedInPacket;
import com.sunflow.tutorialmod.network.packet.PlayerSkinChangedPacket;

import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class PlayerSkinHandler {

	public static void playerJoined(ClientPlayerNetworkEvent.LoggedInEvent event) { Networking.sendToServer(new PlayerLoggedInPacket()); }

	public static final void changePlayerSkin(AbstractClientPlayer player, ResourceLocation location) {
		if (!player.isCapeLoaded()) throw new IllegalAccessError("The player needs to have Player Info");
		PlayerInfo playerInfo = TutorialMod.proxy.getMinecraft().getConnection().getPlayerInfo(player.getUUID());
		Map<MinecraftProfileTexture.Type, ResourceLocation> textures = ObfuscationReflectionHelper.getPrivateValue(PlayerInfo.class, playerInfo, "textureLocations");
		// if (!textures.containsKey(MinecraftProfileTexture.Type.SKIN) || !textures.get(MinecraftProfileTexture.Type.SKIN).equals(location)) {
		// textures.put(MinecraftProfileTexture.Type.SKIN, location);
		// Networking.sendToServer(new PlayerSkinChangedPacket(player.getUUID(), location));
		// }
		textures.compute(MinecraftProfileTexture.Type.SKIN, (type, loc) -> {
			if (!loc.equals(location)) Networking.sendToServer(new PlayerSkinChangedPacket(player.getUUID(), location));
			return location;
		});
	}
}