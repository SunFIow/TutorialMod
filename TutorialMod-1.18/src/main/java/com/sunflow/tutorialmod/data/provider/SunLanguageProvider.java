package com.sunflow.tutorialmod.data.provider;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.copper_chest.CopperChest;
import com.sunflow.tutorialmod.block.furniture.fancyblock.FancyBlock;
import com.sunflow.tutorialmod.block.multipart.ComplexMultipart;
import com.sunflow.tutorialmod.config.ConfigScreen;
import com.sunflow.tutorialmod.config.ConfigScreen.SunOption;
import com.sunflow.tutorialmod.config.TutorialModConfig1;
import com.sunflow.tutorialmod.item.armor.ItemNBTSkin;
import com.sunflow.tutorialmod.setup.ModTabs;
import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.util.enums.KeyBindings;

import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.RegistryObject;

public class SunLanguageProvider extends LanguageProvider {

    public SunLanguageProvider(DataGenerator generator, String locale) { super(generator, TutorialMod.MODID, locale); }

    @Override
    protected void addTranslations() {
        // CreativeModeTabs
        add(((TranslatableComponent) ModTabs.ITEM_TAB.getDisplayName()).getKey(), "Tutorial Items");
        add(((TranslatableComponent) ModTabs.ITEM_TAB2.getDisplayName()).getKey(), "Tutorial Test Items");

        // KeyBindings
        add(KeyBindings.CATERGORY, TutorialMod.NAME);
        for (KeyBindings key : KeyBindings.values()) add(key.getName(), key.getTranslation());

        // Config
        add(getSidedTranslationKey(ModConfig.Type.CLIENT), "Client Settings");
        add(getSidedTranslationKey(ModConfig.Type.COMMON), "Common Settings");
        add(getSidedTranslationKey(ModConfig.Type.SERVER), "Server Settings");
        ConfigScreen.Builder configBuilder = TutorialModConfig1.getBuilder();
        configBuilder.getList(ModConfig.Type.CLIENT).forEach(this::addOption);
        configBuilder.getList(ModConfig.Type.COMMON).forEach(this::addOption);
        configBuilder.getList(ModConfig.Type.SERVER).forEach(this::addOption);

        // Messages

        // _____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________   
        // Game objects
        // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // BLOCKS
        add(Registration.RUBY.get(), "Ruby");
        add(Registration.RUBY_BLOCK.block(), "Ruby Block");
        add(Registration.CONFIG_BLOCK.block(), "Config Block");
        add(Registration.MULTIBLOCK.block(), "Multi Block");

        // Ores
        add(Registration.RUBY_ORE.block(), "Ruby Ore");
        add(Registration.SUN_ORE_OVERWORLD.block(), "Sun Ore");
        add(Registration.SUN_ORE_DEEPSLATE.block(), "Sun Ore");
        add(Registration.SUN_ORE_NETHER.block(), "Sun Ore");
        add(Registration.SUN_ORE_END.block(), "Sun Ore");

        // Furniture
        add(Registration.SANTA_HAT.block(), "Santa Hat");

        // Food
        add(Registration.RICE.item(), "Rice");
        add(Registration.RICE.block(), "Rice");
        add(Registration.RICE_BOWL.get(), "Rice Bowl");

        // Machines
        add(Registration.TELEPORTER.block(), "Teleporter");
        add(ComplexMultipart.TOOLTIP.getKey(), "This is a block with a complex model consisting of multiple model which can change with interaction");
        add(Registration.COMPLEX_MULTIPART.block(), "Complex Multipart");

        // Tree
        add(Registration.COPPER_INGOT.get(), "Copper Ingot");
        add(Registration.COPPER_BLOCK.block(), "Copper Block");
        add(Registration.COPPER_ORE_OVERWORLD.block(), "Copper Ore");
        add(Registration.COPPER_ORE_NETHER.block(), "Copper Ore");
        add(Registration.COPPER_ORE_END.block(), "Copper Ore");
        add(Registration.COPPER_ORE_DEEPSLATE.block(), "Copper Ore");
        add(Registration.COPPER_LEAVES.block(), "Copper Leaves");
        add(Registration.COPPER_LOG.block(), "Copper Log");
        add(Registration.COPPER_PLANKS.block(), "Copper Planks");
        add(Registration.COPPER_SAPLING.block(), "Copper Sapling");

        add(Registration.ALUMINIUM_INGOT.get(), "Aluminium Ingot");
        add(Registration.ALUMINIUM_BLOCK.block(), "Aluminium Block");
        add(Registration.ALUMINIUM_ORE_OVERWORLD.block(), "Aluminium Ore");
        add(Registration.ALUMINIUM_ORE_NETHER.block(), "Aluminium Ore");
        add(Registration.ALUMINIUM_ORE_END.block(), "Aluminium Ore");
        add(Registration.ALUMINIUM_ORE_DEEPSLATE.block(), "Aluminium Ore");
        add(Registration.ALUMINIUM_LEAVES.block(), "Aluminium Leaves");
        add(Registration.ALUMINIUM_LOG.block(), "Aluminium Log");
        add(Registration.ALUMINIUM_PLANKS.block(), "Aluminium Planks");
        add(Registration.ALUMINIUM_SAPLING.block(), "Aluminium Sapling");

        // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // FLUIDS
        add(Registration.MOLTEN_COPPER_BUCKET.get(), "Molten Copper Bucket");
        add(Registration.MOLTEN_COPPER_ATTRIBUTES.build(Registration.MOLTEN_COPPER.get()).getTranslationKey(), "Molten Copper Fluid");
        add(Registration.MOLTEN_COPPER_ATTRIBUTES.build(Registration.FLOWING_MOLTEN_COPPER.get()).getTranslationKey(), "Molten Copper Fluid");

        // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // ITEMS
        add(Registration.FIRSTITEM.get(), "First Item");
        add(Registration.TESTITEM.get(), "Test Item");
        add(Registration.ENERGYWAND.get(), "Energy Wand");
        add(Registration.FANCY_SWORD.get(), "Fancy Sword");

        // Wands
        add(Registration.WOOD_WAND.get(), "Wood Wand");
        add(Registration.STONE_WAND.get(), "Stone Wand");
        add(Registration.IRON_WAND.get(), "Iron Wand");
        add(Registration.GOLD_WAND.get(), "Gold Wand");
        add(Registration.DIAMOND_WAND.get(), "Diamond Wand");
        add(Registration.RUBY_WAND.get(), "Ruby Wand");
        add(Registration.OBSIDIAN_WAND.get(), "Obsidian Wand");
        add(Registration.EMERALD_WAND.get(), "Emerald Wand");

        // Tools
        add(Registration.RUBY_AXE.get(), "Ruby Axe");
        add(Registration.RUBY_HOE.get(), "Ruby Hoe");
        add(Registration.RUBY_PICKAXE.get(), "Ruby Pickaxe");
        add(Registration.RUBY_SHOVEL.get(), "Ruby Shovel");
        add(Registration.RUBY_SWORD.get(), "Ruby Sword");

        // Armor
        add(Registration.RUBY_HELMET.get(), "Ruby Helmet");
        add(Registration.RUBY_CHESTPLATE.get(), "Ruby Chestplate");
        add(Registration.RUBY_LEGGINGS.get(), "Ruby Leggings");
        add(Registration.RUBY_BOOTS.get(), "Ruby Boots");

        add(Registration.SUNFLOW_HELMET.get(), "SunFlow Helmet");
        add(Registration.SUNFLOW_CHESTPLATE.get(), "SunFlow Chestplate");
        add(Registration.SUNFLOW_LEGGINGS.get(), "SunFlow Leggings");
        add(Registration.SUNFLOW_BOOTS.get(), "SunFlow Boots");

        add(Registration.BEKE_HELMET.get(), "Bekescaba Helmet");
        add(Registration.BEKE_CHESTPLATE.get(), "Bekescaba Chestplate");
        add(Registration.BEKE_LEGGINGS.get(), "Bekescaba Leggings");
        add(Registration.BEKE_BOOTS.get(), "Bekescaba Boots");

        add(Registration.PHANTOM_HELMET.get(), "Phantom Helmet");
        add(Registration.PHANTOM_CHESTPLATE.get(), "Phantom Chestplate");
        add(Registration.PHANTOM_LEGGINGS.get(), "Phantom Leggings");
        add(Registration.PHANTOM_BOOTS.get(), "Phantom Boots");

        for (RegistryObject<ItemNBTSkin> skin : Registration.SKIN) add(skin.get(), skin.get().getTranslation());

        // Food
        add(Registration.EVIL_APPLE.get(), "Evil Apple");

        // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // MOB EFFECTS

        // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // SOUNDS
        addSound(Registration.ENTITY_CENTAUR_AMBIENT.get(), "Centaur Ambient");
        addSound(Registration.ENTITY_CENTAUR_HURT.get(), "Centaur Hurt");
        addSound(Registration.ENTITY_CENTAUR_DEATH.get(), "Centaur Death");

        // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // POTIONS

        // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // ENCHANTMENTS
        add(Registration.ENCHANTMENT_MULTIJUMP.get(), "MultiJump");

        // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // ENTITIES
        add(Registration.GRENADE.get(), "Grenade");
        add(Registration.GRENADE_ENTITY.get(), "Grenade");
        add(Registration.WEIRDMOB_SPAWN_EGG.get(), "Weird Mob Spawn Egg");
        add(Registration.WEIRDMOB.get(), "Weird Mob");
        add(Registration.CENTAUR_SPAWN_EGG.get(), "Centaur Spawn Egg");
        add(Registration.CENTAUR.get(), "Centaur");

        // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // BLOCK ENTITES        
        add(CopperChest.CONTAINER_TITLE.getString(), "Copper Chest");
        add(Registration.COPPER_CHEST.block(), "Copper Chest");
        add(FancyBlock.TOOLTIP.getKey(), "This fancy block can mimic other blocks by right clicking the other block on it");
        add(Registration.FANCYBLOCK.block(), "Fancy Block");
        add(Registration.MAGICBLOCK.block(), "Magic Block");

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
    }

    private void addSound(SoundEvent sound, String translation) { add("subtitle." + TutorialMod.MODID + "." + sound.getLocation().getPath(), translation); }

    private void addOption(SunOption option) { add(getTranslationKeyOption(option.getType(), option.getTranslationKey()), option.getTranslation()); }

    public static String getTranslationKeyOption(ModConfig.Type screenType, String translationKey) { return getSidedTranslationKey(screenType) + "." + translationKey; }

    public static String getSidedTranslationKey(ModConfig.Type screenType) { return "option." + TutorialMod.MODID + "." + screenType.extension(); }

    public static String getTranslationKeyMessage(String translationKey) { return "message." + TutorialMod.MODID + "." + translationKey; }

    @Override
    public String getName() { return TutorialMod.MODID + " " + super.getName(); }
}
