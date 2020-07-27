package com.example.examplemod;

import org.apache.logging.log4j.Logger;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
public class ExampleMod {
	public static final String MODID = "tutorialmod";
	public static final String NAME = "Tutorial Mod";
	public static final String VERSION = "0.1.0";

	private static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		ConfigManager.sync(MODID, net.minecraftforge.common.config.Config.Type.INSTANCE);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// some example code
		logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
	}
}
