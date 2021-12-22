package com.sunflow.tutorialmod.block.copper_chest;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class CopperChestContainer extends AbstractContainerMenu {
	private final Container chestInventory;
	private final int containerRows;

	public CopperChestContainer(int id, Inventory inv, FriendlyByteBuf data) {
		this(id, inv, new SimpleContainer(CopperChestEntity.SIZE));
	}

	public CopperChestContainer(int id, Inventory playerInv, Container chestInv) {
		super(Registration.COPPER_CHEST_CONTAINER.get(), id);
		checkContainerSize(chestInv, CopperChestEntity.SIZE);
		this.chestInventory = chestInv;
		this.containerRows = CopperChestEntity.ROWS;
		chestInv.startOpen(playerInv.player);
		int w = 18;
		int yOff = (CopperChestEntity.ROWS - 4) * w - 1;

		for (int row = 0; row < CopperChestEntity.ROWS; ++row) {
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
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < CopperChestEntity.SIZE) {
				if (!this.moveItemStackTo(itemstack1, CopperChestEntity.SIZE, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 0, CopperChestEntity.SIZE, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}

		return itemstack;
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
		this.chestInventory.stopOpen(playerIn);
	}

	public Container getChestInventory() {
		return this.chestInventory;
	}

	@Override
	public boolean stillValid(Player player) {
		return this.chestInventory.stillValid(player);
	}
}