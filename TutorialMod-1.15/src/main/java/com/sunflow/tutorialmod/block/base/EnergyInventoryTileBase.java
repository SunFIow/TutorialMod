package com.sunflow.tutorialmod.block.base;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class EnergyInventoryTileBase extends EnergyTileBase {

	protected ItemStackHandler itemHandler = createHandler();
	private LazyOptional<IItemHandler> itemHandlerOptional = LazyOptional.of(() -> itemHandler);

	protected abstract ItemStackHandler createHandler();

	public EnergyInventoryTileBase(TileEntityType<?> type) {
		super(type);
	}

	@Override
	public void read(CompoundNBT tag) {
		itemHandler.deserializeNBT(tag.getCompound("inv"));
		super.read(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		tag.put("inv", itemHandler.serializeNBT());

		return super.write(tag);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return itemHandlerOptional.cast();
		}
		return super.getCapability(cap, side);
	}
}