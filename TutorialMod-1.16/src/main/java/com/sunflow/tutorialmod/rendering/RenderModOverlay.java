package com.sunflow.tutorialmod.rendering;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.config.TutorialModConfig;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class RenderModOverlay {

	@SuppressWarnings("resource")
	public static void render(RenderGameOverlayEvent.Text event) {
		if (!TutorialMod.proxy.getMinecraft().gameSettings.showDebugInfo && TutorialModConfig.CONFIG_SHOW_OVERLAY.get()) {
			BlockPos pos = TutorialMod.proxy.getClientPlayer().func_233580_cy_();
			event.getLeft().add(0, String.format("X: %s, Y: %s, Z: %s", pos.getX(), pos.getY(), pos.getZ()));
			event.getLeft().add(0, String.format("%sfps", TutorialMod.proxy.getMinecraft().debug.split("fps")[0]));
		}
	}
}
