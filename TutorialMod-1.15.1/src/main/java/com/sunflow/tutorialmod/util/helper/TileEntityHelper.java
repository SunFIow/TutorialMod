package com.sunflow.tutorialmod.util.helper;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.setup.ModTileEntitiyTypes;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityHelper {

	private TileEntityHelper() {}

	public static <T extends TileEntity> TileEntityType<T> createType(Supplier<T> factory, Block... blocks) {
		TileEntityType.Builder<T> builder = TileEntityType.Builder.create(factory, blocks);
		TileEntityType<T> type = builder.build(null);
		type.setRegistryName(blocks[0].getRegistryName());
		ModTileEntitiyTypes.TILE_TYPES.add(type);
		return type;
	}
}
