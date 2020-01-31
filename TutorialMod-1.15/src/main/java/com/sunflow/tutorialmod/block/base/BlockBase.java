package com.sunflow.tutorialmod.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class BlockBase extends Block {

	public BlockBase(Properties properties) {
		super(properties);
	}

	public BlockBase(Material material) {
		this(Block.Properties.create(material).hardnessAndResistance(2.5f));
	}

	public BlockBase(Material material, MaterialColor color, float hardness, float resistance, SoundType soundtype) {
		this(Block.Properties.create(material, color).hardnessAndResistance(hardness, resistance).sound(soundtype));
	}

	public BlockBase(Material material, float hardness, float resistance, ToolType tooltype, int harvestlevel, SoundType soundtype, int lightlevel) {
		this(Block.Properties.create(material).hardnessAndResistance(hardness, resistance).harvestTool(tooltype).harvestLevel(harvestlevel).sound(soundtype).lightValue(lightlevel));
	}
}
