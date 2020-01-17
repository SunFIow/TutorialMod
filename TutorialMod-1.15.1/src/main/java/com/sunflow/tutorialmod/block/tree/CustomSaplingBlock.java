package com.sunflow.tutorialmod.block.tree;

import com.sunflow.tutorialmod.setup.ModBlocks;
import com.sunflow.tutorialmod.setup.ModGroups;
import com.sunflow.tutorialmod.setup.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.IPlantable;

public class CustomSaplingBlock extends SaplingBlock {

	public CustomSaplingBlock(String name, Block leaves, Block log, IPlantable sapling) {
		super(new CustomTree(log, leaves, sapling), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT));
		this.setRegistryName(name);

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new BlockItem(this, new Item.Properties().group(ModGroups.itemGroup)).setRegistryName(name));
		RenderTypeLookup.setRenderLayer(this, RenderType.func_228643_e_());
	}
}
