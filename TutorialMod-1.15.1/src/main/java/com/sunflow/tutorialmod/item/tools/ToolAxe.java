package com.sunflow.tutorialmod.item.tools;

import com.sunflow.tutorialmod.setup.ModGroups;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class ToolAxe extends AxeItem {

	public ToolAxe(float attackDamge, float attackSpeed, IItemTier tier) {
		super(tier, attackDamge, attackSpeed, new Item.Properties().group(ModGroups.itemGroup));
	}
}
