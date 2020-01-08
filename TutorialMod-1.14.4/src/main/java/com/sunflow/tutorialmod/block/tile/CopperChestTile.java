package com.sunflow.tutorialmod.block.tile;

import com.sunflow.tutorialmod.block.CopperChestBlock;
import com.sunflow.tutorialmod.block.container.CopperChestContainer;
import com.sunflow.tutorialmod.setup.ModTileEntitiyTypes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

@OnlyIn(value = Dist.CLIENT, _interface = IChestLid.class)
public class CopperChestTile extends LockableLootTileEntity implements IChestLid, ITickableTileEntity {
	public static final int ROWS = 8;
	public static final int SIZE = ROWS * 9;

	private NonNullList<ItemStack> chestContents = NonNullList.withSize(SIZE, ItemStack.EMPTY);
	protected float lidAngle;
	protected float prevLidAngle;
	protected int numPlayersUsing;
	private int ticksSinceSync;
	private LazyOptional<IItemHandlerModifiable> chestHandler;

	public CopperChestTile() {
		super(ModTileEntitiyTypes.COPPER_CHEST_TILE);
	}

	@Override
	public int getSizeInventory() {
		return SIZE;
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.copper_chest");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory playerInv) {
		return new CopperChestContainer(id, playerInv, this);
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.chestContents) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.chestContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		if (!this.checkLootAndRead(compound)) {
			ItemStackHelper.loadAllItems(compound, this.chestContents);
		}

	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		if (!this.checkLootAndWrite(compound)) {
			ItemStackHelper.saveAllItems(compound, this.chestContents);
		}

		return compound;
	}

	@Override
	public void tick() {
//		int i = this.pos.getX();
//		int j = this.pos.getY();
//		int k = this.pos.getZ();
		++ticksSinceSync;
		numPlayersUsing = syncNumPlayersUsing(world, this, ticksSinceSync, pos.getX(), pos.getY(), pos.getZ(), numPlayersUsing);
		prevLidAngle = lidAngle;
//		float f = 0.1F;
		if (numPlayersUsing > 0 && lidAngle == 0.0F) {
			playSound(SoundEvents.BLOCK_CHEST_OPEN);
		}

		if (numPlayersUsing == 0 && lidAngle > 0.0F || numPlayersUsing > 0 && lidAngle < 1.0F) {
			float lidAngleSave = lidAngle;
			if (numPlayersUsing > 0) {
				lidAngle += 0.1F;
			} else {
				lidAngle -= 0.1F;
			}

			if (lidAngle > 1.0F) {
				lidAngle = 1.0F;
			}

//			float f2 = 0.5F;
			if (lidAngle < 0.5F && lidAngleSave >= 0.5F) {
				playSound(SoundEvents.BLOCK_CHEST_CLOSE);
			}

			if (lidAngle < 0.0F) {
				lidAngle = 0.0F;
			}
		}

	}

	public static int syncNumPlayersUsing(World world, CopperChestTile tile, int ticksSinceSync, int x, int y, int z, int numPlayersUsing) {
		if (!world.isRemote && numPlayersUsing != 0 && (ticksSinceSync + x + y + z) % 200 == 0) {
			numPlayersUsing = getNumPlayerUsing(world, tile, x, y, z);
		}

		return numPlayersUsing;
	}

	public static int getNumPlayerUsing(World world, CopperChestTile tile, int x, int y, int z) {
		final float f = 5.0F;
		int i = 0;

		for (PlayerEntity playerentity : world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(x - f, y - f, z - f, x + 1 + f, y + 1 + f, z + 1 + f))) {
			if (playerentity.openContainer instanceof CopperChestContainer) {
				IInventory iinventory = ((CopperChestContainer) playerentity.openContainer).getChestInventory();
				if (iinventory == tile) {
					++i;
				}
			}
		}

		return i;
	}

	private void playSound(SoundEvent sound) {
		double x = this.pos.getX() + 0.5D;
		double y = this.pos.getY() + 0.5D;
		double z = this.pos.getZ() + 0.5D;

		this.world.playSound((PlayerEntity) null, x, y, z, sound, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
	}

	/**
	 * See {@link Block#eventReceived} for more information. This must return true serverside before it is called
	 * clientside.
	 */
	@Override
	public boolean receiveClientEvent(int id, int type) {
		if (id == 1) {
			this.numPlayersUsing = type;
			return true;
		} else {
			return super.receiveClientEvent(id, type);
		}
	}

	@Override
	public void openInventory(PlayerEntity player) {
		if (!player.isSpectator()) {
			if (this.numPlayersUsing < 0) {
				this.numPlayersUsing = 0;
			}

			++this.numPlayersUsing;
			this.onOpenOrClose();
		}
	}

	@Override
	public void closeInventory(PlayerEntity player) {
		if (!player.isSpectator()) {
			--this.numPlayersUsing;
			this.onOpenOrClose();
		}

	}

	protected void onOpenOrClose() {
		Block block = this.getBlockState().getBlock();
		if (block instanceof CopperChestBlock) {
			this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
			this.world.notifyNeighborsOfStateChange(this.pos, block);
		}

	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.chestContents;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> itemsIn) {
		this.chestContents = itemsIn;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public float getLidAngle(float partialTicks) {
		return MathHelper.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
	}

	public static int getPlayersUsing(IBlockReader reader, BlockPos posIn) {
		BlockState blockstate = reader.getBlockState(posIn);
		if (blockstate.hasTileEntity()) {
			TileEntity tileentity = reader.getTileEntity(posIn);
			if (tileentity instanceof CopperChestTile) {
				return ((CopperChestTile) tileentity).numPlayersUsing;
			}
		}

		return 0;
	}

	public static void swapContents(CopperChestTile chest, CopperChestTile otherChest) {
		NonNullList<ItemStack> nonnulllist = chest.getItems();
		chest.setItems(otherChest.getItems());
		otherChest.setItems(nonnulllist);
	}

	@Override
	public void updateContainingBlockInfo() {
		super.updateContainingBlockInfo();
		if (this.chestHandler != null) {
			this.chestHandler.invalidate();
			this.chestHandler = null;
		}
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (!this.removed && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (this.chestHandler == null) {
				this.chestHandler = LazyOptional.of(this::createHandler);
			}
			return this.chestHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	private IItemHandlerModifiable createHandler() {
		BlockState state = this.getBlockState();
		if (!(state.getBlock() instanceof CopperChestBlock)) {
			return new InvWrapper(this);
		}
		return new InvWrapper(this);
	}

	@Override
	public void remove() {
		super.remove();
		if (chestHandler != null)
			chestHandler.invalidate();
	}

}