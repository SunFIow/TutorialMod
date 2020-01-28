package com.sunflow.tutorialmod.data.provider;

import com.sunflow.tutorialmod.block.base.TileBlockBase;
import com.sunflow.tutorialmod.setup.registration.Registration;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;

public class ModLootTabelProvider extends BaseLootTableProvider {

	public ModLootTabelProvider(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}

	@Override
	protected void addTables() {
		lootTables.put(Registration.FIRSTBLOCK.get(), createStandardTableNBT(Registration.FIRSTBLOCK.get().getRegistryName().getPath(), Registration.FIRSTBLOCK.get(), "inv", "energy", "cooktime", "currentfuel"));
		lootTables.put(Registration.ENERGY_STORAGE.get(), createStandardTableNBT(Registration.ENERGY_STORAGE.get().getRegistryName().getPath(), Registration.ENERGY_STORAGE.get(), "energy"));
		lootTables.put(Registration.SINTERING_FURNACE.get(), createStandardTableNBT(Registration.SINTERING_FURNACE.get().getRegistryName().getPath(), Registration.SINTERING_FURNACE.get(), "inv", "cooktime", "burntime", "burntimetotal"));
		lootTables.put(Registration.ELECTRIC_SINTERING_FURNACE.get(), createStandardTableNBT(Registration.ELECTRIC_SINTERING_FURNACE.get().getRegistryName().getPath(), Registration.ELECTRIC_SINTERING_FURNACE.get(), "inv", "energy", "cooktime"));

		Registration.BLOCKS.getEntries().forEach((block) -> {
			if (!(block.get() instanceof TileBlockBase) && block.get() != Registration.RICE_PLANT.get())
				addStandartBlock(block.get());
		});
	}

	private void addStandartBlock(Block block) {
		lootTables.put(block, createStandardTable(block));
	}
}
