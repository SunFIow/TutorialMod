package com.sunflow.tutorialmod.block.energy.machine.energy_storage;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.ContainerBase;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.wrapper.InvWrapper;

public class EnergyStorageContainer extends ContainerBase {

	public EnergyStorageContainer(int id, PlayerInventory inv, PacketBuffer data) {
		this(id, inv, TutorialMod.proxy.getClientWorld().getTileEntity(data.readBlockPos()));
	}

	public EnergyStorageContainer(int id, PlayerInventory inv, TileEntity tile) {
		super(Registration.ENERGY_STORAGE_CONTAINER.get(), id, 1, tile);

		layoutPlayerInventorySlots(new InvWrapper(inv), 8, 84);

		trackEnergy();
		trackEnergyMax();
	}
}
