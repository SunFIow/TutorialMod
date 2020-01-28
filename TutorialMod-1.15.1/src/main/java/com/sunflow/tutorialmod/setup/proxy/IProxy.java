package com.sunflow.tutorialmod.setup.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface IProxy {

	public abstract World getClientWorld();

	public abstract PlayerEntity getClientPlayer();

	public abstract Minecraft getMinecraft();
}
