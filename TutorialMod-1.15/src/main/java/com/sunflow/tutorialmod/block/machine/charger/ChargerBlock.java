package com.sunflow.tutorialmod.block.machine.charger;

import com.sunflow.tutorialmod.block.base.EnergyTileBlockBase;
import com.sunflow.tutorialmod.capability.CapabilityItemEnergy;
import com.sunflow.tutorialmod.util.energy.CustomEnergyStorage;
import com.sunflow.tutorialmod.util.interfaces.IEnergyItem;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ChargerBlock extends EnergyTileBlockBase {
	public ChargerBlock() {
		super(Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f)
				.lightValue(14));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) { return new ChargerTile(); }

	@Override
	public int getComparatorInputOverride(BlockState state, World world, BlockPos pos) {
		TileEntity tileEntity = world.getTileEntity(pos);

//		ItemStack stack = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).map(h -> h.getStackInSlot(ChargerTile.CHARGE_SLOT)).orElse(null);
//		CustomEnergyStorage itemEnergyStorage = tileEntity.getCapability(CapabilityItemEnergy.ENERGYITEM_CAPABILITY).map(IItemEnergy::getStorage).orElse(CustomEnergyStorage.DEFAULT);
//
//		int energy = itemEnergyStorage.getEnergyStored();
//		int capacity = itemEnergyStorage.getMaxEnergyStored();
//
//		return 14 * energy / capacity + (stack.getItem() instanceof IEnergyItem ? 1 : 0);

		return tileEntity.getCapability(CapabilityItemEnergy.ENERGYITEM_CAPABILITY).map(i -> {
			int itemPower = 0;
			if (i.getItemStack().getItem() instanceof IEnergyItem) {
				CustomEnergyStorage itemEnergyStorage = i.getStorage();
				int energy = itemEnergyStorage.getEnergyStored();
				int capacity = itemEnergyStorage.getMaxEnergyStored();
				if (capacity != 0) itemPower = 1 + 14 * energy / capacity;
				else itemPower = 1;
			}
			return itemPower;
		}).orElse(0);
	}
}
