package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.init.ModBlocks;
import com.sunflow.tutorialmod.init.ModItems;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModSetup {

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

	public ModSetup() {
		itemGroup.setBackgroundImageName("tutorial.png");
		itemGroup2.setBackgroundImageName("tutorial2.png");
	}

	public void init() {
		itemGroup.setBackgroundImageName("tutorial.png");
		itemGroup2.setBackgroundImageName("tutorial2.png");
	}
}
