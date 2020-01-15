package com.sunflow.tutorialmod.item.tools;

import com.sunflow.tutorialmod.setup.ModGroups;
import com.sunflow.tutorialmod.setup.ModItems;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class ToolPickaxe extends PickaxeItem {

	public ToolPickaxe(String name, int attackDamage, float attackSpeed, IItemTier tier) {
		super(tier, attackDamage, attackSpeed, new Item.Properties().group(ModGroups.itemGroup));
		this.setRegistryName(name);

		ModItems.ITEMS.add(this);
	}
}
