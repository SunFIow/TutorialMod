package com.sunflow.tutorialmod.rendering;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.config.TutorialModConfig;

import net.minecraft.core.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class RenderModOverlay {

	@SuppressWarnings("resource")
	public static void render(RenderGameOverlayEvent.Text event) {
		if (!TutorialMod.proxy.getMinecraft().options.renderDebug && TutorialModConfig.CLIENT.CONFIG_SHOW_OVERLAY.get()) {
			BlockPos pos = TutorialMod.proxy.getClientPlayer().blockPosition();
			event.getLeft().add(0, String.format("X: %s, Y: %s, Z: %s", pos.getX(), pos.getY(), pos.getZ()));
			event.getLeft().add(0, String.format("%s", TutorialMod.proxy.getMinecraft().fpsString));
		}
	}
}
