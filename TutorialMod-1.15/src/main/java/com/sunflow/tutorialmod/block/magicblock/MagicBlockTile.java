package com.sunflow.tutorialmod.block.magicblock;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class MagicBlockTile extends TileEntity {
	public MagicBlockTile() { super(Registration.MAGICBLOCK_TILE.get()); }

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos(), getPos().add(1, 3, 1));
	}
}
