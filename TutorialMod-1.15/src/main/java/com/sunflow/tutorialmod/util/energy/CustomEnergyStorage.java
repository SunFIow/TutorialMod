package com.sunflow.tutorialmod.util.energy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class CustomEnergyStorage extends EnergyStorage implements INBTSerializable<CompoundNBT> {
	public CustomEnergyStorage(int capacity) {
		super(capacity);
	}

	public CustomEnergyStorage(int capacity, int maxTransfer) {
		super(capacity, maxTransfer);
	}

	public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract) {
		super(capacity, maxReceive, maxExtract);
	}

	public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
		super(capacity, maxReceive, maxExtract, energy);
	}

	protected void onEnergyChanged() {}

	public void setEnergy(int value) {
		energy = value;
		onEnergyChanged();
	}

	public void setMaxEnergy(int value) {
		capacity = value;
		onEnergyChanged();
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		int energyReceived = super.receiveEnergy(maxReceive, simulate);
		if (energyReceived != 0) onEnergyChanged();
		return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		int energyExtracted = super.extractEnergy(maxExtract, simulate);
		if (energyExtracted != 0) onEnergyChanged();
		return energyExtracted;
	}

	public float getFillLevel() {
		return (float) energy / capacity;
	}

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT tag = new CompoundNBT();

		tag.putInt("Energy", energy);
		tag.putInt("Capacity", capacity);
		tag.putInt("MaxReceive", maxReceive);
		tag.putInt("MaxExtract", maxExtract);

		return tag;
	}

	@Override
	public void deserializeNBT(CompoundNBT tag) {
		energy = tag.getInt("Energy");
		capacity = tag.getInt("Capacity");
		maxReceive = tag.getInt("MaxReceive");
		maxExtract = tag.getInt("MaxExtract");
	}

	public static CustomEnergyStorage fromNBT(CompoundNBT tag) {
		int energy = tag.getInt("Energy");
		int capacity = tag.getInt("Capacity");
		int maxReceive = tag.getInt("MaxReceive");
		int maxExtract = tag.getInt("MaxExtract");
		CustomEnergyStorage energyStorage = new CustomEnergyStorage(capacity, maxReceive, maxExtract, energy);
		return energyStorage;
	}

}
