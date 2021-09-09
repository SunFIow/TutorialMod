package com.sunflow.tutorialmod.config;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public interface IConfigValue {

	CompoundTag serializeNBT();

	void deserializeNBT(ConfigValue<?> value, CompoundTag tag);

}
