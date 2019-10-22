package com.sunflow.tutorialmod.util.handlers;

import com.sunflow.tutorialmod.util.interfaces.ICustomUse;

import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CustomHandlers {
	@SubscribeEvent
	public static void onPlayerUseItem(RightClickItem event) {
		if (event.getItemStack().getItem() instanceof ICustomUse)
			((ICustomUse) event.getItemStack().getItem()).customOnItemRightClick(event.getWorld(), event.getPlayer(), event.getHand());
		// CustomPlayerSkinHandler.changeOnUse(event.getEntityPlayer());

	}
}