package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.blocks.generator.GeneratorBE;
import com.sunflow.tutorialmod.blocks.generator.GeneratorBlock;
import com.sunflow.tutorialmod.blocks.generator.GeneratorContainer;
import com.sunflow.tutorialmod.blocks.poweruser.PowerUserBE;
import com.sunflow.tutorialmod.blocks.poweruser.PowerUserBlock;
import com.sunflow.tutorialmod.items.TutorialItem;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {

	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MODID);
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MODID);
	private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, TutorialMod.MODID);
	private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, TutorialMod.MODID);

	public static void init(IEventBus bus) {
		ITEMS.register(bus);
		BLOCKS.register(bus);
		BLOCK_ENTITIES.register(bus);
		CONTAINERS.register(bus);
	}

	public static final RegistryObject<TutorialItem> TUTORIAL_ITEM = ITEMS.register("tutorial_item", TutorialItem::new);

	public static final RegistryObject<GeneratorBlock> GENERATOR_BLOCK = BLOCKS.register("generator", GeneratorBlock::new);
	public static final RegistryObject<BlockItem> GENERATOR_ITEM = ITEMS.register("generator", () -> new BlockItem(GENERATOR_BLOCK.get(), new Item.Properties().tab(TutorialMod.TAB_TUTORIALMOD)));
	public static final RegistryObject<BlockEntityType<GeneratorBE>> GENERATOR_BE = BLOCK_ENTITIES.register("generator", () -> BlockEntityType.Builder.of(GeneratorBE::new, GENERATOR_BLOCK.get()).build(null));

	public static final RegistryObject<MenuType<GeneratorContainer>> GENERATOR_CONTAINER = CONTAINERS.register("generator", () -> IForgeContainerType.create((windowId, inv, data) -> {
		BlockPos pos = data.readBlockPos();
		Level world = inv.player.getCommandSenderWorld();
		return new GeneratorContainer(windowId, world, pos, inv, inv.player);
	}));

	public static final RegistryObject<PowerUserBlock> POWERUSER_BLOCK = BLOCKS.register("poweruser", PowerUserBlock::new);
	public static final RegistryObject<BlockItem> POWERUSER_ITEM = ITEMS.register("poweruser", () -> new BlockItem(POWERUSER_BLOCK.get(), new Item.Properties().tab(TutorialMod.TAB_TUTORIALMOD)));
	public static final RegistryObject<BlockEntityType<PowerUserBE>> POWERUSER_BE = BLOCK_ENTITIES.register("poweruser", () -> BlockEntityType.Builder.of(PowerUserBE::new, POWERUSER_BLOCK.get()).build(null));

}
