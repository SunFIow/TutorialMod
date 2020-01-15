package com.sunflow.tutorialmod.setup.proxy;

import com.sunflow.tutorialmod.setup.ModEnchantments;
import com.sunflow.tutorialmod.setup.registration.ClientRegistrations;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.handlers.ClientForgeEventHandlers;
import com.sunflow.tutorialmod.util.handlers.KeyBindingHandler;
import com.sunflow.tutorialmod.util.handlers.PlayerSkinHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy extends CommonProxy {

	@Override
	public void preSetup() {
		Log.info("Welcome home senpai.");
//		TutorialMod.LOGGER.info("Gimme just a moment senpai while I get some things to tinker with...");
		Log.info("Would you like to have dinner first?");
		Log.info("Or would you rather take a bath?");
		Log.info("Or...");

		super.preSetup();

		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.register(ClientRegistrations.class);
	}

	@Override
	public void setup() {
		Log.info("perhaps you would like...");

		super.setup();

		ClientRegistrations.registerScreens();
		KeyBindingHandler.setup();

//		RenderingRegistry.registerEntityRenderingHandler(WeirdMobEntity.class, WeirdMobRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(CentaurEntity.class, CentaurRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.WEIRDMOB, WeirdMobRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CENTAUR, CentaurRenderer::new);

//		ClientRegistry.bindTileEntitySpecialRenderer(CopperChestTile.class, new CopperChestTileRenderer<CopperChestTile>());
//		ClientRegistry.bindTileEntityRenderer(ModTileEntitiyTypes.COPPER_CHEST_TILE, new CopperChestTileRenderer<CopperChestTile>());

		final IEventBus eventBus = MinecraftForge.EVENT_BUS;
		eventBus.register(ClientForgeEventHandlers.class);
		eventBus.register(KeyBindingHandler.class);
		eventBus.register(PlayerSkinHandler.class);
		eventBus.register(ModEnchantments.ENCHANTMENT_MULTIJUMP);

		Log.info("m...");
		Log.info("mm...");
		Log.info("me? ");
		Log.info("*giggling*");

		Log.info("I am ready now senpai. Shall we begin?");
	}

	@Override
	public World getClientWorld() { return Minecraft.getInstance().world; }

	@Override
	public PlayerEntity getClientPlayer() { return Minecraft.getInstance().player; }

	@Override
	public Minecraft getMinecraft() { return Minecraft.getInstance(); }
}
