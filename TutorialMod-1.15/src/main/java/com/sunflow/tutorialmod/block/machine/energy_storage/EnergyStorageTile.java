package com.sunflow.tutorialmod.block.machine.energy_storage;

import com.sunflow.tutorialmod.block.base.EnergyTileEntityBase;
import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.setup.registration.Registration;
import com.sunflow.tutorialmod.util.CustomEnergyStorage;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class EnergyStorageTile extends EnergyTileEntityBase {

	@Override
	protected CustomEnergyStorage createEnergy() {
		return new CustomEnergyStorage(TutorialModConfig.GLOWSTONE_GENERATOR_MAXPOWER.get(), TutorialModConfig.GLOWSTONE_GENERATOR_TRANSFER.get());
	}

	public EnergyStorageTile() {
		super(Registration.ENERGY_STORAGE_TILE.get());
	}

	@Override
	public void tick() {
		super.tick();
		if (world != null && !world.isRemote && world.getGameTime() % 20L == 0L) {
			boolean powered = getFillLevel() > 0;
			if (getBlockState().get(EnergyStorageBlock.POWERED) != powered) {
				world.setBlockState(pos, getBlockState().with(EnergyStorageBlock.POWERED, powered), 3);
			}
		}
	}

	@Override
	public Container createMenu(int i, PlayerInventory inv, PlayerEntity player) {
		return new EnergyStorageContainer(i, inv, this);
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.energy_storage");
	}
}
