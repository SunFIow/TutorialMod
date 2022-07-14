package com.sunflow.tutorialmod.rendering;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.util.NBTUtils;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class RenderNoArmor {
	private static final String NBT_LOC_ARMOR = "armor";
	private static final String NBT_LOC_BOOTS = "boots";
	private static final String NBT_LOC_LEGGINGS = "leggings";
	private static final String NBT_LOC_CHESTPLATE = "chestplate";
	private static final String NBT_LOC_HEAD = "head";

	public static void renderPre(RenderPlayerEvent.Pre event) {
		if (!TutorialModConfig.CLIENT.CONFIG_SHOW_ARMOR.get()) {
			Player player = (Player) event.getEntity();
			if (player != TutorialMod.proxy.getClientPlayer()) return;
			NonNullList<ItemStack> armorList = (NonNullList<ItemStack>) player.getArmorSlots();
			CompoundTag tag = NBTUtils.getModTag(player);
			CompoundTag armorNBT = tag.getCompound(NBT_LOC_ARMOR);

			armorNBT.put(NBT_LOC_BOOTS, armorList.get(0).serializeNBT());
			armorNBT.put(NBT_LOC_LEGGINGS, armorList.get(1).serializeNBT());
			armorNBT.put(NBT_LOC_CHESTPLATE, armorList.get(2).serializeNBT());
			armorNBT.put(NBT_LOC_HEAD, armorList.get(3).serializeNBT());

			tag.put(NBT_LOC_ARMOR, armorNBT);
			armorList.clear();
		}
	}

	public static void renderPost(RenderPlayerEvent.Post event) {
		if (!TutorialModConfig.CLIENT.CONFIG_SHOW_ARMOR.get()) {
			Player player = (Player) event.getEntity();
			if (player != TutorialMod.proxy.getClientPlayer()) return;
			NonNullList<ItemStack> armorList = (NonNullList<ItemStack>) player.getArmorSlots();
			CompoundTag tag = NBTUtils.getModTag(player);
			CompoundTag armorNBT = tag.getCompound(NBT_LOC_ARMOR);

			armorList.set(0, ItemStack.of(armorNBT.getCompound(NBT_LOC_BOOTS)));
			armorList.set(1, ItemStack.of(armorNBT.getCompound(NBT_LOC_LEGGINGS)));
			armorList.set(2, ItemStack.of(armorNBT.getCompound(NBT_LOC_CHESTPLATE)));
			armorList.set(3, ItemStack.of(armorNBT.getCompound(NBT_LOC_HEAD)));
		}
	}
}
