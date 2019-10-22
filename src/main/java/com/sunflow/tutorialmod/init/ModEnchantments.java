package com.sunflow.tutorialmod.init;

import java.util.ArrayList;
import java.util.List;

import com.sunflow.tutorialmod.enchantments.EnchantmentBase;
import com.sunflow.tutorialmod.enchantments.EnchantmentMultiJump;

import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModEnchantments {

	public static final List<EnchantmentBase> ENCHANTMENTS = new ArrayList<>();

	public static final EnchantmentMultiJump ENCHANTMENT_MULTIJUMP = new EnchantmentMultiJump();

	@SubscribeEvent
	public static void inputUpdateEvent(InputUpdateEvent event) {
		ENCHANTMENT_MULTIJUMP.enchantmentFunction(event);
	}
}
