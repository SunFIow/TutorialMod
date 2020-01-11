package com.sunflow.tutorialmod.world.biome;

import com.sunflow.tutorialmod.setup.ModBiomes;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.registries.IForgeRegistry;

public class BiomeBase extends Biome {

	private BiomeType biomeType;
	private Type[] types;

	protected BiomeBase(String name, Builder biomeBuilder, BiomeType biomeType, Type... types) {
		super(biomeBuilder);
		this.setRegistryName(name);
		this.biomeType = biomeType;
		this.types = types;

		ModBiomes.BIOMES.add(this);
	}

	public void register(IForgeRegistry<Biome> registry) {
		BiomeDictionary.addTypes(this, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(this, 100));
		BiomeManager.addSpawnBiome(this);
	}
}
