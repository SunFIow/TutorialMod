package com.sunflow.tutorialmod.block.machine.electric_sintering_furnace;

import com.sunflow.tutorialmod.block.base.EnergyInventoryTileBase;
import com.sunflow.tutorialmod.block.machine.sintering_furnace.SinteringFurnaceTile;
import com.sunflow.tutorialmod.capability.CapabilityProcessor;
import com.sunflow.tutorialmod.capability.IProcessor;
import com.sunflow.tutorialmod.capability.Processor;
import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.util.energy.CustomEnergyStorage;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

public class ElectricSinteringFurnaceTile extends EnergyInventoryTileBase implements INamedContainerProvider {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	public static final int INPUT1_ID = 0, INPUT2_ID = 1, OUTPUT_ID = 2;

	public static final Item EMPTY = ItemStack.EMPTY.getItem();

	private static final int ENERGY_USE = TutorialModConfig.SERVER.ELECTRIC_SINTERING_FURNACE_CONSUMPTION.get() / TutorialModConfig.SERVER.ELECTRIC_SINTERING_FURNACE_TICKS.get();

	protected Processor cooker = createProcessor();
	private LazyOptional<IProcessor> processor = LazyOptional.of(() -> cooker);

	protected Processor createProcessor() { return new Processor(0, TutorialModConfig.SERVER.ELECTRIC_SINTERING_FURNACE_TICKS.get()); }

	@Override
	protected ItemStackHandler createHandler() {
		return new ItemStackHandler(3) {
			@Override
			public boolean isItemValid(int slot, ItemStack stack) {
				switch (slot) {
					case OUTPUT_ID:
						return false;
					default:
						return true;
				}
			}

			@Override
			public void onContentsChanged(int slot) {
				markDirty();
			}
		};
	}

	@Override
	protected CustomEnergyStorage createEnergy() {
		return new CustomEnergyStorage(TutorialModConfig.SERVER.ELECTRIC_SINTERING_FURNACE_MAXPOWER.get(), TutorialModConfig.SERVER.ELECTRIC_SINTERING_FURNACE_RECEIVE.get(), 50000) {
			@Override
			protected void onEnergyChanged() {
				markDirty();
			}
		};
	}

	public ElectricSinteringFurnaceTile() {
		super(Registration.ELECTRIC_SINTERING_FURNACE_TILE.get());
	}

	@Override
	public void tick() {
		super.tick();
		if (world.isRemote) return;

		boolean usedEnergy = false;
		if (canBurn() && canSmelt()) {
			cooker.addTime(1);
			energyStorage.extractEnergy(ENERGY_USE, false);
			usedEnergy = true;

//			TutorialModConfig.ELECTRIC_SINTERING_FURNACE_TICKS.get()
			if (cooker.getTime() >= cooker.getTotalTime()) {
				cooker.setTime(0);
				smeltItem();
			}
			markDirty();
		}
		if (!canBurn() || !canSmelt() && cooker.getTime() > 0) {
			cooker.setTime(MathHelper.clamp(cooker.getTime() - 2, 0, cooker.getTotalTime()));
		}

//		if (!world.isRemote) {
		BlockState state = world.getBlockState(pos);
		if (state.get(POWERED) != usedEnergy) {
			world.setBlockState(pos, state.with(POWERED, usedEnergy), 3);
		}
//		}
	}

	private boolean canBurn() {
		return energyStorage.getEnergyStored() >= ENERGY_USE;
	}

	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack input1 = itemHandler.getStackInSlot(INPUT1_ID);
			ItemStack input2 = itemHandler.getStackInSlot(INPUT2_ID);
			ItemStack result = SinteringFurnaceTile.Recipes.getInstance().getSinteringResult(input1, input2);
			ItemStack output = itemHandler.getStackInSlot(OUTPUT_ID);

			if (output.isEmpty()) {
				itemHandler.setStackInSlot(OUTPUT_ID, result.copy());
			} else if (output.getItem() == result.getItem()) {
				output.grow(result.getCount());
			}

			itemHandler.extractItem(INPUT1_ID, 1, false);
			itemHandler.extractItem(INPUT2_ID, 1, false);
		}

	}

	private boolean canSmelt() {
		ItemStack input1 = itemHandler.getStackInSlot(INPUT1_ID);
		ItemStack input2 = itemHandler.getStackInSlot(INPUT2_ID);

		if (!input1.isEmpty() && !input2.isEmpty()) {
			ItemStack result = SinteringFurnaceTile.Recipes.getInstance().getSinteringResult(input1, input2);
			if (!result.isEmpty()) {
				ItemStack output = itemHandler.getStackInSlot(OUTPUT_ID);
				if (output.isEmpty()) {
					return true;
				} else {
					if (output.isItemEqual(result)) {
						int res = output.getCount() + result.getCount();
						return res <= itemHandler.getSlotLimit(OUTPUT_ID) && res <= output.getMaxStackSize();
					}
				}
			}
		}
		return false;
	}

	@Override
	public void read(CompoundNBT tag) {
		cooker.deserializeNBT(tag.getCompound("cooker"));

		super.read(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		tag.put("cooker", cooker.serializeNBT());

		return super.write(tag);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityProcessor.COOKER_CAPABILITY) {
			return processor.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new ElectricSinteringFurnaceContainer(id, inv, this);
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.electric_sintering_furnace");
	}
}
