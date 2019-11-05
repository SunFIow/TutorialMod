package com.sunflow.tutorialmod.setup.registration;

import com.sunflow.tutorialmod.setup.ModBiomes;
import com.sunflow.tutorialmod.setup.ModBlocks;
import com.sunflow.tutorialmod.setup.ModContainerTypes;
import com.sunflow.tutorialmod.setup.ModEnchantments;
import com.sunflow.tutorialmod.setup.ModEntityTypes;
import com.sunflow.tutorialmod.setup.ModFluids;
import com.sunflow.tutorialmod.setup.ModItems;
import com.sunflow.tutorialmod.setup.ModSounds;
import com.sunflow.tutorialmod.setup.ModTileEntitiyTypes;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonRegistrations {

	@SubscribeEvent
	public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
		Log.debug("I am going to register the blocks now senpai.");

		IForgeRegistry<Block> registry = event.getRegistry();
		registry.registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	}

	@SubscribeEvent
	public static void onItemRegistry(RegistryEvent.Register<Item> event) {
		Log.debug("I am going to register the items now senpai.");

		IForgeRegistry<Item> registry = event.getRegistry();
		registry.registerAll(ModItems.ITEMS.toArray(new Item[0]));

//		event.getRegistry().register(ModItems.SKIN);
	}

	@SubscribeEvent
	public static void onBiomeRegistry(RegistryEvent.Register<Biome> event) {
		Log.debug("I am going to register the biomes now senpai.");

		IForgeRegistry<Biome> registry = event.getRegistry();
		registry.registerAll(ModBiomes.BIOMES.toArray(new Biome[0]));
	}

	@SubscribeEvent
	public static void onTileEntityRegistry(RegistryEvent.Register<TileEntityType<?>> event) {
		Log.debug("I am going to register the tileentites now senpai.");

		IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();
		registry.registerAll(ModTileEntitiyTypes.TILE_TYPES.toArray(new TileEntityType[0]));
	}

	@SubscribeEvent
	public static void onEnchantmentRegistry(RegistryEvent.Register<Enchantment> event) {
		Log.debug("I am going to register the enchantments now senpai.");

		IForgeRegistry<Enchantment> registry = event.getRegistry();
		registry.registerAll(ModEnchantments.ENCHANTMENTS.toArray(new Enchantment[0]));
	}

	@SubscribeEvent
	public static void onEntityRegistry(RegistryEvent.Register<EntityType<?>> event) {
		Log.debug("I am going to register the entities now senpai.");

		IForgeRegistry<EntityType<?>> registry = event.getRegistry();
		registry.registerAll(ModEntityTypes.ENTITY_TYPES.toArray(new EntityType[0]));
	}

	@SubscribeEvent
	public static void onFluidRegistry(RegistryEvent.Register<Fluid> event) {
		Log.debug("I am going to register the fluids now senpai.");

		IForgeRegistry<Fluid> registry = event.getRegistry();
		registry.registerAll(ModFluids.FLUIDS.toArray(new Fluid[0]));
	}

	@SubscribeEvent
	public static void onContainerRegistry(RegistryEvent.Register<ContainerType<?>> event) {
		Log.debug("I am going to register the containers now senpai.");

		IForgeRegistry<ContainerType<?>> registry = event.getRegistry();
		registry.registerAll(ModContainerTypes.CONTAINER_TYPES.toArray(new ContainerType[0]));

	}

	@SubscribeEvent
	public static void onSoundRegistry(RegistryEvent.Register<SoundEvent> event) {
		Log.debug("I am going to register the sounds now senpai.");

		IForgeRegistry<SoundEvent> registry = event.getRegistry();
		registry.registerAll(ModSounds.SOUNDS.toArray(new SoundEvent[0]));
	}
}
