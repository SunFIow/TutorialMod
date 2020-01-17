package com.sunflow.tutorialmod.setup;

import java.util.ArrayList;
import java.util.List;

import com.sunflow.tutorialmod.block.copper_chest.CopperChestContainer;
import com.sunflow.tutorialmod.block.machine.charger.ChargerContainer;
import com.sunflow.tutorialmod.block.machine.electric_sintering_furnace.ElectricSinteringFurnaceContainer;
import com.sunflow.tutorialmod.block.machine.energy_storage.EnergyStorageContainer;
import com.sunflow.tutorialmod.block.machine.firstblock.FirstBlockContainer;
import com.sunflow.tutorialmod.block.machine.sintering_furnace.SinteringFurnaceContainer;
import com.sunflow.tutorialmod.util.ModTypeUtils;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

public class ModContainerTypes {
	public static final List<ContainerType<? extends Container>> CONTAINER_TYPES = new ArrayList<>();

	public static final ContainerType<FirstBlockContainer> FIRSTBLOCK_CONTAINER = ModTypeUtils.createType(FirstBlockContainer::new, ModBlocks.FIRSTBLOCK);

	public static final ContainerType<EnergyStorageContainer> ENERGY_STORAGE_CONTAINER = ModTypeUtils.createType(EnergyStorageContainer::new, ModBlocks.ENERGY_STORAGE);

	public static final ContainerType<SinteringFurnaceContainer> SINTERING_FURNACE_CONTAINER = ModTypeUtils.createType(SinteringFurnaceContainer::new, ModBlocks.SINTERING_FURNACE);

	public static final ContainerType<ElectricSinteringFurnaceContainer> ELECTRIC_SINTERING_FURNACE_CONTAINER = ModTypeUtils.createType(ElectricSinteringFurnaceContainer::new, ModBlocks.ELECTRIC_SINTERING_FURNACE);

	public static final ContainerType<ChargerContainer> CHARGER_CONTAINER = ModTypeUtils.createType(ChargerContainer::new, ModBlocks.CHARGER);

	public static final ContainerType<CopperChestContainer> COPPER_CHEST_CONTAINER = ModTypeUtils.createType(CopperChestContainer::new, ModBlocks.COPPER_CHEST);
}