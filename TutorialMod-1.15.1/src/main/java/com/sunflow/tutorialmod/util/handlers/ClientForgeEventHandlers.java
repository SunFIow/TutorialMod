package com.sunflow.tutorialmod.util.handlers;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.Config;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientForgeEventHandlers {
	@SubscribeEvent
	public static void onRenderOverlayText(RenderGameOverlayEvent.Text event) {
		if (!TutorialMod.proxy.getMinecraft().gameSettings.showDebugInfo && Config.CONFIG_SHOW_OVERLAY.get()) {
			BlockPos pos = TutorialMod.proxy.getClientPlayer().getPosition();
			event.getLeft().add(0, String.format("X: %s, Y: %s, Z: %s", pos.getX(), pos.getY(), pos.getZ()));
			event.getLeft().add(0, String.format("%sfps", TutorialMod.proxy.getMinecraft().debug.split("fps")[0]));
		}
	}
}
