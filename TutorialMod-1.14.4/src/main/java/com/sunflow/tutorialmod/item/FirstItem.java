package com.sunflow.tutorialmod.item;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.item.base.ItemBase;

import net.minecraft.item.Item;

public class FirstItem extends ItemBase {

	public FirstItem() {
		super("firstitem", new Item.Properties().group(TutorialMod.groups.itemGroup2));
	}
}
