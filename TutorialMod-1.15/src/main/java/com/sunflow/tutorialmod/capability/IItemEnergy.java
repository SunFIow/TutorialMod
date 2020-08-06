package com.sunflow.tutorialmod.capability;

import com.sunflow.tutorialmod.util.energy.CustomEnergyStorage;

import net.minecraft.item.ItemStack;

public interface IItemEnergy {

	CustomEnergyStorage getStorage();

	ItemStack getItemStack();
}