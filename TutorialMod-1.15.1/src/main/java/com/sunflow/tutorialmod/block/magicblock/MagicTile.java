package com.sunflow.tutorialmod.block.magicblock;

import com.sunflow.tutorialmod.setup.registration.Registration;

import net.minecraft.tileentity.TileEntity;

public class MagicTile extends TileEntity {
	public MagicTile() { super(Registration.MAGICBLOCK_TILE.get()); }
}
