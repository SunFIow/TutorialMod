package com.sunflow.tutorialmod.item.base;

import com.sunflow.tutorialmod.setup.ModGroups;
import com.sunflow.tutorialmod.setup.ModItems;

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
		this(name, new Item.Properties().maxDamage(maxDamageIn).group(ModGroups.itemGroup));
	}

	public ItemBase(String name, int maxStackSizeIn, ItemGroup groupIn) {
		this(name, new Item.Properties().maxStackSize(maxStackSizeIn).group(groupIn));
	}

	public ItemBase(String name, int maxStackSizeIn) {
		this(name, new Item.Properties().maxStackSize(maxStackSizeIn).group(ModGroups.itemGroup));
	}

	public ItemBase(String name) {
		this(name, new Item.Properties().group(ModGroups.itemGroup));
	}
}