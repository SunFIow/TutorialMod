package com.sunflow.tutorialmod.setup.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ServerProxy extends CommonProxy {

	@Override
	public void preSetup() {
		super.preSetup();
	}

	@Override
	public void setup() {
		super.setup();
	}

	@Override
	public World getClientWorld() {
		throw new IllegalStateException("Only run this on the client!");
	}

	@Override
	public PlayerEntity getClientPlayer() {
		throw new IllegalStateException("Only run this on the client!");
	}
}
