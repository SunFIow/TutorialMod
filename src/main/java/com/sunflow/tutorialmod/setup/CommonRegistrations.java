package com.sunflow.tutorialmod.setup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.blocks.container.ChargerContainer;
import com.sunflow.tutorialmod.blocks.container.CopperChestContainer;
import com.sunflow.tutorialmod.blocks.container.ElectricSinteringFurnaceContainer;
import com.sunflow.tutorialmod.blocks.container.EnergyStorageContainer;
import com.sunflow.tutorialmod.blocks.container.FirstBlockContainer;
import com.sunflow.tutorialmod.blocks.container.SinteringFurnaceContainer;
import com.sunflow.tutorialmod.blocks.tile.ChargerTile;
import com.sunflow.tutorialmod.blocks.tile.CopperChestTile;
import com.sunflow.tutorialmod.blocks.tile.ElectricSinteringFurnaceTile;
import com.sunflow.tutorialmod.blocks.tile.EnergyStorageTile;
import com.sunflow.tutorialmod.blocks.tile.FirstBlockTile;
import com.sunflow.tutorialmod.blocks.tile.SinteringFurnaceTile;
import com.sunflow.tutorialmod.entity.CentaurEntity;
import com.sunflow.tutorialmod.entity.GrenadeEntity;
import com.sunflow.tutorialmod.entity.WeirdMobEntity;
import com.sunflow.tutorialmod.init.ModBlocks;
import com.sunflow.tutorialmod.init.ModEnchantments;
import com.sunflow.tutorialmod.init.ModItems;
import com.sunflow.tutorialmod.init.ModSounds;
import com.sunflow.tutorialmod.items.base.MobEggBase;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.Builder;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
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
	public static void onTileEntityRegistry(RegistryEvent.Register<TileEntityType<?>> event) {
		Log.debug("I am going to register the tileEntites now senpai.");

		IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();
		registerTileEntity(registry, FirstBlockTile::new, ModBlocks.FIRSTBLOCK);
		registerTileEntity(registry, EnergyStorageTile::new, ModBlocks.ENERGY_STORAGE);
		registerTileEntity(registry, SinteringFurnaceTile::new, ModBlocks.SINTERING_FURNACE);
		registerTileEntity(registry, ElectricSinteringFurnaceTile::new, ModBlocks.ELECTRIC_SINTERING_FURNACE);
		registerTileEntity(registry, CopperChestTile::new, ModBlocks.COPPER_CHEST);
		registerTileEntity(registry, ChargerTile::new, ModBlocks.CHARGER);
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
		registry.register(createEntityType("weirdmob", 1.0F, 1.0F, false, EntityClassification.CREATURE, WeirdMobEntity::new, ModItems.WEIRDMOB_SPAWN_EGG));
		registry.register(createEntityType("centaur", 0.9F, 2.8F, false, EntityClassification.CREATURE, CentaurEntity::new, ModItems.CENTAUR_SPAWN_EGG));
		registry.register(createEntityType("grenade", 0.5F, 0.5F, true, EntityType.Builder.<GrenadeEntity>create(GrenadeEntity::new, EntityClassification.MISC)));
	}

	@SubscribeEvent
	public static void onContainerRegistry(RegistryEvent.Register<ContainerType<?>> event) {
		Log.debug("I am going to register the containers now senpai.");

		event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
			return new CopperChestContainer(windowId, inv, new Inventory(CopperChestTile.SIZE));
		}).setRegistryName(ModBlocks.COPPER_CHEST.getRegistryName()));

		IForgeRegistry<ContainerType<?>> registry = event.getRegistry();
		registerContainer(registry, FirstBlockContainer.class, ModBlocks.FIRSTBLOCK);
		registerContainer(registry, EnergyStorageContainer.class, ModBlocks.ENERGY_STORAGE);
		registerContainer(registry, SinteringFurnaceContainer.class, ModBlocks.SINTERING_FURNACE);
		registerContainer(registry, ElectricSinteringFurnaceContainer.class, ModBlocks.ELECTRIC_SINTERING_FURNACE);
		registerContainer(registry, ChargerContainer.class, ModBlocks.CHARGER);

	}

	@SubscribeEvent
	public static void onSoundRegistry(RegistryEvent.Register<SoundEvent> event) {
		Log.debug("I am going to register the sounds now senpai.");

		IForgeRegistry<SoundEvent> registry = event.getRegistry();
		registry.registerAll(ModSounds.SOUNDS.toArray(new SoundEvent[0]));
//		registry.register(ModSounds.ENTITY_CENTAUR_AMBIENT);
//		registry.register(ModSounds.ENTITY_CENTAUR_HURT);
//		registry.register(ModSounds.ENTITY_CENTAUR_DEATH);
	}

	public static <T extends TileEntity> void registerTileEntity(IForgeRegistry<TileEntityType<?>> registry, Supplier<? extends T> factory, Block block) {
		TileEntityType.Builder<T> builder = TileEntityType.Builder.create(factory, block);
		TileEntityType<?> type = builder.build(null).setRegistryName(block.getRegistryName());
		registry.register(type);
	}

	private static <T extends Entity> EntityType<?> createEntityType(String name, float w, float h, boolean receiveVelocityUpdates, EntityClassification classification, EntityType.IFactory<T> factory, MobEggBase... eggs) {
		Builder<T> builder = EntityType.Builder.<T>create(factory, classification);
		return createEntityType(name, w, h, receiveVelocityUpdates, builder, eggs);
	}

	private static <T extends Entity> EntityType<?> createEntityType(String name, float w, float h, boolean receiveVelocityUpdates, Builder<T> builder, MobEggBase... eggs) {
		EntityType<?> type = builder
				.size(w, h)
				.setShouldReceiveVelocityUpdates(receiveVelocityUpdates)
				.build(name).setRegistryName(TutorialMod.MODID, name);
		for (MobEggBase egg : eggs) {
			egg.setEntityType(type);
		}
		return type;
	}

	private static <T extends Container> void registerContainer(IForgeRegistry<ContainerType<?>> registry, Class<T> clazz, Block block) {
		try {
			Constructor<T> constructor = clazz.getConstructor(int.class, World.class, BlockPos.class, PlayerInventory.class);
			ContainerType<?> type = IForgeContainerType.create((windowId, inv, data) -> {
				try {
					return constructor.newInstance(windowId, TutorialMod.proxy.getClientWorld(), data.readBlockPos(), inv);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				return null;
			}).setRegistryName(block.getRegistryName());
			registry.register(type);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
}
