package com.sunflow.tutorialmod.init;

import java.util.ArrayList;
import java.util.List;

import com.sunflow.tutorialmod.blocks.ChargerBlock;
import com.sunflow.tutorialmod.blocks.CopperChestBlock;
import com.sunflow.tutorialmod.blocks.ElectricSinteringFurnaceBlock;
import com.sunflow.tutorialmod.blocks.EnergyStorageBlock;
import com.sunflow.tutorialmod.blocks.FirstBlock;
import com.sunflow.tutorialmod.blocks.SinteringFurnaceBlock;
import com.sunflow.tutorialmod.blocks.TeleporterBlock;
import com.sunflow.tutorialmod.blocks.base.BakedBlockBase;
import com.sunflow.tutorialmod.blocks.base.BlockBase;
import com.sunflow.tutorialmod.blocks.base.TileBlockBase;
import com.sunflow.tutorialmod.blocks.food.FoodPlantBlock;
import com.sunflow.tutorialmod.blocks.furniture.FancyBlock;
import com.sunflow.tutorialmod.blocks.furniture.SantaHatBlock;
import com.sunflow.tutorialmod.blocks.ore.CustomOreBlock;
import com.sunflow.tutorialmod.blocks.ore.RubyBlock;
import com.sunflow.tutorialmod.blocks.ore.RubyOre;
import com.sunflow.tutorialmod.blocks.tree.CustomLeavesBlock;
import com.sunflow.tutorialmod.blocks.tree.CustomLogBlock;
import com.sunflow.tutorialmod.blocks.tree.CustomSaplingBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class ModBlocks {
	public static final List<Block> BLOCKS = new ArrayList<>();
	public static final List<BakedBlockBase> BAKEDMODELBLOCKS = new ArrayList<>();

	public static final Block RUBY_BLOCK = new RubyBlock();
	public static final Block COPPER_BLOCK = new BlockBase("copper_block", Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(5.0f));
	public static final Block ALUMINIUM_BLOCK = new BlockBase("aluminium_block", Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(5.0f));

	public static final Block RUBY_ORE = new RubyOre();

	public static final Block COPPER_ORE_OVERWORLD = new CustomOreBlock("copper_ore", 3f, 2, 0);
	public static final Block COPPER_ORE_END = new CustomOreBlock("copper_ore_end", 3f, 2, 0);
	public static final Block COPPER_ORE_NETHER = new CustomOreBlock("copper_ore_nether", 3f, 2, 0);

	public static final Block ALUMINIUM_ORE_OVERWORLD = new CustomOreBlock("aluminium_ore", 3f, 2, 0);
	public static final Block ALUMINIUM_ORE_END = new CustomOreBlock("aluminium_ore_end", 3f, 2, 0);
	public static final Block ALUMINIUM_ORE_NETHER = new CustomOreBlock("aluminium_ore_nether", 3f, 2, 0);

// Furniture
	public static final Block SANTA_HAT = new SantaHatBlock();
	public static final Block FANCY_BLOCK = new FancyBlock();

// Food
	public static final Block RICE_PLANT = new FoodPlantBlock("rice_plant", ModItems.RICE, ModItems.RICE, 3f, 2f);

// Tree	
	public static final Block COPPER_LEAVES = new CustomLeavesBlock("copper_leaves");
	public static final Block COPPER_LOG = new CustomLogBlock("copper_log", MaterialColor.ORANGE_TERRACOTTA);
	public static final Block COPPER_PLANKS = new BlockBase("copper_planks", Material.WOOD, MaterialColor.ORANGE_TERRACOTTA, 2.0F, 3.0F, SoundType.WOOD);
	public static final Block COPPER_SAPLING = new CustomSaplingBlock("copper_sapling", COPPER_LEAVES, COPPER_LOG, ModBlocks.COPPER_SAPLING);

	public static final Block ALUMINIUM_LEAVES = new CustomLeavesBlock("aluminium_leaves");
	public static final Block ALUMINIUM_LOG = new CustomLogBlock("aluminium_log", MaterialColor.LIGHT_GRAY);
	public static final Block ALUMINIUM_PLANKS = new BlockBase("aluminium_planks", Material.WOOD, MaterialColor.LIGHT_GRAY, 2.0F, 3.0F, SoundType.WOOD);
	public static final Block ALUMINIUM_SAPLING = new CustomSaplingBlock("aluminium_sapling", ALUMINIUM_LEAVES, ALUMINIUM_LOG, ModBlocks.ALUMINIUM_SAPLING);

	public static final Block COPPER_CHEST = new CopperChestBlock();

// Machines
	public static final Block TELEPORTER = new TeleporterBlock(1);

	public static final TileBlockBase FIRSTBLOCK = new FirstBlock();
	public static final TileBlockBase ENERGY_STORAGE = new EnergyStorageBlock();
	public static final TileBlockBase SINTERING_FURNACE = new SinteringFurnaceBlock();
	public static final TileBlockBase ELECTRIC_SINTERING_FURNACE = new ElectricSinteringFurnaceBlock();
	public static final TileBlockBase CHARGER = new ChargerBlock();
}
