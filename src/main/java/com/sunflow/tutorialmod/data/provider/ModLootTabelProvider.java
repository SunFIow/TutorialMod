package com.sunflow.tutorialmod.data.provider;

import com.sunflow.tutorialmod.blocks.base.TileBlockBase;
import com.sunflow.tutorialmod.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;

public class ModLootTabelProvider extends BaseLootTableProvider {

	public ModLootTabelProvider(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}

	@Override
	protected void addTables() {
		lootTables.put(ModBlocks.FIRSTBLOCK, createStandardTableNBT(ModBlocks.FIRSTBLOCK.getRegistryName().getPath(), ModBlocks.FIRSTBLOCK, "inv", "energy", "cooktime", "currentfuel"));
		lootTables.put(ModBlocks.ENERGY_STORAGE, createStandardTableNBT(ModBlocks.ENERGY_STORAGE.getRegistryName().getPath(), ModBlocks.ENERGY_STORAGE, "energy"));
		lootTables.put(ModBlocks.SINTERING_FURNACE, createStandardTableNBT(ModBlocks.SINTERING_FURNACE.getRegistryName().getPath(), ModBlocks.SINTERING_FURNACE, "inv", "cooktime", "burntime", "burntimetotal"));
		lootTables.put(ModBlocks.ELECTRIC_SINTERING_FURNACE, createStandardTableNBT(ModBlocks.ELECTRIC_SINTERING_FURNACE.getRegistryName().getPath(), ModBlocks.ELECTRIC_SINTERING_FURNACE, "inv", "energy", "cooktime"));

		for (Block block : ModBlocks.BLOCKS) {
			if (!(block instanceof TileBlockBase) && block != ModBlocks.RICE_PLANT) {
				addStandartBlock(block);
			}
		}
	}

	private void addStandartBlock(Block block) {
		lootTables.put(block, createStandardTable(block));
	}
}
