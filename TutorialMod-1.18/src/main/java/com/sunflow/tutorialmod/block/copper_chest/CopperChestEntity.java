package com.sunflow.tutorialmod.block.copper_chest;

import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CopperChestEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {
	public static final int ROWS = 8;
	public static final int SIZE = ROWS * 9;

	private NonNullList<ItemStack> chestContents = NonNullList.withSize(SIZE, ItemStack.EMPTY);
	protected float lidAngle;
	protected float prevLidAngle;
	protected int numPlayersUsing;

	private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
		@Override
		protected void onOpen(Level p_155357_, BlockPos p_155358_, BlockState p_155359_) {
			playSound(p_155357_, p_155358_, p_155359_, SoundEvents.CHEST_OPEN);
		}

		@Override
		protected void onClose(Level p_155367_, BlockPos p_155368_, BlockState p_155369_) {
			playSound(p_155367_, p_155368_, p_155369_, SoundEvents.CHEST_CLOSE);
		}

		@Override
		protected void openerCountChanged(Level p_155361_, BlockPos p_155362_, BlockState p_155363_, int p_155364_, int p_155365_) {
			CopperChestEntity.this.signalOpenCount(p_155361_, p_155362_, p_155363_, p_155364_, p_155365_);
		}

		@Override
		protected boolean isOwnContainer(Player player) {
			if (!(player.containerMenu instanceof CopperChestContainer)) {
				return false;
			} else {
				Container container = ((CopperChestContainer) player.containerMenu).getChestInventory();
				return container == CopperChestEntity.this || container instanceof CompoundContainer compoundconatiner && compoundconatiner.contains(CopperChestEntity.this);
			}
		}
	};

	private void playSound(Level level, BlockPos pos, BlockState state, SoundEvent event) {
		double x = (double) pos.getX() + 0.5D;
		double y = (double) pos.getY() + 0.5D;
		double z = (double) pos.getZ() + 0.5D;
		level.playSound((Player) null, x, y, z, event, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
	}

	protected void signalOpenCount(Level level, BlockPos pos, BlockState state, int p_155336_, int p_155337_) {
		Block block = state.getBlock();
		level.blockEvent(pos, block, 1, p_155337_);
	}

	private final ChestLidController chestLidController = new ChestLidController();

	public CopperChestEntity(BlockPos pos, BlockState state) { super(Registration.COPPER_CHEST.blockEntity(), pos, state); }

	@Override
	public int getContainerSize() { return SIZE; }

	@Override
	protected Component getDefaultName() { return CopperChestBlock.CONTAINER_TITLE; }

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory playerInv) {
		return new CopperChestContainer(id, playerInv, this);
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		this.chestContents = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(tag)) {
			ContainerHelper.loadAllItems(tag, this.chestContents);
		}
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		if (!this.trySaveLootTable(tag)) {
			ContainerHelper.saveAllItems(tag, this.chestContents);
		}
	}

	public static void lidAnimateTick(Level level, BlockPos pos, BlockState state, CopperChestEntity blockEntity) {
		blockEntity.chestLidController.tickLid();
	}

	@Override
	public boolean triggerEvent(int type, int i2) {
		if (type == 1) {
			this.chestLidController.shouldBeOpen(i2 > 0);
			return true;
		} else {
			return super.triggerEvent(type, i2);
		}
	}

	@Override
	public void startOpen(Player player) {
		if (!this.remove && !player.isSpectator()) {
			this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	@Override
	public void stopOpen(Player player) {
		if (!this.remove && !player.isSpectator()) {
			this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	@Override
	protected NonNullList<ItemStack> getItems() { return this.chestContents; }

	@Override
	protected void setItems(NonNullList<ItemStack> items) { this.chestContents = items; }

	@Override
	public float getOpenNess(float f0) { return this.chestLidController.getOpenness(f0); }

	public static int getOpenCount(BlockGetter getter, BlockPos pos) {
		BlockState blockstate = getter.getBlockState(pos);
		if (blockstate.hasBlockEntity()) {
			BlockEntity blockentity = getter.getBlockEntity(pos);
			if (blockentity instanceof CopperChestEntity copperchestentity) {
				return copperchestentity.openersCounter.getOpenerCount();
			}
		}

		return 0;
	}

}