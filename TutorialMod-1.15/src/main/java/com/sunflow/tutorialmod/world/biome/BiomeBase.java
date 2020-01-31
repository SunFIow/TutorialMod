package com.sunflow.tutorialmod.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class BiomeBase extends Biome {

	private BiomeType biomeType;
	private Type[] types;

	protected BiomeBase(Builder biomeBuilder, BiomeType biomeType, Type... types) {
		super(biomeBuilder);
		this.biomeType = biomeType;
		this.types = types;
	}

	public void register() {
		BiomeDictionary.addTypes(this, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(this, 100));
		BiomeManager.addSpawnBiome(this);
	}
}
