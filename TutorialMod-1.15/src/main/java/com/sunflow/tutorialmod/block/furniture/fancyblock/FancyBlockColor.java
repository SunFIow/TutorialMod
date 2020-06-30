package com.sunflow.tutorialmod.block.furniture.fancyblock;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILightReader;

public class FancyBlockColor implements IBlockColor {

	@Override
	public int getColor(BlockState blockState, ILightReader world, BlockPos pos, int tint) {
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof FancyBlockTile) {
			FancyBlockTile fancy = (FancyBlockTile) te;
			BlockState mimic = fancy.getMimic();
			if (mimic != null) {
				return TutorialMod.proxy.getMinecraft().getBlockColors().getColor(mimic, world, pos, tint);
			}
		}
		return -1;
	}

}
