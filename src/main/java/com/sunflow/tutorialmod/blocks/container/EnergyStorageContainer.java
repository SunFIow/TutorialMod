package com.sunflow.tutorialmod.blocks.container;

import com.sunflow.tutorialmod.blocks.base.ContainerBase;
import com.sunflow.tutorialmod.blocks.tile.EnergyStorageTile;
import com.sunflow.tutorialmod.init.ModTypes;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.InvWrapper;

public class EnergyStorageContainer extends ContainerBase {

	public EnergyStorageContainer(int id, World world, BlockPos pos, PlayerInventory inv) {
		super(ModTypes.ENERGY_STORAGE_CONTAINER, id, 1, world, pos);

		layoutPlayerInventorySlots(new InvWrapper(inv), 8, 84);

		makeIntArray(2);
	}

	public int getEnergy() {
		return ((EnergyStorageTile) tile).getField(EnergyStorageTile.ENERGY_ID);
	}

	public int getEnergyMax() {
		return ((EnergyStorageTile) tile).getField(EnergyStorageTile.ENERGY_MAX_ID);
	}
}
