package com.sunflow.tutorialmod.network;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;;

public class NetworkHandler {
	public static final SimpleChannel tutorialModChannel = null;// = NetworkHandler.getTutorialModChannel();

	public static final ResourceLocation TUTORIALMOD_MAIN_RESOURCE = new ResourceLocation(TutorialMod.MODID, "main");
	public static final String TUTORIALMOD_NETMARKER = "TUTORIALMOD";
	public static final int TUTORIALMOD_NETVERSION = 41;
	public static final String NETVERSION = TUTORIALMOD_NETMARKER + TUTORIALMOD_NETVERSION;

	private static int id = 15;

	public static String init() {
		return NetworkHandler.NETVERSION;
	}

	public static SimpleChannel getTutorialModChannel() {
		SimpleChannel channel = NetworkRegistry.ChannelBuilder
				.named(NetworkHandler.TUTORIALMOD_MAIN_RESOURCE)
				.clientAcceptedVersions(a -> true)
				.serverAcceptedVersions(a -> true)
				.networkProtocolVersion(() -> NetworkHandler.NETVERSION)
				.simpleChannel();

		channel.messageBuilder(MessageExplode.class, NetworkHandler.id++)
				.decoder(MessageExplode::decode)
				.encoder(MessageExplode::encode)
				.consumer(MessageExplode::onMessage)
				.add();

		return channel;
	}

	// Sending to Server
	public static void sendToServer(MessageExplode msg) {
		tutorialModChannel.sendToServer(msg);
	}

	// Sending to one player
	public static void sendToPlayer(Supplier<ServerPlayerEntity> player, MessageExplode msg) {
		tutorialModChannel.send(PacketDistributor.PLAYER.with(player), msg);
	}

	// Send to all players tracking this chunk
	public static void sendToChunk(Supplier<Chunk> chunk, MessageExplode msg) {
		tutorialModChannel.send(PacketDistributor.TRACKING_CHUNK.with(chunk), msg);
	}

	// Sending to all connected players
	public static void sendToConnected(MessageExplode msg) {
		tutorialModChannel.send(PacketDistributor.ALL.noArg(), msg);
	}
}
