package com.sunflow.tutorialmod.setup;

import java.util.ArrayList;
import java.util.List;

import com.sunflow.tutorialmod.block.container.ChargerContainer;
import com.sunflow.tutorialmod.block.container.CopperChestContainer;
import com.sunflow.tutorialmod.block.container.ElectricSinteringFurnaceContainer;
import com.sunflow.tutorialmod.block.container.EnergyStorageContainer;
import com.sunflow.tutorialmod.block.container.FirstBlockContainer;
import com.sunflow.tutorialmod.block.container.SinteringFurnaceContainer;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.network.IContainerFactory;

public class ModContainerTypes {
	public static final List<ContainerType<? extends Container>> CONTAINER_TYPES = new ArrayList<>();

	public static final ContainerType<FirstBlockContainer> FIRSTBLOCK_CONTAINER = createContainerType(FirstBlockContainer::new, ModBlocks.FIRSTBLOCK);

	public static final ContainerType<EnergyStorageContainer> ENERGY_STORAGE_CONTAINER = createContainerType(EnergyStorageContainer::new, ModBlocks.ENERGY_STORAGE);

	public static final ContainerType<SinteringFurnaceContainer> SINTERING_FURNACE_CONTAINER = createContainerType(SinteringFurnaceContainer::new, ModBlocks.SINTERING_FURNACE);

	public static final ContainerType<ElectricSinteringFurnaceContainer> ELECTRIC_SINTERING_FURNACE_CONTAINER = createContainerType(ElectricSinteringFurnaceContainer::new, ModBlocks.ELECTRIC_SINTERING_FURNACE);

	public static final ContainerType<ChargerContainer> CHARGER_CONTAINER = createContainerType(ChargerContainer::new, ModBlocks.CHARGER);

	public static final ContainerType<CopperChestContainer> COPPER_CHEST_CONTAINER = createContainerType(CopperChestContainer::new, ModBlocks.COPPER_CHEST);

	private static <T extends Container> ContainerType<T> createContainerType(IContainerFactory<T> factory, Block block) {
		ContainerType<T> type = IForgeContainerType.create(factory);
		type.setRegistryName(block.getRegistryName());
		CONTAINER_TYPES.add(type);
		return type;
	}
}
