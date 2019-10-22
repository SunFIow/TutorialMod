package com.sunflow.tutorialmod.blocks.container;

import com.sunflow.tutorialmod.blocks.tile.CopperChestTile;
import com.sunflow.tutorialmod.init.ModTypes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class CopperChestContainer extends Container {
	private final IInventory chestInventory;

	public CopperChestContainer(int id, PlayerInventory playerInv, IInventory chestInv) {
		super(ModTypes.COPPER_CHEST_CONTAINER, id);
		assertInventorySize(chestInv, CopperChestTile.SIZE);
		this.chestInventory = chestInv;
		chestInv.openInventory(playerInv.player);
		int w = 18;
		int yOff = (CopperChestTile.ROWS - 4) * w - 1;

		for (int row = 0; row < CopperChestTile.ROWS; ++row) {
			for (int slotId = 0; slotId < 9; ++slotId) {
				this.addSlot(new Slot(chestInv, slotId + row * 9, 8 + slotId * w, w + row * w));
			}
		}

		for (int row = 0; row < 3; ++row) {
			for (int slotId = 0; slotId < 9; ++slotId) {
				this.addSlot(new Slot(playerInv, slotId + row * 9 + 9, 8 + slotId * w, 103 + row * w + yOff));
			}
		}

		for (int slotId = 0; slotId < 9; ++slotId) {
			this.addSlot(new Slot(playerInv, slotId, 8 + slotId * w, 161 + yOff));
		}
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return this.chestInventory.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < CopperChestTile.SIZE) {
				if (!this.mergeItemStack(itemstack1, CopperChestTile.SIZE, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, CopperChestTile.SIZE, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	@Override
	public void onContainerClosed(PlayerEntity playerIn) {
		super.onContainerClosed(playerIn);
		this.chestInventory.closeInventory(playerIn);
	}

	public IInventory getChestInventory() {
		return this.chestInventory;
	}
}