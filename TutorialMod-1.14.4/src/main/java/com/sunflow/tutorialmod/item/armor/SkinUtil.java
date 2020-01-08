package com.sunflow.tutorialmod.item.armor;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class SkinUtil {
	public static ItemStack createSkin(ItemStack stack, SkinType type) {
		CompoundNBT tag = stack.getOrCreateTag();
		tag.putString("Skin", type.getName());
		stack.setTag(tag);
		return stack;
	}

	public static String getRegistryNameFromNBT(ItemStack stack) {
		String type = stack.getOrCreateTag().getString("Skin");
		return type != "" ? type : "error";
	}

	public static enum SkinType {
		DEFAULT("default"), SUNFLOW("sunflow"), BEKESCSABA99("beke"), PHANTOM("phantom");

		private String name;

		private SkinType(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}
}
