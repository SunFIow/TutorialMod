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
		String tex_loc_field_name = "f_105299_"; // "b", "f_105299_", "textureLocations", "field_187107_a", "a"
		Map<MinecraftProfileTexture.Type, ResourceLocation> textures = ObfuscationReflectionHelper.getPrivateValue(PlayerInfo.class, playerInfo, tex_loc_field_name);
		textures.compute(MinecraftProfileTexture.Type.SKIN, (type, loc) -> {
			if (!location.equals(loc)) Networking.sendToServer(new PlayerSkinChangedPacket(player.getUUID(), location));
			return location;
		});
	}
}