package com.sunflow.tutorialmod.blocks.container;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.blocks.base.ContainerBase;
import com.sunflow.tutorialmod.blocks.tile.SinteringFurnaceTile;
import com.sunflow.tutorialmod.init.ModContainerTypes;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class SinteringFurnaceContainer extends ContainerBase {

	public SinteringFurnaceContainer(int windowId, PlayerInventory inv, PacketBuffer data) {
		this(windowId, TutorialMod.proxy.getClientWorld(), data.readBlockPos(), inv);
	}

	public SinteringFurnaceContainer(int id, World world, BlockPos pos, PlayerInventory inv) {
		super(ModContainerTypes.SINTERING_FURNACE_CONTAINER, id, 4, world, pos);

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

		makeIntArray(3);
	}

	public int getCookTime() {
		return ((SinteringFurnaceTile) tile).getField(SinteringFurnaceTile.COOKTIME_ID);
	}

	public int getBurnTime() {
		return ((SinteringFurnaceTile) tile).getField(SinteringFurnaceTile.BURNTIME_ID);
	}

	public int getBurnTimeTotal() {
		return ((SinteringFurnaceTile) tile).getField(SinteringFurnaceTile.BURNTIME_TOTAL_ID);
	}
}
