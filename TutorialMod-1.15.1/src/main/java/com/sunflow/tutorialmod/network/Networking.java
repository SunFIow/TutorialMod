package com.sunflow.tutorialmod.network;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.network.packet.ExplodePacket;
import com.sunflow.tutorialmod.network.packet.MultiJumpPacket;
import com.sunflow.tutorialmod.network.packet.OpenGuiPacket;
import com.sunflow.tutorialmod.network.packet.SpawnPacket;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Networking {
	private static int ID = 0;

	public static final String TUTORIALMOD_NETMARKER = TutorialMod.MODID.toUpperCase();;
	public static final int TUTORIALMOD_NETVERSION = 1;
	public static final String NETVERSION = TUTORIALMOD_NETMARKER + ":" + TUTORIALMOD_NETVERSION;

	public static final ResourceLocation TUTORIALMOD_MAIN_RESOURCE = new ResourceLocation(TutorialMod.MODID, "main");
	public static final SimpleChannel TUTORIALMOD_CHANNEL = getTutorialModChannel();

	private Networking() {}

	private static int nextID() { return ID++; }

	public static String init() { return NETVERSION; }

	public static SimpleChannel getTutorialModChannel() {
		SimpleChannel channel = NetworkRegistry.ChannelBuilder
				.named(TUTORIALMOD_MAIN_RESOURCE)
				.networkProtocolVersion(() -> NETVERSION)
				.clientAcceptedVersions(s -> true)
				.serverAcceptedVersions(s -> true)
				.simpleChannel();

		channel.registerMessage(nextID(),
				OpenGuiPacket.class,
				OpenGuiPacket::encode,
				OpenGuiPacket::new,
				OpenGuiPacket::onMessage);

		channel.registerMessage(nextID(),
				SpawnPacket.class,
				SpawnPacket::encode,
				SpawnPacket::new,
				SpawnPacket::onMessage);

		channel.messageBuilder(ExplodePacket.class, nextID())
				.encoder(ExplodePacket::encode)
				.decoder(ExplodePacket::new)
				.consumer(ExplodePacket::onMessage)
				.add();

		channel.messageBuilder(MultiJumpPacket.class, nextID())
				.encoder(MultiJumpPacket::encode)
				.decoder(MultiJumpPacket::new)
				.consumer(MultiJumpPacket::onMessage)
				.add();

		return channel;
	}

	// Sending to Server
	public static <MSG> void sendToServer(MSG msg) {
		TUTORIALMOD_CHANNEL.sendToServer(msg);
	}

	// Sending to one player
	public static <MSG> void sendToPlayer(Supplier<ServerPlayerEntity> player, MSG msg) {
		TUTORIALMOD_CHANNEL.send(PacketDistributor.PLAYER.with(player), msg);
	}

	// Send to all players tracking this chunk
	public static <MSG> void sendToChunk(Supplier<Chunk> chunk, MSG msg) {
		TUTORIALMOD_CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(chunk), msg);
	}

	// Sending to all connected players
	public static <MSG> void sendToConnected(MSG msg) {
		TUTORIALMOD_CHANNEL.send(PacketDistributor.ALL.noArg(), msg);
	}

	public static <MSG> void sendTo(MSG packet, NetworkManager manager, NetworkDirection direction) {
		TUTORIALMOD_CHANNEL.sendTo(packet, manager, direction);
	}
}
