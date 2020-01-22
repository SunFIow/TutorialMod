package com.sunflow.tutorialmod.setup.registration;

import com.sunflow.tutorialmod.block.model.CustomBakedModel;
import com.sunflow.tutorialmod.setup.ModBlocks;
import com.sunflow.tutorialmod.setup.ModItems;
import com.sunflow.tutorialmod.setup.ModScreens;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class ClientRegistrations {

	@SubscribeEvent
	public static void onItemColor(ColorHandlerEvent.Item event) {
		Log.debug("I am going to register the item colors now senpai.");

		ItemColors registry = event.getItemColors();
		ModItems.EGGS.forEach((egg) -> registry.register((x, i) -> egg.eggColor, egg));
	}

	@SubscribeEvent
	public static void onTextureStich(TextureStitchEvent.Pre event) {
		// ----------------------------------- PlayerContainer.field_226615_c_) return;
		if (event.getMap().func_229223_g_() != AtlasTexture.LOCATION_BLOCKS_TEXTURE) return;

		ModBlocks.BAKEDMODELBLOCKS.forEach((block) -> event.addSprite(block.location()));

	}

	@SubscribeEvent
	public static void onModelBake(ModelBakeEvent event) {
//		event.getModelRegistry().put(new ModelResourceLocation(new ResourceLocation(TutorialMod.MODID, "fancyblock"), ""),
//				new FancyBakedModel());
//		event.getModelRegistry().put(new ModelResourceLocation(new ResourceLocation(TutorialMod.MODID, "fancyblock"), "inventory"),
//				new FancyBakedModel());

		ModBlocks.BAKEDMODELBLOCKS.forEach((block) -> {
			event.getModelRegistry().put(new ModelResourceLocation(block.getRegistryName(), ""),
					new CustomBakedModel(block.location(), block.offset(), block.length()));
			event.getModelRegistry().put(new ModelResourceLocation(block.getRegistryName(), "inventory"),
					new CustomBakedModel(block.location(), block.offset(), block.length()));
		});
	}

	@SuppressWarnings("unchecked")
	public static <M extends Container, U extends Screen & IHasContainer<M>> void registerScreens() {
		Log.debug("I am going to register the screens now senpai.");

		ModScreens.SCREENS.forEach((screen) -> ScreenManager.registerFactory(screen.type, screen.factory));
	}
}
