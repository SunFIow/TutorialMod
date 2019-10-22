package com.sunflow.tutorialmod.blocks.container;

import com.sunflow.tutorialmod.blocks.base.ContainerBase;
import com.sunflow.tutorialmod.blocks.tile.ElectricSinteringFurnaceTile;
import com.sunflow.tutorialmod.init.ModTypes;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ElectricSinteringFurnaceContainer extends ContainerBase {

	public ElectricSinteringFurnaceContainer(int id, World world, BlockPos pos, PlayerInventory inv) {
		super(ModTypes.ELECTRIC_SINTERING_FURNACE_CONTAINER, id, 3, world, pos);

		tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent((h) -> {
//			addSlot(new SlotItemHandler(h, 0, 81, 35));
			addSlot(new SlotItemHandler(h, ElectricSinteringFurnaceTile.INPUT1_ID, 56, 17));
			addSlot(new SlotItemHandler(h, ElectricSinteringFurnaceTile.INPUT2_ID, 56, 53));
//			addSlot(new SlotSinteringFurnaceOutput(h, SinteringFurnaceTile.OUTPUT_ID, 116, 35));
			addSlot(new SlotItemHandler(h, ElectricSinteringFurnaceTile.OUTPUT_ID, 116, 35));

		});

		layoutPlayerInventorySlots(new InvWrapper(inv), 8, 84);

		makeIntArray(3);
	}

	public int getCookTime() {
		return field.getField(ElectricSinteringFurnaceTile.COOKTIME_ID);
	}

	public int getEnergy() {
		return field.getField(ElectricSinteringFurnaceTile.ENERGY_ID);
	}

	public int getEnergyMax() {
		return field.getField(ElectricSinteringFurnaceTile.ENERGY_MAX_ID);
	}
}
