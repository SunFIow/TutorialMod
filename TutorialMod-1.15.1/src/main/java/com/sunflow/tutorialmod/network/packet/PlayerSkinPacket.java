package com.sunflow.tutorialmod.network.packet;

import java.util.Random;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.handlers.PlayerSkinHandler;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlayerSkinPacket extends BasePacket {

	public PlayerSkinPacket(PacketBuffer buf) {}

	public PlayerSkinPacket() {}

	@Override
	public void encode(PacketBuffer buf) {}

	@Override
	protected boolean action(NetworkEvent.Context ctx) {
		ServerPlayerEntity spe = ctx.getSender();

		ClientPlayerEntity clplayer = null;
		ResourceLocation loc = new ResourceLocation(TutorialMod.MODID, "textures/entity/skins/customplayer_" + clplayer.getSkinType() + "_" + new Random().nextInt(3) + ".png");
		PlayerSkinHandler.changePlayerSkin2(clplayer, loc);

		return true;
	}
}
