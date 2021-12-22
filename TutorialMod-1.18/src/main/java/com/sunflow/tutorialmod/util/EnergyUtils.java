package com.sunflow.tutorialmod.util;

import javax.annotation.Nullable;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.interfaces.IEnergyItem;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

public class EnergyUtils {
	private EnergyUtils() {}

	public static final String DEFAULT_ENERGY_UNIT = "default";

	public static double getEnergyDurabilityForDisplay(ItemStack stack, EnergyUnit unit) {
		if (hasSupport(stack, unit)) {
			double capacity = getCapacity(stack, unit);
			double energyDiff = capacity - getEnergyStored(stack, unit);

			return energyDiff / capacity;
		}
		return 0;
	}

	public static boolean hasSupport(ItemStack stack, EnergyUnit unit) {
		CompoundTag modTag = stack.getOrCreateTagElement(TutorialMod.MODID);

		if (modTag == null)
			return false;

		return modTag.contains(unit.name);
	}

	public static int getCapacity(ItemStack stack, EnergyUnit unit) {
		return getEnergyUnit(stack, unit).getInt(CustomEnergyStorage.NBT_LOC_CAPACITY);
	}

	public static int getEnergyStored(ItemStack stack, EnergyUnit unit) {
		return getEnergyUnit(stack, unit).getInt(CustomEnergyStorage.NBT_LOC_ENERGY);
	}

	@Nullable
	public static CustomEnergyStorage readStorage(ItemStack stack, EnergyUnit unit) {
		return CustomEnergyStorage.fromNBT(getEnergyUnit(stack, unit));
	}

	public static void writeStorage(ItemStack stack, EnergyUnit unit, CustomEnergyStorage storage) {
		stack.getOrCreateTagElement(TutorialMod.MODID).put(unit.name, storage.serializeNBT());
	}

	private static CompoundTag getEnergyUnit(ItemStack stack, EnergyUnit unit) {
		CompoundTag energyUnitNBT = stack.getOrCreateTagElement(TutorialMod.MODID).getCompound(unit.name);
		if (energyUnitNBT.isEmpty()) {
			if (!(stack.getItem() instanceof IEnergyItem)) {
				return new CompoundTag();
			}
			writeStorage(stack, unit, ((IEnergyItem) stack.getItem()).createEnergy());
			energyUnitNBT = stack.getOrCreateTagElement(TutorialMod.MODID).getCompound(unit.name);
		}
		return energyUnitNBT;
	}

	public static int getEnergyRGBForDisplay(ItemStack stack, EnergyUnit unit) { return unit.getColor(); }

	public static enum EnergyUnit {

		DEFAULT(DEFAULT_ENERGY_UNIT, 255, 0, 150);

		private final int[] color;
		private final String name;

		private EnergyUnit(String name, int red, int green, int blue) {
			this.name = name;
			this.color = new int[] { red, green, blue };
		}

		public String getName() { return name; }

		public int getColor() { return Mth.color(color[0], color[1], color[2]); }
	}
}
