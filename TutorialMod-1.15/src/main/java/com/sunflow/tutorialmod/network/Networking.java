package com.sunflow.tutorialmod.network;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.config.SyncConfigPacket;
import com.sunflow.tutorialmod.network.packet.ExplodePacket;
import com.sunflow.tutorialmod.network.packet.MultiJumpPacket;
import com.sunflow.tutorialmod.network.packet.OpenGuiPacket;
import com.sunflow.tutorialmod.network.packet.PlayerLoggedInPacket;
import com.sunflow.tutorialmod.network.packet.PlayerSkinChangedPacket;
import com.sunflow.tutorialmod.network.packet.PlayerSkinPacket;
import com.sunflow.tutorialmod.network.packet.ScrollPacket;
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
		TUTORIALMOD_CHANNEL.registerMessage(nextID(),
				OpenGuiPacket.class,
				OpenGuiPacket::encode,
				OpenGuiPacket::new,
				OpenGuiPacket::onMessage);

		TUTORIALMOD_CHANNEL.registerMessage(nextID(),
				SpawnPacket.class,
				SpawnPacket::encode,
				SpawnPacket::new,
				SpawnPacket::onMessage);

		TUTORIALMOD_CHANNEL.messageBuilder(ExplodePacket.class, nextID())
				.encoder(ExplodePacket::encode)
				.decoder(ExplodePacket::new)
				.consumer(ExplodePacket::onMessage)
				.add();

		TUTORIALMOD_CHANNEL.messageBuilder(MultiJumpPacket.class, nextID())
				.encoder(MultiJumpPacket::encode)
				.decoder(MultiJumpPacket::new)
				.consumer(MultiJumpPacket::onMessage)
				.add();

		TUTORIALMOD_CHANNEL.messageBuilder(ScrollPacket.class, nextID())
				.encoder(ScrollPacket::encode)
				.decoder(ScrollPacket::new)
				.consumer(ScrollPacket::onMessage)
				.add();

		TUTORIALMOD_CHANNEL.messageBuilder(PlayerSkinPacket.class, nextID())
				.encoder(PlayerSkinPacket::encode)
				.decoder(PlayerSkinPacket::new)
				.consumer(PlayerSkinPacket::onMessage)
				.add();

		TUTORIALMOD_CHANNEL.messageBuilder(PlayerLoggedInPacket.class, nextID())
				.encoder(PlayerLoggedInPacket::encode)
				.decoder(PlayerLoggedInPacket::new)
				.consumer(PlayerLoggedInPacket::onMessage)
				.add();

		TUTORIALMOD_CHANNEL.messageBuilder(PlayerSkinChangedPacket.class, nextID())
				.encoder(PlayerSkinChangedPacket::encode)
				.decoder(PlayerSkinChangedPacket::new)
				.consumer(PlayerSkinChangedPacket::onMessage)
				.add();

		TUTORIALMOD_CHANNEL.messageBuilder(SyncConfigPacket.class, nextID())
				.encoder(SyncConfigPacket::encode)
				.decoder(SyncConfigPacket::new)
				.consumer(SyncConfigPacket::onMessage)
				.add();
	}

	// Sending to Server
	public static <MSG> void sendToServer(MSG msg) {
		TUTORIALMOD_CHANNEL.sendToServer(msg);
	}

	// Sending to one player
	public static <MSG> void sendToPlayer(Supplier<ServerPlayerEntity> player, MSG msg) {
		TUTORIALMOD_CHANNEL.send(PacketDistributor.PLAYER.with(player), msg);
	}

	// Sending to one player
	public static <MSG> void sendToClient(ServerPlayerEntity player, MSG msg) {
		TUTORIALMOD_CHANNEL.sendTo(msg, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
	}

	// Send to all players tracking this chunk
	public static <MSG> void sendToChunk(Supplier<Chunk> chunk, MSG msg) {
		TUTORIALMOD_CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(chunk), msg);
	}

	// Sending to all connected players
	public static <MSG> void sendToConnected(MSG msg) {
		TUTORIALMOD_CHANNEL.send(PacketDistributor.ALL.noArg(), msg);
	}

	public static <MSG> void sendTo(MSG msg, NetworkManager manager, NetworkDirection direction) {
		TUTORIALMOD_CHANNEL.sendTo(msg, manager, direction);
	}

}
