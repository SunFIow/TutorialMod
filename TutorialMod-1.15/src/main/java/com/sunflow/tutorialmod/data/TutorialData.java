package com.sunflow.tutorialmod.data;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.storage.SaveHandler;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.WorldPersistenceHooks;

public class TutorialData implements WorldPersistenceHooks.WorldPersistenceHook {

	@Override
	public String getModId() { return TutorialMod.MODID; }

	@Override
	public CompoundNBT getDataForWriting(SaveHandler handler, WorldInfo info) {
		CompoundNBT modData = new CompoundNBT();
		Double value = Math.random();
		modData.putDouble("exampleData", value);
		Log.info("Writing exampleData: " + value);
		return modData;
	}

	@Override
	public void readData(SaveHandler handler, WorldInfo info, CompoundNBT tag) {
		if (tag.contains("exampleData", Constants.NBT.TAG_DOUBLE)) {
			Double value = tag.getDouble("exampleData");
			Log.info("Reading exampleData: " + value);
		}
	}

}
