package com.sunflow.tutorialmod.block.base;

import javax.annotation.Nullable;

import com.sunflow.tutorialmod.util.interfaces.ICustomNameable;
import com.sunflow.tutorialmod.util.interfaces.IHasField;

import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class InventoryTileEntityBase extends TileEntity implements INamedContainerProvider, ICustomNameable, IHasField {

	protected LazyOptional<ItemStackHandler> handler = LazyOptional.of(this::getHandler);

	protected abstract ItemStackHandler getHandler();

	private ITextComponent customName;

	public InventoryTileEntityBase(TileEntityType<?> type) {
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
