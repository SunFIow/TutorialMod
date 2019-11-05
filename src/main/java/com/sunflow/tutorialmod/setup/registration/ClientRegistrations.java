package com.sunflow.tutorialmod.setup.registration;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.BakedBlockBase;
import com.sunflow.tutorialmod.block.model.CustomBakedModel;
import com.sunflow.tutorialmod.item.base.MobEggBase;
import com.sunflow.tutorialmod.setup.ModBlocks;
import com.sunflow.tutorialmod.setup.ModItems;
import com.sunflow.tutorialmod.setup.ModScreens;
import com.sunflow.tutorialmod.setup.ModScreens.ScreenEntry;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.block.Block;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

//@Mod.EventBusSubscriber(modid = TutorialMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
@OnlyIn(Dist.CLIENT)
public class ClientRegistrations {

	@SubscribeEvent
	public static void onItemColor(ColorHandlerEvent.Item event) {
		Log.debug("I am going to register the item colors now senpai.");

		ItemColors registry = event.getItemColors();
		for (MobEggBase egg : ModItems.EGGS) {
			registry.register((stack, i) -> egg.eggColor, egg);
		}
	}

	@SubscribeEvent
	public static void onTextureStich(TextureStitchEvent.Pre event) {
		if (!event.getMap().getBasePath().equals("textures")) {
			return;
		}

		for (Block b : ModBlocks.BAKEDMODELBLOCKS) {
			event.addSprite(new ResourceLocation(TutorialMod.MODID, "block/" + b.getRegistryName().getPath()));
		}
	}

	@SubscribeEvent
	public static void onModelBake(ModelBakeEvent event) {
		for (BakedBlockBase b : ModBlocks.BAKEDMODELBLOCKS) {
			event.getModelRegistry().put(new ModelResourceLocation(b.getRegistryName(), ""),
					new CustomBakedModel(DefaultVertexFormats.BLOCK, b.getRegistryName().getPath(), b.offset, b.length));
			event.getModelRegistry().put(new ModelResourceLocation(b.getRegistryName(), "inventory"),
					new CustomBakedModel(DefaultVertexFormats.ITEM, b.getRegistryName().getPath(), b.offset, b.length));
		}
	}

	@SuppressWarnings("unchecked")
	public static <M extends Container, U extends Screen & IHasContainer<M>> void registerScreens() {
		Log.debug("I am going to register the screens now senpai.");

		for (ScreenEntry<M, U> screen : ModScreens.SCREENS) {
			ScreenManager.registerFactory(screen.type, screen.factory);
		}
	}
}
