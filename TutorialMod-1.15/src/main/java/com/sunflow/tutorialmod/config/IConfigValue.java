package com.sunflow.tutorialmod.config;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public interface IConfigValue {

	CompoundNBT serializeNBT();

	void deserializeNBT(ConfigValue<?> value, CompoundNBT tag);

}
