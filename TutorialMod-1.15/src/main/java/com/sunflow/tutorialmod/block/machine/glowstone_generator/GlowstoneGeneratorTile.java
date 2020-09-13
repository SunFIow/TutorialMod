package com.sunflow.tutorialmod.block.machine.glowstone_generator;

import com.sunflow.tutorialmod.block.base.EnergyInventoryTileBase;
import com.sunflow.tutorialmod.capability.CapabilityProcessor;
import com.sunflow.tutorialmod.capability.IProcessor;
import com.sunflow.tutorialmod.capability.Processor;
import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.util.energy.CustomEnergyStorage;
import com.sunflow.tutorialmod.util.energy.EnergyUtils;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

public class GlowstoneGeneratorTile extends EnergyInventoryTileBase implements INamedContainerProvider {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	public static final Item EMPTY = ItemStack.EMPTY.getItem();
	public static final int FUEL_SLOT = 0;
	private static final Item FUEL_ITEM = Items.GLOWSTONE_DUST;

	protected Processor cooker = createProcessor();
	private LazyOptional<IProcessor> processor = LazyOptional.of(() -> cooker);

	protected Processor createProcessor() { return new Processor(); }

	@Override
	protected ItemStackHandler createHandler() {
		return new ItemStackHandler(1) {
			@Override
			public boolean isItemValid(int slot, ItemStack stack) {
				return stack.getItem() == Items.GLOWSTONE_DUST;
			}

			@Override
			public void onContentsChanged(int slot) {
				markDirty();
			}
		};
	}

	@Override
	protected CustomEnergyStorage createEnergy() {
		return new CustomEnergyStorage(TutorialModConfig.SERVER.GLOWSTONE_GENERATOR_MAXPOWER.get(), TutorialModConfig.SERVER.GLOWSTONE_GENERATOR_TRANSFER.get()) {
			@Override
			protected void onEnergyChanged() {
				markDirty();
			}
		};
	}

	private Item currentFuel = ItemStack.EMPTY.getItem();

//	private int cookTime;
	private float powerLeftOvers;

	public GlowstoneGeneratorTile() {
		super(Registration.GLOWSTONE_GENERATOR_TILE.get());
	}

	@Override
	public boolean receiveClientEvent(int id, int type) {
		return super.receiveClientEvent(id, type);
//		return true;
	}

	@Override
	public void tick() {
		super.tick();
		if (world.isRemote) return;

		boolean producedEnergy = false;

		if (currentFuel != EMPTY) {
			int fuelValue = getFuelValue(currentFuel.getItem());
			int fuelTicks = getFuelTicks(currentFuel.getItem());
			int partialValue = fuelValue / fuelTicks;
			powerLeftOvers += (float) fuelValue / fuelTicks - partialValue;
			partialValue += Math.round(powerLeftOvers);
			powerLeftOvers -= Math.round(powerLeftOvers);

			if (energyStorage.receiveEnergy(partialValue, true) > 0) {
				producedEnergy = true;
				cooker.addTime(energyStorage.receiveEnergy(partialValue, false) / partialValue);

				if (cooker.getTime() >= fuelTicks) {
					currentFuel = EMPTY;
					cooker.setTime(0);
				}
				markDirty();
			}
		}
		if (currentFuel == EMPTY) {
			ItemStack fuelSlot = itemHandler.getStackInSlot(FUEL_SLOT);
			if (!fuelSlot.isEmpty() && isItemFuel(fuelSlot.getItem())) {
				currentFuel = fuelSlot.getItem();
				cooker.setTotalTime(getFuelTicks(currentFuel.getItem()));
				itemHandler.extractItem(FUEL_SLOT, 1, false);
			}
		}

		BlockState state = world.getBlockState(pos);
		if (state.get(POWERED) != producedEnergy) {
			world.setBlockState(pos, state.with(POWERED, producedEnergy),
					Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
		}

//		sendOutPower();
		if (EnergyUtils.sendOutPower(world, pos, energyStorage)) markDirty();
	}

//	private void sendOutPower() {
//		if (energyStorage.getEnergyStored() > 0) {
//			for (Direction dir : Direction.values()) {
//				TileEntity tileentity = world.getTileEntity(pos.offset(dir));
//				if (tileentity != null) {
//					tileentity.getCapability(CapabilityEnergy.ENERGY, dir.getOpposite()).ifPresent(e -> {
//						if (e.canReceive()) {
////							int maxEExt = energyHandler.extractEnergy(TutorialModConfig.GLOWSTONE_GENERATOR_TRANSFER.get(), true);
////							int eReceived = e.receiveEnergy(maxEExt, false);
////							energyHandler.extractEnergy(eReceived, false);							
////							tileentity.markDirty();
//
//							int maxExtracted = Math.min(energyStorage.getEnergyStored(), energyStorage.getMaxExtract());
//							int received = e.receiveEnergy(maxExtracted, false);
//							energyStorage.extractEnergy(received, false);
//
//							this.markDirty();
//						}
//					});
//				}
//				if (energyStorage.getEnergyStored() <= 0) {
//					return;
//				}
//			}
//		}
//	}

	private boolean isItemFuel(Item item) { return getFuelValue(item) > 0; }

	private int getFuelValue(Item item) {
		if (item == FUEL_ITEM) return TutorialModConfig.SERVER.GLOWSTONE_GENERATOR_GENERATE.get();
		else return 0;
	}

	private int getFuelTicks(Item item) {
		if (item == FUEL_ITEM) return TutorialModConfig.SERVER.GLOWSTONE_GENERATOR_TICKS.get();
		else return 0;
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityProcessor.COOKER_CAPABILITY) {
			return processor.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void read(CompoundNBT tag) {
		cooker.deserializeNBT(tag.getCompound("cooker"));
		powerLeftOvers = tag.getFloat("powerleftovers");
		currentFuel = ItemStack.read(tag.getCompound("currentfuel")).getItem();

		super.read(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		tag.put("cooker", cooker.serializeNBT());
		tag.putFloat("powerleftovers", powerLeftOvers);
		tag.put("currentfuel", new ItemStack(currentFuel).write(new CompoundNBT()));

		return super.write(tag);
	}

//	public void readInventory(CompoundNBT tag) {
//		itemHandler.deserializeNBT(tag.getCompound("inv"));
//	}
//
//	public CompoundNBT writeInventory(CompoundNBT tag) {
//		tag.put("inv", itemHandler.serializeNBT());
//		return tag;
//	}
//
//	@Override
//	public CompoundNBT getUpdateTag() {
//		CompoundNBT tag = super.getUpdateTag();
//		writeInventory(tag);
//		return tag;
//	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new GlowstoneGeneratorContainer(id, inv, this);
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.glowstone_generator");
	}
}
