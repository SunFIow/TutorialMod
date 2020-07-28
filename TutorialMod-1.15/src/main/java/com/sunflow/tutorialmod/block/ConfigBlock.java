package com.sunflow.tutorialmod.block;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.config.ConfigScreen;
import com.sunflow.tutorialmod.config.InGameConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class ConfigBlock extends Block {

	public ConfigBlock() {
		super(Properties.create(Material.ROCK));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (worldIn.isRemote) {
			InGameConfig.create(null, ConfigScreen.Catergory.GENERAL).open(TutorialMod.proxy.getMinecraft());
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}
}
