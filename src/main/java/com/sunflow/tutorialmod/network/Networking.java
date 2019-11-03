package com.sunflow.tutorialmod.network;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.network.packet.OpenGuiPacket;
import com.sunflow.tutorialmod.network.packet.SpawnPacket;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Networking {
	public static SimpleChannel INSTANCE;
	private static int ID = 0;

	public static int nextID() {
		return ID++;
	}

	public static void registerMessages() {
		INSTANCE = NetworkRegistry.newSimpleChannel(
				new ResourceLocation(TutorialMod.MODID, "main"),
				() -> "1.0",
				s -> true,
				s -> true);

		INSTANCE.registerMessage(nextID(),
				OpenGuiPacket.class,
				OpenGuiPacket::encode,
				OpenGuiPacket::new,
				OpenGuiPacket::onMessage);

		INSTANCE.registerMessage(nextID(),
				SpawnPacket.class,
				SpawnPacket::encode,
				SpawnPacket::new,
				SpawnPacket::onMessage);
	}

//	Networking.INSTANCE.sendTo(new OpenGuiPacket(), player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);

}
