package com.sunflow.tutorialmod.blocks.tile;

import com.sunflow.tutorialmod.blocks.EnergyStorageBlock;
import com.sunflow.tutorialmod.blocks.base.EnergyTileEntityBase;
import com.sunflow.tutorialmod.blocks.container.EnergyStorageContainer;
import com.sunflow.tutorialmod.init.ModTypes;
import com.sunflow.tutorialmod.util.Config;
import com.sunflow.tutorialmod.util.CustomEnergyStorage;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class EnergyStorageTile extends EnergyTileEntityBase {

	@Override
	protected CustomEnergyStorage getEnergy() {
		return new CustomEnergyStorage(Config.FIRSTBLOCK_MAXPOWER.get(), Config.FIRSTBLOCK_TRANSFER.get());
	}

	public EnergyStorageTile() {
		super(ModTypes.ENERGY_STORAGE_TILE);
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
		return new EnergyStorageContainer(i, world, pos, inv);
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.energy_storage");
	}
}
