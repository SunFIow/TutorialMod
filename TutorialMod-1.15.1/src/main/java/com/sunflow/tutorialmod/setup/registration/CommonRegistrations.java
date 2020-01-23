package com.sunflow.tutorialmod.setup.registration;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.ModBiomes;
import com.sunflow.tutorialmod.setup.ModBlocks;
import com.sunflow.tutorialmod.setup.ModContainerTypes;
import com.sunflow.tutorialmod.setup.ModDimensions;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = TutorialMod.MODID, value = { Dist.CLIENT, Dist.DEDICATED_SERVER }, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonRegistrations {

	@SubscribeEvent
	public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
		Log.debug("I am going to register the blocks now senpai.");

		IForgeRegistry<Block> registry = event.getRegistry();
		ModBlocks.BLOCKS.forEach((block) -> registry.register(block));
	}

	@SubscribeEvent
	public static void onItemRegistry(RegistryEvent.Register<Item> event) {
		Log.debug("I am going to register the items now senpai.");

		IForgeRegistry<Item> registry = event.getRegistry();
		ModItems.ITEMS.forEach((item) -> registry.register(item));

//		event.getRegistry().register(ModItems.SKIN);
	}

	@SubscribeEvent
	public static void onDimensionRegistry(RegistryEvent.Register<ModDimension> event) {
		Log.debug("I am going to register the dimensions now senpai.");

		IForgeRegistry<ModDimension> registry = event.getRegistry();
		ModDimensions.DIMENSIONS.forEach((dimension) -> registry.register(dimension));
	}

	@SubscribeEvent
	public static void onBiomeRegistry(RegistryEvent.Register<Biome> event) {
		Log.debug("I am going to register the biomes now senpai.");

		IForgeRegistry<Biome> registry = event.getRegistry();
		ModBiomes.BIOMES.forEach((biome) -> registry.register(biome));
	}

	@SubscribeEvent
	public static void onTileEntityRegistry(RegistryEvent.Register<TileEntityType<?>> event) {
		Log.debug("I am going to register the tileentites now senpai.");

		IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();
		ModTileEntitiyTypes.TILE_TYPES.forEach((tileEntityType) -> registry.register(tileEntityType));
	}

	@SubscribeEvent
	public static void onEnchantmentRegistry(RegistryEvent.Register<Enchantment> event) {
		Log.debug("I am going to register the enchantments now senpai.");

		IForgeRegistry<Enchantment> registry = event.getRegistry();
		ModEnchantments.ENCHANTMENTS.forEach((enchantment) -> registry.register(enchantment));
	}

	@SubscribeEvent
	public static void onEntityRegistry(RegistryEvent.Register<EntityType<?>> event) {
		Log.debug("I am going to register the entities now senpai.");

		IForgeRegistry<EntityType<?>> registry = event.getRegistry();
		ModEntityTypes.ENTITY_TYPES.forEach((entityType) -> registry.register(entityType));
	}

	@SubscribeEvent
	public static void onFluidRegistry(RegistryEvent.Register<Fluid> event) {
		Log.debug("I am going to register the fluids now senpai.");

		IForgeRegistry<Fluid> registry = event.getRegistry();
		ModFluids.FLUIDS.forEach((fluid) -> registry.register(fluid));
	}

	@SubscribeEvent
	public static void onContainerRegistry(RegistryEvent.Register<ContainerType<?>> event) {
		Log.debug("I am going to register the containers now senpai.");

		IForgeRegistry<ContainerType<?>> registry = event.getRegistry();
		ModContainerTypes.CONTAINER_TYPES.forEach((containerType) -> registry.register(containerType));
	}

	@SubscribeEvent
	public static void onSoundRegistry(RegistryEvent.Register<SoundEvent> event) {
		Log.debug("I am going to register the sounds now senpai.");

		IForgeRegistry<SoundEvent> registry = event.getRegistry();
		ModSounds.SOUNDS.forEach((sound) -> registry.register(sound));
	}
}
