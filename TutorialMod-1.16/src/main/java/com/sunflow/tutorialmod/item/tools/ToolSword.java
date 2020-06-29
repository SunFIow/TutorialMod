package com.sunflow.tutorialmod.item.tools;

import com.sunflow.tutorialmod.setup.ModGroups;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class ToolSword extends SwordItem {

	public ToolSword(int attackDamage, float attackSpeed, IItemTier tier) {
		super(tier, attackDamage, attackSpeed, new Item.Properties().group(ModGroups.itemGroup));
	}
}
