package com.sunflow.tutorialmod.block.machine.firstblock;

import java.util.concurrent.atomic.AtomicBoolean;

import com.sunflow.tutorialmod.block.base.EnergyInvTileEntityBase;
import com.sunflow.tutorialmod.setup.ModTileEntitiyTypes;
import com.sunflow.tutorialmod.util.CustomEnergyStorage;
import com.sunflow.tutorialmod.util.config.Config;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.ItemStackHandler;

public class FirstBlockTile extends EnergyInvTileEntityBase {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	public static final Item EMPTY = ItemStack.EMPTY.getItem();
	public static final int FUEL_SLOT = 0;
	public static final int COOKTIME_ID = 2;

	@Override
	protected ItemStackHandler getHandler() {
		return new ItemStackHandler(1) {
			@Override
			public boolean isItemValid(int slot, ItemStack stack) {
				return stack.getItem() == Items.GLOWSTONE_DUST;
			}

			@Override
			public void onContentsChanged(int slot) {
				markDirty();
			}
		};
	}

	@Override
	protected CustomEnergyStorage getEnergy() {
		return new CustomEnergyStorage(Config.FIRSTBLOCK_MAXPOWER.get(), Config.FIRSTBLOCK_TRANSFER.get());
	}

	private Item currentFuel = ItemStack.EMPTY.getItem();

	private float cookTime;
	private float powerLeftOvers;

	public FirstBlockTile() {
		super(ModTileEntitiyTypes.FIRSTBLOCK_TILE);
	}

	@Override
	public boolean receiveClientEvent(int id, int type) {
		return super.receiveClientEvent(id, type);
//		return true;
	}

	@Override
	public void tick() {
//		System.out.println("h");
		super.tick();
		AtomicBoolean receivedEnergy = new AtomicBoolean(false);

		handler.ifPresent((handler) -> {
			energy.ifPresent((energy) -> {
				if (currentFuel != EMPTY) {
					int fuelValue = getFuelValue(currentFuel.getItem());
					int fuelTicks = getFuelTicks(currentFuel.getItem());
					int partialValue = fuelValue / fuelTicks;
					powerLeftOvers += (float) fuelValue / fuelTicks - partialValue;
					partialValue += Math.round(powerLeftOvers);
					powerLeftOvers -= Math.round(powerLeftOvers);
					if (energy.receiveEnergy(partialValue, true) > 0) {
						receivedEnergy.set(true);
						cookTime += energy.receiveEnergy(partialValue, false) / partialValue;
						if (cookTime >= fuelTicks) {
							currentFuel = EMPTY;
							cookTime = 0;
						}
						markDirty();
					}
				}
			});
			if (currentFuel == EMPTY) {
				ItemStack fuelSlot = handler.getStackInSlot(FUEL_SLOT);
				if (!fuelSlot.isEmpty() && isItemFuel(fuelSlot.getItem())) {
					currentFuel = fuelSlot.getItem();
					handler.extractItem(FUEL_SLOT, 1, false);
				}
			}
		});

		if (!world.isRemote) {
			BlockState state = world.getBlockState(pos);
			boolean generating = currentFuel != EMPTY && receivedEnergy.get();
			if (state.get(POWERED) != generating) {
				world.setBlockState(pos, state.with(POWERED, generating), 3);
			}
		}
		sendOutPower();
	}

	private void sendOutPower() {
		energy.ifPresent(energy -> {
			if (energy.getEnergyStored() > 0) {
				for (Direction dir : Direction.values()) {
					TileEntity tile = world.getTileEntity(pos.offset(dir));
					if (tile != null) {
						tile.getCapability(CapabilityEnergy.ENERGY, dir.getOpposite()).ifPresent(e -> {
							if (e.canReceive()) {
								int maxEExt = energy.extractEnergy(Config.FIRSTBLOCK_TRANSFER.get(), true);
								int eReceived = e.receiveEnergy(maxEExt, false);
//								if (eReceived > 0) {
								energy.extractEnergy(eReceived, false);
								tile.markDirty();
								this.markDirty();
//								}
							}
						});
					}
					if (energy.getEnergyStored() <= 0) {
						return;
					}
				}
			}
		});
	}

	private boolean isItemFuel(Item item) {
		return getFuelValue(item) > 0;
	}

	private int getFuelValue(Item item) {
		if (item == Items.GLOWSTONE_DUST)
			return Config.FIRSTBLOCK_GENERATE.get();
		else
			return 0;
	}

	private int getFuelTicks(Item item) {
		if (item == Items.GLOWSTONE_DUST)
			return Config.FIRSTBLOCK_TICKS.get();
		else
			return 0;
	}

	@Override
	public int getField(int id) {
		switch (id) {
			case COOKTIME_ID:
				return (int) cookTime;
		}
		return super.getField(id);
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
			case COOKTIME_ID:
				cookTime = value;
				break;
		}
		super.setField(id, value);
	}

	@Override
	public void read(CompoundNBT tag) {
		cookTime = tag.getFloat("cooktime");
		powerLeftOvers = tag.getFloat("powerleftovers");
		currentFuel = ItemStack.read(tag.getCompound("currentfuel")).getItem();

		super.read(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		tag.putFloat("cooktime", cookTime);
		tag.putFloat("powerleftovers", powerLeftOvers);
		tag.put("currentfuel", new ItemStack(currentFuel).write(new CompoundNBT()));

		return super.write(tag);
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
//		return new FirstBlockContainer(id, world, pos, inv);
		return new FirstBlockContainer(id, inv, this);
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.firstblock");
	}
}
