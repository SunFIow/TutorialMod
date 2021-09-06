package com.sunflow.tutorialmod.blocks.generator;

import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.tools.CustomEnergyStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class GeneratorContainer extends AbstractContainerMenu {

	private BlockEntity blockEntity;
	private Player playerEntity;
	private IItemHandler playerInventory;

	public GeneratorContainer(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
		super(Registration.GENERATOR_CONTAINER.get(), windowId);
		this.blockEntity = world.getBlockEntity(pos);
		this.playerEntity = player;
		this.playerInventory = new InvWrapper(playerInventory);

		if (blockEntity != null) {
			blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
				addSlot(new SlotItemHandler(handler, 0, 64, 24));
			});
		}
		layoutPlayerInventorySlots(10, 70);
		trackPower();
	}

	// Setup syncing of power from server to clinet so that the GUI can show tjhe amount of pwoer in the block
	private void trackPower() {
		// Unfortunally on a dedicated server ints are actually truncated to short so we need
		// to split out integer here (split our 32 bit into two 16 bit integers
		addDataSlot(new DataSlot() {
			@Override
			public void set(int amt) {
				blockEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(handler -> {
					int energyStored = handler.getEnergyStored() & 0xffff0000;
					((CustomEnergyStorage) handler).setEnergy(energyStored + (amt & 0xffff));
				});
			}

			@Override
			public int get() { return getEnergy() & 0xffff; }
		});
		addDataSlot(new DataSlot() {
			@Override
			public void set(int amt) {
				blockEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(handler -> {
					int energyStored = handler.getEnergyStored() & 0x0000ffff;
					((CustomEnergyStorage) handler).setEnergy(energyStored | (amt << 16));
				});
			}

			@Override
			public int get() { return (getEnergy() >> 16) & 0xffff; }
		});
	}

	public int getEnergy() {
		return blockEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(-1);
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), playerEntity, Registration.GENERATOR_BLOCK.get());
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack stack = slot.getItem();
			itemstack = stack.copy();
			if (index == 0) {
				if (!this.moveItemStackTo(stack, 1, 37, false)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (stack.getItem() == Items.DIAMOND) {
					if (!this.moveItemStackTo(stack, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index < 28) {
					if (!this.moveItemStackTo(stack, 28, 37, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index < 37) {
					if (!this.moveItemStackTo(stack, 1, 28, false)) {
						return ItemStack.EMPTY;
					}
				}
			}

			if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
			else slot.setChanged();

			if (stack.getCount() == itemstack.getCount()) return ItemStack.EMPTY;

			slot.onTake(player, stack);
		}
		return itemstack;
	}

	private void layoutPlayerInventorySlots(int leftCol, int topRow) {
		// Player Inventory
		addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);
		// Player Hotbar
		topRow += 58;
		addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
	}

	private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
		for (int i = 0; i < verAmount; i++, y += dy)
			index = addSlotRange(handler, index, x, y, horAmount, dx);

		return index;
	}

	private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
		for (int i = 0; i < amount; i++, index++, x += dx)
			addSlot(new SlotItemHandler(handler, index, x, y));
		return index;
	}

}
