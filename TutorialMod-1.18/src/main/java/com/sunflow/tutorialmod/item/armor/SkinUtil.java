package com.sunflow.tutorialmod.item.armor;

import com.sunflow.tutorialmod.util.NBTUtils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class SkinUtil {
	public static ItemStack createSkin(ItemStack stack, SkinType type) {
		CompoundTag tag = NBTUtils.getModTag(stack);
		tag.putString("skin", type.getName());
		return stack;
	}

	public static String getRegistryNameFromNBT(ItemStack stack) {
		String type = NBTUtils.getModTag(stack).getString("skin");
		return !type.equals("") ? type : "error";
	}

	public enum SkinType {
		DEFAULT("default"), SUNFLOW("sunflow"), BEKESCSABA99("beke"), PHANTOM("phantom");

		private String name;

		private SkinType(String name) { this.name = name; }

		public String getName() { return name; }
	}
}
