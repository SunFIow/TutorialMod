package com.sunflow.tutorialmod.util.handlers;

import com.sunflow.tutorialmod.setup.ModDimensions;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.interfaces.ICustomUse;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeEventHandlers {
	@SubscribeEvent
	public static void onPlayerUseItem(RightClickItem event) {
		if (event.getItemStack().getItem() instanceof ICustomUse)
			((ICustomUse) event.getItemStack().getItem()).customOnItemRightClick(event.getWorld(), event.getPlayer(), event.getHand());
		// CustomPlayerSkinHandler.changeOnUse(event.getEntityPlayer());
	}

	@SubscribeEvent
	public static void onDimensionRegistry(RegisterDimensionsEvent event) {
//		registerOrGetDimension
		Log.warn("RegisterDimensionsEvent");
		ModDimension dim = ModDimensions.BADLANDS;
		ResourceLocation loc = dim.getRegistryName();
		ModDimensions.BADLANDS_TYPE = DimensionManager.registerOrGetDimension(loc, dim, null, true);
	}
}