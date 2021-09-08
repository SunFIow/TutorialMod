package com.sunflow.tutorialmod.blocks.generator;

import java.util.concurrent.atomic.AtomicInteger;

import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.tools.CustomEnergyStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class GeneratorBE extends BlockEntity {

	private final ItemStackHandler itemStorage = createHandler();

	private final CustomEnergyStorage energyStorage = createEnergy();

	// Never create LazyOptionals in getCapability. Always place them as fields in the tile entity
	private final LazyOptional<IItemHandler> items = LazyOptional.of(() -> itemStorage);
	private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

	private int counter;

	public GeneratorBE(BlockPos pos, BlockState state) { super(Registration.GENERATOR_BE.get(), pos, state); }

	@Override
	public void setRemoved() {
		super.setRemoved();
		// Don't forget to invalidate your caps when block entity is removed
		items.invalidate();
		energy.invalidate();
	}

	public void tickServer() {
		if (counter > 0) {
			counter--;
			energyStorage.addEnergy(50);
			setChanged();
		}

		if (counter <= 0) {
			ItemStack stack = itemStorage.getStackInSlot(0);
			int burnTime = ForgeHooks.getBurnTime(stack, RecipeType.SMELTING);
			itemStorage.extractItem(0, 1, false);
			counter = burnTime;
			setChanged();
		}

		BlockState state = level.getBlockState(worldPosition);
		if (state.getValue(BlockStateProperties.POWERED) != counter > 0) {
			level.setBlock(worldPosition, state.setValue(BlockStateProperties.POWERED, counter > 0), Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
		}

		sendOutPower();
	}

	private void sendOutPower() {
		AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
		if (capacity.get() > 0) {
			for (Direction dir : Direction.values()) {
				BlockEntity be = level.getBlockEntity(worldPosition.relative(dir));
				if (be == null) continue;
				boolean noEnergy = be.getCapability(CapabilityEnergy.ENERGY, dir).map(storage -> {
					if (storage.canReceive()) {
						int recieved = storage.receiveEnergy(capacity.get(), false);
						capacity.addAndGet(-recieved);
						energyStorage.consumeEnergy(recieved);
						setChanged();
						return capacity.get() <= 0;
					} else return false;
				}).orElse(false);
				if (noEnergy) return;
			}
		}
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return items.cast();
		if (cap == CapabilityEnergy.ENERGY)
			return energy.cast();
		return super.getCapability(cap, side);
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		CompoundTag tm = tag.getCompound("tutorialmod");
		itemStorage.deserializeNBT(tm.getCompound("inv"));
		energyStorage.deserializeNBT(tm.getCompound("energy"));
		counter = tm.getInt("counter");
	}

	@Override
	public CompoundTag save(CompoundTag tag) {
		super.save(tag);
		CompoundTag tm = tag.getCompound("tutorialmod");
		tm.put("inv", itemStorage.serializeNBT());
		tm.put("energy", energyStorage.serializeNBT());
		tm.putInt("counter", counter);
		return tag;
	}

	private final ItemStackHandler createHandler() {
		return new ItemStackHandler(1) {
			@Override
			protected void onContentsChanged(int slot) {
				// To make sure the BE persists when the chunk is saved later
				// we need to mark it dirty every thime the item handler changes.
				setChanged();
			}

			@Override
			public boolean isItemValid(int slot, ItemStack stack) {
				return ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0;
			}

			@Override
			public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
				if (ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) <= 0) return stack;
				return super.insertItem(slot, stack, simulate);
			}
		};
	}

	private final CustomEnergyStorage createEnergy() {
		return new CustomEnergyStorage(100000, 200) {
			@Override
			protected void onEnergyChanged() {
				// To make sure the BE persists when the chunk is saved later
				// we need to mark it dirty every thime the item handler changes.
				setChanged();
			}
		};
	}
}
