package com.sunflow.tutorialmod.setup.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

	@Override
	public World getClientWorld() { return Minecraft.getInstance().world; }

	@Override
	public PlayerEntity getClientPlayer() { return Minecraft.getInstance().player; }

	@Override
	public Minecraft getMinecraft() { return Minecraft.getInstance(); }
}
