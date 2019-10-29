package com.sunflow.tutorialmod.blocks.container;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.blocks.base.ContainerBase;
import com.sunflow.tutorialmod.blocks.tile.FirstBlockTile;
import com.sunflow.tutorialmod.init.ModContainerTypes;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class FirstBlockContainer extends ContainerBase {

	public FirstBlockContainer(int windowId, PlayerInventory inv, PacketBuffer data) {
		this(windowId, TutorialMod.proxy.getClientWorld(), data.readBlockPos(), inv);
	}

	public FirstBlockContainer(int id, World world, BlockPos pos, PlayerInventory inv) {
		super(ModContainerTypes.FIRSTBLOCK_CONTAINER, id, 1, world, pos);

		tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent((h) -> {
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
