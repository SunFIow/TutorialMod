package com.sunflow.tutorialmod.tools;

import net.minecraftforge.energy.EnergyStorage;

public class CustomEnergyStorage extends EnergyStorage {

	public CustomEnergyStorage(int capacity, int maxTransfer) { super(capacity, maxTransfer); }

	protected void onEnergyChanged() {}

	public void setEnergy(int amount) { this.energy = amount; }

	public void addEnergy(int amount) {
		this.energy += amount;
		if (this.energy > getMaxEnergyStored()) this.energy = getMaxEnergyStored();
		onEnergyChanged();
	}

	public void consumeEnergy(int amount) {
		this.energy -= amount;
		if (this.energy < 0) this.energy = 0;
		onEnergyChanged();
	}

}
