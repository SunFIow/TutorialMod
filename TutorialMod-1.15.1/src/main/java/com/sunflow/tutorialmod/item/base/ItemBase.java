package com.sunflow.tutorialmod.item.base;

import com.sunflow.tutorialmod.setup.ModGroups;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item {

	protected String customName;

	public ItemBase(Item.Properties properties) {
		super(properties);
	}

	public ItemBase(int maxDamageIn, ItemGroup groupIn, Object __) {
		this(new Item.Properties().maxDamage(maxDamageIn).group(groupIn).defaultMaxDamage(maxDamageIn));
	}

	public ItemBase(int maxDamageIn, Object __) {
		this(new Item.Properties().maxDamage(maxDamageIn).group(ModGroups.itemGroup));
	}

	public ItemBase(int maxStackSizeIn, ItemGroup groupIn) {
		this(new Item.Properties().maxStackSize(maxStackSizeIn).group(groupIn));
	}

	public ItemBase(int maxStackSizeIn) {
		this(new Item.Properties().maxStackSize(maxStackSizeIn).group(ModGroups.itemGroup));
	}

	public ItemBase() { this(new Item.Properties().group(ModGroups.itemGroup)); }
}