package com.sunflow.tutorialmod.rendering;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.config.TutorialModConfig;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class RenderNoArmor {

	@SuppressWarnings("resource")
	public static void renderPre(RenderPlayerEvent.Pre event) {
		if (!TutorialMod.proxy.getMinecraft().gameSettings.showDebugInfo && TutorialModConfig.CONFIG_SHOW_OVERLAY.get()) {
			PlayerEntity player = (PlayerEntity) event.getEntity();
			if (player != TutorialMod.proxy.getClientPlayer()) return;
			NonNullList<ItemStack> armorList = (NonNullList<ItemStack>) player.getArmorInventoryList();
			CompoundNBT nbt = player.getPersistentData();
			CompoundNBT tutorialmodNBT = nbt.getCompound("tutorialmod");
			CompoundNBT armorNBT = nbt.getCompound("armor");

			armorNBT.put("boots", armorList.get(0).serializeNBT());
			armorNBT.put("leggings", armorList.get(1).serializeNBT());
			armorNBT.put("chestplate", armorList.get(2).serializeNBT());
			armorNBT.put("head", armorList.get(3).serializeNBT());

			tutorialmodNBT.put("armor", armorNBT);
			nbt.put("tutorialmod", tutorialmodNBT);

			armorList.clear();
		}
	}

	public static void renderPost(RenderPlayerEvent.Post event) {
		if (!TutorialMod.proxy.getMinecraft().gameSettings.showDebugInfo && TutorialModConfig.CONFIG_SHOW_OVERLAY.get()) {
			PlayerEntity player = (PlayerEntity) event.getEntity();
			if (player != TutorialMod.proxy.getClientPlayer()) return;
			NonNullList<ItemStack> armorList = (NonNullList<ItemStack>) player.getArmorInventoryList();
			CompoundNBT nbt = player.getPersistentData();
			CompoundNBT tutorialmodNBT = nbt.getCompound("tutorialmod");
			CompoundNBT armorNBT = tutorialmodNBT.getCompound("armor");

			armorList.set(0, ItemStack.read(armorNBT.getCompound("boots")));
			armorList.set(1, ItemStack.read(armorNBT.getCompound("leggings")));
			armorList.set(2, ItemStack.read(armorNBT.getCompound("chestplate")));
			armorList.set(3, ItemStack.read(armorNBT.getCompound("head")));
		}
	}
}
