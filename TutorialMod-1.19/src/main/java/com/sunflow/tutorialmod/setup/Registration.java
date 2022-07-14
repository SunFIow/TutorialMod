package com.sunflow.tutorialmod.setup;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.ConfigBlock;
import com.sunflow.tutorialmod.block.MultiBlock;
import com.sunflow.tutorialmod.block.TeleporterBlock;
import com.sunflow.tutorialmod.block.copper_chest.CopperChest;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestContainer;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestEntity;
import com.sunflow.tutorialmod.block.food.FoodPlantBlock;
import com.sunflow.tutorialmod.block.furniture.SantaHatBlock;
import com.sunflow.tutorialmod.block.furniture.fancyblock.FancyBlock;
import com.sunflow.tutorialmod.block.furniture.fancyblock.FancyBlockEntity;
import com.sunflow.tutorialmod.block.magicblock.MagicBlock;
import com.sunflow.tutorialmod.block.magicblock.MagicBlockEntity;
import com.sunflow.tutorialmod.block.multipart.ComplexMultipart;
import com.sunflow.tutorialmod.block.multipart.ComplexMultipartEntity;
import com.sunflow.tutorialmod.block.ore.CustomOreBlock;
import com.sunflow.tutorialmod.block.ore.RubyBlock;
import com.sunflow.tutorialmod.block.ore.RubyOre;
import com.sunflow.tutorialmod.block.tree.CustomLeavesBlock;
import com.sunflow.tutorialmod.block.tree.CustomLogBlock;
import com.sunflow.tutorialmod.block.tree.CustomSaplingBlock;
import com.sunflow.tutorialmod.enchantment.EnchantmentMultiJump;
import com.sunflow.tutorialmod.entity.centaur.CentaurEntity;
import com.sunflow.tutorialmod.entity.weirdmob.WeirdMobEntity;
import com.sunflow.tutorialmod.item.EnergyWandItem;
import com.sunflow.tutorialmod.item.FancySwordItem;
import com.sunflow.tutorialmod.item.ItemUtil;
import com.sunflow.tutorialmod.item.TestItem;
import com.sunflow.tutorialmod.item.armor.ArmorBase;
import com.sunflow.tutorialmod.item.armor.ArmorSkinBase;
import com.sunflow.tutorialmod.item.armor.CustomArmorMaterial;
import com.sunflow.tutorialmod.item.armor.ItemNBTSkin;
import com.sunflow.tutorialmod.item.armor.ItemNBTSkin.SkinType;
import com.sunflow.tutorialmod.item.food.SeedItem;
import com.sunflow.tutorialmod.item.grenade.GrenadeEntity;
import com.sunflow.tutorialmod.item.grenade.GrenadeItem;
import com.sunflow.tutorialmod.item.tools.ToolAxe;
import com.sunflow.tutorialmod.item.tools.ToolHoe;
import com.sunflow.tutorialmod.item.tools.ToolMaterial;
import com.sunflow.tutorialmod.item.tools.ToolPickaxe;
import com.sunflow.tutorialmod.item.tools.ToolShovel;
import com.sunflow.tutorialmod.item.tools.ToolSword;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.world.ForgeWorldPreset;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {

    // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
    // Game objects
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MODID);
    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, TutorialMod.MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MODID);
    // private static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TutorialMod.MODID);
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TutorialMod.MODID);
    // private static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, TutorialMod.MODID);
    private static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, TutorialMod.MODID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, TutorialMod.MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, TutorialMod.MODID);
    // private static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, TutorialMod.MODID);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, TutorialMod.MODID);
    // private static final DeferredRegister<Motive> PAINTING_TYPES = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, TutorialMod.MODID);
    // private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TutorialMod.MODID);
    // private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, TutorialMod.MODID);
    // private static final DeferredRegister<StatType<?>> STAT_TYPES = DeferredRegister.create(ForgeRegistries.STAT_TYPES, TutorialMod.MODID);

    // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
    // Villages
    // public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, TutorialMod.MODID);
    // public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, TutorialMod.MODID);
    // public static final DeferredRegister<MemoryModuleType<?>> MEMORY_MODULE_TYPES = DeferredRegister.create(ForgeRegistries.MEMORY_MODULE_TYPES, TutorialMod.MODID);
    // public static final DeferredRegister<SensorType<?>> SENSOR_TYPES = DeferredRegister.create(ForgeRegistries.SENSOR_TYPES, TutorialMod.MODID);
    // public static final DeferredRegister<Schedule> SCHEDULES = DeferredRegister.create(ForgeRegistries.SCHEDULES, TutorialMod.MODID);
    // public static final DeferredRegister<Activity> ACTIVITIES = DeferredRegister.create(ForgeRegistries.ACTIVITIES, TutorialMod.MODID);

    // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
    // Worldgen
    // public static final DeferredRegister<WorldCarver<?>> WORLD_CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, TutorialMod.MODID);
    // public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, TutorialMod.MODID);
    // public static final DeferredRegister<ChunkStatus> CHUNK_STATUS = DeferredRegister.create(ForgeRegistries.CHUNK_STATUS, TutorialMod.MODID);
    // public static final DeferredRegister<StructureFeature<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, TutorialMod.MODID);
    // public static final DeferredRegister<BlockStateProviderType<?>> BLOCK_STATE_PROVIDER_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_STATE_PROVIDER_TYPES, TutorialMod.MODID);
    // public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, TutorialMod.MODID);
    // public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, TutorialMod.MODID);

    // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
    // Dynamic/Data driven.
    // public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, TutorialMod.MODID);

    // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
    // Custom forge registries
    // public static final DeferredRegister<DataSerializerEntry> DATA_SERIALIZERS = DeferredRegister.create(ForgeRegistries.DATA_SERIALIZERS, TutorialMod.MODID);
    // public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, TutorialMod.MODID);
    // public static final DeferredRegister<ForgeWorldPreset> WORLD_TYPES = DeferredRegister.create(ForgeRegistries.WORLD_TYPES, TutorialMod.MODID);

    public static void init() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
        // Game objects
        BLOCKS.register(modBus);
        FLUIDS.register(modBus);
        ITEMS.register(modBus);
        // MOB_EFFECTS.register(modBus);
        SOUND_EVENTS.register(modBus);
        // POTIONS.register(modBus);
        ENCHANTMENTS.register(modBus);
        ENTITIES.register(modBus);
        BLOCK_ENTITIES.register(modBus);
        // PARTICLE_TYPES.register(modBus);
        CONTAINERS.register(modBus);
        // PAINTING_TYPES.register(modBus);
        // RECIPE_SERIALIZERS.register(modBus);
        // ATTRIBUTES.register(modBus);
        // STAT_TYPES.register(modBus);

        // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
        // Villages
        // PROFESSIONS.register(modBus);
        // POI_TYPES.register(modBus);
        // MEMORY_MODULE_TYPES.register(modBus);
        // SENSOR_TYPES.register(modBus);
        // SCHEDULES.register(modBus);
        // ACTIVITIES.register(modBus);

        // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
        // Worldgen
        // WORLD_CARVERS.register(modBus);
        // FEATURES.register(modBus);
        // CHUNK_STATUS.register(modBus);
        // STRUCTURE_FEATURES.register(modBus);
        // BLOCK_STATE_PROVIDER_TYPES.register(modBus);
        // FOLIAGE_PLACER_TYPES.register(modBus);
        // TREE_DECORATOR_TYPES.register(modBus);

        // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
        // Dynamic/Data driven.
        // BIOMES.register(modBus);

        // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
        // Custom forge registries
        // DATA_SERIALIZERS.register(modBus);
        // LOOT_MODIFIER_SERIALIZERS.register(modBus);
        // WORLD_TYPES.register(modBus);
    }

    public static final BlockBehaviour.Properties ORE_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.5F, 4.0F);
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModTabs.ITEM_TAB);
    public static final Item.Properties ITEM_PROPERTIES2 = new Item.Properties().tab(ModTabs.ITEM_TAB2);

    // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
    // Game objects
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // BLOCKS
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", ItemUtil::Default);
    public static final SunSimpleBlock<RubyBlock> RUBY_BLOCK = new SunSimpleBlock<>("ruby_block", RubyBlock::new, ITEM_PROPERTIES);
    public static final SunSimpleBlock<ConfigBlock> CONFIG_BLOCK = new SunSimpleBlock<>("config_block", ConfigBlock::new, ITEM_PROPERTIES);
    public static final SunSimpleBlock<MultiBlock> MULTIBLOCK = new SunSimpleBlock<>("multiblock", MultiBlock::new, ITEM_PROPERTIES);

    // public static final RegistryObject<FancyBlock> FANCYBLOCK = BLOCKS.register("fancyblock", FancyBlock::new);
    // public static final RegistryObject<BlockItem> FANCYBLOCK_ITEM = ITEMS.register("fancyblock", () -> new BlockItem(FANCYBLOCK.get(), new Item.Properties().group(ModGroups.itemGroup2)));
    // public static final RegistryObject<TileEntityType<FancyBlockTile>> FANCY_BLOCK_TILE = TILEENTITIES.register("fancyblock", () -> TileEntityType.Builder.create(FancyBlockTile::new, FANCYBLOCK.get()).build(null));

    // public static final RegistryObject<MagicBlock> MAGICBLOCK = BLOCKS.register("magicblock", MagicBlock::new);
    // public static final RegistryObject<BlockItem> MAGICBLOCK_ITEM = ITEMS.register("magicblock", () -> new BlockItem(MAGICBLOCK.get(), new Item.Properties().group(ModGroups.itemGroup2)));
    // public static final RegistryObject<TileEntityType<MagicBlockTile>> MAGICBLOCK_TILE = TILEENTITIES.register("magicblock", () -> TileEntityType.Builder.create(MagicBlockTile::new, MAGICBLOCK.get()).build(null));

    // Ores    
    public static final SunSimpleBlock<RubyOre> RUBY_ORE = new SunSimpleBlock<>("ruby_ore", RubyOre::new, ITEM_PROPERTIES);
    public static final SunSimpleBlock<Block> SUN_ORE_OVERWORLD = new SunSimpleBlock<>("sun_ore_overworld", () -> new Block(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunSimpleBlock<Block> SUN_ORE_NETHER = new SunSimpleBlock<>("sun_ore_nether", () -> new Block(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunSimpleBlock<Block> SUN_ORE_END = new SunSimpleBlock<>("sun_ore_end", () -> new Block(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunSimpleBlock<Block> SUN_ORE_DEEPSLATE = new SunSimpleBlock<>("sun_ore_deepslate", () -> new Block(ORE_PROPERTIES), ITEM_PROPERTIES);

    // Furniture
    public static final SunSimpleBlock<SantaHatBlock> SANTA_HAT = new SunSimpleBlock<>("santa_hat", SantaHatBlock::new, ITEM_PROPERTIES);

    // Food
    // public static final RegistryObject<SeedItem> RICE = ITEMS.register("rice", () -> new SeedItem(Registration.RICE_PLANT.get(), 3));
    // public static final RegistryObject<FoodPlantBlock<SeedItem, SeedItem>> RICE_PLANT = BLOCKS.register("rice", () -> new FoodPlantBlock<>(RICE, RICE, 3f, 2f));
    public static final SunSimpleBlock<Block> RICE = new SunSimpleBlock<>(
            "rice",
            () -> new FoodPlantBlock<>(Registration.RICE::item, Registration.RICE::item, 3f, 2f),
            () -> new SeedItem(Registration.RICE::block, 3));
    public static final RegistryObject<Item> RICE_BOWL = ITEMS.register("rice_bowl", () -> new Item(ItemUtil.Food(ModTabs.ITEM_TAB, 8, 2)));

    // Machines
    public static final SunSimpleBlock<TeleporterBlock> TELEPORTER = new SunSimpleBlock<>("teleporter", () -> new TeleporterBlock(1), ITEM_PROPERTIES);
    public static final SunBlockEntity<ComplexMultipart, ComplexMultipartEntity> COMPLEX_MULTIPART = new SunBlockEntity<>("complex_multipart", ComplexMultipart::new, ITEM_PROPERTIES, ComplexMultipartEntity::new);

    // Tree
    public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot", ItemUtil::Default);
    public static final SunSimpleBlock<Block> COPPER_BLOCK = new SunSimpleBlock<>("copper_block", () -> new Block(Block.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5.0f)), ITEM_PROPERTIES);
    public static final SunSimpleBlock<CustomOreBlock> COPPER_ORE_OVERWORLD = new SunSimpleBlock<>("copper_ore", () -> new CustomOreBlock(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunSimpleBlock<CustomOreBlock> COPPER_ORE_NETHER = new SunSimpleBlock<>("copper_ore_nether", () -> new CustomOreBlock(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunSimpleBlock<CustomOreBlock> COPPER_ORE_END = new SunSimpleBlock<>("copper_ore_end", () -> new CustomOreBlock(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunSimpleBlock<CustomOreBlock> COPPER_ORE_DEEPSLATE = new SunSimpleBlock<>("copper_ore_deepslate", () -> new CustomOreBlock(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunSimpleBlock<CustomLeavesBlock> COPPER_LEAVES = new SunSimpleBlock<>("copper_leaves", CustomLeavesBlock::new, ITEM_PROPERTIES);
    public static final SunSimpleBlock<CustomLogBlock> COPPER_LOG = new SunSimpleBlock<>("copper_log", () -> new CustomLogBlock(MaterialColor.TERRACOTTA_ORANGE, Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2F).sound(SoundType.WOOD)), ITEM_PROPERTIES);
    public static final SunSimpleBlock<Block> COPPER_PLANKS = new SunSimpleBlock<>("copper_planks", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), ITEM_PROPERTIES);
    public static final SunSimpleBlock<CustomSaplingBlock> COPPER_SAPLING = new SunSimpleBlock<>("copper_sapling", () -> new CustomSaplingBlock(COPPER_LEAVES.block(), COPPER_LOG.block()), ITEM_PROPERTIES);

    public static final RegistryObject<Item> ALUMINIUM_INGOT = ITEMS.register("aluminium_ingot", ItemUtil::Default);
    public static final SunSimpleBlock<Block> ALUMINIUM_BLOCK = new SunSimpleBlock<>("aluminium_block", () -> new Block(Block.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5.0f)), ITEM_PROPERTIES);
    public static final SunSimpleBlock<CustomOreBlock> ALUMINIUM_ORE_OVERWORLD = new SunSimpleBlock<>("aluminium_ore", () -> new CustomOreBlock(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunSimpleBlock<CustomOreBlock> ALUMINIUM_ORE_NETHER = new SunSimpleBlock<>("aluminium_ore_nether", () -> new CustomOreBlock(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunSimpleBlock<CustomOreBlock> ALUMINIUM_ORE_END = new SunSimpleBlock<>("aluminium_ore_end", () -> new CustomOreBlock(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunSimpleBlock<CustomOreBlock> ALUMINIUM_ORE_DEEPSLATE = new SunSimpleBlock<>("aluminium_ore_deepslate", () -> new CustomOreBlock(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunSimpleBlock<CustomLeavesBlock> ALUMINIUM_LEAVES = new SunSimpleBlock<>("aluminium_leaves", CustomLeavesBlock::new, ITEM_PROPERTIES);
    public static final SunSimpleBlock<CustomLogBlock> ALUMINIUM_LOG = new SunSimpleBlock<>("aluminium_log", () -> new CustomLogBlock(MaterialColor.COLOR_LIGHT_GRAY, Block.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).strength(2F).sound(SoundType.WOOD)), ITEM_PROPERTIES);
    public static final SunSimpleBlock<Block> ALUMINIUM_PLANKS = new SunSimpleBlock<>("aluminium_planks", () -> new Block(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)), ITEM_PROPERTIES);
    public static final SunSimpleBlock<CustomSaplingBlock> ALUMINIUM_SAPLING = new SunSimpleBlock<>("aluminium_sapling", () -> new CustomSaplingBlock(ALUMINIUM_LEAVES.block(), ALUMINIUM_LOG.block()), ITEM_PROPERTIES);

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // FLUIDS
    public static final FluidAttributes.Builder MOLTEN_COPPER_ATTRIBUTES = FluidAttributes.builder(
            new ResourceLocation(TutorialMod.MODID, "block/molten_copper_still"),
            new ResourceLocation(TutorialMod.MODID, "block/molten_copper_flow"))
            // .color(0xFF_3F_76_E4)
            .luminosity(15).density(3000).viscosity(6000).temperature(1300)
            .sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA);
    public static final ForgeFlowingFluid.Properties MOLTEN_COPPER_PROPERTIES = new ForgeFlowingFluid.Properties(
            Registration.MOLTEN_COPPER,
            Registration.FLOWING_MOLTEN_COPPER,
            MOLTEN_COPPER_ATTRIBUTES)
                    .bucket(Registration.MOLTEN_COPPER_BUCKET)
                    .block(Registration.MOLTEN_COPPER_BLOCK);

    public static final RegistryObject<ForgeFlowingFluid.Source> MOLTEN_COPPER = FLUIDS.register("molten_copper", () -> new ForgeFlowingFluid.Source(MOLTEN_COPPER_PROPERTIES));

    public static final RegistryObject<ForgeFlowingFluid.Flowing> FLOWING_MOLTEN_COPPER = FLUIDS.register("molten_copper_flowing", () -> new ForgeFlowingFluid.Flowing(MOLTEN_COPPER_PROPERTIES));

    public static final RegistryObject<LiquidBlock> MOLTEN_COPPER_BLOCK = BLOCKS.register("molten_copper_block", () -> new LiquidBlock(MOLTEN_COPPER, Block.Properties.copy(Blocks.LAVA)));

    public static final RegistryObject<BucketItem> MOLTEN_COPPER_BUCKET = ITEMS.register("molten_copper_bucket", () -> new BucketItem(MOLTEN_COPPER, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ModTabs.ITEM_TAB)));

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ITEMS
    public static final RegistryObject<Item> FIRSTITEM = ITEMS.register("firstitem", () -> new Item(ITEM_PROPERTIES2));
    public static final RegistryObject<Item> TESTITEM = ITEMS.register("testitem", TestItem::new);
    public static final RegistryObject<Item> ENERGYWAND = ITEMS.register("energy_wand", EnergyWandItem::new);
    public static final RegistryObject<Item> FANCY_SWORD = ITEMS.register("fancy_sword", FancySwordItem::new);

    // Wands
    public static final RegistryObject<Item> WOOD_WAND = ITEMS.register("wood_wand", ItemUtil::Default);
    public static final RegistryObject<Item> STONE_WAND = ITEMS.register("stone_wand", ItemUtil::Default);
    public static final RegistryObject<Item> IRON_WAND = ITEMS.register("iron_wand", ItemUtil::Default);
    public static final RegistryObject<Item> GOLD_WAND = ITEMS.register("gold_wand", ItemUtil::Default);
    public static final RegistryObject<Item> DIAMOND_WAND = ITEMS.register("diamond_wand", ItemUtil::Default);
    public static final RegistryObject<Item> RUBY_WAND = ITEMS.register("ruby_wand", ItemUtil::Default);
    public static final RegistryObject<Item> OBSIDIAN_WAND = ITEMS.register("obsidian_wand", ItemUtil::Default);
    public static final RegistryObject<Item> EMERALD_WAND = ITEMS.register("emerald_wand", ItemUtil::Default);

    // Tools
    public static final RegistryObject<Item> RUBY_AXE = ITEMS.register("ruby_axe", () -> new ToolAxe(6.0F, -3.1F, ToolMaterial.RUBY));
    public static final RegistryObject<Item> RUBY_HOE = ITEMS.register("ruby_hoe", () -> new ToolHoe(-2, -1.0f, ToolMaterial.RUBY));
    public static final RegistryObject<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", () -> new ToolPickaxe(1, -3.2f, ToolMaterial.RUBY));
    public static final RegistryObject<Item> RUBY_SHOVEL = ITEMS.register("ruby_shovel", () -> new ToolShovel(1.5f, -3.0f, ToolMaterial.RUBY));
    public static final RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword", () -> new ToolSword(6, -3f, ToolMaterial.RUBY));

    // Armor
    public static final RegistryObject<ArmorBase> RUBY_HELMET = ITEMS.register("ruby_helmet", () -> new ArmorBase(CustomArmorMaterial.RUBY, EquipmentSlot.HEAD));
    public static final RegistryObject<ArmorBase> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate", () -> new ArmorBase(CustomArmorMaterial.RUBY, EquipmentSlot.CHEST));
    public static final RegistryObject<ArmorBase> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () -> new ArmorBase(CustomArmorMaterial.RUBY, EquipmentSlot.LEGS));
    public static final RegistryObject<ArmorBase> RUBY_BOOTS = ITEMS.register("ruby_boots", () -> new ArmorBase(CustomArmorMaterial.RUBY, EquipmentSlot.FEET));

    public static final RegistryObject<ArmorSkinBase> SUNFLOW_HELMET = ITEMS.register("sunflow_helmet", () -> new ArmorSkinBase(SkinType.SUNFLOW, CustomArmorMaterial.RUBY, 1, EquipmentSlot.HEAD));
    public static final RegistryObject<ArmorSkinBase> SUNFLOW_CHESTPLATE = ITEMS.register("sunflow_chestplate", () -> new ArmorSkinBase(SkinType.SUNFLOW, CustomArmorMaterial.RUBY, 1, EquipmentSlot.CHEST));
    public static final RegistryObject<ArmorSkinBase> SUNFLOW_LEGGINGS = ITEMS.register("sunflow_leggings", () -> new ArmorSkinBase(SkinType.SUNFLOW, CustomArmorMaterial.RUBY, 2, EquipmentSlot.LEGS));
    public static final RegistryObject<ArmorSkinBase> SUNFLOW_BOOTS = ITEMS.register("sunflow_boots", () -> new ArmorSkinBase(SkinType.SUNFLOW, CustomArmorMaterial.RUBY, 1, EquipmentSlot.FEET));

    public static final RegistryObject<ArmorSkinBase> BEKE_HELMET = ITEMS.register("beke_helmet", () -> new ArmorSkinBase(SkinType.BEKESCSABA99, CustomArmorMaterial.RUBY, 1, EquipmentSlot.HEAD));
    public static final RegistryObject<ArmorSkinBase> BEKE_CHESTPLATE = ITEMS.register("beke_chestplate", () -> new ArmorSkinBase(SkinType.BEKESCSABA99, CustomArmorMaterial.RUBY, 1, EquipmentSlot.CHEST));
    public static final RegistryObject<ArmorSkinBase> BEKE_LEGGINGS = ITEMS.register("beke_leggings", () -> new ArmorSkinBase(SkinType.BEKESCSABA99, CustomArmorMaterial.RUBY, 2, EquipmentSlot.LEGS));
    public static final RegistryObject<ArmorSkinBase> BEKE_BOOTS = ITEMS.register("beke_boots", () -> new ArmorSkinBase(SkinType.BEKESCSABA99, CustomArmorMaterial.RUBY, 1, EquipmentSlot.FEET));

    public static final RegistryObject<ArmorSkinBase> PHANTOM_HELMET = ITEMS.register("phantom_helmet", () -> new ArmorSkinBase(SkinType.PHANTOM, CustomArmorMaterial.RUBY, 1, EquipmentSlot.HEAD));
    public static final RegistryObject<ArmorSkinBase> PHANTOM_CHESTPLATE = ITEMS.register("phantom_chestplate", () -> new ArmorSkinBase(SkinType.PHANTOM, CustomArmorMaterial.RUBY, 1, EquipmentSlot.CHEST));
    public static final RegistryObject<ArmorSkinBase> PHANTOM_LEGGINGS = ITEMS.register("phantom_leggings", () -> new ArmorSkinBase(SkinType.PHANTOM, CustomArmorMaterial.RUBY, 2, EquipmentSlot.LEGS));
    public static final RegistryObject<ArmorSkinBase> PHANTOM_BOOTS = ITEMS.register("phantom_boots", () -> new ArmorSkinBase(SkinType.PHANTOM, CustomArmorMaterial.RUBY, 1, EquipmentSlot.FEET));

    public static final RegistryObject<ItemNBTSkin>[] SKIN = SkinType.create(ITEMS);

    // Food
    public static final RegistryObject<Item> EVIL_APPLE = ITEMS.register("evil_apple", () -> new Item(ItemUtil.Food(ModTabs.ITEM_TAB, 6, 4, false, false, true,
            () -> new MobEffectInstance(MobEffects.HEAL, 1, 1),
            () -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 4, 0))));

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // MOB EFFECTS

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // SOUNDS
    public static final RegistryObject<SoundEvent> ENTITY_CENTAUR_AMBIENT = SOUND_EVENTS.register("entity.centaur.ambient", () -> new SoundEvent(new ResourceLocation(TutorialMod.MODID, "entity.centaur.ambient")));
    // public static final RegistryObject<SoundEvent> ENTITY_CENTAUR_HURT = SOUND_EVENTS.register("entity.centaur.hurt", () -> new SoundEvent(new ResourceLocation(TutorialMod.MODID, "entity.centaur.hurt")));
    public static final RegistryObject<SoundEvent> ENTITY_CENTAUR_HURT = SOUND_EVENTS.register("entity.centaur.hurt", () -> new SoundEvent(new ResourceLocation(TutorialMod.MODID, "entity.centaur.hurt")));
    public static final RegistryObject<SoundEvent> ENTITY_CENTAUR_DEATH = SOUND_EVENTS.register("entity.centaur.death", () -> new SoundEvent(new ResourceLocation(TutorialMod.MODID, "entity.centaur.death")));

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // POTIONS

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ENCHANTMENTS
    public static final RegistryObject<Enchantment> ENCHANTMENT_MULTIJUMP = ENCHANTMENTS.register("multijump", EnchantmentMultiJump::new);

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ENTITIES
    public static final RegistryObject<GrenadeItem> GRENADE = ITEMS.register("grenade", GrenadeItem::new);
    public static final RegistryObject<EntityType<GrenadeEntity>> GRENADE_ENTITY = ENTITIES.register("grenade", () -> EntityType.Builder.<GrenadeEntity>of(GrenadeEntity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .setShouldReceiveVelocityUpdates(true)
            .build("grenade"));

    public static final RegistryObject<ForgeSpawnEggItem> WEIRDMOB_SPAWN_EGG = ITEMS.register("weirdmob_spawn_egg", () -> new ForgeSpawnEggItem(Registration.WEIRDMOB, 0xff0000, 0xff1111, ITEM_PROPERTIES));
    public static final RegistryObject<EntityType<WeirdMobEntity>> WEIRDMOB = ENTITIES.register("weirdmob", () -> EntityType.Builder.of(WeirdMobEntity::new, MobCategory.CREATURE)
            .sized(1.0F, 1.0F)
            .setShouldReceiveVelocityUpdates(false)
            .build("weirdmob"));

    public static final RegistryObject<ForgeSpawnEggItem> CENTAUR_SPAWN_EGG = ITEMS.register("centaur_spawn_egg", () -> new ForgeSpawnEggItem(Registration.CENTAUR, 0xff0000, 0xeecc11, ITEM_PROPERTIES2));
    public static final RegistryObject<EntityType<CentaurEntity>> CENTAUR = ENTITIES.register("centaur", () -> EntityType.Builder.of(CentaurEntity::new, MobCategory.CREATURE)
            .sized(0.9F, 1.4F)
            .setShouldReceiveVelocityUpdates(false)
            .build("centaur"));

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // BLOCK ENTITES
    public static final SunBlockEntityMenu<CopperChest, CopperChestEntity, CopperChestContainer> COPPER_CHEST = new SunBlockEntityMenu<>(
            "copper_chest", CopperChest::new, () -> ItemUtil.BEWLR(Registration.COPPER_CHEST.block(), ITEM_PROPERTIES),
            CopperChestEntity::new, CopperChestContainer::new);
    public static final SunBlockEntity<FancyBlock, FancyBlockEntity> FANCYBLOCK = new SunBlockEntity<>("fancyblock", FancyBlock::new, ITEM_PROPERTIES2, FancyBlockEntity::new);
    public static final SunBlockEntity<MagicBlock, MagicBlockEntity> MAGICBLOCK = new SunBlockEntity<>("magicblock", MagicBlock::new, ITEM_PROPERTIES2, MagicBlockEntity::new);

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // PARTICLES
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // CONTAINERS
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // PAINTINGS
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // RECIPES
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // STATS

    // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
    // Villages
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // PROFESSIONS
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // POI_TYPES
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // MEMORY_MODULE_TYPES
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // SENSOR_TYPES
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // SCHEDULES
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ACTIVITIES

    // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
    // Worldgen
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // WORLD_CARVERS
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // FEATURES
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // CHUNK_STATUS
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // STRUCTURE_FEATURES
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // BLOCK_STATE_PROVIDER_TYPES
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // FOLIAGE_PLACER_TYPES
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // TREE_DECORATOR_TYPES

    // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
    // Dynamic/Data driven.
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // BIOMES

    // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
    // Custom forge registries
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // DATA_SERIALIZERS
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // LOOT_MODIFIER_SERIALIZERS
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // WORLD_TYPES

    // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
    // UTILS

    public static class SunBlockEntityMenu<B extends Block, E extends BlockEntity, M extends AbstractContainerMenu> extends SunBlockEntity<B, E> {
        private final RegistryObject<MenuType<M>> menuType;

        public SunBlockEntityMenu(String name, Supplier<B> blockGetter, Item.Properties itemProperties,
                BlockEntityType.BlockEntitySupplier<E> blockEntityGetter,
                MenuType.MenuSupplier<M> menuGetter) {
            super(name, blockGetter, itemProperties, blockEntityGetter);
            menuType = CONTAINERS.register(name, () -> new MenuType<>(menuGetter));
        }

        public <I extends Item> SunBlockEntityMenu(String name, Supplier<B> blockGetter, Supplier<I> itemGetter,
                BlockEntityType.BlockEntitySupplier<E> blockEntityGetter,
                MenuType.MenuSupplier<M> menuGetter) {
            super(name, blockGetter, itemGetter, blockEntityGetter);
            menuType = CONTAINERS.register(name, () -> new MenuType<>(menuGetter));
        }

        public MenuType<M> menu() { return menuType.get(); }
    }

    public static class SunBlockEntity<B extends Block, E extends BlockEntity> extends SunSimpleBlock<B> {
        private final RegistryObject<BlockEntityType<E>> blockEntityType;

        public SunBlockEntity(String name, Supplier<B> blockGetter, Item.Properties itemProperties,
                BlockEntityType.BlockEntitySupplier<E> blockEntityGetter) {
            super(name, blockGetter, itemProperties);
            blockEntityType = BLOCK_ENTITIES.register(name, () -> BlockEntityType.Builder.of(blockEntityGetter, block()).build(null));
        }

        public <I extends Item> SunBlockEntity(String name, Supplier<B> blockGetter, Supplier<I> itemGetter,
                BlockEntityType.BlockEntitySupplier<E> blockEntityGetter) {
            super(name, blockGetter, itemGetter);
            blockEntityType = BLOCK_ENTITIES.register(name, () -> BlockEntityType.Builder.of(blockEntityGetter, block()).build(null));
        }

        public BlockEntityType<E> blockEntity() { return blockEntityType.get(); }
    }

    public static class SunSimpleBlock<B extends Block> {
        private final RegistryObject<B> block;
        private final RegistryObject<Item> item;

        public SunSimpleBlock(String name, Supplier<B> blockGetter, Item.Properties itemProperties) {
            block = BLOCKS.register(name, blockGetter);
            item = ITEMS.register(name, () -> new BlockItem(block(), itemProperties));
        }

        public <I extends Item> SunSimpleBlock(String name, Supplier<B> blockGetter, Supplier<I> itemGetter) {
            block = BLOCKS.register(name, blockGetter);
            item = ITEMS.register(name, itemGetter);
        }

        public B block() { return block.get(); }

        public Item item() { return item.get(); }

    }

    // public static class SunBlockMenu<B extends Block, M extends AbstractContainerMenu> extends SunSimpleBlock<B> {
    // public final RegistryObject<MenuType<M>> menuType;

    // public SunBlockMenu(String name, Supplier<B> blockGetter, Item.Properties itemProperties,
    // IContainerFactory<M> menuGetter) {
    // super(name, blockGetter, itemProperties);
    // menuType = CONTAINERS.register(name, () -> IForgeMenuType.create(menuGetter));
    // }

    // public MenuType<M> menu() { return menuType.get(); }
    // }

}
