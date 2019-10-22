package com.sunflow.tutorialmod.blocks.base;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.init.ModBlocks;
import com.sunflow.tutorialmod.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;

public class BlockBase extends Block {

	public BlockBase(String name, ItemGroup group, Properties properties) {
		super(properties);
		this.setRegistryName(name);

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new BlockItem(this, new Item.Properties().group(group)).setRegistryName(name));
	}

	public BlockBase(String name, Properties properties) {
		this(name, TutorialMod.setup.itemGroup, properties);
	}

	public BlockBase(String name, Material material) {
		this(name, Block.Properties.create(material).hardnessAndResistance(2.5f, 5.0f));
	}

	public BlockBase(String name, Material material, float hardness, float resistance, ToolType tooltype, int harvestlevel, SoundType soundtype, int lightlevel) {
		this(name, Block.Properties.create(material).hardnessAndResistance(hardness, resistance).harvestTool(tooltype).harvestLevel(harvestlevel).sound(soundtype).lightValue(lightlevel));
	}
}
