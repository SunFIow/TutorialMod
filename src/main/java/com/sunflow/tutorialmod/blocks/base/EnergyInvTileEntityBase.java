package com.sunflow.tutorialmod.blocks.base;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class EnergyInvTileEntityBase extends EnergyTileEntityBase {

	protected LazyOptional<ItemStackHandler> handler = LazyOptional.of(this::getHandler);

	protected abstract ItemStackHandler getHandler();

	public EnergyInvTileEntityBase(TileEntityType<?> type) {
		super(type);
	}

	@Override
	public void read(CompoundNBT tag) {
		handler.ifPresent(h -> h.deserializeNBT(tag.getCompound("inv")));
		super.read(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		handler.ifPresent(h -> tag.put("inv", h.serializeNBT()));

		return super.write(tag);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return handler.cast();
		}
		return super.getCapability(cap, side);
	}
}