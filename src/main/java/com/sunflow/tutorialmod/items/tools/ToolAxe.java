package com.sunflow.tutorialmod.items.tools;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.init.ModItems;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class ToolAxe extends AxeItem {

	public ToolAxe(String name, float attackDamge, float attackSpeed, IItemTier tier) {
		super(tier, attackDamge, attackSpeed, new Item.Properties().group(TutorialMod.setup.itemGroup));
		this.setRegistryName(name);

		ModItems.ITEMS.add(this);
	}
}
