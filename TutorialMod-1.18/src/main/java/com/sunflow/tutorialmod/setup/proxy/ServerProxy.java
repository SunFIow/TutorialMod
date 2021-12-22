package com.sunflow.tutorialmod.setup.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ServerProxy extends CommonProxy {

	private static final String ONLY_RUN_THIS_ON_THE_CLIENT = "Only run this on the client!";

	@Override
	public Level getClientWorld() { throw new IllegalStateException(ONLY_RUN_THIS_ON_THE_CLIENT); }

	@Override
	public Player getClientPlayer() { throw new IllegalStateException(ONLY_RUN_THIS_ON_THE_CLIENT); }

	@Override
	public Minecraft getMinecraft() { throw new IllegalStateException(ONLY_RUN_THIS_ON_THE_CLIENT); }

	@Override
	public boolean isClient() { return false; }

	@Override
	public boolean isServer() { return true; }
}
