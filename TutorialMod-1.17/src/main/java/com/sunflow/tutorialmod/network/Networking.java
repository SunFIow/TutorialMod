package com.sunflow.tutorialmod.network;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.config.SyncConfigPacket;

import net.minecraft.network.Connection;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.fmllegacy.network.NetworkDirection;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.PacketDistributor;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

public class Networking {
	private static int ID = 0;

	public static final String TUTORIALMOD_NETMARKER = TutorialMod.MODID.toUpperCase();
	public static final String TUTORIALMOD_NETVERSION = "1.0";
	public static final String NETVERSION = TUTORIALMOD_NETMARKER + ":" + TUTORIALMOD_NETVERSION;

	public static final ResourceLocation NAME = new ResourceLocation(TutorialMod.MODID, "tutorialmod");
	public static final SimpleChannel TUTORIALMOD_CHANNEL = getTutorialModChannel();

	private Networking() {}

	private static int nextID() { return ID++; }

	public static String getVersion() { return NETVERSION; }

	private static SimpleChannel getTutorialModChannel() {
		return NetworkRegistry.ChannelBuilder
				.named(NAME)
				.networkProtocolVersion(() -> NETVERSION)
				.clientAcceptedVersions(s -> true)
				.serverAcceptedVersions(s -> true)
				.simpleChannel();
	}

	public static void registerMessages() {
		TUTORIALMOD_CHANNEL.messageBuilder(SyncConfigPacket.class, nextID())
				.encoder(SyncConfigPacket::encode)
				.decoder(SyncConfigPacket::new)
				.consumer(SyncConfigPacket::onMessage)
				.add();

		TUTORIALMOD_CHANNEL.registerMessage(nextID(),
				SyncConfigPacket.class,
				SyncConfigPacket::encode,
				SyncConfigPacket::new,
				SyncConfigPacket::onMessage);
	}

	// Sending to Server
	public static <MSG> void sendToServer(MSG msg) {
		TUTORIALMOD_CHANNEL.sendToServer(msg);
	}

	// Sending to one player
	public static <MSG> void sendToPlayer(Supplier<ServerPlayer> player, MSG msg) {
		TUTORIALMOD_CHANNEL.send(PacketDistributor.PLAYER.with(player), msg);
	}

	// Sending to one player
	public static <MSG> void sendToClient(ServerPlayer player, MSG msg) {
		TUTORIALMOD_CHANNEL.sendTo(msg, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
	}

	// Send to all players tracking this chunk
	public static <MSG> void sendToChunk(Supplier<LevelChunk> chunk, MSG msg) {
		TUTORIALMOD_CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(chunk), msg);
	}

	// Sending to all connected players
	public static <MSG> void sendToConnected(MSG msg) {
		TUTORIALMOD_CHANNEL.send(PacketDistributor.ALL.noArg(), msg);
	}

	public static <MSG> void sendTo(MSG msg, Connection manager, NetworkDirection direction) {
		TUTORIALMOD_CHANNEL.sendTo(msg, manager, direction);
	}

}
