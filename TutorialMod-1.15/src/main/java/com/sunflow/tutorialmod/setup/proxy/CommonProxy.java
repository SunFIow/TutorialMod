package com.sunflow.tutorialmod.setup.proxy;

import com.sunflow.tutorialmod.setup.CommonSetup;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public abstract class CommonProxy {

	public void setup() { FMLJavaModLoadingContext.get().getModEventBus().addListener(CommonSetup::setup); }

	public abstract World getClientWorld();

	public abstract PlayerEntity getClientPlayer();

	public abstract Minecraft getMinecraft();

	public abstract boolean isClient();

}
