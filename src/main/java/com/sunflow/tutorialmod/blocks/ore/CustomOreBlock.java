package com.sunflow.tutorialmod.blocks.ore;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.init.ModBlocks;
import com.sunflow.tutorialmod.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;

public class CustomOreBlock extends OreBlock {

	public CustomOreBlock(String name, Properties properties) {
		this(name, TutorialMod.setup.itemGroup, properties);
	}

	public CustomOreBlock(String name, Material material) {
		this(name, TutorialMod.setup.itemGroup, Block.Properties.create(material).hardnessAndResistance(2.5f, 5.0f));
	}

	public CustomOreBlock(String name, Material material, float hardness, float resistance, ToolType tooltype, int harvestlevel, SoundType soundtype, int lightlevel) {
		this(name, TutorialMod.setup.itemGroup, Block.Properties.create(material).hardnessAndResistance(hardness, resistance).harvestTool(tooltype).harvestLevel(harvestlevel).sound(soundtype).lightValue(lightlevel));
	}

	public CustomOreBlock(String name, ItemGroup group, Properties properties) {
		super(properties);
		this.setRegistryName(name);

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new BlockItem(this, new Item.Properties().group(group)).setRegistryName(name));
	}
}
