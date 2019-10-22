package com.sunflow.tutorialmod.items.base;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.init.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item {

	protected String customName;

	public ItemBase(String name, Item.Properties properties) {
		super(properties);
		this.setRegistryName(name);

		ModItems.ITEMS.add(this);
	}

	public ItemBase(String name, int maxDamageIn, ItemGroup groupIn, Object __) {
		this(name, new Item.Properties().maxDamage(maxDamageIn).group(groupIn).defaultMaxDamage(maxDamageIn));
	}

	public ItemBase(String name, int maxDamageIn, Object __) {
		this(name, new Item.Properties().maxDamage(maxDamageIn).group(TutorialMod.setup.itemGroup));
	}

	public ItemBase(String name, int maxStackSizeIn, ItemGroup groupIn) {
		this(name, new Item.Properties().maxStackSize(maxStackSizeIn).group(groupIn));
	}

	public ItemBase(String name, int maxStackSizeIn) {
		this(name, new Item.Properties().maxStackSize(maxStackSizeIn).group(TutorialMod.setup.itemGroup));
	}

	public ItemBase(String name) {
		this(name, new Item.Properties().group(TutorialMod.setup.itemGroup));
	}
}