package com.sunflow.tutorialmod.data.provider;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.DynamicLootEntry;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;
import net.minecraft.world.storage.loot.functions.CopyName;
import net.minecraft.world.storage.loot.functions.CopyNbt;
import net.minecraft.world.storage.loot.functions.CopyNbt.Builder;
import net.minecraft.world.storage.loot.functions.SetContents;

public abstract class BaseLootTableProvider extends LootTableProvider {

	private static final Logger LOGGER = LogManager.getLogger();
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

	protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
	private final DataGenerator generator;

	public BaseLootTableProvider(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
		this.generator = dataGeneratorIn;
	}

	protected abstract void addTables();

	protected void addNBTBlock(Block block, String... nbtStrings) {
		lootTables.put(block, createStandardTableNBT(block.getRegistryName().getPath(), block, nbtStrings));
	}

	protected void addStandartBlock(Block block) {
		lootTables.put(block, createStandardTable(block.getRegistryName().getPath(), block));
	}

	protected LootTable.Builder createStandardTableNBT(String name, Block block, String... nbtStrings) {
		Builder nbts = CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY);
		for (String nbt : nbtStrings) {
			nbts.addOperation(nbt, "BlockEntityTag." + nbt, CopyNbt.Action.REPLACE);
		}
		LootPool.Builder builder = LootPool.builder()
				.name(name)
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(block)
						.acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
						.acceptFunction(nbts)
						.acceptFunction(SetContents.builder()
								.addLootEntry(DynamicLootEntry.func_216162_a(new ResourceLocation("minecraft", "contents")))))
				.acceptCondition(SurvivesExplosion.builder());
		return LootTable.builder().addLootPool(builder);
	}

	protected LootTable.Builder createStandardTable(String name, Block block) {
		LootPool.Builder builder = LootPool.builder()
				.name(name)
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(block))
				.acceptCondition(SurvivesExplosion.builder());
		return LootTable.builder().addLootPool(builder);
	}

	@Override
	public void act(DirectoryCache cache) {
		addTables();

		Map<ResourceLocation, LootTable> tables = new HashMap<>();
		for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
			tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
		}
		writeTables(cache, tables);
	}

	private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
		Path outputFolder = this.generator.getOutputFolder();
		tables.forEach((key, lootTable) -> {
			Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
			try {
				IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
			} catch (IOException e) {
				LOGGER.error("Couldn't write loot table {}", path, e);
			}
		});
	}

	@Override
	public String getName() {
		return TutorialMod.NAME + " " + super.getName();
	}
}