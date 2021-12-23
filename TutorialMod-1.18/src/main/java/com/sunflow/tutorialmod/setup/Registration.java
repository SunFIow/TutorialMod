package com.sunflow.tutorialmod.setup;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.RubyBlock;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestBlock;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestContainer;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestEntity;
import com.sunflow.tutorialmod.enchantment.EnchantmentMultiJump;
import com.sunflow.tutorialmod.item.armor.ArmorBase;
import com.sunflow.tutorialmod.item.armor.ArmorSkinBase;
import com.sunflow.tutorialmod.item.armor.CustomArmorMaterial;
import com.sunflow.tutorialmod.item.armor.ItemNBTSkin;
import com.sunflow.tutorialmod.item.armor.SkinUtil.SkinType;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {
    private Registration() {}

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MODID);
    private static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, TutorialMod.MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, TutorialMod.MODID);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, TutorialMod.MODID);

    public static void init() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modBus);
        ITEMS.register(modBus);
        ENCHANTMENTS.register(modBus);
        BLOCK_ENTITIES.register(modBus);
        CONTAINERS.register(modBus);
    }

    public static final BlockBehaviour.Properties ORE_PROPERTIES = BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0f);
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModGroups.ITEM_GROUP);

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // BLOCKS

    public static final SunSimpleBlock<RubyBlock> RUBY_BLOCK = new SunSimpleBlock<>("ruby_block", RubyBlock::new, ITEM_PROPERTIES);

    // Ores
    public static final SunSimpleBlock<Block> SUN_ORE_OVERWORLD = new SunSimpleBlock<>("sun_ore_overworld", () -> new Block(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunSimpleBlock<Block> SUN_ORE_NETHER = new SunSimpleBlock<>("sun_ore_nether", () -> new Block(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunSimpleBlock<Block> SUN_ORE_END = new SunSimpleBlock<>("sun_ore_end", () -> new Block(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunSimpleBlock<Block> SUN_ORE_DEEPSLATE = new SunSimpleBlock<>("sun_ore_deepslate", () -> new Block(ORE_PROPERTIES), ITEM_PROPERTIES);

    // Furniture
    public static final SunBlockEntityMenu<CopperChestBlock, CopperChestEntity, CopperChestContainer> COPPER_CHEST = new SunBlockEntityMenu<>(
            "copper_chest", CopperChestBlock::new, ITEM_PROPERTIES,
            CopperChestEntity::new, CopperChestContainer::new);
    // Food

    // Machines

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ITEMS

    // Items

    // Wands

    // Tools

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

    public static final RegistryObject<ItemNBTSkin>[] SKIN = ItemNBTSkin.create(ITEMS);

    // Food

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // DIMENSIONS

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // BIOMES

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // TILEENTITIES
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ENCHANTMENTS

    public static final RegistryObject<Enchantment> ENCHANTMENT_MULTIJUMP = ENCHANTMENTS.register("multijump", EnchantmentMultiJump::new);

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ENTITIES
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // FLUIDS

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // CONTAINERS
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // SOUNDS

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // UTILS

    public static class SunBlockEntityMenu<B extends Block, E extends BlockEntity, M extends AbstractContainerMenu> extends SunBlockEntity<B, E> {
        private final RegistryObject<MenuType<M>> menuType;

        public SunBlockEntityMenu(String name, Supplier<B> blockGetter, Item.Properties itemProperties,
                BlockEntityType.BlockEntitySupplier<E> blockEntityGetter,
                MenuType.MenuSupplier<M> menuGetter) {
            super(name, blockGetter, itemProperties, blockEntityGetter);
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

        public BlockEntityType<E> blockEntity() { return blockEntityType.get(); }
    }

    public static class SunSimpleBlock<B extends Block> {
        private final RegistryObject<B> block;
        private final RegistryObject<BlockItem> item;

        public SunSimpleBlock(String name, Supplier<B> blockGetter, Item.Properties itemProperties) {
            block = BLOCKS.register(name, blockGetter);
            item = ITEMS.register(name, () -> new BlockItem(block(), itemProperties));
        }

        public B block() { return block.get(); }

        public BlockItem item() { return item.get(); }
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
