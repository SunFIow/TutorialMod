package com.sunflow.tutorialmod.setup.registration;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestTile;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestTileRenderer;
import com.sunflow.tutorialmod.block.model.CustomBakedModel;
import com.sunflow.tutorialmod.block.model.CustomModelLoader;
import com.sunflow.tutorialmod.entity.centaur.CentaurRenderer;
import com.sunflow.tutorialmod.entity.weirdmob.WeirdMobRenderer;
import com.sunflow.tutorialmod.setup.ModBlocks;
import com.sunflow.tutorialmod.setup.ModEntityTypes;
import com.sunflow.tutorialmod.setup.ModItems;
import com.sunflow.tutorialmod.setup.ModScreens;
import com.sunflow.tutorialmod.setup.ModTileEntitiyTypes;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.handlers.KeyBindingHandler;

import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

//@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = TutorialMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistrations {
	public static void setup() {
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.WEIRDMOB, WeirdMobRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CENTAUR, CentaurRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GRENADE_ENTITY, (erm) -> new SpriteRenderer<>(erm, TutorialMod.proxy.getMinecraft().getItemRenderer()));

		ClientRegistry.bindTileEntityRenderer(ModTileEntitiyTypes.COPPER_CHEST_TILE, (dispatcher) -> new CopperChestTileRenderer<CopperChestTile>(dispatcher));
		registerScreens();
		KeyBindingHandler.setup();

//		ModelLoaderRegistry.registerLoader(new ResourceLocation(TutorialMod.MODID, "customloader"), new CustomModelLoader());
		ModBlocks.BAKEDMODELBLOCKS.forEach((block) -> {
			CustomBakedModel bakedModel = new CustomBakedModel(block.location(), block.offset(), block.length());
			String name = "customloader";
			ModelLoaderRegistry.registerLoader(new ResourceLocation(TutorialMod.MODID, name), new CustomModelLoader(() -> bakedModel));
		});
	}

	@SuppressWarnings("unchecked")
	public static <M extends Container, U extends Screen & IHasContainer<M>> void registerScreens() {
		Log.debug("I am going to register the screens now senpai.");

		ModScreens.SCREENS.forEach((screen) -> ScreenManager.registerFactory(screen.type, screen.factory));

	}

	@SubscribeEvent
	public static void onItemColor(ColorHandlerEvent.Item event) {
		Log.debug("I am going to register the item colors now senpai.");

		ItemColors registry = event.getItemColors();
		ModItems.EGGS.forEach((egg) -> registry.register((stack, i) -> egg.eggColor, egg));
	}
}
