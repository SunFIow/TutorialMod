package com.sunflow.tutorialmod.util.energy;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nullable;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.interfaces.IEnergyItem;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;

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

	public static int getEnergyStored(ItemStack stack, EnergyUnit unit) {
		return getEnergyUnit(stack, unit).getInt("Energy");
	}

	public static int getCapacity(ItemStack stack, EnergyUnit unit) {
		return getEnergyUnit(stack, unit).getInt("Capacity");
	}

	public static void setEnergyStored(ItemStack stack, EnergyUnit unit, int value) {
		getEnergyUnit(stack, unit).putInt("Energy", value);
	}

	public static void setCapacity(ItemStack stack, EnergyUnit unit, int value) {
		getEnergyUnit(stack, unit).putInt("Capacity", value);
	}

	@Nullable
	public static CustomEnergyStorage readStorage(ItemStack stack, EnergyUnit unit) {
		return CustomEnergyStorage.fromNBT(getEnergyUnit(stack, unit));
	}

	public static void writeStorage(ItemStack stack, EnergyUnit unit, CustomEnergyStorage storage) {
		stack.getOrCreateChildTag(TutorialMod.MODID).put(unit.name, storage.serializeNBT());
	}

	private static CompoundNBT getEnergyUnit(ItemStack stack, EnergyUnit unit) {
		if (stack == null || !(stack.getItem() instanceof IEnergyItem)) return new CompoundNBT();
		CompoundNBT energyUnitNBT = stack.getOrCreateChildTag(TutorialMod.MODID).getCompound(unit.name);
		if (energyUnitNBT.isEmpty()) {
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

	public static boolean receivePower(World world, BlockPos pos, CustomEnergyStorage energyStorage) {
		AtomicBoolean markDirty = new AtomicBoolean(false);
		if (energyStorage.getEnergyStored() < energyStorage.getMaxEnergyStored()) {
			for (Direction dir : Direction.values()) {
				TileEntity tileentity = world.getTileEntity(pos.offset(dir));
				if (tileentity != null) {
					tileentity.getCapability(CapabilityEnergy.ENERGY, dir.getOpposite()).ifPresent(e -> {
						if (e.canReceive()) {
//							int maxEExt = energyHandler.extractEnergy(TutorialModConfig.GLOWSTONE_GENERATOR_TRANSFER.get(), true);
//							int eReceived = e.receiveEnergy(maxEExt, false);
//							energyHandler.extractEnergy(eReceived, false);							
//							tileentity.markDirty();

							int maxExtract = e.extractEnergy(e.getMaxEnergyStored(), true);
							if (maxExtract > 0) {
								int received = energyStorage.receiveEnergy(maxExtract, false);
								e.extractEnergy(received, false);

								markDirty.set(true);
							}
						}
					});
				}
				if (energyStorage.getEnergyStored() <= 0) return markDirty.get();
			}
		}
		return markDirty.get();

	}

	public static boolean sendOutPower(World world, BlockPos pos, CustomEnergyStorage energyStorage) {
		AtomicBoolean markDirty = new AtomicBoolean(false);
		if (energyStorage.getEnergyStored() > 0) {
			for (Direction dir : Direction.values()) {
				TileEntity tileentity = world.getTileEntity(pos.offset(dir));
				if (tileentity != null) {
					tileentity.getCapability(CapabilityEnergy.ENERGY, dir.getOpposite()).ifPresent(e -> {
						if (e.canReceive()) {
//							int maxEExt = energyHandler.extractEnergy(TutorialModConfig.GLOWSTONE_GENERATOR_TRANSFER.get(), true);
//							int eReceived = e.receiveEnergy(maxEExt, false);
//							energyHandler.extractEnergy(eReceived, false);							
//							tileentity.markDirty();

							int maxExtract = energyStorage.extractEnergy(energyStorage.getMaxEnergyStored(), true);
							if (maxExtract > 0) {
								int received = e.receiveEnergy(maxExtract, false);
								energyStorage.extractEnergy(received, false);

								markDirty.set(true);
							}
						}
					});
				}
				if (energyStorage.getEnergyStored() <= 0) return markDirty.get();
			}
		}
		return markDirty.get();
	}
}
