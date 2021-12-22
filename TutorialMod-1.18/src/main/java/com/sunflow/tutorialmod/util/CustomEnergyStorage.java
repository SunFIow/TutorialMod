package com.sunflow.tutorialmod.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.energy.EnergyStorage;

public class CustomEnergyStorage extends EnergyStorage {

	public static final String NBT_LOC_ENERGY = "Energy";
	public static final String NBT_LOC_CAPACITY = "Capacity";
	public static final String NBT_LOC_MAX_RECEIVE = "MaxReceive";
	public static final String NBT_LOC_MAX_EXTRACT = "MaxExtract";

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
	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();

		tag.putInt(NBT_LOC_ENERGY, energy);
		tag.putInt(NBT_LOC_CAPACITY, capacity);
		tag.putInt(NBT_LOC_MAX_RECEIVE, maxReceive);
		tag.putInt(NBT_LOC_MAX_EXTRACT, maxExtract);

		return tag;
	}

	@Override
	public void deserializeNBT(Tag t) {
		if (t instanceof CompoundTag tag) {
			energy = tag.getInt(NBT_LOC_ENERGY);
			capacity = tag.getInt(NBT_LOC_CAPACITY);
			maxReceive = tag.getInt(NBT_LOC_MAX_RECEIVE);
			maxExtract = tag.getInt(NBT_LOC_MAX_EXTRACT);
		} else throw new IllegalArgumentException("Tried to deserialize a CustomEnergyStorage with " + t + " but CompoundTag is needed");

	}

	public static CustomEnergyStorage fromNBT(CompoundTag tag) {
		int energy = tag.getInt(NBT_LOC_ENERGY);
		int capacity = tag.getInt(NBT_LOC_CAPACITY);
		int maxReceive = tag.getInt(NBT_LOC_MAX_RECEIVE);
		int maxExtract = tag.getInt(NBT_LOC_MAX_EXTRACT);
		return new CustomEnergyStorage(capacity, maxReceive, maxExtract, energy);
	}

}
