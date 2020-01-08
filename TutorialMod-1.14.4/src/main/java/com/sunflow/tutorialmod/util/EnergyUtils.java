package com.sunflow.tutorialmod.util;

import javax.annotation.Nullable;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.interfaces.IEnergyItem;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;

public class EnergyUtils {

//	public static final String DEFAULT_ENERGY_UNIT = "default";

	public static double getEnergyDurabilityForDisplay(ItemStack stack, EnergyUnit unit) {
		if (hasSupport(stack, unit)) {
			double capacity = getCapacity(stack, unit);
			double energyDiff = capacity - getEnergyStored(stack, unit);

			return energyDiff / capacity;
		}
		return 0;
	}

	public static boolean hasSupport(ItemStack stack, EnergyUnit unit) {
		CompoundNBT modTag = stack.getChildTag(TutorialMod.MODID);

		if (modTag == null)
			return false;

		return modTag.contains(unit.name);
	}

	public static int getCapacity(ItemStack stack, EnergyUnit unit) {
		return getEnergyUnit(stack, unit).getInt("Capacity");
	}

	public static double getEnergyStored(ItemStack stack, EnergyUnit unit) {
		return getEnergyUnit(stack, unit).getInt("Energy");
	}

	@Nullable
	public static CustomEnergyStorage readStorage(ItemStack stack, EnergyUnit unit) {
		return CustomEnergyStorage.fromNBT(getEnergyUnit(stack, unit));
	}

	public static void writeStorage(ItemStack stack, EnergyUnit unit, CustomEnergyStorage storage) {
		stack.getOrCreateChildTag(TutorialMod.MODID).put(unit.name, storage.serializeNBT());
	}

	private static CompoundNBT getEnergyUnit(ItemStack stack, EnergyUnit unit) {
		CompoundNBT energyUnitNBT = stack.getOrCreateChildTag(TutorialMod.MODID).getCompound(unit.name);
		if (energyUnitNBT.isEmpty()) {
			if (!(stack.getItem() instanceof IEnergyItem)) {
				return new CompoundNBT();
			}
			writeStorage(stack, unit, ((IEnergyItem) stack.getItem()).createEnergy());
			energyUnitNBT = stack.getOrCreateChildTag(TutorialMod.MODID).getCompound(unit.name);
		}
		return energyUnitNBT;
	}

	public static int getEnergyRGBForDisplay(ItemStack stack, EnergyUnit unit) {
		return unit.getColor();
	}

	public static enum EnergyUnit {

		DEFAULT("default", 255, 0, 150);

		public final int[] color;
		public final String name;

		private EnergyUnit(String name, int red, int green, int blue) {
			this.name = name;
			this.color = new int[] { red, green, blue };
		}

		public int getColor() {
			return MathHelper.rgb(color[0], color[1], color[2]);
		}
	}
}
