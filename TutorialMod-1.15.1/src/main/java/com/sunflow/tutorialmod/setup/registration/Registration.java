package com.sunflow.tutorialmod.setup.registration;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.TeleporterBlock;
import com.sunflow.tutorialmod.block.base.BakedBlockBase;
import com.sunflow.tutorialmod.block.base.BlockBase;
import com.sunflow.tutorialmod.block.base.TileBlockBase;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestBlock;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestContainer;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestTile;
import com.sunflow.tutorialmod.block.food.FoodPlantBlock;
import com.sunflow.tutorialmod.block.furniture.SantaHatBlock;
import com.sunflow.tutorialmod.block.furniture.fancyblock.FancyBlock;
import com.sunflow.tutorialmod.block.furniture.fancyblock.FancyBlockTile;
import com.sunflow.tutorialmod.block.machine.charger.ChargerBlock;
import com.sunflow.tutorialmod.block.machine.charger.ChargerContainer;
import com.sunflow.tutorialmod.block.machine.charger.ChargerTile;
import com.sunflow.tutorialmod.block.machine.electric_sintering_furnace.ElectricSinteringFurnaceBlock;
import com.sunflow.tutorialmod.block.machine.electric_sintering_furnace.ElectricSinteringFurnaceContainer;
import com.sunflow.tutorialmod.block.machine.electric_sintering_furnace.ElectricSinteringFurnaceTile;
import com.sunflow.tutorialmod.block.machine.energy_storage.EnergyStorageBlock;
import com.sunflow.tutorialmod.block.machine.energy_storage.EnergyStorageContainer;
import com.sunflow.tutorialmod.block.machine.energy_storage.EnergyStorageTile;
import com.sunflow.tutorialmod.block.machine.glowstone_generator.GlowstoneGeneratorBlock;
import com.sunflow.tutorialmod.block.machine.glowstone_generator.GlowstoneGeneratorContainer;
import com.sunflow.tutorialmod.block.machine.glowstone_generator.GlowstoneGeneratorTile;
import com.sunflow.tutorialmod.block.machine.sintering_furnace.SinteringFurnaceBlock;
import com.sunflow.tutorialmod.block.machine.sintering_furnace.SinteringFurnaceContainer;
import com.sunflow.tutorialmod.block.machine.sintering_furnace.SinteringFurnaceTile;
import com.sunflow.tutorialmod.block.magicblock.MagicBlock;
import com.sunflow.tutorialmod.block.ore.CustomOreBlock;
import com.sunflow.tutorialmod.block.ore.RubyBlock;
import com.sunflow.tutorialmod.block.ore.RubyOre;
import com.sunflow.tutorialmod.block.tree.CustomLeavesBlock;
import com.sunflow.tutorialmod.block.tree.CustomSaplingBlock;
import com.sunflow.tutorialmod.dimension.ModDimensionBase;
import com.sunflow.tutorialmod.dimension.badlands.BadlandsDimension;
import com.sunflow.tutorialmod.enchantment.EnchantmentMultiJump;
import com.sunflow.tutorialmod.entity.centaur.CentaurEntity;
import com.sunflow.tutorialmod.entity.weirdmob.WeirdMobEntity;
import com.sunflow.tutorialmod.item.EnergyWandItem;
import com.sunflow.tutorialmod.item.FancySwordItem;
import com.sunflow.tutorialmod.item.TestItem;
import com.sunflow.tutorialmod.item.armor.ArmorBase;
import com.sunflow.tutorialmod.item.armor.ArmorSkinBase;
import com.sunflow.tutorialmod.item.armor.CustomArmorMaterial;
import com.sunflow.tutorialmod.item.armor.ItemNBTSkin;
import com.sunflow.tutorialmod.item.armor.SkinUtil.SkinType;
import com.sunflow.tutorialmod.item.base.ItemBase;
import com.sunflow.tutorialmod.item.base.MobEggBase;
import com.sunflow.tutorialmod.item.food.FoodItem;
import com.sunflow.tutorialmod.item.food.SeedItem;
import com.sunflow.tutorialmod.item.grenade.GrenadeEntity;
import com.sunflow.tutorialmod.item.grenade.GrenadeItem;
import com.sunflow.tutorialmod.item.tools.ToolAxe;
import com.sunflow.tutorialmod.item.tools.ToolHoe;
import com.sunflow.tutorialmod.item.tools.ToolMaterial;
import com.sunflow.tutorialmod.item.tools.ToolPickaxe;
import com.sunflow.tutorialmod.item.tools.ToolShovel;
import com.sunflow.tutorialmod.item.tools.ToolSword;
import com.sunflow.tutorialmod.setup.ModGroups;
import com.sunflow.tutorialmod.world.biome.CopperBiome;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {

	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, TutorialMod.MODID);
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, TutorialMod.MODID);
	public static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, TutorialMod.MODID);
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, TutorialMod.MODID);
	public static final DeferredRegister<TileEntityType<?>> TILEENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, TutorialMod.MODID);
	public static final DeferredRegister<Enchantment> ENCHANTMENTS = new DeferredRegister<>(ForgeRegistries.ENCHANTMENTS, TutorialMod.MODID);
	public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, TutorialMod.MODID);
	public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, TutorialMod.MODID);
	public static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, TutorialMod.MODID);
	public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, TutorialMod.MODID);

	public static void init() {
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		DIMENSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
		TILEENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
		CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
		SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public static final RegistryObject<TileBlockBase> GLOWSTONE_GENERATOR_BLOCK = BLOCKS.register("glowstone_generator", GlowstoneGeneratorBlock::new);
	public static final RegistryObject<Item> GLOWSTONE_GENERATOR_ITEM = ITEMS.register("glowstone_generator", () -> new BlockItem(GLOWSTONE_GENERATOR_BLOCK.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<TileEntityType<GlowstoneGeneratorTile>> GLOWSTONE_GENERATOR_TILE = TILEENTITIES.register("glowstone_generator", () -> TileEntityType.Builder.create(GlowstoneGeneratorTile::new, GLOWSTONE_GENERATOR_BLOCK.get()).build(null));
	public static final RegistryObject<ContainerType<GlowstoneGeneratorContainer>> GLOWSTONE_GENERATOR_CONTAINER = CONTAINERS.register("glowstone_generator", () -> IForgeContainerType.create(GlowstoneGeneratorContainer::new));

	public static final RegistryObject<TileBlockBase> ENERGY_STORAGE = BLOCKS.register("energy_storage", EnergyStorageBlock::new);
	public static final RegistryObject<Item> ENERGY_STORAGE_ITEM = ITEMS.register("energy_storage", () -> new BlockItem(ENERGY_STORAGE.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<TileEntityType<EnergyStorageTile>> ENERGY_STORAGE_TILE = TILEENTITIES.register("energy_storage", () -> TileEntityType.Builder.create(EnergyStorageTile::new, ENERGY_STORAGE.get()).build(null));
	public static final RegistryObject<ContainerType<EnergyStorageContainer>> ENERGY_STORAGE_CONTAINER = CONTAINERS.register("energy_storage", () -> IForgeContainerType.create(EnergyStorageContainer::new));

	public static final RegistryObject<TileBlockBase> SINTERING_FURNACE = BLOCKS.register("sintering_furnace", SinteringFurnaceBlock::new);
	public static final RegistryObject<Item> SINTERING_FURNACE_ITEM = ITEMS.register("sintering_furnace", () -> new BlockItem(SINTERING_FURNACE.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<TileEntityType<SinteringFurnaceTile>> SINTERING_FURNACE_TILE = TILEENTITIES.register("sintering_furnace", () -> TileEntityType.Builder.create(SinteringFurnaceTile::new, SINTERING_FURNACE.get()).build(null));
	public static final RegistryObject<ContainerType<SinteringFurnaceContainer>> SINTERING_FURNACE_CONTAINER = CONTAINERS.register("sintering_furnace", () -> IForgeContainerType.create(SinteringFurnaceContainer::new));

	public static final RegistryObject<TileBlockBase> ELECTRIC_SINTERING_FURNACE = BLOCKS.register("electric_sintering_furnace", ElectricSinteringFurnaceBlock::new);
	public static final RegistryObject<Item> ELECTRIC_SINTERING_FURNACE_ITEM = ITEMS.register("electric_sintering_furnace", () -> new BlockItem(ELECTRIC_SINTERING_FURNACE.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<TileEntityType<ElectricSinteringFurnaceTile>> ELECTRIC_SINTERING_FURNACE_TILE = TILEENTITIES.register("electric_sintering_furnace", () -> TileEntityType.Builder.create(ElectricSinteringFurnaceTile::new, ELECTRIC_SINTERING_FURNACE.get()).build(null));
	public static final RegistryObject<ContainerType<ElectricSinteringFurnaceContainer>> ELECTRIC_SINTERING_FURNACE_CONTAINER = CONTAINERS.register("electric_sintering_furnace", () -> IForgeContainerType.create(ElectricSinteringFurnaceContainer::new));

	public static final RegistryObject<TileBlockBase> CHARGER = BLOCKS.register("charger", ChargerBlock::new);
	public static final RegistryObject<Item> CHARGER_ITEM = ITEMS.register("charger", () -> new BlockItem(CHARGER.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<TileEntityType<ChargerTile>> CHARGER_TILE = TILEENTITIES.register("charger", () -> TileEntityType.Builder.create(ChargerTile::new, CHARGER.get()).build(null));
	public static final RegistryObject<ContainerType<ChargerContainer>> CHARGER_CONTAINER = CONTAINERS.register("charger", () -> IForgeContainerType.create(ChargerContainer::new));

	public static final RegistryObject<Block> COPPER_CHEST = BLOCKS.register("copper_chest", CopperChestBlock::new);
	public static final RegistryObject<Item> COPPER_CHEST_ITEM = ITEMS.register("copper_chest", () -> new BlockItem(COPPER_CHEST.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<TileEntityType<CopperChestTile>> COPPER_CHEST_TILE = TILEENTITIES.register("copper_chest", () -> TileEntityType.Builder.create(CopperChestTile::new, COPPER_CHEST.get()).build(null));
	public static final RegistryObject<ContainerType<CopperChestContainer>> COPPER_CHEST_CONTAINER = CONTAINERS.register("copper_chest", () -> IForgeContainerType.create(CopperChestContainer::new));

	public static final RegistryObject<BakedBlockBase> FANCYBLOCK = BLOCKS.register("fancyblock", FancyBlock::new);
	public static final RegistryObject<Item> FANCYBLOCK_ITEM = ITEMS.register("fancyblock", () -> new BlockItem(FANCYBLOCK.get(), new Item.Properties().group(ModGroups.itemGroup2)));
	public static final RegistryObject<TileEntityType<FancyBlockTile>> FANCY_BLOCK_TILE = TILEENTITIES.register("fancyblock", () -> TileEntityType.Builder.create(FancyBlockTile::new, FANCYBLOCK.get()).build(null));

	public static final RegistryObject<Block> MAGICBLOCK = BLOCKS.register("magicblock", MagicBlock::new);
	public static final RegistryObject<Item> MAGICBLOCK_ITEM = ITEMS.register("magicblock", () -> new BlockItem(MAGICBLOCK.get(), new Item.Properties().group(ModGroups.itemGroup2)));
	public static final RegistryObject<TileEntityType<FancyBlockTile>> MAGICBLOCK_TILE = TILEENTITIES.register("magicblock", () -> TileEntityType.Builder.create(FancyBlockTile::new, MAGICBLOCK.get()).build(null));

	public static final RegistryObject<Item> GRENADE = ITEMS.register("grenade", GrenadeItem::new);
//	public static final RegistryObject<EntityType<GrenadeEntity>> GRENADE_ENTITY = ENTITIES.register("grenade", () -> createEntity("grenade", 0.5F, 0.5F, true, EntityClassification.MISC, GrenadeEntity::new));
	public static final RegistryObject<EntityType<GrenadeEntity>> GRENADE_ENTITY = ENTITIES.register("grenade", () -> EntityType.Builder.<GrenadeEntity>create(GrenadeEntity::new, EntityClassification.MISC)
			.size(0.5F, 0.5F)
			.setShouldReceiveVelocityUpdates(true)
			.build("grenade"));

	public static final RegistryObject<MobEggBase> WEIRDMOB_SPAWN_EGG = ITEMS.register("weirdmob_spawn_egg", () -> new MobEggBase(0xff1111, ModGroups.itemGroup, Registration.WEIRDMOB::get));
//	public static final RegistryObject<EntityType<?>> WEIRDMOB = ENTITIES.register("weirdmob", () -> createEntity("weirdmob", 1.0F, 1.0F, false, EntityClassification.CREATURE, WeirdMobEntity::new));
	public static final RegistryObject<EntityType<WeirdMobEntity>> WEIRDMOB = ENTITIES.register("weirdmob", () -> EntityType.Builder.create(WeirdMobEntity::new, EntityClassification.CREATURE)
			.size(1.0F, 1.0F)
			.setShouldReceiveVelocityUpdates(false)
			.build("weirdmob"));

	public static final RegistryObject<MobEggBase> CENTAUR_SPAWN_EGG = ITEMS.register("centaur_spawn_egg", () -> new MobEggBase(0xeecc11, ModGroups.itemGroup2, Registration.CENTAUR::get));
//	public static final RegistryObject<EntityType<?>> CENTAUR = ENTITIES.register("centaur", () -> createEntity("centaur", 0.9F, 2.8F, false, EntityClassification.CREATURE, CentaurEntity::new));
	public static final RegistryObject<EntityType<CentaurEntity>> CENTAUR = ENTITIES.register("centaur", () -> EntityType.Builder.create(CentaurEntity::new, EntityClassification.CREATURE)
			.size(0.9F, 2.8F)
			.setShouldReceiveVelocityUpdates(false)
			.build("centaur"));
	public static final RegistryObject<SoundEvent> ENTITY_CENTAUR_AMBIENT = SOUNDS.register("entity.centaur.ambient", () -> new SoundEvent(new ResourceLocation(TutorialMod.MODID, "entity.centaur.ambient")));
	public static final RegistryObject<SoundEvent> ENTITY_CENTAUR_HURT = SOUNDS.register("entity.centaur.hurt", () -> new SoundEvent(new ResourceLocation(TutorialMod.MODID, "entity.centaur.hurt")));
	public static final RegistryObject<SoundEvent> ENTITY_CENTAUR_DEATH = SOUNDS.register("entity.centaur.death", () -> new SoundEvent(new ResourceLocation(TutorialMod.MODID, "entity.centaur.death")));

	public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", ItemBase::new);
	public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", RubyBlock::new);
	public static final RegistryObject<Item> RUBY_BLOCK_ITEM = ITEMS.register("ruby_block", () -> new BlockItem(RUBY_BLOCK.get(), new Item.Properties().group(ModGroups.itemGroup)));

	// Tree
// TODO: Looking for improvments
	public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot", ItemBase::new);
	public static final RegistryObject<Block> COPPER_BLOCK = BLOCKS.register("copper_block", () -> new BlockBase(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(5.0f)));
	public static final RegistryObject<Item> COPPER_BLOCK_ITEM = ITEMS.register("copper_block", () -> new BlockItem(COPPER_BLOCK.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<Block> COPPER_ORE_OVERWORLD = BLOCKS.register("copper_ore", () -> new CustomOreBlock(3f, 2, 0));
	public static final RegistryObject<Item> COPPER_ORE_OVERWORLD_ITEM = ITEMS.register("copper_ore", () -> new BlockItem(COPPER_ORE_OVERWORLD.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<Block> COPPER_ORE_END = BLOCKS.register("copper_ore_end", () -> new CustomOreBlock(3f, 2, 0));
	public static final RegistryObject<Item> COPPER_ORE_END_ITEM = ITEMS.register("copper_ore_end", () -> new BlockItem(COPPER_ORE_END.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<Block> COPPER_ORE_NETHER = BLOCKS.register("copper_ore_nether", () -> new CustomOreBlock(3f, 2, 0));
	public static final RegistryObject<Item> COPPER_ORE_NETHER_ITEM = ITEMS.register("copper_ore_nether", () -> new BlockItem(COPPER_ORE_NETHER.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<Block> COPPER_LEAVES = BLOCKS.register("copper_leaves", CustomLeavesBlock::new);
	public static final RegistryObject<Item> COPPER_LEAVES_ITEM = ITEMS.register("copper_leaves", () -> new BlockItem(COPPER_LEAVES.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<Block> COPPER_LOG = BLOCKS.register("copper_log", () -> new LogBlock(MaterialColor.ORANGE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2F).sound(SoundType.WOOD)));
	public static final RegistryObject<Item> COPPER_LOG_ITEM = ITEMS.register("copper_log", () -> new BlockItem(COPPER_LOG.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<Block> COPPER_PLANKS = BLOCKS.register("copper_planks", () -> new BlockBase(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA, 2.0F, 3.0F, SoundType.WOOD));
	public static final RegistryObject<Item> COPPER_PLANKS_ITEM = ITEMS.register("copper_planks", () -> new BlockItem(COPPER_PLANKS.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<SaplingBlock> COPPER_SAPLING = BLOCKS.register("copper_sapling", () -> new CustomSaplingBlock(COPPER_LEAVES.get(), COPPER_LOG.get(), Registration.COPPER_SAPLING::get));
	public static final RegistryObject<Item> COPPER_SAPLING_ITEM = ITEMS.register("copper_sapling", () -> new BlockItem(COPPER_SAPLING.get(), new Item.Properties().group(ModGroups.itemGroup)));

	public static final RegistryObject<Block> ALUMINIUM_BLOCK = BLOCKS.register("aluminium_block", () -> new BlockBase(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(5.0f)));
	public static final RegistryObject<Item> ALUMINIUM_BLOCK_ITEM = ITEMS.register("aluminium_block", () -> new BlockItem(ALUMINIUM_BLOCK.get(), new Item.Properties().group(ModGroups.itemGroup)));

	public static final RegistryObject<Block> ALUMINIUM_ORE_OVERWORLD = BLOCKS.register("aluminium_ore", () -> new CustomOreBlock(3f, 2, 0));
	public static final RegistryObject<Item> ALUMINIUM_ORE_OVERWORLD_ITEM = ITEMS.register("aluminium_ore", () -> new BlockItem(ALUMINIUM_ORE_OVERWORLD.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<Block> ALUMINIUM_ORE_END = BLOCKS.register("aluminium_ore_end", () -> new CustomOreBlock(3f, 2, 0));
	public static final RegistryObject<Item> ALUMINIUM_ORE_END_ITEM = ITEMS.register("aluminium_ore_end", () -> new BlockItem(ALUMINIUM_ORE_END.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<Block> ALUMINIUM_ORE_NETHER = BLOCKS.register("aluminium_ore_nether", () -> new CustomOreBlock(3f, 2, 0));
	public static final RegistryObject<Item> ALUMINIUM_ORE_NETHER_ITEM = ITEMS.register("aluminium_ore_nether", () -> new BlockItem(ALUMINIUM_ORE_NETHER.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<Block> ALUMINIUM_LEAVES = BLOCKS.register("aluminium_leaves", CustomLeavesBlock::new);
	public static final RegistryObject<Item> ALUMINIUM_LEAVES_ITEM = ITEMS.register("aluminium_leaves", () -> new BlockItem(ALUMINIUM_LEAVES.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<Block> ALUMINIUM_LOG = BLOCKS.register("aluminium_log", () -> new LogBlock(MaterialColor.LIGHT_GRAY, Block.Properties.create(Material.WOOD, MaterialColor.LIGHT_GRAY).hardnessAndResistance(2F).sound(SoundType.WOOD)))));
	public static final RegistryObject<Item> ALUMINIUM_LOG_ITEM = ITEMS.register("aluminium_log", () -> new BlockItem(ALUMINIUM_LOG.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<Block> ALUMINIUM_PLANKS = BLOCKS.register("aluminium_planks", () -> new BlockBase(Material.WOOD, MaterialColor.LIGHT_GRAY, 2.0F, 3.0F, SoundType.WOOD));
	public static final RegistryObject<Item> ALUMINIUM_PLANKS_ITEM = ITEMS.register("aluminium_planks", () -> new BlockItem(ALUMINIUM_PLANKS.get(), new Item.Properties().group(ModGroups.itemGroup)));
	public static final RegistryObject<SaplingBlock> ALUMINIUM_SAPLING = BLOCKS.register("aluminium_sapling", () -> new CustomSaplingBlock(ALUMINIUM_LEAVES.get(), ALUMINIUM_LOG.get(), Registration.ALUMINIUM_SAPLING::get));
	public static final RegistryObject<Item> ALUMINIUM_SAPLING_ITEM = ITEMS.register("aluminium_sapling", () -> new BlockItem(ALUMINIUM_SAPLING.get(), new Item.Properties().group(ModGroups.itemGroup)));

// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// BLOCKS

	// Ores
	public static final RegistryObject<Block> RUBY_ORE = BLOCKS.register("ruby_ore", RubyOre::new);
	public static final RegistryObject<Item> RUBY_ORE_ITEM = ITEMS.register("ruby_ore", () -> new BlockItem(RUBY_ORE.get(), new Item.Properties().group(ModGroups.itemGroup)));

	// Furniture
	public static final RegistryObject<Block> SANTA_HAT = BLOCKS.register("santa_hat", SantaHatBlock::new);
	public static final RegistryObject<Item> SANTA_HAT_ITEM = ITEMS.register("santa_hat", () -> new BlockItem(SANTA_HAT.get(), new Item.Properties().group(ModGroups.itemGroup)));

	// Food
// TODO: Looking for improvments
	public static final RegistryObject<Item> RICE = ITEMS.register("rice", () -> new SeedItem(Registration.RICE_PLANT.get(), 3));
//	public static final RegistryObject<Block> RICE_PLANT = BLOCKS.register("rice_plant", () -> new FoodPlantBlock(() -> Registration.RICE.get(), () -> Registration.RICE.get(), 3f, 2f));
	public static final RegistryObject<Block> RICE_PLANT = BLOCKS.register("rice_plant", () -> new FoodPlantBlock(() -> RICE.get(), () -> RICE.get(), 3f, 2f));
//	public static final RegistryObject<Item> RICE = ITEMS.register("rice_plant", () -> new BlockItem(RICE_PLANT.get(), new Item.Properties().group(ModGroups.itemGroup)));

	public static final RegistryObject<Item> RICE_BOWL = ITEMS.register("rice_bowl", () -> new FoodItem(8, 2, false, false, false));

	// Machines
	public static final RegistryObject<Block> TELEPORTER = BLOCKS.register("teleporter", () -> new TeleporterBlock(1));
	public static final RegistryObject<Item> TELEPORTER_ITEM = ITEMS.register("teleporter", () -> new BlockItem(TELEPORTER.get(), new Item.Properties().group(ModGroups.itemGroup)));

// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ITEMS 

	// Items
	public static final RegistryObject<Item> FIRSTITEM = ITEMS.register("firstitem", () -> new Item(new Item.Properties().group(ModGroups.itemGroup2)));
	public static final RegistryObject<Item> TESTITEM = ITEMS.register("testitem", TestItem::new);
	public static final RegistryObject<Item> ENERGYWAND = ITEMS.register("energy_wand", EnergyWandItem::new);
	public static final RegistryObject<Item> FANCY_SWORD = ITEMS.register("fancy_sword", FancySwordItem::new);

	// Wands
	public static final RegistryObject<Item> WOOD_WAND = ITEMS.register("wood_wand", ItemBase::new);
	public static final RegistryObject<Item> STONE_WAND = ITEMS.register("stone_wand", ItemBase::new);
	public static final RegistryObject<Item> IRON_WAND = ITEMS.register("iron_wand", ItemBase::new);
	public static final RegistryObject<Item> GOLD_WAND = ITEMS.register("gold_wand", ItemBase::new);
	public static final RegistryObject<Item> DIAMOND_WAND = ITEMS.register("diamond_wand", ItemBase::new);
	public static final RegistryObject<Item> RUBY_WAND = ITEMS.register("ruby_wand", ItemBase::new);
	public static final RegistryObject<Item> OBSIDIAN_WAND = ITEMS.register("obsidian_wand", ItemBase::new);
	public static final RegistryObject<Item> EMERALD_WAND = ITEMS.register("emerald_wand", ItemBase::new);

	// Tools
	public static final RegistryObject<Item> RUBY_AXE = ITEMS.register("ruby_axe", () -> new ToolAxe(6.0F, -3.1F, ToolMaterial.RUBY));
	public static final RegistryObject<Item> RUBY_HOE = ITEMS.register("ruby_hoe", () -> new ToolHoe(-1.0f, ToolMaterial.RUBY));
	public static final RegistryObject<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", () -> new ToolPickaxe(1, -3.2f, ToolMaterial.RUBY));
	public static final RegistryObject<Item> RUBY_SHOVEL = ITEMS.register("ruby_shovel", () -> new ToolShovel(1.5f, -3.0f, ToolMaterial.RUBY));
	public static final RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword", () -> new ToolSword(6, -3f, ToolMaterial.RUBY));

	// Armor
	public static final RegistryObject<Item> RUBY_HELMET = ITEMS.register("ruby_helmet", () -> new ArmorBase(CustomArmorMaterial.RUBY, EquipmentSlotType.HEAD));
	public static final RegistryObject<Item> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate", () -> new ArmorBase(CustomArmorMaterial.RUBY, EquipmentSlotType.CHEST));
	public static final RegistryObject<Item> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () -> new ArmorBase(CustomArmorMaterial.RUBY, EquipmentSlotType.LEGS));
	public static final RegistryObject<Item> RUBY_BOOTS = ITEMS.register("ruby_boots", () -> new ArmorBase(CustomArmorMaterial.RUBY, EquipmentSlotType.FEET));

	public static final RegistryObject<Item> SUNFLOW_HELMET = ITEMS.register("sunflow_helmet", () -> new ArmorSkinBase(SkinType.SUNFLOW, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.HEAD));
	public static final RegistryObject<Item> SUNFLOW_CHESTPLATE = ITEMS.register("sunflow_chestplate", () -> new ArmorSkinBase(SkinType.SUNFLOW, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.CHEST));
	public static final RegistryObject<Item> SUNFLOW_LEGGINGS = ITEMS.register("sunflow_leggings", () -> new ArmorSkinBase(SkinType.SUNFLOW, CustomArmorMaterial.RUBY, 2, EquipmentSlotType.LEGS));
	public static final RegistryObject<Item> SUNFLOW_BOOTS = ITEMS.register("sunflow_boots", () -> new ArmorSkinBase(SkinType.SUNFLOW, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.FEET));

	public static final RegistryObject<Item> BEKE_HELMET = ITEMS.register("beke_helmet", () -> new ArmorSkinBase(SkinType.BEKESCSABA99, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.HEAD));
	public static final RegistryObject<Item> BEKE_CHESTPLATE = ITEMS.register("beke_chestplate", () -> new ArmorSkinBase(SkinType.BEKESCSABA99, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.CHEST));
	public static final RegistryObject<Item> BEKE_LEGGINGS = ITEMS.register("beke_leggings", () -> new ArmorSkinBase(SkinType.BEKESCSABA99, CustomArmorMaterial.RUBY, 2, EquipmentSlotType.LEGS));
	public static final RegistryObject<Item> BEKE_BOOTS = ITEMS.register("beke_boots", () -> new ArmorSkinBase(SkinType.BEKESCSABA99, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.FEET));

	public static final RegistryObject<Item> PHANTOME_HELMET = ITEMS.register("phantom_helmet", () -> new ArmorSkinBase(SkinType.PHANTOM, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.HEAD));
	public static final RegistryObject<Item> PHANTOM_CHESTPLATE = ITEMS.register("phantom_chestplate", () -> new ArmorSkinBase(SkinType.PHANTOM, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.CHEST));
	public static final RegistryObject<Item> PHANTOME_LEGGINGS = ITEMS.register("phantom_leggings", () -> new ArmorSkinBase(SkinType.PHANTOM, CustomArmorMaterial.RUBY, 2, EquipmentSlotType.LEGS));
	public static final RegistryObject<Item> PHANTOM_BOOTS = ITEMS.register("phantom_boots", () -> new ArmorSkinBase(SkinType.PHANTOM, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.FEET));

	public static final RegistryObject<Item>[] SKIN = ItemNBTSkin.create(ITEMS);

	// Food
	public static final RegistryObject<Item> EVIL_APPLE = ITEMS.register("evil_apple", () -> new FoodItem(6, 4, false, false, true,
			new EffectInstance(Effects.INSTANT_HEALTH, 1, 1),
			new EffectInstance(Effects.REGENERATION, 20 * 4, 0)));

// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// DIMENSIONS

	public static DimensionType BADLANDS_TYPE;
	public static final RegistryObject<ModDimension> BADLANDS = DIMENSIONS.register("badlands", () -> new ModDimensionBase(BadlandsDimension::new));

// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// BIOMES

	public static final RegistryObject<CopperBiome> COPPER = BIOMES.register("copper", CopperBiome::new);

// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// TILEENTITIES
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ENCHANTMENTS

	public static final RegistryObject<EnchantmentMultiJump> ENCHANTMENT_MULTIJUMP = ENCHANTMENTS.register("multijump", EnchantmentMultiJump::new);

// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ENTITIES
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// FLUIDS
	public static final ForgeFlowingFluid.Properties MOLTEN_COPPER_PROPERTIES = new ForgeFlowingFluid.Properties(() -> Registration.MOLTEN_COPPER.get(), () -> Registration.FLOWING_MOLTEN_COPPER.get(),
			FluidAttributes.builder(new ResourceLocation(TutorialMod.MODID, "block/molten_copper_still"), new ResourceLocation(TutorialMod.MODID, "block/molten_copper_flow")))
					.bucket(() -> Registration.MOLTEN_COPPER_BUCKET.get()).block(() -> Registration.MOLTEN_COPPER_BLOCK.get());

	public static final RegistryObject<FlowingFluid> MOLTEN_COPPER = FLUIDS.register("molten_copper", () -> new ForgeFlowingFluid.Source(MOLTEN_COPPER_PROPERTIES));

	public static final RegistryObject<FlowingFluid> FLOWING_MOLTEN_COPPER = FLUIDS.register("molten_copper_flowing", () -> new ForgeFlowingFluid.Flowing(MOLTEN_COPPER_PROPERTIES));

	public static final RegistryObject<FlowingFluidBlock> MOLTEN_COPPER_BLOCK = BLOCKS.register("molten_copper_block", () -> new FlowingFluidBlock(() -> MOLTEN_COPPER.get(), Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));

	public static final RegistryObject<BucketItem> MOLTEN_COPPER_BUCKET = ITEMS.register("molten_copper_bucket", () -> new BucketItem(() -> MOLTEN_COPPER.get(), new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ModGroups.itemGroup)));

// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// CONTAINERS
// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// SOUNDS

	public static <T extends Entity> EntityType<T> createEntity(String name, float w, float h, boolean receiveVelocityUpdates, EntityClassification classification, EntityType.IFactory<T> factory) {
		EntityType<T> type = EntityType.Builder.<T>create(factory, classification)
				.size(w, h)
				.setShouldReceiveVelocityUpdates(receiveVelocityUpdates)
				.build(name);
		return type;
	}
}
