package com.sunflow.tutorialmod.block.furniture.fancyblock;

import com.sunflow.tutorialmod.block.base.BakedBlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class FancyBlock extends BakedBlockBase {
//	private static final VoxelShape GRIDLESS_AABB = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
	private static final VoxelShape GRIDLESS_AABB = Block.makeCuboidShape(1.0D, 1.0D, 0.0D, 15.0D, 14.0D, 15.0D);

	public FancyBlock() {
		super("fancyblock", Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f), GRIDLESS_AABB);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new FancyBlockTile();
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		ItemStack stack = player.getHeldItem(hand);
		if (!stack.isEmpty() && stack.getItem() instanceof BlockItem) {
			if (!world.isRemote) {
				TileEntity tile = world.getTileEntity(pos);
				if (tile instanceof FancyBlockTile) {
					BlockState mimicState = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
					((FancyBlockTile) tile).setMimic(mimicState);
				}
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}

	@SuppressWarnings("resource")
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		if (!context.getWorld().isRemote && context.getFace() == Direction.UP) {
			Vector3d hit = context.getHitVec();
			BlockPos pos = context.getPos();
			Vector3d diff = hit.subtract(pos.getX(), pos.getY(), pos.getZ());
			double x = MathHelper.clamp(diff.x - 0.5D, -length.x, length.x);
			double y = 0.0D;
			double z = MathHelper.clamp(diff.z - 0.5D, -length.z, length.z);
			offset = new Vector3d(x, y, z);
		}
		return super.getStateForPlacement(context);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (!world.isRemote) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof FancyBlockTile) {
				((FancyBlockTile) tile).setOffset(offset);
			}
		}
	}

}
