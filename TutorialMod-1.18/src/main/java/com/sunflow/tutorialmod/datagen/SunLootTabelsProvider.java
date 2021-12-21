package com.sunflow.tutorialmod.datagen;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetContainerContents;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class SunLootTabelsProvider extends LootTableProvider {

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	private final DataGenerator generator;

	public SunLootTabelsProvider(DataGenerator generator) { super(generator); this.generator = generator; }

	@Override
	public void run(HashCache cache) {
		// Map<ResourceLocation, LootTable> tables = new HashMap<>();
		// simpleBlock(tables, Registration.GENERATOR_BLOCK.get());
		// simpleBlock(tables, Registration.POWERUSER_BLOCK.get());

		// writeTables(cache, tables);
	}

	// private void simpleBlock(Map<ResourceLocation, LootTable> tables, Block block) {
	// tables.put(block.getLootTable(), createStandartTable(block).setParamSet(LootContextParamSets.BLOCK).build());
	// }

	// private LootTable.Builder createStandartTable(Block block) {
	// String name = block.getRegistryName().getPath();

	// LootPool.Builder builder = LootPool.lootPool()
	// .name(name)
	// .setRolls(ConstantValue.exactly(1))
	// .add(LootItem.lootTableItem(block)
	// .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
	// .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
	// .copy("inv", "BlockEntityTag.inv", CopyNbtFunction.MergeStrategy.REPLACE)
	// .copy("energy", "BlockEntityTag.energy", CopyNbtFunction.MergeStrategy.REPLACE))
	// .apply(SetContainerContents.setContents()
	// .withEntry(DynamicLoot.dynamicEntry(new ResourceLocation("minecraft", "contents")))));
	// return LootTable.lootTable().withPool(builder);
	// }

	// private void writeTables(HashCache cache, Map<ResourceLocation, LootTable> tables) {
	// Path outputFolder = this.generator.getOutputFolder();
	// tables.forEach((key, lootTable) -> {
	// Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
	// try {
	// DataProvider.save(GSON, cache, LootTables.serialize(lootTable), path);
	// } catch (IOException e) {
	// TutorialMod.LOGGER.error("Couldn't write loot table {}", path, e);
	// }
	// });
	// }

	@Override
	public String getName() { return TutorialMod.MODID + " " + super.getName(); }
}
