package com.sunflow.tutorialmod.data;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

public class MySaveData extends SavedData {

	private static final String ID_GENERAL = TutorialMod.MODID + "_general";

	public MySaveData() { TutorialMod.LOGGER.debug("-newSaveData-"); }

	public MySaveData(CompoundTag tag) {
		TutorialMod.LOGGER.debug("-readSaveData-");

		// CompoundTag tagES = tag.getCompound("enderStorage");
		// EnderPackItem.handler.deserializeNBT(tagES);
	}

	@Override
	public CompoundTag save(CompoundTag tag) {
		TutorialMod.LOGGER.debug("-writeSaveData-");

		// CompoundTag tagES = EnderPackItem.handler.serializeNBT();
		// compound.put("enderStorage", tagES);

		return tag;
	}

	public static void register(DimensionDataStorage dataStorage) {
		dataStorage.computeIfAbsent(MySaveData::new, MySaveData::new, MySaveData.ID_GENERAL);
	}

	@Override
	public boolean isDirty() { return true; }
}