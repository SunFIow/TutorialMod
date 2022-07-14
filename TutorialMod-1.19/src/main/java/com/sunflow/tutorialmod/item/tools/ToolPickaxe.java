package com.sunflow.tutorialmod.item.tools;

import com.sunflow.tutorialmod.setup.ModTabs;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class ToolPickaxe extends PickaxeItem {

	public ToolPickaxe(int attackDamage, float attackSpeed, Tier tier) {
		super(tier, attackDamage, attackSpeed, new Item.Properties().tab(ModTabs.ITEM_TAB));
	}
}
