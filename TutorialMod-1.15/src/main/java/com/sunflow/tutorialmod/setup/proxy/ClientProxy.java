package com.sunflow.tutorialmod.setup.proxy;

import com.sunflow.tutorialmod.setup.registration.ClientSetup;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy extends CommonProxy {

	@Override
	public void setup() {
		super.setup();
		Log.warn("Client");
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::setup);
	}

	@Override
	public World getClientWorld() { return Minecraft.getInstance().world; }

	@Override
	public PlayerEntity getClientPlayer() { return Minecraft.getInstance().player; }

	@Override
	public Minecraft getMinecraft() { return Minecraft.getInstance(); }

}
