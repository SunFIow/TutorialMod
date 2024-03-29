package com.sunflow.tutorialmod.block.machine.charger;

import com.sunflow.tutorialmod.block.base.EnergyTileBlockBase;
import com.sunflow.tutorialmod.util.interfaces.IEnergyItem;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ChargerBlock extends EnergyTileBlockBase {
	public ChargerBlock() {
		super(Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f)
				.setLightLevel(state -> state.get(POWERED) ? (int) (state.get(FILLLEVEL) / 15F * 14) : 0));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
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
