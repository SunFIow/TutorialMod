package com.sunflow.tutorialmod.block.base;

import javax.annotation.Nullable;

import com.sunflow.tutorialmod.util.CustomEnergyStorage;
import com.sunflow.tutorialmod.util.interfaces.ICustomNameable;
import com.sunflow.tutorialmod.util.interfaces.IHasField;

import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public abstract class EnergyTileEntityBase extends TileEntity implements INamedContainerProvider, ICustomNameable, IHasField, ITickableTileEntity {

	public static final int ENERGY_ID = 0, ENERGY_MAX_ID = 1;

	protected CustomEnergyStorage energyHandler = createEnergy();
	private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyHandler);

	protected abstract CustomEnergyStorage createEnergy();

	private ITextComponent customName;

	public EnergyTileEntityBase(TileEntityType<?> type) {
		super(type);
	}

	@Override
	public void tick() {
		if (world != null && !world.isRemote && world.getGameTime() % 20L == 0L) {
			int i = (int) (getFillLevel() * 15);
			i = MathHelper.clamp(i, 0, 15);
			if (getBlockState().get(EnergyTileBlockBase.FILLLEVEL) != i) {
				world.setBlockState(pos, getBlockState().with(EnergyTileBlockBase.FILLLEVEL, Integer.valueOf(i)), 3);
			}
		}
	}

	@Override
	public int getField(int id) {
		switch (id) {
			case ENERGY_ID:
				return energyHandler != null ? energyHandler.getEnergyStored() : -1;
			case ENERGY_MAX_ID:
				return energyHandler != null ? energyHandler.getMaxEnergyStored() : -1;
			default:
				return -1;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
			case ENERGY_ID:
				energyHandler.setEnergy(value);
				break;
			case ENERGY_MAX_ID:
				energyHandler.setMaxEnergy(value);
				break;
		}
	}

	public float getFillLevel() {
		return energyHandler.getFillLevel();
	}

	@Override
	public void read(CompoundNBT tag) {
		energyHandler.deserializeNBT(tag.getCompound("energy"));
		super.read(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		tag.put("energy", energyHandler.serializeNBT());

		return super.write(tag);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityEnergy.ENERGY) {
			return energy.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void setCustomName(ITextComponent name) {
		this.customName = name;
	}

	@Override
	public ITextComponent getName() {
		return this.customName != null ? this.customName : this.getDefaultName();
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.getName();
	}

	@Override
	@Nullable
	public ITextComponent getCustomName() {
		return this.customName;
	}

	protected abstract ITextComponent getDefaultName();
}
