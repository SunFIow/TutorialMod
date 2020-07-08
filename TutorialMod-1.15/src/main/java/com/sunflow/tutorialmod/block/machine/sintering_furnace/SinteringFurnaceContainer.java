package com.sunflow.tutorialmod.block.machine.sintering_furnace;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.ContainerBase;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class SinteringFurnaceContainer extends ContainerBase {

	public SinteringFurnaceContainer(int id, PlayerInventory inv, PacketBuffer data) {
		this(id, inv, TutorialMod.proxy.getClientWorld().getTileEntity(data.readBlockPos()));
	}

	public SinteringFurnaceContainer(int id, PlayerInventory inv, TileEntity tile) {
		super(Registration.SINTERING_FURNACE_CONTAINER.get(), id, 4, tile);

		tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent((h) -> {
//			addSlot(new SlotItemHandler(h, 0, 81, 35));
//			addSlot(new SlotSinteringFurnaceFuel(h, SinteringFurnaceTile.FUEL_ID, 21, 35));
			addSlot(new SlotItemHandler(h, SinteringFurnaceTile.FUEL_ID, 21, 35));
			addSlot(new SlotItemHandler(h, SinteringFurnaceTile.INPUT1_ID, 56, 17));
			addSlot(new SlotItemHandler(h, SinteringFurnaceTile.INPUT2_ID, 56, 53));
//			addSlot(new SlotSinteringFurnaceOutput(h, SinteringFurnaceTile.OUTPUT_ID, 116, 35));
			addSlot(new SlotItemHandler(h, SinteringFurnaceTile.OUTPUT_ID, 116, 35));

		});

		layoutPlayerInventorySlots(new InvWrapper(inv), 8, 84);

		trackCookTime();
		trackCookTimeTotal();
		trackBurnTime();
		trackBurnTimeTotal();
	}
}
