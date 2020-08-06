/*
 * Minecraft Forge
 * Copyright (c) 2016-2019.
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 2.1
 * of the License.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */

package com.sunflow.tutorialmod.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityItemEnergy {

	@CapabilityInject(IItemEnergy.class)
	public static Capability<IItemEnergy> ENERGYITEM_CAPABILITY = null;

	public static void register() {
		CapabilityManager.INSTANCE.register(IItemEnergy.class, new DefaultItemEnergyStorage(), DefaultItemEnergy::new);
	}

	private static class DefaultItemEnergyStorage implements Capability.IStorage<IItemEnergy> {
		@Override
		public INBT writeNBT(Capability<IItemEnergy> capability, IItemEnergy instance, Direction side) {
			CompoundNBT tag = new CompoundNBT();
			tag.put("storage", instance.getStorage().serializeNBT());
			tag.put("item", instance.getItemStack().serializeNBT());
			return tag;
		}

		@Override
		public void readNBT(Capability<IItemEnergy> capability, IItemEnergy instance, Direction side, INBT nbt) {
			CompoundNBT tag = (CompoundNBT) nbt;
			instance.getStorage().deserializeNBT(tag.getCompound("storage"));
			instance.getItemStack().deserializeNBT(tag.getCompound("item"));
		}
	}
}