package com.sunflow.tutorialmod.item.tools;

import com.sunflow.tutorialmod.setup.ModTabs;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class ToolShovel extends ShovelItem {

	public ToolShovel(float attackDamage, float attackSpeed, Tier tier) {
		super(tier, attackDamage, attackSpeed, new Item.Properties().tab(ModTabs.ITEM_TAB));
	}
}
