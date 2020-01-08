package com.sunflow.tutorialmod.setup;

import java.util.ArrayList;
import java.util.List;

import com.sunflow.tutorialmod.dimension.ModDimensionBase;
import com.sunflow.tutorialmod.dimension.badlands.BadlandsDimension;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

public class ModDimensions {
	public static final List<ModDimensionBase> DIMENSIONS = new ArrayList<>();

//	public static final ResourceLocation BADLANDS_ID = new ResourceLocation(TutorialMod.MODID, "badlands");

//	@ObjectHolder(TutorialMod.MODID + ":badlands")
	public static ModDimension BADLANDS = new ModDimensionBase("badlands", BadlandsDimension::new, (type) -> BADLANDS_TYPE = type);
	public static DimensionType BADLANDS_TYPE;

//	private static ModDimension createDimension(String name, BiFunction<World, DimensionType, ? extends Dimension> factory) {
//		return createDimension(name, factory, null, null, false);
//	}
//
//	private static ModDimension createDimension(String name, BiFunction<World, DimensionType, ? extends Dimension> factory, DimensionType type, PacketBuffer data, boolean hasSkyLight) {
//		ModDimension dimension = new ModDimension() {
//			@Override
//			public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
//				return factory;
//			}
//		};
//		dimension.setRegistryName(TutorialMod.MODID, name);
//		DIMENSIONS.add(dimension);
//		if (type != null) type = DimensionManager.registerOrGetDimension(dimension.getRegistryName(), dimension, data, hasSkyLight);
//		return dimension;
//	}
}
