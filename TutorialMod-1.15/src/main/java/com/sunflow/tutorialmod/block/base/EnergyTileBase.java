package com.sunflow.tutorialmod.block.base;

import javax.annotation.Nullable;

import com.sunflow.tutorialmod.util.energy.CustomEnergyStorage;
import com.sunflow.tutorialmod.util.interfaces.ICustomNameable;

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

public abstract class EnergyTileBase extends TileEntity implements ICustomNameable, ITickableTileEntity {

	public static final int ENERGY_ID = 0, ENERGY_MAX_ID = 1;

	protected CustomEnergyStorage energyStorage = createEnergy();
	private LazyOptional<IEnergyStorage> energyStorageOptional = LazyOptional.of(() -> energyStorage);

	protected abstract CustomEnergyStorage createEnergy();

	private ITextComponent customName;

	public EnergyTileBase(TileEntityType<?> type) { super(type); }

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

	public float getFillLevel() {
		return energyStorage.getFillLevel();
	}

	@Override
	public void read(CompoundNBT tag) {
		energyStorage.deserializeNBT(tag.getCompound("energy"));
		super.read(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		tag.put("energy", energyStorage.serializeNBT());

		return super.write(tag);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityEnergy.ENERGY) {
			return energyStorageOptional.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void setCustomName(ITextComponent name) {
		this.customName = name;
	}

	@Override
	public ITextComponent getName() {
		return hasCustomName() ? this.customName : this.getDefaultName();
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
