package com.sunflow.tutorialmod.block.base;

import javax.annotation.Nullable;

import com.sunflow.tutorialmod.util.CustomEnergyStorage;
import com.sunflow.tutorialmod.util.interfaces.ICustomNameable;
import com.sunflow.tutorialmod.util.interfaces.IHasField;

import net.minecraft.block.BlockState;
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

	protected CustomEnergyStorage energyStorage = createEnergy();
	private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

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
				return energyStorage != null ? energyStorage.getEnergyStored() : -1;
			case ENERGY_MAX_ID:
				return energyStorage != null ? energyStorage.getMaxEnergyStored() : -1;
			default:
				return -1;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
			case ENERGY_ID:
				energyStorage.setEnergy(value);
				break;
			case ENERGY_MAX_ID:
				energyStorage.setMaxEnergy(value);
				break;
		}
	}

	public float getFillLevel() {
		return energyStorage.getFillLevel();
	}

	@Override
	public void func_230337_a_(BlockState state, CompoundNBT tag) {
		energyStorage.deserializeNBT(tag.getCompound("energy"));
		super.func_230337_a_(state, tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		tag.put("energy", energyStorage.serializeNBT());

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
