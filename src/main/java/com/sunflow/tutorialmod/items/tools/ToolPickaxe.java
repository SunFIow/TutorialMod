package com.sunflow.tutorialmod.items.tools;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.init.ModItems;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class ToolPickaxe extends PickaxeItem {

	public ToolPickaxe(String name, int attackDamage, float attackSpeed, IItemTier tier) {
		super(tier, attackDamage, attackSpeed, new Item.Properties().group(TutorialMod.groups.itemGroup));
		this.setRegistryName(name);

		ModItems.ITEMS.add(this);
	}
}
