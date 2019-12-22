package com.sunflow.tutorialmod.setup;

import java.util.ArrayList;
import java.util.List;

import com.sunflow.tutorialmod.world.biome.BiomeCopper;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomes {
	public static final List<Biome> BIOMES = new ArrayList<>();

	public static final Biome COPPER = new BiomeCopper();

	public static void registerBiomes() {
		ModBiomes.initBiome(ModBiomes.COPPER, "copper", BiomeType.WARM, Type.HILLS, Type.DRY);
	}

	private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types) {
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 100));

//		if (ConfigHandler.spawnCopperBiomOverworld) {
		BiomeManager.addSpawnBiome(biome);
//		Log.info("Added the Biome {} Master.", biome);
//		}
		BIOMES.add(biome);
		return biome;
	}
}
