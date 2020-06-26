package com.sunflow.tutorialmod.dimension;

import java.util.function.BiFunction;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

public class ModDimensionBase extends ModDimension {

	private final BiFunction<World, DimensionType, ? extends Dimension> factory;

	public ModDimensionBase(BiFunction<World, DimensionType, ? extends Dimension> factory) {
		this.factory = factory;
	}

	@Override
	public BiFunction<World, DimensionType, ? extends Dimension> getFactory() { return factory; }
}
