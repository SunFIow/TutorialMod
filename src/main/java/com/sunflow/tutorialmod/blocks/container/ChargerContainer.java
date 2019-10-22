package com.sunflow.tutorialmod.blocks.container;

import com.sunflow.tutorialmod.blocks.base.ContainerBase;
import com.sunflow.tutorialmod.blocks.tile.ChargerTile;
import com.sunflow.tutorialmod.init.ModTypes;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ChargerContainer extends ContainerBase {

	public ChargerContainer(int id, World world, BlockPos pos, PlayerInventory inv) {
		super(ModTypes.CHARGER_CONTAINER, id, 1, world, pos);

		tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent((h) -> {
			addSlot(new SlotItemHandler(h, ChargerTile.CHARGE_SLOT, 80, 35));
		});

		layoutPlayerInventorySlots(new InvWrapper(inv), 8, 84);

		makeIntArray(4);
	}

	public int getItemEnergy() {
		return field.getField(ChargerTile.ITEM_ENERGY_ID);
	}

	public int getItemEnergyMax() {
		return field.getField(ChargerTile.ITEM_ENERGY_MAX_ID);
	}

	public int getEnergy() {
		return field.getField(ChargerTile.ENERGY_ID);
	}

	public int getEnergyMax() {
		return field.getField(ChargerTile.ENERGY_MAX_ID);
	}
}
