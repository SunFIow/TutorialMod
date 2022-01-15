package com.sunflow.tutorialmod.data;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.NBTUtils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.storage.LevelStorageSource.LevelStorageAccess;
import net.minecraft.world.level.storage.WorldData;
import net.minecraftforge.internal.WorldPersistenceHooks;

public class TutorialData implements WorldPersistenceHooks.WorldPersistenceHook {

    @Override
    public String getModId() { return TutorialMod.MODID; }

    @Override
    public CompoundTag getDataForWriting(LevelStorageAccess levelSave, WorldData serverInfo) {
        CompoundTag modData = new CompoundTag();
        Double value = Math.random();
        modData.putDouble("exampleData", value);
        Log.info("Writing exampleData: " + value);
        return modData;
    }

    @Override
    public void readData(LevelStorageAccess levelSave, WorldData serverInfo, CompoundTag tag) {
        if (tag.contains("exampleData", Tag.TAG_DOUBLE)) {
            Double value = tag.getDouble("exampleData");
            Log.info("Reading exampleData: " + value);
        }
    }

}
