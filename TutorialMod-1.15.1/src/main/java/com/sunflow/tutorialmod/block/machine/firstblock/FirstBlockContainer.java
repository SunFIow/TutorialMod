package com.sunflow.tutorialmod.block.machine.firstblock;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.ContainerBase;
import com.sunflow.tutorialmod.setup.ModContainerTypes;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class FirstBlockContainer extends ContainerBase {

	// CLIENT
	public FirstBlockContainer(int id, PlayerInventory inv, PacketBuffer data) {
		this(id, inv, TutorialMod.proxy.getClientWorld().getTileEntity(data.readBlockPos()));
	}

	// SERVER
	public FirstBlockContainer(int windowId, PlayerInventory inv, TileEntity tile) {
		super(ModContainerTypes.FIRSTBLOCK_CONTAINER, windowId, 1, tile);

		this.tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent((h) -> {
			addSlot(new SlotItemHandler(h, FirstBlockTile.FUEL_SLOT, 80, 35));
		});

		layoutPlayerInventorySlots(new InvWrapper(inv), 8, 84);

		makeIntArray(3);
	}

	public int getCookTime() {
		return field.getField(FirstBlockTile.COOKTIME_ID);
	}

	public int getEnergy() {
		return field.getField(FirstBlockTile.ENERGY_ID);
	}

	public int getEnergyMax() {
		return field.getField(FirstBlockTile.ENERGY_MAX_ID);
	}
}
