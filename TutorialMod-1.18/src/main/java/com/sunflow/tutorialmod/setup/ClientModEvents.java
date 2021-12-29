package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestRenderer;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestScreen;
import com.sunflow.tutorialmod.entity.centaur.CentaurModel;
import com.sunflow.tutorialmod.entity.centaur.CentaurRenderer;
import com.sunflow.tutorialmod.entity.weirdmob.WeirdMobModel;
import com.sunflow.tutorialmod.entity.weirdmob.WeirdMobRenderer;
import com.sunflow.tutorialmod.rendering.ModBEWLR;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.enums.KeyBindings;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientModEvents {
	public static void setup(final FMLClientSetupEvent event) {
		TutorialMod.LOGGER.info("perhaps you would like...");

		ModBEWLR.instance.setup();
		registerScreens();

		// setRenderLayers();
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
		ItemBlockRenderTypes.setRenderLayer(Registration.COPPER_LEAVES.block(), RenderType.cutoutMipped());
		ItemBlockRenderTypes.setRenderLayer(Registration.ALUMINIUM_LEAVES.block(), RenderType.cutoutMipped());
		ItemBlockRenderTypes.setRenderLayer(Registration.SANTA_HAT.block(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(Registration.RICE.block(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(Registration.COPPER_SAPLING.block(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(Registration.ALUMINIUM_SAPLING.block(), RenderType.cutout());
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

	public static void onTextureStitch(final TextureStitchEvent.Pre event) {
		TutorialMod.LOGGER.debug("Stitching textures for u senpai.");

		ResourceLocation atlas = event.getAtlas().location();
		if (atlas.equals(TextureAtlas.LOCATION_BLOCKS)) {
			// event.addSprite(MagicBockTileRenderer.MAGICBLOCK_TEXTURE);
		} else if (atlas.equals(Sheets.CHEST_SHEET)) {
			event.addSprite(CopperChestRenderer.COPPER_CHEST_TEXTURE);
		}
	}

	public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(WeirdMobModel.WERIDMOB_LAYER, WeirdMobModel::createBodyLayer);
		event.registerLayerDefinition(CentaurModel.CENTAUR_LAYER, CentaurModel::createBodyLayer);
	}

	public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(Registration.WEIRDMOB.get(), WeirdMobRenderer::new);
		event.registerEntityRenderer(Registration.CENTAUR.get(), CentaurRenderer::new);
		event.registerEntityRenderer(Registration.GRENADE_ENTITY.get(), ThrownItemRenderer::new);
		event.registerBlockEntityRenderer(Registration.COPPER_CHEST.blockEntity(), CopperChestRenderer::new);
		// event.registerBlockEntityRenderer(Registration.MAGICBLOCK_TILE.get(), MagicBockTileRenderer::new);
	}

	// private static void registerBlockColors() {
	// TutorialMod.proxy.getMinecraft().getBlockColors().register(new FancyBlockColor(), Registration.FANCYBLOCK.get());
	// }

}
