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

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityProcessor {
	private static class Cooker extends Processor {}

	private static class Burner extends Processor {}

	@CapabilityInject(Cooker.class)
	public static Capability<IProcessor> COOKER_CAPABILITY = null;

	@CapabilityInject(Burner.class)
	public static Capability<IProcessor> BURNER_CAPABILITY = null;

	public static void register() {
		CapabilityManager.INSTANCE.register(Cooker.class, new DefaultProccesorStorage<>(), Cooker::new);
		CapabilityManager.INSTANCE.register(Burner.class, new DefaultProccesorStorage<>(), Burner::new);
	}

	private static class DefaultProccesorStorage<T extends IProcessor> implements Capability.IStorage<T> {
		@Override
		public INBT writeNBT(Capability<T> capability, T instance, Direction side) {
			return IntNBT.valueOf(instance.getTime());
		}

		@Override
		public void readNBT(Capability<T> capability, T instance, Direction side, INBT nbt) {
			if (!(instance instanceof Processor))
				throw new RuntimeException("Cannot deserialize to an instance that isn't the default implementation");
			Processor processor = (Processor) instance;
			processor.time = ((IntNBT) nbt).getInt();
		}
	}
}