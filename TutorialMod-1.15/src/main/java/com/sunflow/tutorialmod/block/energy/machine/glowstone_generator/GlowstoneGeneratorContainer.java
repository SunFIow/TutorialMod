package com.sunflow.tutorialmod.block.energy.machine.glowstone_generator;

import com.sunflow.tutorialmod.block.base.ContainerBase;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
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

		this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent((h) -> {
			addSlot(new SlotItemHandler(h, GlowstoneGeneratorTile.FUEL_SLOT, 80, 35));
		});

		layoutPlayerInventorySlots(new InvWrapper(inv), 8, 84);

		trackEnergy();
		trackEnergyMax();
		trackCookTime();
		trackCookTimeTotal();
	}
}
