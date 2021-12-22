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
	private static final String[] OBFUSCATED_PLAYER_INFO = new String[] { "d", "field_175157_a", "playerInfo" };

	private static final String[] OBFUSCATED_PLAYER_TEXTURES = new String[] { "a", "field_187107_a", "playerTextures" };

	public static void playerJoined(ClientPlayerNetworkEvent.LoggedInEvent event) { Networking.sendToServer(new PlayerLoggedInPacket()); }

	public static final void changePlayerSkin(AbstractClientPlayer player, ResourceLocation location) {
		if (!player.isCapeLoaded()) throw new IllegalAccessError("The player needs to have Player Info");
		PlayerInfo playerInfo = TutorialMod.proxy.getMinecraft().getConnection().getPlayerInfo(player.getUUID());
		// player.registerSkinTexture(p_172522_, p_172523_);
		Map<MinecraftProfileTexture.Type, ResourceLocation> textures = ObfuscationReflectionHelper.getPrivateValue(PlayerInfo.class, playerInfo, OBFUSCATED_PLAYER_TEXTURES[2]);
		if (!textures.containsKey(MinecraftProfileTexture.Type.SKIN) || !textures.get(MinecraftProfileTexture.Type.SKIN).equals(location)) {
			textures.put(MinecraftProfileTexture.Type.SKIN, location);
			Networking.sendToServer(new PlayerSkinChangedPacket(player.getUUID(), location));
		}
	}
}