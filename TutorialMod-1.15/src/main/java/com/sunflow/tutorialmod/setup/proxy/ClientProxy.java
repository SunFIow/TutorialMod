package com.sunflow.tutorialmod.setup.proxy;

import com.sunflow.tutorialmod.setup.ClientSetup;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy extends CommonProxy {

	@Override
	public void setup() {
		super.setup();
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::setup);
	}

	@SuppressWarnings("resource")
	@Override
	public World getClientWorld() { return Minecraft.getInstance().world; }

	@SuppressWarnings("resource")
	@Override
	public PlayerEntity getClientPlayer() { return Minecraft.getInstance().player; }

	@Override
	public Minecraft getMinecraft() { return Minecraft.getInstance(); }

	@Override
	public boolean isClient() { return true; }

}
