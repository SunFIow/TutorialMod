package com.sunflow.tutorialmod.block.machine.sintering_furnace;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.sunflow.tutorialmod.block.base.InventoryTileBase;
import com.sunflow.tutorialmod.capability.CapabilityProcessor;
import com.sunflow.tutorialmod.capability.IProcessor;
import com.sunflow.tutorialmod.capability.Processor;
import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.items.ItemStackHandler;

public class SinteringFurnaceTile extends InventoryTileBase implements INamedContainerProvider, ITickableTileEntity {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	public static final int FUEL_ID = 0, INPUT1_ID = 1, INPUT2_ID = 2, OUTPUT_ID = 3;

	public static final Item EMPTY = ItemStack.EMPTY.getItem();

	protected Processor cooker = createCookProcessor();
	private LazyOptional<IProcessor> cookProcessor = LazyOptional.of(() -> cooker);

	protected Processor burner = createFuelProcessor();
	private LazyOptional<IProcessor> fuelProcessor = LazyOptional.of(() -> burner);

	protected Processor createCookProcessor() { return new Processor(0, TutorialModConfig.SERVER.SINTERING_FURNACE_TICKS.get()); }

	protected Processor createFuelProcessor() { return new Processor(); }

	@Override
	protected ItemStackHandler createHandler() {
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

	public SinteringFurnaceTile() {
		super(Registration.SINTERING_FURNACE_TILE.get());
	}

	@Override
	public void tick() {
		if (world.isRemote) return;

		if (isBurning()) {
			burner.addTime(-1);
		}
		if (canSmelt()) {
			if (isBurning()) {
				cooker.addTime(1);

//				TutorialModConfig.SINTERING_FURNACE_TICKS.get()
				if (cooker.getTime() >= cooker.getTotalTime()) {
					cooker.setTime(0);
//					this.totalCookTime = this.getCookTime((ItemStack) this.inventory.get(TileEntitySinteringFurnace.INPUT1_ID), (ItemStack) this.inventory.get(TileEntitySinteringFurnace.INPUT2_ID));
					smeltItem();
					markDirty();
				}
			} else {
				ItemStack fuelSlot = itemHandler.getStackInSlot(FUEL_ID);
				if (!fuelSlot.isEmpty()) {
					burner.setTotalTime(getBurnTime(fuelSlot));
					burner.setTime(burner.getTotalTime());
					itemHandler.extractItem(FUEL_ID, 1, false);
				}
			}
		}
		if (!isBurning() || !canSmelt() && cooker.getTime() > 0) {
			cooker.setTime(MathHelper.clamp(cooker.getTime() - 2, 0, cooker.getTotalTime()));
		}

//		if (!world.isRemote) {
		BlockState state = world.getBlockState(pos);
		if (state.get(POWERED) != isBurning()) {
			world.setBlockState(pos, state.with(POWERED, isBurning()), 3);
		}
//		}
	}

	private boolean isBurning() { return burner.getTime() > 0; }

	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack input1 = itemHandler.getStackInSlot(INPUT1_ID);
			ItemStack input2 = itemHandler.getStackInSlot(INPUT2_ID);
			ItemStack result = Recipes.getInstance().getSinteringResult(input1, input2);
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
			ItemStack result = Recipes.getInstance().getSinteringResult(input1, input2);
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
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
//		Log.debug("{}    |    {}", CapabilityProcessor.COOKER_CAPABILITY, CapabilityProcessor.BURNER_CAPABILITY);
//		Log.debug(CapabilityProcessor.COOKER_CAPABILITY == CapabilityProcessor.BURNER_CAPABILITY);

		if (cap == CapabilityProcessor.COOKER_CAPABILITY) {
			return cookProcessor.cast();
		}
		if (cap == CapabilityProcessor.BURNER_CAPABILITY) {
			return fuelProcessor.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void read(CompoundNBT tag) {
		cooker.deserializeNBT(tag.getCompound("cooker"));
		burner.deserializeNBT(tag.getCompound("burner"));

		super.read(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		tag.put("cooker", cooker.serializeNBT());
		tag.put("burner", burner.serializeNBT());

		return super.write(tag);
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new SinteringFurnaceContainer(id, inv, this);
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
			addSinteringRecipe(new ItemStack(Registration.SANTA_HAT.get()), new ItemStack(Registration.RUBY.get()), new ItemStack(Registration.EVIL_APPLE.get()), 5.4f);
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
				} else {
					if (compareItemStacks(input2, entry.getKey())) {
						for (Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
							if (compareItemStacks(input1, ent.getKey())) {
								return ent.getValue();
							}
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

		public float getSinteringExperience(ItemStack stack) {
			for (Entry<ItemStack, Float> entry : experienceList.entrySet())
				if (compareItemStacks(stack, entry.getKey()))
					return entry.getValue().floatValue();
			return 0.0f;
		}
	}

}
