package com.sunflow.tutorialmod.network.old;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;;

public class NetworkHandlerOld {

	private static final String PROTOCOL_VERSION = "1";
	public static SimpleChannel INSTANCE;
	private static int id = 0;

	public static void init() {
		INSTANCE = NetworkRegistry.newSimpleChannel(
				new ResourceLocation(TutorialMod.MODID, "main"),
				() -> PROTOCOL_VERSION,
				PROTOCOL_VERSION::equals,
				PROTOCOL_VERSION::equals);

		int index = id++;
		Class<MyMessage> messageType = MyMessage.class;
		BiConsumer<MyMessage, PacketBuffer> encoder = null;
		Function<PacketBuffer, MyMessage> decoder = null;
		BiConsumer<MyMessage, Supplier<NetworkEvent.Context>> consumer = null;

		INSTANCE.registerMessage(index, messageType, encoder, decoder, consumer);
	}

	public static void handle(MyMessage msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			// Work that needs to be threadsafe (most work)
			@SuppressWarnings("unused")
			PlayerEntity sender = ctx.get().getSender(); // the client that sent this packet
			// do stuff
		});
		ctx.get().setPacketHandled(true);
	}

	public void test() {
		INSTANCE.sendToServer(new MyMessage());

		Supplier<ServerPlayerEntity> player = null;
		Supplier<Chunk> chunk = null;

		// Sending to one player
		INSTANCE.send(PacketDistributor.PLAYER.with(player), new MyMessage());

		// Send to all players tracking this chunk
		INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(chunk), new MyMessage());

		// Sending to all connected players
		INSTANCE.send(PacketDistributor.ALL.noArg(), new MyMessage());
	}

	private static class MyMessage {}
}
