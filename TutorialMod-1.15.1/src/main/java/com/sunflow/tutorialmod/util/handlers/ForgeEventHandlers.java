package com.sunflow.tutorialmod.util.handlers;

import com.sunflow.tutorialmod.setup.ModDimensions;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.interfaces.ICustomUse;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeEventHandlers {
	@SubscribeEvent
	public static void onPlayerUseItem(PlayerInteractEvent.RightClickItem event) {
		if (event.getItemStack().getItem() instanceof ICustomUse)
			((ICustomUse) event.getItemStack().getItem()).customOnItemRightClick(event.getWorld(), event.getPlayer(), event.getHand());
		// CustomPlayerSkinHandler.changeOnUse(event.getEntityPlayer());
	}

	@SubscribeEvent
	public static void onDimensionRegistry(RegisterDimensionsEvent event) {
		Log.info("-registerDimensions-");
//		ModDimension dim = ModDimensions.BADLANDS;
//		ResourceLocation loc = dim.getRegistryName();
//		ModDimensions.BADLANDS_TYPE = DimensionManager.registerOrGetDimension(loc, dim, null, true);

		ModDimensions.DIMENSIONS.forEach((dim) -> dim.setDimensionType(DimensionManager.registerOrGetDimension(dim.getRegistryName(), dim, dim.getData(), dim.hasSkyLight())));
	}
}