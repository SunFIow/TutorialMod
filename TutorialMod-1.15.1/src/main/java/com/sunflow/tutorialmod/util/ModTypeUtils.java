package com.sunflow.tutorialmod.util;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.item.base.MobEggBase;
import com.sunflow.tutorialmod.setup.ModContainerTypes;
import com.sunflow.tutorialmod.setup.ModEntityTypes;
import com.sunflow.tutorialmod.setup.ModTileEntitiyTypes;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.network.IContainerFactory;

public class ModTypeUtils {

	private ModTypeUtils() {}

	public static <T extends TileEntity> TileEntityType<T> createType(Supplier<T> factory, Block... blocks) {
		TileEntityType.Builder<T> builder = TileEntityType.Builder.create(factory, blocks);
		TileEntityType<T> type = builder.build(null);
		type.setRegistryName(blocks[0].getRegistryName());
		ModTileEntitiyTypes.TILE_TYPES.add(type);
		return type;
	}

	public static <T extends Container> ContainerType<T> createType(IContainerFactory<T> factory, Block block) {
		ContainerType<T> type = IForgeContainerType.create(factory);
		type.setRegistryName(block.getRegistryName());
		ModContainerTypes.CONTAINER_TYPES.add(type);
		return type;
	}

	public static <T extends Entity> EntityType<T> createType(String name, float w, float h, boolean receiveVelocityUpdates, EntityClassification classification, EntityType.IFactory<T> factory, MobEggBase... eggs) {
		EntityType<T> type = EntityType.Builder.<T>create(factory, classification)
				.size(w, h)
				.setShouldReceiveVelocityUpdates(receiveVelocityUpdates)
				.build(name);
		type.setRegistryName(TutorialMod.MODID, name);
		for (MobEggBase egg : eggs) {
			egg.setEntityType(type);
		}
		ModEntityTypes.ENTITY_TYPES.add(type);
		return type;
	}
}
