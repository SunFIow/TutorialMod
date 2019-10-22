package com.sunflow.tutorialmod.util.handlers;

import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.WeakHashMap;

import com.google.common.collect.ImmutableSet;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.Reference;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

//@OnlyIn(Dist.CLIENT)
public class PlayerSkinHandler {
	private static final ImmutableSet<String> UUIDS = ImmutableSet.of("id1", "id2");

	private static final Set<PlayerEntity> done = Collections.newSetFromMap(new WeakHashMap<PlayerEntity, Boolean>());

	@SubscribeEvent
	public static void playerJoined(ClientPlayerNetworkEvent.LoggedInEvent event) {
//		net.minecraftforge.client.event.ClientPlayerNetworkEvent.

		PlayerEntity player = event.getPlayer();
		System.out.println(player.getServer().getPlayerList());

		String uuid = PlayerEntity.getUUID(player.getGameProfile()).toString();
//		if (player instanceof AbstractClientPlayerEntity && !done.contains(player) && UUIDS.contains(uuid)) {

		TutorialMod.LOGGER.info("Joined " + player);
		if (player instanceof AbstractClientPlayerEntity && !done.contains(player)) {
			AbstractClientPlayerEntity clplayer = (AbstractClientPlayerEntity) player;
			ResourceLocation loc = new ResourceLocation(TutorialMod.MODID, "textures/entity/skins/customplayer_" + clplayer.getSkinType() + "_" + new Random().nextInt(3) + ".png");
			changePlayerSkin(clplayer, loc);
			done.add(player);
		}
	}

	public static final void changePlayerSkin(AbstractClientPlayerEntity player, ResourceLocation location) {
		if (player.hasPlayerInfo()) {
			player.sendStatusMessage(new StringTextComponent("LoL"), false);
			NetworkPlayerInfo info = ObfuscationReflectionHelper.getPrivateValue(AbstractClientPlayerEntity.class, player, Reference.OBFUSCATED_PLAYER_INFO[2]);
			Map<MinecraftProfileTexture.Type, ResourceLocation> textures = ObfuscationReflectionHelper.getPrivateValue(NetworkPlayerInfo.class, info, Reference.OBFUSCATED_PLAYER_TEXTURES[2]);
			textures.put(MinecraftProfileTexture.Type.SKIN, location);
		}
	}

	private static class PlayerSkin {
		private AbstractClientPlayerEntity player;
		private ResourceLocation location;

		public PlayerSkin(AbstractClientPlayerEntity player, ResourceLocation location) {
			this.player = player;
			this.location = location;
		}

		public AbstractClientPlayerEntity getPlayer() {
			return player;
		}

		public ResourceLocation getLocation() {
			return location;
		}
//
//		public void setPlayer(AbstractClientPlayerEntity player) {
//			this.player = player;
//		}
//
//		public void setLocation(ResourceLocation location) {
//			this.location = location;
//		}
	}

}