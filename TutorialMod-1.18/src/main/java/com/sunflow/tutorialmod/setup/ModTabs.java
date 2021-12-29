package com.sunflow.tutorialmod.setup;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTabs {

	public static final CreativeModeTab ITEM_TAB = new SunCreativeModeTab("tutorialtab", () -> new ItemStack(Registration.SUN_ORE_OVERWORLD.block()), "tutorial");
	public static final CreativeModeTab ITEM_TAB2 = new SunCreativeModeTab("tutorialtab2", () -> new ItemStack(Registration.SUN_ORE_NETHER.block()), "tutorial2");

	private static class SunCreativeModeTab extends CreativeModeTab {
		private final Supplier<ItemStack> icon;

		public SunCreativeModeTab(String name, Supplier<ItemStack> icon) { super(name); this.icon = icon; }

		public SunCreativeModeTab(String name, Supplier<ItemStack> icon, String backgroundImage) {
			this(name, icon);
			this.setBackgroundImage(new ResourceLocation(TutorialMod.MODID, "textures/gui/container/creative_inventory/tab_" + backgroundImage + ".png"));
		}

		@Override
		public ItemStack makeIcon() { return icon.get(); }
	}
}
