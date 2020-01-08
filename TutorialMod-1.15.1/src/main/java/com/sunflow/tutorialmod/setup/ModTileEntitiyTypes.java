package com.sunflow.tutorialmod.setup;

import java.util.ArrayList;
import java.util.List;

import com.sunflow.tutorialmod.block.copper_chest.CopperChestTile;
import com.sunflow.tutorialmod.block.furniture.fancyblock.FancyBlockTile;
import com.sunflow.tutorialmod.block.machine.charger.ChargerTile;
import com.sunflow.tutorialmod.block.machine.electric_sintering_furnace.ElectricSinteringFurnaceTile;
import com.sunflow.tutorialmod.block.machine.energy_storage.EnergyStorageTile;
import com.sunflow.tutorialmod.block.machine.firstblock.FirstBlockTile;
import com.sunflow.tutorialmod.block.machine.sintering_furnace.SinteringFurnaceTile;
import com.sunflow.tutorialmod.util.helper.TileEntityHelper;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class ModTileEntitiyTypes {
	public static final List<TileEntityType<? extends TileEntity>> TILE_TYPES = new ArrayList<TileEntityType<? extends TileEntity>>();

	public static final TileEntityType<FirstBlockTile> FIRSTBLOCK_TILE = TileEntityHelper.createType(FirstBlockTile::new, ModBlocks.FIRSTBLOCK);

	public static final TileEntityType<EnergyStorageTile> ENERGY_STORAGE_TILE = TileEntityHelper.createType(EnergyStorageTile::new, ModBlocks.ENERGY_STORAGE);

	public static final TileEntityType<SinteringFurnaceTile> SINTERING_FURNACE_TILE = TileEntityHelper.createType(SinteringFurnaceTile::new, ModBlocks.SINTERING_FURNACE);

	public static final TileEntityType<ElectricSinteringFurnaceTile> ELECTRIC_SINTERING_FURNACE_TILE = TileEntityHelper.createType(ElectricSinteringFurnaceTile::new, ModBlocks.ELECTRIC_SINTERING_FURNACE);

	public static final TileEntityType<ChargerTile> CHARGER_TILE = TileEntityHelper.createType(ChargerTile::new, ModBlocks.CHARGER);

	public static final TileEntityType<CopperChestTile> COPPER_CHEST_TILE = TileEntityHelper.createType(CopperChestTile::new, ModBlocks.COPPER_CHEST);

	public static final TileEntityType<FancyBlockTile> FANCYBLOCK_TILE = TileEntityHelper.createType(FancyBlockTile::new, ModBlocks.FANCY_BLOCK);

//	public static <T extends TileEntity> TileEntityType<T> createTileEntityType(Supplier<T> factory, Block... blocks) {
//		TileEntityType.Builder<T> builder = TileEntityType.Builder.create(factory, blocks);
//		TileEntityType<T> type = builder.build(null);
//		type.setRegistryName(blocks[0].getRegistryName());
//		TILE_TYPES.add(type);
//		return type;
//	}
}
