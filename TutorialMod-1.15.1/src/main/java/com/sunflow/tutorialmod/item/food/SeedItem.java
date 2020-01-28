package com.sunflow.tutorialmod.item.food;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class SeedItem extends FoodItem implements IPlantable {

	private Block plantBlock;

	public SeedItem(Block plantBlock, int hunger, float saturation, boolean meat, boolean fastEat, boolean alwaysEdible) {
		super(hunger, saturation, meat, fastEat, alwaysEdible);
		this.plantBlock = plantBlock;
	}

	public SeedItem(Block plantBlock, int hunger, float saturation, boolean meat, boolean fastEat) {
		this(plantBlock, hunger, saturation, meat, fastEat, false);
	}

	public SeedItem(Block plantBlock, int hunger, float saturation, boolean meat) {
		this(plantBlock, hunger, saturation, meat, false, false);
	}

	public SeedItem(Block plantBlock, int hunger, float saturation) {
		this(plantBlock, hunger, saturation, false, false, false);
	}

	public SeedItem(Block plantBlock, int hunger) {
		this(plantBlock, hunger, 0.6f, false, false, false);
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		final Direction facing = context.getFace();
		final PlayerEntity player = context.getPlayer();
		final BlockPos pos = context.getPos();
		final World world = context.getWorld();
		final ItemStack stack = player.getHeldItem(context.getHand());
		final BlockState state = world.getBlockState(pos);

		if (facing == Direction.UP && player.canPlayerEdit(pos.offset(facing), facing, stack) && state.getBlock().canSustainPlant(state, world, pos, Direction.UP, this) && world.isAirBlock(pos.up())) {
			world.setBlockState(pos.up(), plantBlock.getDefaultState());
			stack.shrink(1);
			return ActionResultType.SUCCESS;
		} else {
			return ActionResultType.FAIL;
		}
	}

	@Override
	public PlantType getPlantType(IBlockReader world, BlockPos pos) { return PlantType.Crop; }

	@Override
	public BlockState getPlant(IBlockReader world, BlockPos pos) { return plantBlock.getDefaultState(); }
}
