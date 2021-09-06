package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.blocks.generator.GeneratorScreen;
import com.sunflow.tutorialmod.items.TutorialItem;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {
	public static final ResourceLocation DISTANCE_PROPERTY = new ResourceLocation(TutorialMod.MODID, "distance");

	public static void setup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(Registration.GENERATOR_CONTAINER.get(), GeneratorScreen::new);
			setupTutorialItemOverrides();
		});
	}

	private static void setupTutorialItemOverrides() {
		TutorialItem item = Registration.TUTORIAL_ITEM.get();
		ItemProperties.register(item, DISTANCE_PROPERTY, (stack, level, entity, damage) -> TutorialItem.getDistance(stack));
	}
}
