package com.sunflow.tutorialmod.setup;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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

    public static void init() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modBus);
        ITEMS.register(modBus);
    }

    public static final BlockBehaviour.Properties ORE_PROPERTIES = BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0f);
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModSetup.ITEM_GROUP);

    public static final SunBlock<Block> SUN_ORE_OVERWORLD = new SunBlock<>("sun_ore_overworld", () -> new Block(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunBlock<Block> SUN_ORE_NETHER = new SunBlock<>("sun_ore_nether", () -> new Block(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunBlock<Block> SUN_ORE_END = new SunBlock<>("sun_ore_end", () -> new Block(ORE_PROPERTIES), ITEM_PROPERTIES);
    public static final SunBlock<Block> SUN_ORE_DEEPSLATE = new SunBlock<>("sun_ore_deepslate", () -> new Block(ORE_PROPERTIES), ITEM_PROPERTIES);

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
