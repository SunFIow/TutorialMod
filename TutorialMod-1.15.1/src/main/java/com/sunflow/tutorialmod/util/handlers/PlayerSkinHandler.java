package com.sunflow.tutorialmod.util.handlers;

import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.WeakHashMap;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.Reference;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

//@OnlyIn(Dist.CLIENT)
public class PlayerSkinHandler {
//	private static final ImmutableSet<String> UUIDS = ImmutableSet.of("id1", "id2");

	private static final Set<PlayerEntity> done = Collections.newSetFromMap(new WeakHashMap<PlayerEntity, Boolean>());

	@SubscribeEvent
	public static void playerJoined(ClientPlayerNetworkEvent.LoggedInEvent event) {
//		net.minecraftforge.client.event.ClientPlayerNetworkEvent.

		ClientPlayerEntity player = event.getPlayer();
//		System.out.println(player.getServer().getPlayerList());

//		String uuid = PlayerEntity.getUUID(player.getGameProfile()).toString();
//		if (UUIDS.contains(uuid)) {
//			player.sendMessage(new StringTextComponent("Welcome Back"));
//		}

//		if (player instanceof AbstractClientPlayerEntity && !done.contains(player) && UUIDS.contains(uuid)) {
		Log.info("Joined " + player);
		if (!done.contains(player)) {
			ResourceLocation loc = new ResourceLocation(TutorialMod.MODID, "textures/entity/skins/customplayer_" + player.getSkinType() + "_" + new Random().nextInt(3) + ".png");
			changePlayerSkin2(player, loc);
			done.add(player);
		}
	}

	public static final void changePlayerSkin(ClientPlayerEntity player, ResourceLocation location) {
		if (player.hasPlayerInfo()) {
			player.sendStatusMessage(new StringTextComponent("LoL"), false);
			NetworkPlayerInfo info = ObfuscationReflectionHelper.getPrivateValue(AbstractClientPlayerEntity.class, player, Reference.OBFUSCATED_PLAYER_INFO[2]);
			Map<MinecraftProfileTexture.Type, ResourceLocation> textures = ObfuscationReflectionHelper.getPrivateValue(NetworkPlayerInfo.class, info, Reference.OBFUSCATED_PLAYER_TEXTURES[2]);
			textures.put(MinecraftProfileTexture.Type.SKIN, location);
		}
	}

	public static final void changePlayerSkin2(ClientPlayerEntity player, ResourceLocation location) {
		if (player.hasPlayerInfo()) {
			player.sendStatusMessage(new StringTextComponent("LoL2"), false);
			NetworkPlayerInfo playerInfo = TutorialMod.proxy.getMinecraft().getConnection().getPlayerInfo(player.getUniqueID());
			NetworkPlayerInfo info = ObfuscationReflectionHelper.getPrivateValue(AbstractClientPlayerEntity.class, player, Reference.OBFUSCATED_PLAYER_INFO[2]);
			Log.warn("{} == {} : {}", playerInfo, info, playerInfo == info);
			Map<MinecraftProfileTexture.Type, ResourceLocation> textures = ObfuscationReflectionHelper.getPrivateValue(NetworkPlayerInfo.class, info, Reference.OBFUSCATED_PLAYER_TEXTURES[2]);
			textures.put(MinecraftProfileTexture.Type.SKIN, location);
		}
	}

//	public static final void changePlayerSkin(ServerPlayerEntity player, ResourceLocation location) {
//		if (player.hasPlayerInfo()) {
//			player.sendStatusMessage(new StringTextComponent("LoL"), false);
//			NetworkPlayerInfo info = ObfuscationReflectionHelper.getPrivateValue(AbstractClientPlayerEntity.class, player, Reference.OBFUSCATED_PLAYER_INFO[2]);
//			Map<MinecraftProfileTexture.Type, ResourceLocation> textures = ObfuscationReflectionHelper.getPrivateValue(NetworkPlayerInfo.class, info, Reference.OBFUSCATED_PLAYER_TEXTURES[2]);
//			textures.put(MinecraftProfileTexture.Type.SKIN, location);
//		}
//	}
}