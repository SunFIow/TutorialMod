package com.sunflow.tutorialmod.item;

import com.sunflow.tutorialmod.setup.ModGroups;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class TestItem extends Item {

	public TestItem() { super(new Item.Properties().group(ModGroups.itemGroup)); }

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		player.sendMessage(new StringTextComponent(world.isRemote + ": " + player.fallDistance));

		return super.onItemRightClick(world, player, hand);
	}
}
