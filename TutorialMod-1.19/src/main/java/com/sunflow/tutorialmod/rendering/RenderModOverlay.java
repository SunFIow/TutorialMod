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
			event.getLeft().add(String.format("§e%s fps", TutorialMod.proxy.getMinecraft().fpsString.split(" fps")[0]));
			event.getLeft().add(String.format("§cX %s§r §9Y %s§r §aZ %s", pos.getX(), pos.getY(), pos.getZ()));
			if (!TutorialModConfig.CLIENT.CONFIG_SHOW_ARMOR.get()) event.getRight().add("Hiding your Armor");
		}
	}
}
