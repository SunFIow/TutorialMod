package com.sunflow.tutorialmod.setup.registration;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.ModBlocks;
import com.sunflow.tutorialmod.setup.ModItems;
import com.sunflow.tutorialmod.setup.ModScreens;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
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
//		if (!event.getMap().getBasePath().equals("textures")) return;
		if (event.getMap().func_229223_g_() != PlayerContainer.field_226615_c_) return;

		ModBlocks.BAKEDMODELBLOCKS.forEach((block) -> event.addSprite(new ResourceLocation(TutorialMod.MODID, "block/" + block.getRegistryName().getPath())));
	}

//	@SubscribeEvent
//	public static void onModelBake(ModelBakeEvent event) {
////		for (BakedBlockBase b : ModBlocks.BAKEDMODELBLOCKS) {
////			event.getModelRegistry().put(new ModelResourceLocation(b.getRegistryName(), ""),
////					new CustomBakedModel(DefaultVertexFormats.BLOCK, b.getRegistryName().getPath(), b.offset, b.length));
////			event.getModelRegistry().put(new ModelResourceLocation(b.getRegistryName(), "inventory"),
////					new CustomBakedModel(DefaultVertexFormats.ITEM, b.getRegistryName().getPath(), b.offset, b.length));
////		}
//		ModBlocks.BAKEDMODELBLOCKS.forEach((block) -> {
//			event.getModelRegistry().put(new ModelResourceLocation(block.getRegistryName(), ""),
//					new CustomBakedModel(DefaultVertexFormats.BLOCK, block.getRegistryName().getPath(), block.offset, block.length));
//			event.getModelRegistry().put(new ModelResourceLocation(block.getRegistryName(), "inventory"),
//					new CustomBakedModel(DefaultVertexFormats.BLOCK, block.getRegistryName().getPath(), block.offset, block.length));
//		});
//	}

	@SuppressWarnings("unchecked")
	public static <M extends Container, U extends Screen & IHasContainer<M>> void registerScreens() {
		Log.debug("I am going to register the screens now senpai.");

		ModScreens.SCREENS.forEach((screen) -> ScreenManager.registerFactory(screen.type, screen.factory));
	}
}
