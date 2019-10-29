package com.sunflow.tutorialmod.blocks.tile;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.sunflow.tutorialmod.blocks.base.InventoryTileEntityBase;
import com.sunflow.tutorialmod.blocks.container.SinteringFurnaceContainer;
import com.sunflow.tutorialmod.init.ModBlocks;
import com.sunflow.tutorialmod.init.ModItems;
import com.sunflow.tutorialmod.init.ModTileEntitiyTypes;
import com.sunflow.tutorialmod.util.Config;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.items.ItemStackHandler;

public class SinteringFurnaceTile extends InventoryTileEntityBase implements ITickableTileEntity {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	public static final int FUEL_ID = 0, INPUT1_ID = 1, INPUT2_ID = 2, OUTPUT_ID = 3;
	public static final int BURNTIME_ID = 0, BURNTIME_TOTAL_ID = 1, COOKTIME_ID = 2;

	public static final Item EMPTY = ItemStack.EMPTY.getItem();

	@Override
	protected ItemStackHandler getHandler() {
		return new ItemStackHandler(4) {
			@Override
			public boolean isItemValid(int slot, ItemStack stack) {
				switch (slot) {
					case FUEL_ID:
						return isFuel(stack);
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

	private float cookTime;
	private int burnTime;
	private int burnTimeTotal;

	public SinteringFurnaceTile() {
		super(ModTileEntitiyTypes.SINTERING_FURNACE_TILE);
	}

	@Override
	public void tick() {
		handler.ifPresent(h -> {

			if (isBurning()) {
				--burnTime;
			}
			ItemStack fuel = h.getStackInSlot(FUEL_ID);
			if (canSmelt()) {
				if (isBurning()) {
					cookTime++;

					if (cookTime >= Config.SINTERING_FURNACE_TICKS.get()) {
						cookTime = 0;
//						this.totalCookTime = this.getCookTime((ItemStack) this.inventory.get(TileEntitySinteringFurnace.INPUT1_ID), (ItemStack) this.inventory.get(TileEntitySinteringFurnace.INPUT2_ID));
						smeltItem();
						markDirty();
					}

				} else {
					if (!fuel.isEmpty()) {
						burnTimeTotal = getBurnTime(fuel);
						burnTime = burnTimeTotal;
						h.extractItem(FUEL_ID, 1, false);
					}
				}
			}
			if (!isBurning() || !canSmelt() && cookTime > 0) {
				cookTime = MathHelper.clamp(cookTime - 2, 0, Config.SINTERING_FURNACE_TICKS.get());
			}
		});

		if (!world.isRemote) {
			BlockState state = world.getBlockState(pos);
			if (state.get(POWERED) != isBurning()) {
				world.setBlockState(pos, state.with(POWERED, isBurning()), 3);
			}
		}
	}

	private boolean isBurning() {
		return this.burnTime > 0;
	}

	public void smeltItem() {
		if (this.canSmelt()) {
			handler.ifPresent(h -> {

				ItemStack input1 = h.getStackInSlot(INPUT1_ID);
				ItemStack input2 = h.getStackInSlot(INPUT2_ID);
				ItemStack result = Recipes.getInstance().getSinteringResult(input1, input2);
				ItemStack output = h.getStackInSlot(OUTPUT_ID);

				if (output.isEmpty()) {
					h.setStackInSlot(OUTPUT_ID, result.copy());
				} else if (output.getItem() == result.getItem()) {
					output.grow(result.getCount());
				}

//				input1.shrink(1);
//				input2.shrink(1);
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
				ItemStack result = Recipes.getInstance().getSinteringResult(input1, input2);
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

	public static boolean isFuel(ItemStack stack) {
		int ret = stack.getBurnTime();
		return ForgeEventFactory.getItemBurnTime(stack, ret == -1 ? getBurnTimes().getOrDefault(stack.getItem(), 0) : ret) > 0;
	}

	protected int getBurnTime(ItemStack stack) {
		if (stack.isEmpty()) {
			return 0;
		} else {
			Item item = stack.getItem();
			int ret = stack.getBurnTime();
			return ForgeEventFactory.getItemBurnTime(stack, ret == -1 ? getBurnTimes().getOrDefault(item, 0) : ret);
		}
	}

	public static Map<Item, Integer> getBurnTimes() {
		Map<Item, Integer> map = Maps.newLinkedHashMap();
		addItemBurnTime(map, Items.LAVA_BUCKET, 20000);
		addItemBurnTime(map, Blocks.COAL_BLOCK, 16000);
		addItemBurnTime(map, Items.BLAZE_ROD, 2400);
		addItemBurnTime(map, Items.COAL, 1600);
		addItemBurnTime(map, Items.CHARCOAL, 1600);
		addItemTagBurnTime(map, ItemTags.LOGS, 300);
		addItemTagBurnTime(map, ItemTags.PLANKS, 300);
		addItemTagBurnTime(map, ItemTags.WOODEN_STAIRS, 300);
		addItemTagBurnTime(map, ItemTags.WOODEN_SLABS, 150);
		addItemTagBurnTime(map, ItemTags.WOODEN_TRAPDOORS, 300);
		addItemTagBurnTime(map, ItemTags.WOODEN_PRESSURE_PLATES, 300);
		addItemBurnTime(map, Blocks.OAK_FENCE, 300);
		addItemBurnTime(map, Blocks.BIRCH_FENCE, 300);
		addItemBurnTime(map, Blocks.SPRUCE_FENCE, 300);
		addItemBurnTime(map, Blocks.JUNGLE_FENCE, 300);
		addItemBurnTime(map, Blocks.DARK_OAK_FENCE, 300);
		addItemBurnTime(map, Blocks.ACACIA_FENCE, 300);
		addItemBurnTime(map, Blocks.OAK_FENCE_GATE, 300);
		addItemBurnTime(map, Blocks.BIRCH_FENCE_GATE, 300);
		addItemBurnTime(map, Blocks.SPRUCE_FENCE_GATE, 300);
		addItemBurnTime(map, Blocks.JUNGLE_FENCE_GATE, 300);
		addItemBurnTime(map, Blocks.DARK_OAK_FENCE_GATE, 300);
		addItemBurnTime(map, Blocks.ACACIA_FENCE_GATE, 300);
		addItemBurnTime(map, Blocks.NOTE_BLOCK, 300);
		addItemBurnTime(map, Blocks.BOOKSHELF, 300);
		addItemBurnTime(map, Blocks.LECTERN, 300);
		addItemBurnTime(map, Blocks.JUKEBOX, 300);
		addItemBurnTime(map, Blocks.CHEST, 300);
		addItemBurnTime(map, Blocks.TRAPPED_CHEST, 300);
		addItemBurnTime(map, Blocks.CRAFTING_TABLE, 300);
		addItemBurnTime(map, Blocks.DAYLIGHT_DETECTOR, 300);
		addItemTagBurnTime(map, ItemTags.BANNERS, 300);
		addItemBurnTime(map, Items.BOW, 300);
		addItemBurnTime(map, Items.FISHING_ROD, 300);
		addItemBurnTime(map, Blocks.LADDER, 300);
		addItemTagBurnTime(map, ItemTags.SIGNS, 200);
		addItemBurnTime(map, Items.WOODEN_SHOVEL, 200);
		addItemBurnTime(map, Items.WOODEN_SWORD, 200);
		addItemBurnTime(map, Items.WOODEN_HOE, 200);
		addItemBurnTime(map, Items.WOODEN_AXE, 200);
		addItemBurnTime(map, Items.WOODEN_PICKAXE, 200);
		addItemTagBurnTime(map, ItemTags.WOODEN_DOORS, 200);
		addItemTagBurnTime(map, ItemTags.BOATS, 200);
		addItemTagBurnTime(map, ItemTags.WOOL, 100);
		addItemTagBurnTime(map, ItemTags.WOODEN_BUTTONS, 100);
		addItemBurnTime(map, Items.STICK, 100);
		addItemTagBurnTime(map, ItemTags.SAPLINGS, 100);
		addItemBurnTime(map, Items.BOWL, 100);
		addItemTagBurnTime(map, ItemTags.CARPETS, 67);
		addItemBurnTime(map, Blocks.DRIED_KELP_BLOCK, 4001);
		addItemBurnTime(map, Items.CROSSBOW, 300);
		addItemBurnTime(map, Blocks.BAMBOO, 50);
		addItemBurnTime(map, Blocks.DEAD_BUSH, 100);
		addItemBurnTime(map, Blocks.SCAFFOLDING, 50);
		addItemBurnTime(map, Blocks.LOOM, 300);
		addItemBurnTime(map, Blocks.BARREL, 300);
		addItemBurnTime(map, Blocks.CARTOGRAPHY_TABLE, 300);
		addItemBurnTime(map, Blocks.FLETCHING_TABLE, 300);
		addItemBurnTime(map, Blocks.SMITHING_TABLE, 300);
		addItemBurnTime(map, Blocks.COMPOSTER, 300);
		return map;
	}

	private static void addItemTagBurnTime(Map<Item, Integer> map, Tag<Item> itemTag, int p_213992_2_) {
		for (Item item : itemTag.getAllElements()) {
			map.put(item, p_213992_2_);
		}

	}

	private static void addItemBurnTime(Map<Item, Integer> map, IItemProvider itemProvider, int burnTimeIn) {
		map.put(itemProvider.asItem(), burnTimeIn);
	}

	@Override
	public int getField(int id) {
		switch (id) {
			case COOKTIME_ID:
				return (int) cookTime;
			case BURNTIME_ID:
				return burnTime;
			case BURNTIME_TOTAL_ID:
				return burnTimeTotal;
			default:
				return -1;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
			case COOKTIME_ID:
				cookTime = value;
				break;
			case BURNTIME_ID:
				burnTime = value;
				break;
			case BURNTIME_TOTAL_ID:
				burnTimeTotal = value;
				break;
		}
	}

	@Override
	public void read(CompoundNBT tag) {
		cookTime = tag.getFloat("cooktime");
		this.burnTime = tag.getInt("burntime");
		this.burnTimeTotal = tag.getInt("burntimetotal");

		super.read(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		tag.putFloat("cooktime", cookTime);
		tag.putInt("burntime", burnTime);
		tag.putInt("burntimetotal", burnTimeTotal);

		return super.write(tag);
	}

	@Override
	public Container createMenu(int i, PlayerInventory inv, PlayerEntity player) {
		return new SinteringFurnaceContainer(i, inv, this);
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.sintering_furnace");
	}

	public static class Recipes {

		private static final Recipes INSTANCE = new Recipes();
		private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
		private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

		public static Recipes getInstance() {
			return Recipes.INSTANCE;
		}

		private Recipes() {
			addSinteringRecipe(new ItemStack(ModBlocks.SANTA_HAT), new ItemStack(ModItems.RUBY), new ItemStack(ModItems.EVIL_APPLE), 5.4f);
			addSinteringRecipe(new ItemStack(Blocks.COAL_ORE), new ItemStack(Blocks.IRON_ORE), new ItemStack(Blocks.DIAMOND_ORE), 5.4f);
		}

		public void addSinteringRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
			if (this.getSinteringResult(input1, input2) != ItemStack.EMPTY)
				return;
			smeltingList.put(input1, input2, result);
			experienceList.put(result, Float.valueOf(experience));
		}

		public ItemStack getSinteringResult(ItemStack input1, ItemStack input2) {
			for (Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet()) {
				if (compareItemStacks(input1, entry.getKey())) {
					for (Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
						if (compareItemStacks(input2, ent.getKey())) {
							return ent.getValue();
						}
					}
				}
			}
			return ItemStack.EMPTY;
		}

		private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
//			return stack1.getItem() == stack2.getItem() && (stack1.getMetadata() == stack2.getMetadata() || stack2.getMetadata() == 32767);
			return stack1.isItemEqual(stack2);
		}

		public Table<ItemStack, ItemStack, ItemStack> getDuelSmeltingList() {
			return smeltingList;
		}

		public float getSinteringExperience(ItemStack stack) {
			for (Entry<ItemStack, Float> entry : experienceList.entrySet())
				if (compareItemStacks(stack, entry.getKey()))
					return entry.getValue().floatValue();
			return 0.0f;
		}
	}

}
