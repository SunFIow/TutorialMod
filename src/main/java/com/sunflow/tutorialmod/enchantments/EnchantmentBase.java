package com.sunflow.tutorialmod.enchantments;

import com.sunflow.tutorialmod.init.ModEnchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public abstract class EnchantmentBase extends Enchantment {

	public EnchantmentBase(String name, Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
		super(rarityIn, typeIn, slots);
//		this.setRegistryName(new ResourceLocation(Reference.MOD_ID + ":enchantment_" + name));
//		this.setRegistryName(new ResourceLocation(":enchantment_" + name));
		this.setRegistryName(name);

		ModEnchantments.ENCHANTMENTS.add(this);
	}
}