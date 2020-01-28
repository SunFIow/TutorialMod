package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.setup.registration.Registration;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModGroups {

	public static final ItemGroup itemGroup = new ItemGroup("tutorialtab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(Registration.RUBY.get());
		}
	};

	public static final ItemGroup itemGroup2 = new ItemGroup("tutorialtab2") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(Registration.GLOWSTONE_GENERATOR_BLOCK.get());
		}
	};

	static {
		itemGroup.setBackgroundImageName("tutorial.png");
		itemGroup2.setBackgroundImageName("tutorial2.png");
	}
}
