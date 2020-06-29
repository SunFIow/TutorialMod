package com.sunflow.tutorialmod.block.machine.glowstone_generator;

import com.sunflow.tutorialmod.block.base.ContainerBase;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class GlowstoneGeneratorContainer extends ContainerBase {

	// CLIENT
	public GlowstoneGeneratorContainer(int id, PlayerInventory inv, PacketBuffer data) {
		this(id, inv, inv.player.getEntityWorld().getTileEntity(data.readBlockPos()));
	}

	// SERVER
	public GlowstoneGeneratorContainer(int id, PlayerInventory inv, TileEntity tile) {
		super(Registration.GLOWSTONE_GENERATOR_CONTAINER.get(), id, 1, tile);

		this.tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent((h) -> {
			addSlot(new SlotItemHandler(h, GlowstoneGeneratorTile.FUEL_SLOT, 80, 35));
		});

		layoutPlayerInventorySlots(new InvWrapper(inv), 8, 84);

		makeIntArray(3);
	}

	public int getCookTime() {
		return field.getField(GlowstoneGeneratorTile.COOKTIME_ID);
	}

	public int getEnergy() {
		return tileentity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
//		return field.getField(GlowstoneGeneratorTile.ENERGY_ID);
	}

	public int getEnergyMax() {
		return tileentity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getMaxEnergyStored).orElse(0);
//		return field.getField(GlowstoneGeneratorTile.ENERGY_MAX_ID);
	}
}
