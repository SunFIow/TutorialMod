package com.sunflow.tutorialmod.capability;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.util.energy.CustomEnergyStorage;

import net.minecraft.item.ItemStack;

public class DefaultItemEnergy implements IItemEnergy {
	protected Supplier<CustomEnergyStorage> storage;
	protected Supplier<ItemStack> stack;

	public DefaultItemEnergy() { this(() -> CustomEnergyStorage.DEFAULT, () -> ItemStack.EMPTY); }

	public DefaultItemEnergy(Supplier<CustomEnergyStorage> storage, Supplier<ItemStack> stack) {
		setStorage(storage);
		setItemStack(stack);
	}

	@Override
	public CustomEnergyStorage getStorage() { return storage.get(); }

	@Override
	public ItemStack getItemStack() { return stack.get(); }

	public void setStorage(CustomEnergyStorage storage) { setStorage(() -> storage); }

	public void setStorage(Supplier<CustomEnergyStorage> storage) { this.storage = storage; }

	public void setItemStack(ItemStack stack) { setItemStack(() -> stack); }

	public void setItemStack(Supplier<ItemStack> stack) { this.stack = stack; }

}
