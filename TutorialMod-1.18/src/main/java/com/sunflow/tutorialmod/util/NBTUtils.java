package com.sunflow.tutorialmod.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.extensions.IForgeEntity;

public class NBTUtils {
    private NBTUtils() {}

    public static final String NBT_LOC_TUTORIALMOD = "tutorialmod";

    public static CompoundTag getModTag(IForgeEntity entity) {
        CompoundTag tag = entity.getPersistentData();
        CompoundTag tutorialmodTag = tag.getCompound(NBT_LOC_TUTORIALMOD);
        if (!tag.contains(NBT_LOC_TUTORIALMOD)) tag.put(NBT_LOC_TUTORIALMOD, tutorialmodTag);
        return tutorialmodTag;
    }

    public static CompoundTag getModTag(BlockEntity entity) {
        CompoundTag tag = entity.getTileData();
        CompoundTag tutorialmodTag = tag.getCompound(NBT_LOC_TUTORIALMOD);
        if (!tag.contains(NBT_LOC_TUTORIALMOD)) tag.put(NBT_LOC_TUTORIALMOD, tutorialmodTag);
        return tutorialmodTag;
    }

    public static CompoundTag getModTag(ItemStack stack) {
        return stack.getOrCreateTagElement(NBT_LOC_TUTORIALMOD);
    }
}
