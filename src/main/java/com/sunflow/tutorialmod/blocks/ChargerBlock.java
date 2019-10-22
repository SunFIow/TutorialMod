package com.sunflow.tutorialmod.blocks;

import com.sunflow.tutorialmod.blocks.base.EnergyTileBlockBase;
import com.sunflow.tutorialmod.blocks.tile.ChargerTile;
import com.sunflow.tutorialmod.util.interfaces.IEnergyItem;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ChargerBlock extends EnergyTileBlockBase {
	public ChargerBlock() {
		super("charger", Material.ROCK, 2.0F, 14);
	}

	@Override
	public TileEntity getTileEntity() {
		return new ChargerTile();
	}

	@Override
	public int getComparatorInputOverride(BlockState state, World world, BlockPos pos) {
		ChargerTile tile = (ChargerTile) world.getTileEntity(pos);
		double capacity = tile.getField(ChargerTile.ITEM_ENERGY_MAX_ID);
		double energy = tile.getField(ChargerTile.ITEM_ENERGY_ID);
		IItemHandler h = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
		return (int) (energy * 14 / capacity) + (h != null && h.getStackInSlot(ChargerTile.CHARGE_SLOT).getItem() instanceof IEnergyItem ? 1 : 0);
//		return 5;
	}
}
