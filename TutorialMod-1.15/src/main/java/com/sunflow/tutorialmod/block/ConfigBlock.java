package com.sunflow.tutorialmod.block;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.config.TutorialModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.config.ModConfig;

public class ConfigBlock extends Block {

	public ConfigBlock() { super(Properties.create(Material.ROCK)); }

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (world.isRemote && hand == Hand.MAIN_HAND) {
			TutorialModConfig.createScreen(null, ModConfig.Type.COMMON).open(TutorialMod.proxy.getMinecraft());
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}
}
