package com.sunflow.tutorialmod.block.furniture.fancyblock;

import javax.annotation.Nullable;

import com.mojang.math.Vector3d;
import com.sunflow.tutorialmod.block.base.BakedBlockBase;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FancyBlock extends BakedBlockBase implements EntityBlock {
	//	private static final VoxelShape GRIDLESS_AABB = Block.Block(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
	private static final VoxelShape GRIDLESS_AABB = Block.box(1.0D, 1.0D, 0.0D, 15.0D, 14.0D, 15.0D);

	public FancyBlock() {
		super("fancyblock", Properties.of(Material.STONE)
				.strength(2.0f), GRIDLESS_AABB);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new FancyBlockTile(pPos, pState);
	}

	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		// return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
		ItemStack stack = pPlayer.getItemInHand(pHand);
		if (!stack.isEmpty() && stack.getItem() instanceof BlockItem blockitem) {
			if (!pLevel.isClientSide()) {
				BlockEntity tile = pLevel.getBlockEntity(pPos);
				if (tile instanceof FancyBlockTile fancyEntity) {
					BlockState mimicState = blockitem.getBlock().defaultBlockState();
					fancyEntity.setMimic(mimicState);
				}
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		if (!context.getLevel().isClientSide() && context.getClickedFace() == Direction.UP) {
			Vec3 hit = context.getClickLocation();
			BlockPos pos = context.getClickedPos();
			Vec3 diff = hit.subtract(pos.getX(), pos.getY(), pos.getZ());
			double x = Mth.clamp(diff.x - 0.5D, -length.x, length.x);
			double y = 0.0D;
			double z = Mth.clamp(diff.z - 0.5D, -length.z, length.z);
			offset = new Vector3d(x, y, z);
		}
		return super.getStateForPlacement(context);
	}

	@Override
	public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
		if (!pLevel.isClientSide()) {
			BlockEntity tile = pLevel.getBlockEntity(pPos);
			if (tile instanceof FancyBlockTile fancyEntity) {
				fancyEntity.setOffset(offset);
			}
		}
	}

}
