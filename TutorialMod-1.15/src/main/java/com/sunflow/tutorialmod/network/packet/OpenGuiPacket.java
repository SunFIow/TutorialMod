package com.sunflow.tutorialmod.network.packet;

import com.sunflow.tutorialmod.command.spawner.SpawnerScreen;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class OpenGuiPacket extends BasePacket {

	public OpenGuiPacket(PacketBuffer buf) {}

	public OpenGuiPacket() {}

	@Override
	public boolean action(NetworkEvent.Context ctx) { SpawnerScreen.open(); return true; }
}
