package com.sunflow.tutorialmod.block.furniture.fancyblock;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FancyBlockColor implements BlockColor {

	@Override
	public int getColor(BlockState pState, BlockAndTintGetter pLevel, BlockPos pPos, int pTintIndex) {
		BlockEntity te = pLevel.getBlockEntity(pPos);
		if (te instanceof FancyBlockEntity fancy) {
			BlockState mimic = fancy.getMimic();
			if (mimic != null) {
				return TutorialMod.proxy.getMinecraft().getBlockColors().getColor(mimic, pLevel, pPos, pTintIndex);
			}
		}
		return -1;
	}

}
