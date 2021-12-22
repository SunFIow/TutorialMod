package com.sunflow.tutorialmod.setup;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModGroups extends CreativeModeTab {

	public static final ModGroups ITEM_GROUP = new ModGroups("tutorialtab", () -> new ItemStack(Registration.SUN_ORE_OVERWORLD.block()), "tutorial");
	public static final ModGroups ITEM_GROUP2 = new ModGroups("tutorialtab2", () -> new ItemStack(Registration.SUN_ORE_NETHER.block()), "tutorial2");

	public final String name;
	private final Supplier<ItemStack> icon;

	public ModGroups(String name, Supplier<ItemStack> icon) { super(name); this.name = name; this.icon = icon; }

	public ModGroups(String name, Supplier<ItemStack> icon, String backgroundImage) {
		this(name, icon);
		this.setBackgroundImage(new ResourceLocation(TutorialMod.MODID, "textures/gui/container/creative_inventory/tab_" + backgroundImage + ".png"));
	}

	@Override
	public ItemStack makeIcon() { return icon.get(); }
}
