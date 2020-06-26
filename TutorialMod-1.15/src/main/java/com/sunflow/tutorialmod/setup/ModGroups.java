package com.sunflow.tutorialmod.setup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModGroups {
	public static final ItemGroup itemGroup = (new ItemGroup("tutorialtab") {
		@Override
		public ItemStack createIcon() { return new ItemStack(Registration.RUBY.get()); }
	}).setBackgroundImageName("tutorial.png");

	public static final ItemGroup itemGroup2 = (new ItemGroup("tutorialtab2") {
		@Override
		public ItemStack createIcon() { return new ItemStack(Registration.GLOWSTONE_GENERATOR_BLOCK.get()); }
	}).setBackgroundImageName("tutorial2.png");
}
