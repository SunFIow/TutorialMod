package com.sunflow.tutorialmod.block.base;

import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.sunflow.tutorialmod.block.machine.charger.ChargerTile;
import com.sunflow.tutorialmod.capability.CapabilityProcessor;
import com.sunflow.tutorialmod.capability.IProcessor;
import com.sunflow.tutorialmod.util.energy.CustomEnergyStorage;
import com.sunflow.tutorialmod.util.energy.EnergyUtils;
import com.sunflow.tutorialmod.util.energy.EnergyUtils.EnergyUnit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public abstract class ContainerBase extends Container {

	protected final TileEntity tileEntity;
	protected final int containerSlotCount;
	private int dragType;

	public ContainerBase(ContainerType<?> type, int windowId, int containerSlotCount, TileEntity tileentity) {
		super(type, windowId);
		this.containerSlotCount = containerSlotCount;
		this.tileEntity = tileentity;
	}

	// Setup syncing of energy from server to client so that the GUI can show the amount of energy in the block
	protected void track(Supplier<Integer> getter, Consumer<Integer> setter) {
		// Unfortunatelly on a dedicated server ints are actually truncated to short so we need
		// to split our integer here (split our 32 bit integer into two 16 bit integers)
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return getter.get() & 0xffff;
			}

			@Override
			public void set(int value) {
				int energyStored = getter.get() & 0xffff0000;
				setter.accept(energyStored + (value & 0xffff));
			}
		});
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return (getter.get() >> 16) & 0xffff;
			}

			@Override
			public void set(int value) {
				int energyStored = getter.get() & 0x0000ffff;
				setter.accept(energyStored | (value << 16));
			}
		});
	}

	// Setup syncing of energy from server to client so that the GUI can show the amount of energy in the block
	protected void trackEnergy() {
		// Unfortunatelly on a dedicated server ints are actually truncated to short so we need
		// to split our integer here (split our 32 bit integer into two 16 bit integers)
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return getEnergy() & 0xffff;
			}

			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> {
					int energyStored = h.getEnergyStored() & 0xffff0000;
					((CustomEnergyStorage) h).setEnergy(energyStored + (value & 0xffff));
				});
			}
		});
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return (getEnergy() >> 16) & 0xffff;
			}

			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> {
					int energyStored = h.getEnergyStored() & 0x0000ffff;
					((CustomEnergyStorage) h).setEnergy(energyStored | (value << 16));
				});
			}
		});
	}

	// Setup syncing of energy from server to client so that the GUI can show the amount of energy in the block
	protected void trackEnergyMax() {
		// Unfortunatelly on a dedicated server ints are actually truncated to short so we need
		// to split our integer here (split our 32 bit integer into two 16 bit integers)
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return getEnergyMax() & 0xffff;
			}

			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> {
					int maxEnergyStored = h.getMaxEnergyStored() & 0xffff0000;
					((CustomEnergyStorage) h).setMaxEnergy(maxEnergyStored + (value & 0xffff));
				});
			}
		});
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return (getEnergyMax() >> 16) & 0xffff;
			}

			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> {
					int maxEnergyStored = h.getMaxEnergyStored() & 0x0000ffff;
					((CustomEnergyStorage) h).setMaxEnergy(maxEnergyStored | (value << 16));
				});
			}
		});
	}

	// Setup syncing of CookTime from server to client so that the GUI can show the amount of CookTime in the block
	protected void trackCookTime() {
		// Unfortunatelly on a dedicated server ints are actually truncated to short so we need
		// to split our integer here (split our 32 bit integer into two 16 bit integers)
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return getCookTime() & 0xffff;
			}

			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityProcessor.COOKER_CAPABILITY).ifPresent(h -> {
					int cookTime = h.getTime() & 0xffff0000;
					h.setTime(cookTime + (value & 0xffff));
				});
			}
		});
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return (getCookTime() >> 16) & 0xffff;
			}

			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityProcessor.COOKER_CAPABILITY).ifPresent(h -> {
					int totalCookTime = h.getTime() & 0x0000ffff;
					h.setTime(totalCookTime | (value << 16));
				});
			}
		});
	}

	// Setup syncing of CookTime from server to client so that the GUI can show the amount of CookTime in the block
	protected void trackCookTimeTotal() {
		// Unfortunatelly on a dedicated server ints are actually truncated to short so we need
		// to split our integer here (split our 32 bit integer into two 16 bit integers)
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return getCookTimeTotal() & 0xffff;
			}

			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityProcessor.COOKER_CAPABILITY).ifPresent(h -> {
					int cookTime = h.getTotalTime() & 0xffff0000;
					h.setTotalTime(cookTime + (value & 0xffff));
				});
			}
		});
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return (getCookTimeTotal() >> 16) & 0xffff;
			}

			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityProcessor.COOKER_CAPABILITY).ifPresent(h -> {
					int totalCookTime = h.getTotalTime() & 0x0000ffff;
					h.setTotalTime(totalCookTime | (value << 16));
				});
			}
		});
	}

	// Setup syncing of BurnTime from server to client so that the GUI can show the amount of BurnTime in the block
	protected void trackBurnTime() {
		// Unfortunatelly on a dedicated server ints are actually truncated to short so we need
		// to split our integer here (split our 32 bit integer into two 16 bit integers)
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return getBurnTime() & 0xffff;
			}

			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityProcessor.BURNER_CAPABILITY).ifPresent(h -> {
					int burnTime = h.getTime() & 0xffff0000;
					h.setTime(burnTime + (value & 0xffff));
				});
			}
		});
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return (getBurnTime() >> 16) & 0xffff;
			}

			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityProcessor.BURNER_CAPABILITY).ifPresent(h -> {
					int burnTime = h.getTime() & 0x0000ffff;
					h.setTime(burnTime | (value << 16));
				});
			}
		});
	}

	// Setup syncing of BurnTime from server to client so that the GUI can show the amount of BurnTime in the block
	protected void trackBurnTimeTotal() {
		// Unfortunatelly on a dedicated server ints are actually truncated to short so we need
		// to split our integer here (split our 32 bit integer into two 16 bit integers)
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return getBurnTimeTotal() & 0xffff;
			}

			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityProcessor.BURNER_CAPABILITY).ifPresent(h -> {
					int totalBurnTime = h.getTotalTime() & 0xffff0000;
					h.setTotalTime(totalBurnTime + (value & 0xffff));
				});
			}
		});
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return (getBurnTimeTotal() >> 16) & 0xffff;
			}

			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityProcessor.BURNER_CAPABILITY).ifPresent(h -> {
					int totalBurnTime = h.getTotalTime() & 0x0000ffff;
					h.setTotalTime(totalBurnTime | (value << 16));
				});
			}
		});
	}

	// Setup syncing of item energy from server to client so that the GUI can show the amount of item energy in the block
	protected void trackItemEnergy() {
		// Unfortunatelly on a dedicated server ints are actually truncated to short so we need
		// to split our integer here (split our 32 bit integer into two 16 bit integers)
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return getItemEnergy() & 0xffff;
			}

			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
					CustomEnergyStorage energy = EnergyUtils.readStorage(h.getStackInSlot(ChargerTile.CHARGE_SLOT), EnergyUnit.DEFAULT);
					int energyStored = energy.getEnergyStored() & 0xffff0000;
					energy.setEnergy(energyStored + (value & 0xffff));
				});
			}
		});
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return (getItemEnergy() >> 16) & 0xffff;
			}

			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
					CustomEnergyStorage energy = EnergyUtils.readStorage(h.getStackInSlot(ChargerTile.CHARGE_SLOT), EnergyUnit.DEFAULT);
					int energyStored = energy.getEnergyStored() & 0x0000ffff;
					energy.setEnergy(energyStored | (value << 16));
				});
			}
		});
	}

	// Setup syncing of item energy from server to client so that the GUI can show the amount of item energy in the block
	protected void trackItemEnergyMax() {
		// Unfortunatelly on a dedicated server ints are actually truncated to short so we need
		// to split our integer here (split our 32 bit integer into two 16 bit integers)
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return getItemEnergyMax() & 0xffff;
			}

			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
					CustomEnergyStorage energy = EnergyUtils.readStorage(h.getStackInSlot(ChargerTile.CHARGE_SLOT), EnergyUnit.DEFAULT);
					int maxEnergyStored = energy.getMaxEnergyStored() & 0xffff0000;
					energy.setMaxEnergy(maxEnergyStored + (value & 0xffff));
				});
			}
		});
		trackInt(new IntReferenceHolder() {
			@Override
			public int get() {
				return (getItemEnergyMax() >> 16) & 0xffff;
			}

			@Override
			public void set(int value) {
				tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
					CustomEnergyStorage energy = EnergyUtils.readStorage(h.getStackInSlot(ChargerTile.CHARGE_SLOT), EnergyUnit.DEFAULT);
					int maxEnergyStored = energy.getMaxEnergyStored() & 0x0000ffff;
					energy.setMaxEnergy(maxEnergyStored | (value << 16));
				});
			}
		});
	}

	public int getEnergy() {
		return tileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(-1);
	}

	public int getEnergyMax() {
		return tileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getMaxEnergyStored).orElse(-1);
	}

	public int getCookTime() {
		return tileEntity.getCapability(CapabilityProcessor.COOKER_CAPABILITY).map(IProcessor::getTime).orElse(-1);
	}

	public int getCookTimeTotal() {
		return tileEntity.getCapability(CapabilityProcessor.COOKER_CAPABILITY).map(IProcessor::getTotalTime).orElse(-1);
	}

	public int getBurnTime() {
		return tileEntity.getCapability(CapabilityProcessor.BURNER_CAPABILITY).map(IProcessor::getTime).orElse(-1);
	}

	public int getBurnTimeTotal() {
		return tileEntity.getCapability(CapabilityProcessor.BURNER_CAPABILITY).map(IProcessor::getTotalTime).orElse(-1);
	}

	public int getItemEnergy() {
		return EnergyUtils.getEnergyStored(
				tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).map(
						h -> h.getStackInSlot(ChargerTile.CHARGE_SLOT))
						.orElse(null),
				EnergyUnit.DEFAULT);
	}

	public int getItemEnergyMax() {

		return EnergyUtils.getCapacity(
				tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).map(
						h -> h.getStackInSlot(ChargerTile.CHARGE_SLOT))
						.orElse(null),
				EnergyUnit.DEFAULT);
	}

	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), player, tileEntity.getBlockState().getBlock());
	}

	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickType, PlayerEntity player) {
		this.dragType = dragType;
		return super.slotClick(slotId, dragType, clickType, player);
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack stack = slot.getStack();
			itemstack = stack.copy();
			if (index < containerSlotCount) {
				if (ItemStack.areItemsEqualIgnoreDurability(stack, player.inventory.getItemStack())) {
					if (dragType == 0) {
						// Swap all items from type == stack.item from this inventory to player
						// inventory
						LinkedList<Slot> slots = getAllItemStacksOf(stack, 0, containerSlotCount);
						for (Slot s : slots) {
							ItemStack newStack = s.getStack();
							ItemStack newStackCopy = newStack.copy();
							if (!this.mergeItemStack(newStack, containerSlotCount, containerSlotCount + 9 * 4, true)) {
								return ItemStack.EMPTY;
							}
							slot.onSlotChange(newStack, newStackCopy);
						}
						return ItemStack.EMPTY;

					} else if (dragType == 1) {
						// Swap all items from type == stack.item from player inventory to this
						// inventory
						LinkedList<Slot> slots = getAllItemStacksOf(stack, containerSlotCount,
								containerSlotCount + 9 * 4);

						for (Slot s : slots) {
							ItemStack newStack = s.getStack();
							ItemStack newStackCopy = newStack.copy();
							if (!this.mergeItemStack(newStack, 0, containerSlotCount, false)) {
								return ItemStack.EMPTY;
							}
							slot.onSlotChange(newStack, newStackCopy);
						}
						return ItemStack.EMPTY;
					}
				}
				if (!this.mergeItemStack(stack, containerSlotCount, containerSlotCount + 9 * 4, true)) {
					return ItemStack.EMPTY;
				}
				slot.onSlotChange(stack, itemstack);
			} else {
				if (!this.mergeItemStack(stack, 0, containerSlotCount, false)) {
					if (index < containerSlotCount + 9 * 3) {
						if (!this.mergeItemStack(stack, containerSlotCount + 9 * 3, containerSlotCount + 9 * 4,
								false)) {
							return ItemStack.EMPTY;
						}
					} else if (index < containerSlotCount + 9 * 4
							&& !this.mergeItemStack(stack, containerSlotCount, containerSlotCount + 9 * 3, false)) {
								return ItemStack.EMPTY;
							}
				}
			}

			if (stack.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (stack.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, stack);
		}

		return itemstack;
		// return super.transferStackInSlot(playerIn, index);
	}

	private LinkedList<Slot> getAllItemStacksOf(ItemStack compareStack, int startIndex, int endIndex) {
		LinkedList<Slot> returnList = new LinkedList<>();
		for (int index = startIndex; index < endIndex; index++) {
			// ItemStack slotItemStack = ItemStack.EMPTY;
			Slot slot = this.inventorySlots.get(index);
			// if (slot != null && slot.getHasStack()) {
			// ItemStack stack = slot.getStack();
			// slotItemStack = stack.copy();
			// }
			if (ItemStack.areItemsEqualIgnoreDurability(slot.getStack(), compareStack)) {
				returnList.add(slot);
			}
		}

		return returnList;
	}

	protected int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
		for (int i = 0; i < amount; i++) {
			addSlot(new SlotItemHandler(handler, index, x, y));
			x += dx;
			index++;
		}
		return index;
	}

	protected int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount,
			int dy) {
		for (int j = 0; j < verAmount; j++) {
			index = addSlotRange(handler, index, x, y, horAmount, dx);
			y += dy;
		}
		return index;
	}

	protected void layoutPlayerInventorySlots(IItemHandler handler, int leftCol, int topRow) {
		// Player inventory
		addSlotBox(handler, 9, leftCol, topRow, 9, 18, 3, 18);

		// Hotbar
		topRow += 58;
		addSlotRange(handler, 0, leftCol, topRow, 9, 18);
	}

	public void mouseScrolled(double mouseX, double mouseY, double scrollDir, int guiLeft, int guiTop) {}
}
