package com.sunflow.tutorialmod.world.biome;

import com.google.common.collect.ImmutableList;
import com.sunflow.tutorialmod.block.tree.CustomTree;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.structure.BuriedTreasureConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.feature.structure.ShipwreckConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class CopperBiome extends BiomeBase {
//	private CustomTree COPPER = new CustomTree(ModBlocks.COPPER_LOG, ModBlocks.COPPER_LEAVES, ModBlocks.COPPER_SAPLING);
//	private CustomTree ALUMINIUM = new CustomTree(ModBlocks.ALUMINIUM_LOG, ModBlocks.ALUMINIUM_LEAVES, ModBlocks.ALUMINIUM_SAPLING);
	public static CustomTree COPPER = new CustomTree(Registration.COPPER_LOG.get(), Registration.COPPER_LEAVES.get(), Registration.COPPER_SAPLING::get);
	public static CustomTree ALUMINIUM = new CustomTree(Registration.ALUMINIUM_LOG.get(), Registration.ALUMINIUM_LEAVES.get(), Registration.ALUMINIUM_SAPLING::get);

	public CopperBiome() {
		super(getBuilder(), BiomeType.WARM, Type.HILLS, Type.DRY);
		addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
		addStructure(Feature.BURIED_TREASURE.withConfiguration(new BuriedTreasureConfig(0.01F)));
		addStructure(Feature.SHIPWRECK.withConfiguration(new ShipwreckConfig(true)));

//		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
//				createDecoratedFeature(
//						Feature.SIMPLE_RANDOM_SELECTOR,
//						new SingleRandomFeature(
//								new Feature[] {
//										new CustomTree(ModBlocks.ALUMINIUM_LOG, ModBlocks.ALUMINIUM_LEAVES, ModBlocks.ALUMINIUM_SAPLING).getTreeFeature(new Random()),
//										new CustomTree(ModBlocks.COPPER_LOG, ModBlocks.COPPER_LEAVES, ModBlocks.COPPER_SAPLING).getTreeFeature(new Random()) },
//								new IFeatureConfig[] {
//										IFeatureConfig.NO_FEATURE_CONFIG,
//										IFeatureConfig.NO_FEATURE_CONFIG }),
//						Placement.COUNT_EXTRA_HEIGHTMAP,
//						new AtSurfaceWithExtraConfig(1, 0.1F, 1)));
//		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
//				Feature.SIMPLE_RANDOM_SELECTOR.func_225566_b_(
//						new SingleRandomFeature(new Feature[] {
//								new CustomTree(ModBlocks.ALUMINIUM_LOG, ModBlocks.ALUMINIUM_LEAVES, ModBlocks.ALUMINIUM_SAPLING).getTreeFeature(new Random()),
//								new CustomTree(ModBlocks.COPPER_LOG, ModBlocks.COPPER_LEAVES, ModBlocks.COPPER_SAPLING).getTreeFeature(new Random())
//						},
//								new IFeatureConfig[] {
//										IFeatureConfig.NO_FEATURE_CONFIG,
//										IFeatureConfig.NO_FEATURE_CONFIG
//								})));

		// .SURFACE_STRUCTURES
//		if (COPPER == null) {
//			CustomTree COPPER = new CustomTree(ModBlocks.COPPER_LOG, ModBlocks.COPPER_LEAVES, ModBlocks.COPPER_SAPLING);
//			CustomTree ALUMINIUM = new CustomTree(ModBlocks.ALUMINIUM_LOG, ModBlocks.ALUMINIUM_LEAVES, ModBlocks.ALUMINIUM_SAPLING);
//		}
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_SELECTOR.withConfiguration(
						new MultipleRandomFeatureConfig(ImmutableList.of(
								Feature.NORMAL_TREE.withConfiguration(COPPER.configSmall()).withChance(0.666F),
								Feature.FANCY_TREE.withConfiguration(COPPER.configSmall()).withChance(0.1F),
								Feature.NORMAL_TREE.withConfiguration(ALUMINIUM.configSmall()).withChance(0.666F),
								Feature.FANCY_TREE.withConfiguration(ALUMINIUM.configSmall()).withChance(0.1F)),
								Feature.NORMAL_TREE.withConfiguration(COPPER.configSmall())
										.withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1))))));

//		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
//				createDecoratedFeature(
//						Feature.ORE,
//						new OreFeatureConfig(
//								OreFeatureConfig.FillerBlockType.NATURAL_STONE,
//								ModBlocks.ALUMINIUM_ORE_END.getDefaultState(),
//								9),
//						Placement.COUNT_RANGE,
//						new CountRangeConfig(20, 32, 32, 80)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE.withConfiguration(
						new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								Registration.ALUMINIUM_ORE_END.get().getDefaultState(),
								9))
						.withPlacement(
								Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 32, 32, 80))));

		DefaultBiomeFeatures.addCarvers(this);
		DefaultBiomeFeatures.addStructures(this);
		DefaultBiomeFeatures.addMonsterRooms(this);
		DefaultBiomeFeatures.addStoneVariants(this);
		DefaultBiomeFeatures.addOres(this);
		DefaultBiomeFeatures.addSedimentDisks(this);
		DefaultBiomeFeatures.addDefaultFlowers(this);
		DefaultBiomeFeatures.addSparseGrass(this);
		DefaultBiomeFeatures.addMushrooms(this);
		DefaultBiomeFeatures.addReedsAndPumpkins(this);
		DefaultBiomeFeatures.addSprings(this);
		DefaultBiomeFeatures.addFreezeTopLayer(this);

		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.TURTLE, 5, 2, 5));
		this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITHER, 10, 1, 5));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDER_DRAGON, 5, 1, 2));

	}

	private static final BlockState BLOCK_RUBY = Registration.RUBY_BLOCK.get().getDefaultState();
	private static final BlockState ORE_RUBY = Registration.RUBY_ORE.get().getDefaultState();

	private static Biome.Builder getBuilder() {
		Biome.Builder builder = new Biome.Builder()
				.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BLOCK_RUBY, ORE_RUBY, ORE_RUBY))
				.precipitation(Biome.RainType.RAIN)
				.category(Biome.Category.PLAINS)
				.depth(1.0F)
				.scale(0.025F)
				.temperature(1.0F)
				.downfall(0.01F)
				.waterColor(4159204)
				.waterFogColor(559891)
				.parent((String) null);

		return builder;
	}
}
