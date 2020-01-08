package com.sunflow.tutorialmod.util.interfaces;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface ICustomUse {
	public void customOnItemRightClick(World world, PlayerEntity player, Hand hand);

}
