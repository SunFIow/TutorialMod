package com.sunflow.tutorialmod.util;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class MyWorldData extends SavedData {

	public static final String ID_GENERAL = TutorialMod.MODID + "_general";

	public MyWorldData() {}

	public MyWorldData(CompoundTag tag) {
		TutorialMod.LOGGER.debug("-readWorldData-");

		// CompoundTag tagES = tag.getCompound("enderStorage");
		// EnderPackItem.handler.deserializeNBT(tagES);
	}

	@Override
	public CompoundTag save(CompoundTag tag) {
		TutorialMod.LOGGER.debug("-writeWorldData-");

		// CompoundTag tagES = EnderPackItem.handler.serializeNBT();
		// compound.put("enderStorage", tagES);

		return tag;
	}

	@Override
	public boolean isDirty() { return true; }
}