package com.sunflow.tutorialmod.item.armor;

import com.sunflow.tutorialmod.setup.ModGroups;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

public class ArmorBase extends ArmorItem {

	public ArmorBase(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot) {
		super(armorMaterial, equipmentSlot, new Item.Properties().tab(ModGroups.ITEM_GROUP));
	}
}
