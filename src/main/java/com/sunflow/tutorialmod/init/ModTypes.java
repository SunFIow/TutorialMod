package com.sunflow.tutorialmod.init;

import com.sunflow.tutorialmod.blocks.container.ChargerContainer;
import com.sunflow.tutorialmod.blocks.container.CopperChestContainer;
import com.sunflow.tutorialmod.blocks.container.EnergyStorageContainer;
import com.sunflow.tutorialmod.blocks.container.FirstBlockContainer;
import com.sunflow.tutorialmod.blocks.container.SinteringFurnaceContainer;
import com.sunflow.tutorialmod.blocks.tile.ChargerTile;
import com.sunflow.tutorialmod.blocks.tile.CopperChestTile;
import com.sunflow.tutorialmod.blocks.tile.EnergyStorageTile;
import com.sunflow.tutorialmod.blocks.tile.FirstBlockTile;
import com.sunflow.tutorialmod.blocks.tile.SinteringFurnaceTile;
import com.sunflow.tutorialmod.entity.GrenadeEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("tutorialmod")
public class ModTypes {

	@ObjectHolder("firstblock")
	public static final TileEntityType<FirstBlockTile> FIRSTBLOCK_TILE = null;

	@ObjectHolder("firstblock")
	public static final ContainerType<FirstBlockContainer> FIRSTBLOCK_CONTAINER = null;

	@ObjectHolder("energy_storage")
	public static final TileEntityType<EnergyStorageTile> ENERGY_STORAGE_TILE = null;

	@ObjectHolder("energy_storage")
	public static final ContainerType<EnergyStorageContainer> ENERGY_STORAGE_CONTAINER = null;

	@ObjectHolder("sintering_furnace")
	public static final TileEntityType<SinteringFurnaceTile> SINTERING_FURNACE_TILE = null;

	@ObjectHolder("sintering_furnace")
	public static final ContainerType<SinteringFurnaceContainer> SINTERING_FURNACE_CONTAINER = null;

	@ObjectHolder("electric_sintering_furnace")
	public static final TileEntityType<SinteringFurnaceTile> ELECTRIC_SINTERING_FURNACE_TILE = null;

	@ObjectHolder("electric_sintering_furnace")
	public static final ContainerType<SinteringFurnaceContainer> ELECTRIC_SINTERING_FURNACE_CONTAINER = null;

	@ObjectHolder("charger")
	public static final TileEntityType<ChargerTile> CHARGER_TILE = null;

	@ObjectHolder("charger")
	public static final ContainerType<ChargerContainer> CHARGER_CONTAINER = null;

	@ObjectHolder("copper_chest")
	public static final TileEntityType<CopperChestTile> COPPER_CHEST_TILE = null;

	@ObjectHolder("copper_chest")
	public static final ContainerType<CopperChestContainer> COPPER_CHEST_CONTAINER = null;

	@ObjectHolder("grenade")
	public static final EntityType<GrenadeEntity> GRENADE_ENTITY = null;
}
