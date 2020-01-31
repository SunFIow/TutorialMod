package com.sunflow.tutorialmod.setup.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface IProxy {

	World getClientWorld();

	PlayerEntity getClientPlayer();

	Minecraft getMinecraft();
}
