package com.sunflow.tutorialmod.dimension;

import java.util.function.BiFunction;
import java.util.function.Consumer;

import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

public class ModDimensionBase extends ModDimension {

	private final BiFunction<World, DimensionType, ? extends Dimension> factory;
	private PacketBuffer data;
	private boolean hasSkyLight;
	private Consumer<DimensionType> dimensionType;

	public ModDimensionBase(BiFunction<World, DimensionType, ? extends Dimension> factory) {
		this(factory, null, null, true);
	}

	public ModDimensionBase(BiFunction<World, DimensionType, ? extends Dimension> factory, Consumer<DimensionType> type) {
		this(factory, type, null, true);
	}

	public ModDimensionBase(BiFunction<World, DimensionType, ? extends Dimension> factory, Consumer<DimensionType> type, PacketBuffer data, boolean hasSkyLight) {
		this.factory = factory;
		this.data = data;
		this.hasSkyLight = hasSkyLight;
		this.dimensionType = type;
	}

	@Override
	public BiFunction<World, DimensionType, ? extends Dimension> getFactory() { return factory; }

	public PacketBuffer getData() { return data; }

	public boolean hasSkyLight() { return hasSkyLight; }

	public void setDimensionType(DimensionType type) { dimensionType.accept(type); }
}
