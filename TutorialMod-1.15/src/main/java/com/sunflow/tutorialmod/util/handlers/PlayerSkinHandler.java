package com.sunflow.tutorialmod.util.handlers;

import java.util.Map;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.PlayerLoggedInPacket;
import com.sunflow.tutorialmod.network.packet.PlayerSkinChangedPacket;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class PlayerSkinHandler {
//	public static final String[] OBFUSCATED_PLAYER_INFO = new String[] { "d", "field_175157_a", "playerInfo" };
	public static final String[] OBFUSCATED_PLAYER_TEXTURES = new String[] { "a", "field_187107_a", "playerTextures" };

	public static void playerJoined(ClientPlayerNetworkEvent.LoggedInEvent event) { Networking.sendToServer(new PlayerLoggedInPacket()); }

	public static final void changePlayerSkin(AbstractClientPlayerEntity player, ResourceLocation location) {
		if (!player.hasPlayerInfo()) throw new IllegalAccessError("The player needs to have Player Info");
		NetworkPlayerInfo playerInfo = TutorialMod.proxy.getMinecraft().getConnection().getPlayerInfo(player.getUniqueID());
		Map<MinecraftProfileTexture.Type, ResourceLocation> textures = ObfuscationReflectionHelper.getPrivateValue(NetworkPlayerInfo.class, playerInfo, OBFUSCATED_PLAYER_TEXTURES[2]);
		if (!textures.containsKey(MinecraftProfileTexture.Type.SKIN) || !textures.get(MinecraftProfileTexture.Type.SKIN).equals(location)) {
			textures.put(MinecraftProfileTexture.Type.SKIN, location);
			Networking.sendToServer(new PlayerSkinChangedPacket(player.getUniqueID(), location));
		}
	}
}