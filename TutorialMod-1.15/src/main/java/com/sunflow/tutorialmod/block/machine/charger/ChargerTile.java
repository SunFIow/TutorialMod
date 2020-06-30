package com.sunflow.tutorialmod.block.machine.charger;

import com.sunflow.tutorialmod.block.base.EnergyInvTileEntityBase;
import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.util.energy.CustomEnergyStorage;
import com.sunflow.tutorialmod.util.energy.EnergyUtils;
import com.sunflow.tutorialmod.util.energy.EnergyUtils.EnergyUnit;
import com.sunflow.tutorialmod.util.interfaces.IEnergyItem;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.items.ItemStackHandler;

public class ChargerTile extends EnergyInvTileEntityBase {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	public static final int CHARGE_SLOT = 0;
	public static final int ITEM_ENERGY_ID = 2, ITEM_ENERGY_MAX_ID = 3;

	@Override
	protected ItemStackHandler createHandler() {
		return new ItemStackHandler(1) {
			@Override
			public boolean isItemValid(int slot, ItemStack stack) { return stack.getItem() instanceof IEnergyItem; }

			@Override
			public void onContentsChanged(int slot) { markDirty(); }
		};
	}

	@Override
	protected CustomEnergyStorage createEnergy() {
		return new CustomEnergyStorage(TutorialModConfig.CHARGER_MAXPOWER.get(), TutorialModConfig.CHARGER_RECEIVE.get(), TutorialModConfig.CHARGER_CHARGE_RATE.get()) {
			@Override
			protected void onEnergyChanged() {
				markDirty();
			}
		};
	}

	public ChargerTile() { super(Registration.CHARGER_TILE.get()); }

	@Override
	public void tick() {
		super.tick();
		if (world.isRemote) return;

		boolean isCharging = false;

		if (energyStorage.getEnergyStored() > 0) {
			ItemStack chargeSlot = itemHandler.getStackInSlot(CHARGE_SLOT);
			if (!chargeSlot.isEmpty()) {
				CustomEnergyStorage itemEnergy = EnergyUtils.readStorage(chargeSlot, EnergyUnit.DEFAULT);
				if (itemEnergy.canReceive()) {
					int maxEExt = energyStorage.extractEnergy(TutorialModConfig.CHARGER_CHARGE_RATE.get(), true);
					int eReceived = itemEnergy.receiveEnergy(maxEExt, false);
					if (eReceived > 0) {
						energyStorage.extractEnergy(eReceived, false);
						markDirty();

						isCharging = true;
					}
					EnergyUtils.writeStorage(chargeSlot, EnergyUnit.DEFAULT, itemEnergy);
				}
			}
		}

//		if (!world.isRemote) {
		BlockState state = world.getBlockState(pos);
		if (state.get(POWERED) != isCharging) {
			world.setBlockState(pos, state.with(POWERED, isCharging), 3);
		}
//		}
	}

	@Override
	public int getField(int id) {
		switch (id) {
			case ITEM_ENERGY_ID:
				return getChargeItemEnergy().getEnergyStored();
			case ITEM_ENERGY_MAX_ID:
				return getChargeItemEnergy().getMaxEnergyStored();
		}
		return super.getField(id);
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
			case ITEM_ENERGY_ID:
				getChargeItemEnergy().setEnergy(value);
				break;
			case ITEM_ENERGY_MAX_ID:
				getChargeItemEnergy().setMaxEnergy(value);
				break;
		}
		super.setField(id, value);
	}

	private CustomEnergyStorage getChargeItemEnergy() {
//		if (!handler.isPresent())
//			throw new RuntimeException("Item Handler of " + this + " is not present");

//		return CustomEnergyStorage.fromNBT(handler.orElse(null)
//				.getStackInSlot(CHARGE_SLOT)
//				.getOrCreateChildTag(TutorialMod.MODID)
//				.getCompound(EnergyUnit.DEFAULT.name));
		ItemStack stack = itemHandler.getStackInSlot(CHARGE_SLOT);
		return EnergyUtils.readStorage(stack, EnergyUnit.DEFAULT);
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new ChargerContainer(id, inv, this);
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.charger");
	}
}
