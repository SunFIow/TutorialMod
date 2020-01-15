package com.sunflow.tutorialmod.item;

import com.sunflow.tutorialmod.item.base.ItemBase;
import com.sunflow.tutorialmod.setup.ModGroups;

import net.minecraft.item.Item;

public class FirstItem extends ItemBase {

	public FirstItem() {
		super("firstitem", new Item.Properties().group(ModGroups.itemGroup2));
	}
}
