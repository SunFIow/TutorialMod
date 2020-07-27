package com.sunflow.tutorialmod.world.gen;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class ModGenerator {
	public static void generate(FMLLoadCompleteEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES) {
			generateOres(biome);
			generateStructures(biome);
		}
	}

	private static void generateStructures(Biome biome) {
		biome.addStructure(Registration.RUN_DOWN_HOUSE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Registration.RUN_DOWN_HOUSE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
				.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));

	}

	private static void generateOres(Biome biome) {
		switch (biome.getCategory()) {
			case NETHER:
				break;
			case THEEND:
				break;
			default:
				genOre(biome, 15, 10, 0, 100,
						OreFeatureConfig.FillerBlockType.NATURAL_STONE,
						Registration.COPPER_ORE_NETHER.get().getDefaultState(),
						8);
				break;
		}

	}

	private static void genOre(Biome biome, int count, int bottomOffset, int topOffset, int maximum,
			OreFeatureConfig.FillerBlockType filler, BlockState defaultBlockstate, int size) {
		CountRangeConfig range = new CountRangeConfig(count, bottomOffset, topOffset, maximum);
		OreFeatureConfig feature = new OreFeatureConfig(filler, defaultBlockstate, size);
		ConfiguredPlacement<CountRangeConfig> placement = Placement.COUNT_RANGE.configure(range);
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(feature).withPlacement(placement));
	}
}
