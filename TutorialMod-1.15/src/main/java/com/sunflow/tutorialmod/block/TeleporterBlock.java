package com.sunflow.tutorialmod.block;

import com.sunflow.tutorialmod.command.util.TeleportationTools;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class TeleporterBlock extends Block {

	private int dimensionID;

	public TeleporterBlock(int dimensionID) {
		super(Properties.create(Material.ROCK));
		this.dimensionID = dimensionID;
	}

	public TeleporterBlock() {
		this(0);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!worldIn.isRemote && hand == Hand.MAIN_HAND) {
			if (player.isSneaking()) {
				dimensionID++;
				if (dimensionID > 1) {
					dimensionID = -1;
				}
				player.sendStatusMessage(new StringTextComponent("Linked to Dimension " + dimensionID), true);
			} else {
				if (player instanceof ServerPlayerEntity) {
					TeleportationTools.teleportToDimension((ServerPlayerEntity) player, this.dimensionID, player.getPosition());
				} else {
					Log.warn("You are not an ServerPlayerEntity");
				}
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}
}
