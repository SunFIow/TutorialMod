package com.sunflow.tutorialmod.item.food;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.item.ItemUtil;
import com.sunflow.tutorialmod.setup.ModTabs;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class SeedItem extends Item implements IPlantable {

	private Supplier<Block> plantBlock;

	// public SeedItem(Block plantBlock) { super(new Item.Properties().group(ModGroups.itemGroup)); this.plantBlock = plantBlock; }

	public SeedItem(Supplier<Block> plantBlock, int hunger, float saturation, boolean meat, boolean fastEat, boolean alwaysEdible) {
		super(ItemUtil.Food(ModTabs.ITEM_TAB, hunger, saturation, meat, fastEat, alwaysEdible));
		this.plantBlock = plantBlock;
	}

	public SeedItem(Supplier<Block> plantBlock, int hunger, float saturation, boolean meat, boolean fastEat) { this(plantBlock, hunger, saturation, meat, fastEat, false); }

	public SeedItem(Supplier<Block> plantBlock, int hunger, float saturation, boolean meat) { this(plantBlock, hunger, saturation, meat, false, false); }

	public SeedItem(Supplier<Block> plantBlock, int hunger, float saturation) { this(plantBlock, hunger, saturation, false, false, false); }

	public SeedItem(Supplier<Block> plantBlock, int hunger) { this(plantBlock, hunger, 0.6f, false, false, false); }

	@Override
	public InteractionResult useOn(UseOnContext pContext) {
		final Direction facing = pContext.getClickedFace();
		final Player player = pContext.getPlayer();
		final BlockPos pos = pContext.getClickedPos();
		final Level level = pContext.getLevel();
		final ItemStack stack = pContext.getItemInHand();
		final BlockState state = level.getBlockState(pos);
		if (facing == Direction.UP && player.mayInteract(level, pos.relative(facing)) && player.mayUseItemAt(pos.relative(facing), facing, stack) && state.getBlock().canSustainPlant(state, level, pos, Direction.UP, this)) {
			level.setBlock(pos.above(), getPlant(level, pos), 20);
			stack.shrink(1);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}

	@Override
	public PlantType getPlantType(BlockGetter world, BlockPos pos) { return PlantType.CROP; }

	@Override
	public BlockState getPlant(BlockGetter world, BlockPos pos) { return plantBlock.get().defaultBlockState(); }
}
