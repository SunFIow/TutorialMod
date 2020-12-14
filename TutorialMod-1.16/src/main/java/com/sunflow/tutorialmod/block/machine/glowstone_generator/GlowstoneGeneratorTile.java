package com.sunflow.tutorialmod.block.machine.glowstone_generator;

import com.sunflow.tutorialmod.block.base.EnergyInvTileEntityBase;
import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.util.CustomEnergyStorage;

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
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.ItemStackHandler;

public class GlowstoneGeneratorTile extends EnergyInvTileEntityBase {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	public static final Item EMPTY = ItemStack.EMPTY.getItem();
	public static final int FUEL_SLOT = 0;
	public static final int COOKTIME_ID = 2;
	private static final Item FUEL_ITEM = Items.GLOWSTONE_DUST;

	@Override
	protected ItemStackHandler createHandler() {
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
	protected CustomEnergyStorage createEnergy() {
		return new CustomEnergyStorage(TutorialModConfig.GLOWSTONE_GENERATOR_MAXPOWER.get(), TutorialModConfig.GLOWSTONE_GENERATOR_TRANSFER.get()) {
			@Override
			protected void onEnergyChanged() {
				markDirty();
			}
		};
	}

	private Item currentFuel = ItemStack.EMPTY.getItem();

	private float cookTime;
	private float powerLeftOvers;

	public GlowstoneGeneratorTile() {
		super(Registration.GLOWSTONE_GENERATOR_TILE.get());
	}

	@Override
	public boolean receiveClientEvent(int id, int type) {
		return super.receiveClientEvent(id, type);
//		return true;
	}

	@Override
	public void tick() {
		super.tick();
		if (world.isRemote) return;

		boolean producedEnergy = false;

		if (currentFuel != EMPTY) {
			int fuelValue = getFuelValue(currentFuel.getItem());
			int fuelTicks = getFuelTicks(currentFuel.getItem());
			int partialValue = fuelValue / fuelTicks;
			powerLeftOvers += (float) fuelValue / fuelTicks - partialValue;
			partialValue += Math.round(powerLeftOvers);
			powerLeftOvers -= Math.round(powerLeftOvers);

			if (energyStorage.receiveEnergy(partialValue, true) > 0) {
				producedEnergy = true;
				cookTime += energyStorage.receiveEnergy(partialValue, false) / partialValue;

				if (cookTime >= fuelTicks) {
					currentFuel = EMPTY;
					cookTime = 0;
				}
				markDirty();
			}
		}
		if (currentFuel == EMPTY) {
			ItemStack fuelSlot = itemHandler.getStackInSlot(FUEL_SLOT);
			if (!fuelSlot.isEmpty() && isItemFuel(fuelSlot.getItem())) {
				currentFuel = fuelSlot.getItem();
				itemHandler.extractItem(FUEL_SLOT, 1, false);
			}
		}

		BlockState state = world.getBlockState(pos);
		if (state.get(POWERED) != producedEnergy) {
			world.setBlockState(pos, state.with(POWERED, producedEnergy),
					Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
		}

		sendOutPower();
	}

	private void sendOutPower() {
		if (energyStorage.getEnergyStored() > 0) {
			for (Direction dir : Direction.values()) {
				TileEntity tileentity = world.getTileEntity(pos.offset(dir));
				if (tileentity != null) {
					tileentity.getCapability(CapabilityEnergy.ENERGY, dir.getOpposite()).ifPresent(e -> {
						if (e.canReceive()) {
//							int maxEExt = energyHandler.extractEnergy(TutorialModConfig.GLOWSTONE_GENERATOR_TRANSFER.get(), true);
//							int eReceived = e.receiveEnergy(maxEExt, false);
//							energyHandler.extractEnergy(eReceived, false);							
//							tileentity.markDirty();

							int maxExtracted = Math.min(energyStorage.getEnergyStored(), TutorialModConfig.GLOWSTONE_GENERATOR_TRANSFER.get());
							int received = e.receiveEnergy(maxExtracted, false);
							energyStorage.extractEnergy(received, false);

							this.markDirty();
						}
					});
				}
				if (energyStorage.getEnergyStored() <= 0) {
					return;
				}
			}
		}
	}

	private boolean isItemFuel(Item item) { return getFuelValue(item) > 0; }

	private int getFuelValue(Item item) {
		if (item == FUEL_ITEM) return TutorialModConfig.GLOWSTONE_GENERATOR_GENERATE.get();
		else return 0;
	}

	private int getFuelTicks(Item item) {
		if (item == FUEL_ITEM) return TutorialModConfig.GLOWSTONE_GENERATOR_TICKS.get();
		else return 0;
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
	public void read(BlockState state, CompoundNBT tag) {
		cookTime = tag.getFloat("cooktime");
		powerLeftOvers = tag.getFloat("powerleftovers");
		currentFuel = ItemStack.read(tag.getCompound("currentfuel")).getItem();

		super.read(state, tag);
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
		return new GlowstoneGeneratorContainer(id, inv, this);
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.glowstone_generator");
	}
}
