package com.sunflow.tutorialmod.item;

import com.sunflow.tutorialmod.setup.ModTabs;

import net.minecraft.Util;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TestItem extends Item {

	public TestItem() { super(new Item.Properties().tab(ModTabs.ITEM_TAB)); }

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
		pPlayer.sendMessage(new TextComponent(pLevel.isClientSide() + ": " + pPlayer.fallDistance), Util.NIL_UUID);

		return super.use(pLevel, pPlayer, pUsedHand);
	}
}
