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
		return new CustomEnergyStorage(TutorialModConfig.SERVER.CHARGER_MAXPOWER.get(), TutorialModConfig.SERVER.CHARGER_RECEIVE.get(), TutorialModConfig.SERVER.CHARGER_CHARGE_RATE.get()) {
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
					int maxEExt = energyStorage.extractEnergy(TutorialModConfig.SERVER.CHARGER_CHARGE_RATE.get(), true);
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
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new ChargerContainer(id, inv, this);
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.charger");
	}
}
