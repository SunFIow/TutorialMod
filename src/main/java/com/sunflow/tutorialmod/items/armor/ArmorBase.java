package com.sunflow.tutorialmod.items.armor;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.init.ModItems;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;

public class ArmorBase extends ArmorItem {
//	protected int renderIndex;

	public ArmorBase(String name, IArmorMaterial armorMaterial, EquipmentSlotType equipmentSlot) {
		super(armorMaterial, equipmentSlot, new Item.Properties().group(TutorialMod.setup.itemGroup));
		this.setRegistryName(name);
//		this.renderIndex = renderIndex;

		ModItems.ITEMS.add(this);
	}
}
