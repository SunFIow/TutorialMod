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
import com.sunflow.tutorialmod.blocks.base.BlockBase;
import com.sunflow.tutorialmod.blocks.base.TileBlockBase;
import com.sunflow.tutorialmod.blocks.food.FoodPlantBlock;
import com.sunflow.tutorialmod.blocks.furniture.SantaHatBlock;
import com.sunflow.tutorialmod.blocks.ore.RubyBlock;
import com.sunflow.tutorialmod.blocks.ore.RubyOre;
import com.sunflow.tutorialmod.blocks.tree.CustomLeavesBlock;
import com.sunflow.tutorialmod.blocks.tree.CustomLogBlock;
import com.sunflow.tutorialmod.blocks.tree.CustomPlanksBlock;
import com.sunflow.tutorialmod.blocks.tree.CustomSaplingBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class ModBlocks {
	public static final List<Block> BLOCKS = new ArrayList<>();

	public static final Block RUBY_BLOCK = new RubyBlock();

	public static final Block COPPER_BLOCK = new BlockBase("copper_block", Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(5.0f));

	public static final Block RUBY_ORE = new RubyOre();

	public static final Block COPPER_ORE_OVERWORLD = new BlockBase("copper_ore", Material.ROCK, 3f, 3f, ToolType.PICKAXE, 2, SoundType.STONE, 0);
	public static final Block COPPER_ORE_END = new BlockBase("copper_ore_end", Material.ROCK, 3f, 3f, ToolType.PICKAXE, 2, SoundType.STONE, 0);
	public static final Block COPPER_ORE_NETHER = new BlockBase("copper_ore_nether", Material.ROCK, 3f, 3f, ToolType.PICKAXE, 2, SoundType.STONE, 0);

	public static final Block ALUMINIUM_ORE_OVERWORLD = new BlockBase("aluminium_ore", Material.ROCK, 3f, 3f, ToolType.PICKAXE, 2, SoundType.STONE, 0);
	public static final Block ALUMINIUM_ORE_END = new BlockBase("aluminium_ore_end", Material.ROCK, 3f, 3f, ToolType.PICKAXE, 2, SoundType.STONE, 0);
	public static final Block ALUMINIUM_ORE_NETHER = new BlockBase("aluminium_ore_nether", Material.ROCK, 3f, 3f, ToolType.PICKAXE, 2, SoundType.STONE, 0);

// Furniture
	public static final Block SANTA_HAT = new SantaHatBlock("santa_hat");

// Food
	public static final Block RICE_PLANT = new FoodPlantBlock("rice_plant", ModItems.RICE, ModItems.RICE, 3f, 2f);

// Tree	
	public static final Block COPPER_LEAVES = new CustomLeavesBlock("copper_leaves");
	public static final Block COPPER_LOG = new CustomLogBlock("copper_log", MaterialColor.ORANGE_TERRACOTTA);
	public static final Block COPPER_PLANKS = new CustomPlanksBlock("copper_planks", MaterialColor.ORANGE_TERRACOTTA);
	public static final Block COPPER_SAPLING = new CustomSaplingBlock("copper_sapling", COPPER_LEAVES, COPPER_LOG, ModBlocks.COPPER_SAPLING);
	public static final Block ALUMINIUM_LEAVES = new CustomLeavesBlock("aluminium_leaves");
	public static final Block ALUMINIUM_LOG = new CustomLogBlock("aluminium_log", MaterialColor.LIGHT_GRAY);
	public static final Block ALUMINIUM_PLANKS = new CustomPlanksBlock("aluminium_planks", MaterialColor.LIGHT_GRAY);
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
