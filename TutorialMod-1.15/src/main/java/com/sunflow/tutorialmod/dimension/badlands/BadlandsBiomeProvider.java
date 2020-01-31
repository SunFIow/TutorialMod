package com.sunflow.tutorialmod.dimension.badlands;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;

public class BadlandsBiomeProvider extends BiomeProvider {
	private final Biome biome;
	private static final List<Biome> SPAWNABLE = Collections.singletonList(Biomes.PLAINS);

	public BadlandsBiomeProvider() {
		super(new HashSet<>(SPAWNABLE));
		biome = Biomes.PLAINS;
	}

	@Override
	public Biome getNoiseBiome(int x, int y, int z) { return biome; }

	@Override
	public List<Biome> getBiomesToSpawnIn() { return SPAWNABLE; }

	@Override
	public boolean hasStructure(Structure<?> structureIn) { return false; }

	@Override
	public Set<BlockState> getSurfaceBlocks() {
		if (topBlocksCache.isEmpty())
			topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
		return topBlocksCache;
	}

}
