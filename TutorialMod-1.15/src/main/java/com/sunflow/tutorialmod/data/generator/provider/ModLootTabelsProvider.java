package com.sunflow.tutorialmod.data.generator.provider;

import com.sunflow.tutorialmod.block.base.ContainerBlockBase;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.data.DataGenerator;

public class ModLootTabelsProvider extends BaseLootTableProvider {

	public ModLootTabelsProvider(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}

	@Override
	protected void addTables() {
//		lootTables.put(Registration.GLOWSTONE_GENERATOR_BLOCK.get(), createStandardTableNBT(Registration.GLOWSTONE_GENERATOR_BLOCK.get().getRegistryName().getPath(), Registration.GLOWSTONE_GENERATOR_BLOCK.get(), "inv", "energy", "cooktime", "currentfuel"));
//		lootTables.put(Registration.ENERGY_STORAGE.get(), createStandardTableNBT(Registration.ENERGY_STORAGE.get().getRegistryName().getPath(), Registration.ENERGY_STORAGE.get(), "energy"));
//		lootTables.put(Registration.SINTERING_FURNACE.get(), createStandardTableNBT(Registration.SINTERING_FURNACE.get().getRegistryName().getPath(), Registration.SINTERING_FURNACE.get(), "inv", "cooktime", "burntime", "burntimetotal"));
//		lootTables.put(Registration.ELECTRIC_SINTERING_FURNACE.get(), createStandardTableNBT(Registration.ELECTRIC_SINTERING_FURNACE.get().getRegistryName().getPath(), Registration.ELECTRIC_SINTERING_FURNACE.get(), "inv", "energy", "cooktime"));

		addNBTBlock(Registration.GLOWSTONE_GENERATOR_BLOCK.get(), "inv", "energy", "cooktime", "currentfuel");
		addNBTBlock(Registration.ENERGY_STORAGE_BLOCK.get(), "energy");
		addNBTBlock(Registration.SINTERING_FURNACE_BLOCK.get(), "inv", "cooktime", "burntime", "burntimetotal");
		addNBTBlock(Registration.ELECTRIC_SINTERING_FURNACE_BLOCK.get(), "inv", "energy", "cooktime");

		Registration.BLOCKS.getEntries().forEach((block) -> {
			if (!(block.get() instanceof ContainerBlockBase) && block.get() != Registration.RICE_PLANT.get())
				addStandartBlock(block.get());
		});
	}
}
