package com.sunflow.tutorialmod.block.machine.charger;

import com.sunflow.tutorialmod.block.base.EnergyInvTileEntityBase;
import com.sunflow.tutorialmod.capability.CapabilityItemEnergy;
import com.sunflow.tutorialmod.capability.DefaultItemEnergy;
import com.sunflow.tutorialmod.capability.IItemEnergy;
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
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

public class ChargerTile extends EnergyInvTileEntityBase {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	public static final int CHARGE_SLOT = 0;

	protected DefaultItemEnergy itemEnergy = new DefaultItemEnergy(
			() -> EnergyUtils.readStorage(this.itemEnergy.getItemStack(), EnergyUnit.DEFAULT),
			() -> itemHandler.getStackInSlot(CHARGE_SLOT));
	private LazyOptional<IItemEnergy> energyItemOptional = LazyOptional.of(() -> itemEnergy);

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
			protected void onEnergyChanged() { markDirty(); }
		};
	}

	public ChargerTile() { super(Registration.CHARGER_TILE.get()); }

	@Override
	public void tick() {
		super.tick();
		if (world.isRemote) return;

		boolean isCharging = false;

		ItemStack chargeSlot = itemEnergy.getItemStack();
		if (!chargeSlot.isEmpty()) {
			CustomEnergyStorage itemEnergyStorage = itemEnergy.getStorage();
			if (energyStorage.getEnergyStored() > 0 && itemEnergyStorage.canReceive()) {
				int maxEExt = energyStorage.extractEnergy(TutorialModConfig.SERVER.CHARGER_CHARGE_RATE.get(), true);
				int eReceived = itemEnergyStorage.receiveEnergy(maxEExt, false);
				if (eReceived > 0) {
					energyStorage.extractEnergy(eReceived, false);
					markDirty();

					isCharging = true;
				}
				EnergyUtils.writeStorage(chargeSlot, EnergyUnit.DEFAULT, itemEnergyStorage);
			}
		} else markDirty();

//		if (!world.isRemote) {
		BlockState state = world.getBlockState(pos);
		if (state.get(POWERED) != isCharging) {
			world.setBlockState(pos, state.with(POWERED, isCharging), 3);
		}
//		}
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityItemEnergy.ENERGYITEM_CAPABILITY) {
			return energyItemOptional.cast();
		}
		return super.getCapability(cap, side);
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
