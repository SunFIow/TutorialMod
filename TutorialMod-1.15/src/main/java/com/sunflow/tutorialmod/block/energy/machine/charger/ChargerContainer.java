package com.sunflow.tutorialmod.block.energy.machine.charger;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.ContainerBase;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ChargerContainer extends ContainerBase {

	public ChargerContainer(int id, PlayerInventory inv, PacketBuffer data) {
		this(id, inv, TutorialMod.proxy.getClientWorld().getTileEntity(data.readBlockPos()));
	}

	public ChargerContainer(int id, PlayerInventory inv, TileEntity tile) {
		super(Registration.CHARGER_CONTAINER.get(), id, 1, tile);

		this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent((h) -> {
			addSlot(new SlotItemHandler(h, ChargerTile.CHARGE_SLOT, 80, 35));
		});

		layoutPlayerInventorySlots(new InvWrapper(inv), 8, 84);

		trackEnergy();
		trackEnergyMax();
		trackItemEnergy();
		trackItemEnergyMax();
	}

}
