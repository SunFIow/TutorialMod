package com.sunflow.tutorialmod.network.packet;

import com.sunflow.tutorialmod.command.spawner.SpawnerScreen;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class OpenGuiPacket extends BasePacket {

	public OpenGuiPacket(FriendlyByteBuf buf) {}

	public OpenGuiPacket() {}

	@Override
	public boolean action(NetworkEvent.Context ctx) { SpawnerScreen.open(); return true; }
}
