package com.sunflow.tutorialmod.blocks.container;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.blocks.base.ContainerBase;
import com.sunflow.tutorialmod.blocks.tile.EnergyStorageTile;
import com.sunflow.tutorialmod.init.ModContainerTypes;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.wrapper.InvWrapper;

public class EnergyStorageContainer extends ContainerBase {

	public EnergyStorageContainer(int id, PlayerInventory inv, PacketBuffer data) {
		this(id, inv, TutorialMod.proxy.getClientWorld().getTileEntity(data.readBlockPos()));
	}

	public EnergyStorageContainer(int id, PlayerInventory inv, TileEntity tile) {
		super(ModContainerTypes.ENERGY_STORAGE_CONTAINER, id, 1, tile);

		layoutPlayerInventorySlots(new InvWrapper(inv), 8, 84);

		makeIntArray(2);
	}

	public int getEnergy() {
		return field.getField(EnergyStorageTile.ENERGY_ID);
	}

	public int getEnergyMax() {
		return field.getField(EnergyStorageTile.ENERGY_MAX_ID);
	}
}
