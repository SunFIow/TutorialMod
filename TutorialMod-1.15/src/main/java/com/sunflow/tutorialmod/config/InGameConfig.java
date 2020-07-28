package com.sunflow.tutorialmod.config;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.config.DefaultConfigScreen.Side;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class InGameConfig {

	public static DefaultConfigScreen create(Screen parent, Side side) {
		return new DefaultConfigScreen(new StringTextComponent(TutorialMod.NAME), parent, DefaultConfigScreen.Builder.create()
				.client()
				.Boolean("options.overlay", TutorialModConfig.CONFIG_SHOW_OVERLAY)
				.server()
				.Integer("options.energyitem.maxpower", TutorialModConfig.ENERGY_ITEM_MAXPOWER, 0.0D, 20000, 1.0F),
				side);
	}

	public static void RegisterExtensionPoint(FMLClientSetupEvent event) {
		Log.warn("RegisterExtensionPoint : " + TutorialMod.proxy.isClient());
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (mc, parent) -> create(parent, Side.CLIENT));
	}

}
