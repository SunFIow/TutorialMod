package com.sunflow.tutorialmod.setup.registration;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.BakedBlockBase;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestScreen;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestTile;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestTileRenderer;
import com.sunflow.tutorialmod.block.machine.charger.ChargerScreen;
import com.sunflow.tutorialmod.block.machine.electric_sintering_furnace.ElectricSinteringFurnaceScreen;
import com.sunflow.tutorialmod.block.machine.energy_storage.EnergyStorageScreen;
import com.sunflow.tutorialmod.block.machine.firstblock.FirstBlockScreen;
import com.sunflow.tutorialmod.block.machine.sintering_furnace.SinteringFurnaceScreen;
import com.sunflow.tutorialmod.block.model.CustomBakedModel;
import com.sunflow.tutorialmod.block.model.CustomModelLoader;
import com.sunflow.tutorialmod.entity.centaur.CentaurRenderer;
import com.sunflow.tutorialmod.entity.weirdmob.WeirdMobRenderer;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.handlers.KeyBindingHandler;
import com.sunflow.tutorialmod.util.handlers.PlayerSkinHandler;

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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TutorialMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
	public static void setup(FMLClientSetupEvent event) {
		Log.info("perhaps you would like...");

		RenderingRegistry.registerEntityRenderingHandler(Registration.WEIRDMOB.get(), WeirdMobRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(Registration.CENTAUR.get(), CentaurRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(Registration.GRENADE_ENTITY.get(), (erm) -> new SpriteRenderer<>(erm, TutorialMod.proxy.getMinecraft().getItemRenderer()));

		ClientRegistry.bindTileEntityRenderer(Registration.COPPER_CHEST_TILE.get(), (dispatcher) -> new CopperChestTileRenderer<CopperChestTile>(dispatcher));
		registerScreens();
		KeyBindingHandler.setup();

		BakedBlockBase block = Registration.FANCY_BLOCK.get();
		ModelLoaderRegistry.registerLoader(new ResourceLocation(TutorialMod.MODID, "customloader"), new CustomModelLoader(() -> new CustomBakedModel(block.location(), block.offset(), block.length())));

		final IEventBus eventBus = MinecraftForge.EVENT_BUS;
		eventBus.register(KeyBindingHandler.class);
		eventBus.register(PlayerSkinHandler.class);
		eventBus.register(Registration.ENCHANTMENT_MULTIJUMP.get());

		Log.info("m...");
		Log.info("mm...");
		Log.info("me? ");
		Log.info("*giggling*");

		Log.info("I am ready now senpai. Shall we begin?");
	}

	public static <M extends Container, U extends Screen & IHasContainer<M>> void registerScreens() {
		Log.debug("I am going to register the screens now senpai.");

		ScreenManager.registerFactory(Registration.FIRSTBLOCK_CONTAINER.get(), FirstBlockScreen::new);
		ScreenManager.registerFactory(Registration.ENERGY_STORAGE_CONTAINER.get(), EnergyStorageScreen::new);
		ScreenManager.registerFactory(Registration.SINTERING_FURNACE_CONTAINER.get(), SinteringFurnaceScreen::new);
		ScreenManager.registerFactory(Registration.ELECTRIC_SINTERING_FURNACE_CONTAINER.get(), ElectricSinteringFurnaceScreen::new);
		ScreenManager.registerFactory(Registration.CHARGER_CONTAINER.get(), ChargerScreen::new);
		ScreenManager.registerFactory(Registration.COPPER_CHEST_CONTAINER.get(), CopperChestScreen::new);
	}

	@SubscribeEvent
	public static void onItemColor(final ColorHandlerEvent.Item event) {
		Log.debug("I am going to register the item colors now senpai.");

		ItemColors registry = event.getItemColors();
		registry.register((stack, i) -> Registration.CENTAUR_SPAWN_EGG.get().eggColor, Registration.CENTAUR_SPAWN_EGG.get());
		registry.register((stack, i) -> Registration.WEIRDMOB_SPAWN_EGG.get().eggColor, Registration.WEIRDMOB_SPAWN_EGG.get());
	}
}
