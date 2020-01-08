package com.sunflow.tutorialmod.setup;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.sunflow.tutorialmod.block.tile.ChargerTile;
import com.sunflow.tutorialmod.block.tile.CopperChestTile;
import com.sunflow.tutorialmod.block.tile.ElectricSinteringFurnaceTile;
import com.sunflow.tutorialmod.block.tile.EnergyStorageTile;
import com.sunflow.tutorialmod.block.tile.FancyBlockTile;
import com.sunflow.tutorialmod.block.tile.FirstBlockTile;
import com.sunflow.tutorialmod.block.tile.SinteringFurnaceTile;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class ModTileEntitiyTypes {
	public static final List<TileEntityType<? extends TileEntity>> TILE_TYPES = new ArrayList<TileEntityType<? extends TileEntity>>();

	public static final TileEntityType<FirstBlockTile> FIRSTBLOCK_TILE = createTileEntityType(FirstBlockTile::new, ModBlocks.FIRSTBLOCK);

	public static final TileEntityType<EnergyStorageTile> ENERGY_STORAGE_TILE = createTileEntityType(EnergyStorageTile::new, ModBlocks.ENERGY_STORAGE);

	public static final TileEntityType<SinteringFurnaceTile> SINTERING_FURNACE_TILE = createTileEntityType(SinteringFurnaceTile::new, ModBlocks.SINTERING_FURNACE);

	public static final TileEntityType<ElectricSinteringFurnaceTile> ELECTRIC_SINTERING_FURNACE_TILE = createTileEntityType(ElectricSinteringFurnaceTile::new, ModBlocks.ELECTRIC_SINTERING_FURNACE);

	public static final TileEntityType<ChargerTile> CHARGER_TILE = createTileEntityType(ChargerTile::new, ModBlocks.CHARGER);

	public static final TileEntityType<CopperChestTile> COPPER_CHEST_TILE = createTileEntityType(CopperChestTile::new, ModBlocks.COPPER_CHEST);

	public static final TileEntityType<FancyBlockTile> FANCYBLOCK_TILE = createTileEntityType(FancyBlockTile::new, ModBlocks.FANCY_BLOCK);

	public static <T extends TileEntity> TileEntityType<T> createTileEntityType(Supplier<T> factory, Block... blocks) {
		TileEntityType.Builder<T> builder = TileEntityType.Builder.create(factory, blocks);
		TileEntityType<T> type = builder.build(null);
		type.setRegistryName(blocks[0].getRegistryName());
		TILE_TYPES.add(type);
		return type;
	}
}
