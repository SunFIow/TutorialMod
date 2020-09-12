package com.sunflow.tutorialmod.energy.block;

import java.util.ArrayList;

import com.sunflow.tutorialmod.block.base.EnergyTileBase;
import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.util.energy.CustomEnergyStorage;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class WireTile extends EnergyTileBase {

	@Override
	protected CustomEnergyStorage createEnergy() {
		return new CustomEnergyStorage(TutorialModConfig.SERVER.WIRE_MAXPOWER.get(), TutorialModConfig.SERVER.WIRE_TRANSFER.get(), TutorialModConfig.SERVER.WIRE_TRANSFER.get());
	}

	public WireTile() {
		super(Registration.WIRE_TILE.get());
	}

	@Override
	public void tick() {
		super.tick();

		if (world != null && !world.isRemote && world.getGameTime() % 20L == 0L) {
			boolean powered = getFillLevel() > 0;
			if (getBlockState().get(WireBlock.POWERED) != powered) {
				world.setBlockState(pos, getBlockState().with(WireBlock.POWERED, powered), 3);
			}
		}

//		if (EnergyUtils.receivePower(world, pos, energyStorage)) markDirty();
//		if (EnergyUtils.sendOutPower(world, pos, energyStorage)) markDirty();
		sendOutPower();
	}

	public void sendOutPower() {
		if (energyStorage.getEnergyStored() > 0) {
			boolean markDirty = false;
			int amount = 0;
//			ArrayList<IEnergyStorage> storages = new ArrayList<>();
			ArrayList<EnergyComp> storages = new ArrayList<>();
			for (Direction dir : Direction.values()) {
				TileEntity tileentity = world.getTileEntity(pos.offset(dir));
				if (tileentity == null) continue;

				LazyOptional<IEnergyStorage> cap = tileentity.getCapability(CapabilityEnergy.ENERGY, dir.getOpposite());
				IEnergyStorage s = cap.orElse(null);
				if (s == null || (tileentity instanceof WireTile && (energyStorage.getEnergyStored() <= s.getEnergyStored() || energyStorage.getEnergyStored() - s.getEnergyStored() <= 1))) continue;

//				Log.debug(tileentity);
//				Log.debug(s);
				storages.add(new EnergyComp(s, tileentity));
			}
			if (storages.isEmpty()) return;

//			Log.debug(storages.size() + ", " + storages);
			amount = energyStorage.getEnergyStored() / storages.size();
//			
			for (EnergyComp e : storages) if (e.storage.canReceive()) {
//					int maxEExt = energyHandler.extractEnergy(TutorialModConfig.GLOWSTONE_GENERATOR_TRANSFER.get(), true);
//					int eReceived = e.receiveEnergy(maxEExt, false);
//					energyHandler.extractEnergy(eReceived, false);							
//					tileentity.markDirty();

				int extract = energyStorage.extractEnergy(amount, true);
				if (extract == 0) continue;
				if (e.tileentity instanceof WireTile)
					extract = Math.min(extract, (energyStorage.getEnergyStored() - e.storage.getEnergyStored()) / 2);
				int received = e.storage.receiveEnergy(extract, false);
				energyStorage.extractEnergy(received, false);
				markDirty = true;

			}

			if (markDirty) markDirty();
		}

	}

	private static class EnergyComp {
		IEnergyStorage storage;
		TileEntity tileentity;

		public EnergyComp(IEnergyStorage storage, TileEntity tileentity) { this.storage = storage; this.tileentity = tileentity; }
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("tile.wire");
	}
}
