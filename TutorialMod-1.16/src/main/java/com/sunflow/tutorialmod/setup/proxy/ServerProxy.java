package com.sunflow.tutorialmod.setup.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ServerProxy extends CommonProxy {

	@Override
	public World getClientWorld() { throw new IllegalStateException("Only run this on the client!"); }

	@Override
	public PlayerEntity getClientPlayer() { throw new IllegalStateException("Only run this on the client!"); }

	@Override
	public Minecraft getMinecraft() { throw new IllegalStateException("Only run this on the client!"); }

	@Override
	public boolean isClient() { return false; }

	@Override
	public boolean isServer() { return true; }
}
