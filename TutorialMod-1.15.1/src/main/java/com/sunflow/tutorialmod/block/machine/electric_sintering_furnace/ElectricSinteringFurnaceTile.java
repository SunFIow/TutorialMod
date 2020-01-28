package com.sunflow.tutorialmod.block.machine.electric_sintering_furnace;

import java.util.concurrent.atomic.AtomicBoolean;

import com.sunflow.tutorialmod.block.base.EnergyInvTileEntityBase;
import com.sunflow.tutorialmod.block.machine.sintering_furnace.SinteringFurnaceTile;
import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.setup.registration.Registration;
import com.sunflow.tutorialmod.util.CustomEnergyStorage;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.items.ItemStackHandler;

public class ElectricSinteringFurnaceTile extends EnergyInvTileEntityBase {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	public static final int INPUT1_ID = 0, INPUT2_ID = 1, OUTPUT_ID = 2;
	public static final int COOKTIME_ID = 2;

	public static final Item EMPTY = ItemStack.EMPTY.getItem();

	private static final int ENERGY_USE = TutorialModConfig.ELECTRIC_SINTERING_FURNACE_CONSUMPTION.get() / TutorialModConfig.ELECTRIC_SINTERING_FURNACE_TICKS.get();

	@Override
	protected ItemStackHandler getHandler() {
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
	protected CustomEnergyStorage getEnergy() {
		return new CustomEnergyStorage(TutorialModConfig.ELECTRIC_SINTERING_FURNACE_MAXPOWER.get(), TutorialModConfig.ELECTRIC_SINTERING_FURNACE_RECEIVE.get(), 50000);
	}

	private float cookTime;

	public ElectricSinteringFurnaceTile() {
		super(Registration.ELECTRIC_SINTERING_FURNACE_TILE.get());
	}

	@Override
	public void tick() {
		super.tick();
		handler.ifPresent(h -> {
			energy.ifPresent(e -> {
				if (canBurn() && canSmelt()) {
					if (canBurn()) {
						cookTime++;
						e.extractEnergy(ENERGY_USE, false);

						if (cookTime >= TutorialModConfig.ELECTRIC_SINTERING_FURNACE_TICKS.get()) {
							cookTime = 0;
//							h.insertItem(OUTPUT_ID, smelting, false);
							smeltItem();
							markDirty();
						}
					}
				}
				if (!canBurn() || !canSmelt() && cookTime > 0) {
					cookTime = MathHelper.clamp(cookTime - 2, 0, TutorialModConfig.ELECTRIC_SINTERING_FURNACE_TICKS.get());
				}
			});
		});

		if (!world.isRemote) {
			BlockState state = world.getBlockState(pos);
			if (state.get(POWERED) != cookTime > 0) {
				world.setBlockState(pos, state.with(POWERED, cookTime > 0), 3);
			}
		}
//		System.out.println(Config.ELECTRIC_SINTERING_FURNACE_TICKS.get() + ", " + world.isRemote + ", " + cookTime);
//		System.out.println(energyUse);
	}

	private boolean canBurn() {
		if (!energy.isPresent())
			return false;

		return energy.orElse(null).getEnergyStored() >= ENERGY_USE;
	}

	public void smeltItem() {
		if (this.canSmelt()) {
			handler.ifPresent(h -> {

				ItemStack input1 = h.getStackInSlot(INPUT1_ID);
				ItemStack input2 = h.getStackInSlot(INPUT2_ID);
				ItemStack result = SinteringFurnaceTile.Recipes.getInstance().getSinteringResult(input1, input2);
				ItemStack output = h.getStackInSlot(OUTPUT_ID);

				if (output.isEmpty()) {
					h.setStackInSlot(OUTPUT_ID, result.copy());
				} else if (output.getItem() == result.getItem()) {
					output.grow(result.getCount());
				}

				h.extractItem(INPUT1_ID, 1, false);
				h.extractItem(INPUT2_ID, 1, false);
			});
		}
	}

	private boolean canSmelt() {
		AtomicBoolean canSmelt = new AtomicBoolean(false);

		handler.ifPresent(h -> {
			ItemStack input1 = h.getStackInSlot(INPUT1_ID);
			ItemStack input2 = h.getStackInSlot(INPUT2_ID);

			if (!input1.isEmpty() && !input2.isEmpty()) {
				ItemStack result = SinteringFurnaceTile.Recipes.getInstance().getSinteringResult(input1, input2);
				if (!result.isEmpty()) {
					ItemStack output = h.getStackInSlot(OUTPUT_ID);
					if (output.isEmpty()) {
						canSmelt.set(true);
					} else {
						if (output.isItemEqual(result)) {
							int res = output.getCount() + result.getCount();
							canSmelt.set(res <= h.getSlotLimit(OUTPUT_ID) && res <= output.getMaxStackSize());
						}
					}
				}
			}
		});
		return canSmelt.get();
	}

	@Override
	public int getField(int id) {
		switch (id) {
			case COOKTIME_ID:
				return (int) cookTime;
		}
		return super.getField(id);
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
			case COOKTIME_ID:
				cookTime = value;
				break;
		}
		super.setField(id, value);
	}

	@Override
	public void read(CompoundNBT tag) {
		cookTime = tag.getFloat("cooktime");

		super.read(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		tag.putFloat("cooktime", cookTime);

		return super.write(tag);
	}

	@Override
	public Container createMenu(int i, PlayerInventory inv, PlayerEntity player) {
		return new ElectricSinteringFurnaceContainer(i, inv, this);
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.electric_sintering_furnace");
	}
}
