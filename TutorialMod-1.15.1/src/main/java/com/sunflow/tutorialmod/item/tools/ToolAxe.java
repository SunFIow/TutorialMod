package com.sunflow.tutorialmod.item.tools;

import com.sunflow.tutorialmod.setup.ModGroups;
import com.sunflow.tutorialmod.setup.ModItems;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class ToolAxe extends AxeItem {

	public ToolAxe(String name, float attackDamge, float attackSpeed, IItemTier tier) {
		super(tier, attackDamge, attackSpeed, new Item.Properties().group(ModGroups.itemGroup));
		this.setRegistryName(name);

		ModItems.ITEMS.add(this);
	}
}
