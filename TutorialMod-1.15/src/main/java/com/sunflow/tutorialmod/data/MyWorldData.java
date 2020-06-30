package com.sunflow.tutorialmod.data;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.storage.WorldSavedData;

public class MyWorldData extends WorldSavedData {

	public static final String ID_GENERAL = TutorialMod.MODID + "_general";

	public MyWorldData() {
		super(ID_GENERAL);
	}

	@Override
	public void read(CompoundNBT compound) {
		Log.debug("-readWorldData-");

//		CompoundNBT tagES = compound.getCompound("enderStorage");
//		EnderPackItem.handler.deserializeNBT(tagES);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		Log.debug("-writeWorldData-");

//		CompoundNBT tagES = EnderPackItem.handler.serializeNBT();
//		compound.put("enderStorage", tagES);

		return compound;
	}
}