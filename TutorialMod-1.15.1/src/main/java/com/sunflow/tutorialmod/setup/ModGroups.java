package com.sunflow.tutorialmod.setup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModGroups {

	public final ItemGroup itemGroup = new ItemGroup("tutorialtab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.RUBY);
		}
	};

	public final ItemGroup itemGroup2 = new ItemGroup("tutorialtab2") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModBlocks.FIRSTBLOCK);
		}
	};

	public ModGroups() {
		itemGroup.setBackgroundImageName("tutorial.png");
		itemGroup2.setBackgroundImageName("tutorial2.png");
	}
}
