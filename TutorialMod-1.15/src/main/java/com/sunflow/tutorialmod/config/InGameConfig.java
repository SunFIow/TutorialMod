package com.sunflow.tutorialmod.config;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class InGameConfig {

	public static ConfigScreen create(Screen parent, ConfigScreen.Catergory category) {
		return new ConfigScreen(new StringTextComponent(TutorialMod.NAME), parent, ConfigScreen.Builder.create()
				.client()
				.Boolean("options.tutorialmod.client.overlay", TutorialModConfig.CONFIG_SHOW_OVERLAY)
				.server()
				.Integer("options.tutorialmod.server.energyitem.maxpower", TutorialModConfig.ENERGY_ITEM_MAXPOWER, 0.0D, 20000, 1.0F),
				category);
	}

	public static void RegisterExtensionPoint(FMLClientSetupEvent event) {
		Log.warn("RegisterExtensionPoint : " + TutorialMod.proxy.isClient());
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (mc, parent) -> create(parent, ConfigScreen.Catergory.CLIENT));
	}

}
