package com.sunflow.tutorialmod.setup;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestBlock;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestContainer;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestEntity;
import com.sunflow.tutorialmod.enchantment.EnchantmentMultiJump;
import com.sunflow.tutorialmod.rendering.ModBEWLR;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.client.model.SeparatePerspectiveModel.BakedModel;
import net.minecraftforge.common.extensions.IForgeMenuType;
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

    public static final SunBlock<Block> SUN_ORE_OVERWORLD = new SunBlock<>("sun_ore_overworld", () -> new Block(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunBlock<Block> SUN_ORE_NETHER = new SunBlock<>("sun_ore_nether", () -> new Block(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunBlock<Block> SUN_ORE_END = new SunBlock<>("sun_ore_end", () -> new Block(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunBlock<Block> SUN_ORE_DEEPSLATE = new SunBlock<>("sun_ore_deepslate", () -> new Block(ORE_PROPERTIES), ITEM_PROPERTIES);

    public static final RegistryObject<Enchantment> ENCHANTMENT_MULTIJUMP = ENCHANTMENTS.register("multijump", EnchantmentMultiJump::new);

    public static final RegistryObject<CopperChestBlock> COPPER_CHEST = BLOCKS.register("copper_chest", CopperChestBlock::new);
    public static final RegistryObject<BlockItem> COPPER_CHEST_ITEM = ITEMS.register("copper_chest", () -> new BlockItem(COPPER_CHEST.get(), ITEM_PROPERTIES) {

        @Override
        public void initializeClient(Consumer<IItemRenderProperties> consumer) {
            consumer.accept(new IItemRenderProperties() {
                @Override
                public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                    return ModBEWLR.instance;
                }
            });
        }
    });
    public static final RegistryObject<BlockEntityType<CopperChestEntity>> COPPER_CHEST_TILE = BLOCK_ENTITIES.register("copper_chest", () -> BlockEntityType.Builder.of(CopperChestEntity::new, COPPER_CHEST.get()).build(null));
    public static final RegistryObject<MenuType<CopperChestContainer>> COPPER_CHEST_CONTAINER = CONTAINERS.register("copper_chest", () -> IForgeMenuType.create(CopperChestContainer::new));

    public static class SunBlock<B extends Block> {
        public final RegistryObject<B> block;
        public final RegistryObject<BlockItem> item;

        public SunBlock(String name, Supplier<B> sup, Item.Properties itemProperties) {
            block = BLOCKS.register(name, sup);
            item = ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), itemProperties));
        }

        public B block() { return block.get(); }

        public BlockItem item() { return item.get(); }
    }
}
