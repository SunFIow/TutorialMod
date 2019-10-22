package com.sunflow.tutorialmod.blocks.base;

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

public abstract class EnergyTileEntityBase extends TileEntity implements INamedContainerProvider, ICustomNameable, IHasField, ITickableTileEntity {

	public static final int ENERGY_ID = 0, ENERGY_MAX_ID = 1;

	protected LazyOptional<CustomEnergyStorage> energy = LazyOptional.of(this::getEnergy);

	protected abstract CustomEnergyStorage getEnergy();

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
		CustomEnergyStorage s;
		switch (id) {
			case ENERGY_ID:
				s = energy.orElse(null);
				return s != null ? s.getEnergyStored() : -1;
			case ENERGY_MAX_ID:
				s = energy.orElse(null);
				return s != null ? s.getMaxEnergyStored() : -1;
			default:
				return -1;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
			case ENERGY_ID:
				energy.ifPresent(e -> e.setEnergy(value));
				break;
			case ENERGY_MAX_ID:
				energy.ifPresent(e -> e.setMaxEnergy(value));
				break;
		}
	}

	public double getFillLevel() {
		if (!energy.isPresent())
			throw new RuntimeException("Energy Storage of " + this + " is not present");
		return energy.orElse(null).getFillLevel();
	}

	@Override
	public void read(CompoundNBT tag) {
		energy.ifPresent(e -> e.deserializeNBT(tag.getCompound("energy")));

		super.read(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		energy.ifPresent(e -> tag.put("energy", e.serializeNBT()));

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
