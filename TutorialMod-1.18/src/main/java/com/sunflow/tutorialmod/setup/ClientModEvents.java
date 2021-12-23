package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestRenderer;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestScreen;
import com.sunflow.tutorialmod.rendering.ModBEWLR;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.enums.KeyBindings;

import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientModEvents {
	public static void setup(final FMLClientSetupEvent event) {
		TutorialMod.LOGGER.info("perhaps you would like...");

		ModBEWLR.instance.setup();
		registerScreens();

		event.enqueueWork(ClientModEvents::setRenderLayers);
		// event.enqueueWork(ClientModEvents::registerBlockColors);
		// registerModelLoaders();

		TutorialMod.LOGGER.debug("Registering the keybindings for u senpai.");
		KeyBindings.register();

		Log.info("m...");
		Log.info("mm...");
		Log.info("me? ");
		TutorialMod.LOGGER.info("*giggling*");

		TutorialMod.LOGGER.info("I am ready now senpai. Shall we begin?");
	}

	private static void registerScreens() {
		TutorialMod.LOGGER.debug("Registering the screens for u senpai.");
		// ScreenManager.registerFactory(Registration.GLOWSTONE_GENERATOR_CONTAINER.get(), GlowstoneGeneratorScreen::new);
		// ScreenManager.registerFactory(Registration.ENERGY_STORAGE_CONTAINER.get(), EnergyStorageScreen::new);
		// ScreenManager.registerFactory(Registration.SINTERING_FURNACE_CONTAINER.get(), SinteringFurnaceScreen::new);
		// ScreenManager.registerFactory(Registration.ELECTRIC_SINTERING_FURNACE_CONTAINER.get(), ElectricSinteringFurnaceScreen::new);
		// ScreenManager.registerFactory(Registration.CHARGER_CONTAINER.get(), ChargerScreen::new);
		// ScreenManager.registerFactory(Registration.COPPER_CHEST_CONTAINER.get(), CopperChestScreen::new);
		MenuScreens.register(Registration.COPPER_CHEST.menu(), CopperChestScreen::new);

	}

	private static void setRenderLayers() {
		// ItemBlockRenderTypes.setRenderLayer(Registration.COPPER_LEAVES.get(), RenderType.cutoutMipped());
		// ItemBlockRenderTypes.setRenderLayer(Registration.ALUMINIUM_LEAVES.get(), RenderType.cutoutMipped());
		// ItemBlockRenderTypes.setRenderLayer(Registration.SANTA_HAT.get(), RenderType.cutout());
		// ItemBlockRenderTypes.setRenderLayer(Registration.RICE_PLANT.get(), RenderType.cutout());
		// ItemBlockRenderTypes.setRenderLayer(Registration.COPPER_SAPLING.get(), RenderType.cutout());
		// ItemBlockRenderTypes.setRenderLayer(Registration.ALUMINIUM_SAPLING.get(), RenderType.cutout());
	}

	// private static void registerBlockColors() {
	// TutorialMod.proxy.getMinecraft().getBlockColors().register(new FancyBlockColor(), Registration.FANCYBLOCK.get());
	// }

	public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		// event.registerEntityRenderer(Registration.WEIRDMOB.get(), WeirdMobRenderer::new);
		// event.registerEntityRenderer(Registration.CENTAUR.get(), CentaurRenderer::new);
		// event.registerEntityRenderer(Registration.GRENADE_ENTITY.get(, erp_c -> {
		// return new SpriteRenderer<>(erp_c, TutorialMod.proxy.getMinecraft().getItemRenderer()));
		// });
		event.registerBlockEntityRenderer(Registration.COPPER_CHEST.blockEntity(), CopperChestRenderer::new);
		// event.registerBlockEntityRenderer(Registration.MAGICBLOCK_TILE.get(), MagicBockTileRenderer::new);

	}

	public static void onItemColor(final ColorHandlerEvent.Item event) {
		TutorialMod.LOGGER.debug("Registering the item colors for u senpai.");

		ItemColors registry = event.getItemColors();
		// registry.register((stack, i) -> Registration.CENTAUR_SPAWN_EGG.get().eggColor, Registration.CENTAUR_SPAWN_EGG.get());
		// registry.register((stack, i) -> Registration.WEIRDMOB_SPAWN_EGG.get().eggColor, Registration.WEIRDMOB_SPAWN_EGG.get());
	}

	public static void onModelRegistryEvent(ModelRegistryEvent event) {
		// BakedBlockBase block = Registration.FANCYBLOCK.get();
		// ModelLoaderRegistry.registerLoader(
		// new ResourceLocation(TutorialMod.MODID, "customloader"),
		// // new CustomModelLoader(block.location(), block.offset(), block.length()));
		// new CustomModelLoader(
		// () -> new CustomModelGeometry(block.location(),
		// () -> new CustomBakedModel(
		// block.location(),
		// block.offset(),
		// block.length()))));
	}

	@SuppressWarnings("deprecation")
	public static void onTextureStitch(final TextureStitchEvent.Pre event) {
		TutorialMod.LOGGER.debug("Stitching textures for u senpai.");

		ResourceLocation atlas = event.getAtlas().location();
		if (atlas.equals(TextureAtlas.LOCATION_BLOCKS)) {
			// event.addSprite(MagicBockTileRenderer.MAGICBLOCK_TEXTURE);
		} else if (atlas.equals(Sheets.CHEST_SHEET)) {
			event.addSprite(CopperChestRenderer.COPPER_CHEST_TEXTURE);
		}
	}

	// public void onTooltipPre(RenderTooltipEvent.Pre event) {
	// Item item = event.getItemStack().getItem();
	// if (item.getRegistryName().getNamespace().equals(TutorialMod.MODID)) {
	// // event.setMaxWidth(200);
	// }
	// }
}
