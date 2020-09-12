package com.sunflow.tutorialmod.block.base;

import javax.annotation.Nullable;

import com.sunflow.tutorialmod.util.interfaces.ICustomNameable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class InventoryTileBase extends TileEntity implements ICustomNameable {

	protected ItemStackHandler itemHandler = createHandler();
	private LazyOptional<IItemHandler> itemHandlerOptional = LazyOptional.of(() -> itemHandler);

	protected abstract ItemStackHandler createHandler();

	private ITextComponent customName;

	public InventoryTileBase(TileEntityType<?> type) { super(type); }

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
