package com.sunflow.tutorialmod.item;

import com.sunflow.tutorialmod.item.base.ItemBase;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class TestItem extends ItemBase {

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		player.sendMessage(new StringTextComponent(world.isRemote + ": " + player.fallDistance));

		return super.onItemRightClick(world, player, hand);
	}
}
