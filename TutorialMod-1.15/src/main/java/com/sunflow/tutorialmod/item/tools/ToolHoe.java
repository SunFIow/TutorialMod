package com.sunflow.tutorialmod.item.tools;

import com.sunflow.tutorialmod.setup.ModGroups;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class ToolHoe extends HoeItem {

	public ToolHoe(float attackSpeed, IItemTier tier) {
		super(tier, attackSpeed, new Item.Properties().group(ModGroups.itemGroup));
	}
}
