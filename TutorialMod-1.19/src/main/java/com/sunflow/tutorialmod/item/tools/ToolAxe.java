package com.sunflow.tutorialmod.item.tools;

import com.sunflow.tutorialmod.setup.ModTabs;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class ToolAxe extends AxeItem {

	public ToolAxe(float attackDamge, float attackSpeed, Tier tier) {
		super(tier, attackDamge, attackSpeed, new Item.Properties().tab(ModTabs.ITEM_TAB));
	}
}
