package com.sunflow.tutorialmod.dimension.badlands;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;

public class BadlandsBiomeProvider extends BiomeProvider {
	private final Biome biome;
	private static final List<Biome> SPAWNABLE = Collections.singletonList(Biomes.PLAINS);

//	private final Layer genBiomes;
	private static final Set<Biome> SPAWNABLE1 = ImmutableSet.of(Biomes.PLAINS);

	public BadlandsBiomeProvider() {
		super(SPAWNABLE1);
		biome = Biomes.PLAINS;
//		genBiomes = LayerUtil.func_227474_a_(settingsProvider.func_226850_a_(), settingsProvider.func_226851_b_(), settingsProvider.getGeneratorSettings());
	}

	@Override
	public List<Biome> getBiomesToSpawnIn() {
//	      return BIOMES_TO_SPAWN_IN;
		return SPAWNABLE;
	}

//	@Override
//	public Biome getBiome(int x, int y) {
//		return biome;
//	}

	@Override
	public Biome func_225526_b_(int p_225526_1_, int p_225526_2_, int p_225526_3_) {
		return biome;
	}

//	@Override
//	public Biome[] getBiomes(int x, int z, int width, int length, boolean cacheFlag) {
//		Biome[] biomes = new Biome[width * length];
//		Arrays.fill(biomes, biome);
//		return biomes;
//	}

//	@Override
//	public Set<Biome> getBiomesInSquare(int centerX, int centerZ, int sideLength) {
//		return Collections.singleton(biome);
//	}

//	@Override
//	public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
//		return new BlockPos(x, 65, z);
//	}

	@Override
	public boolean hasStructure(Structure<?> structureIn) { return false; }

	@Override
	public Set<BlockState> getSurfaceBlocks() {
		if (topBlocksCache.isEmpty())
			topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
		return topBlocksCache;
	}

}
