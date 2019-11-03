package com.sunflow.tutorialmod.items.tools;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.init.ModItems;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class ToolHoe extends HoeItem {

	public ToolHoe(String name, float attackSpeed, IItemTier tier) {
		super(tier, attackSpeed, new Item.Properties().group(TutorialMod.groups.itemGroup));
		this.setRegistryName(name);

		ModItems.ITEMS.add(this);
	}
}
