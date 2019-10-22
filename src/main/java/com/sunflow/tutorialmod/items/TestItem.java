package com.sunflow.tutorialmod.items;

import com.sunflow.tutorialmod.items.base.ItemBase;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class TestItem extends ItemBase {

	public TestItem() {
		super("testitem");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if (!world.isRemote) {
			ItemStack stack = player.getHeldItem(hand);
			CompoundNBT tag = stack.getOrCreateChildTag("tutorialmod");
			if (player.isSneaking()) {
				tag.putInt("counter", tag.getInt("counter") + 1);
			} else {
				player.sendStatusMessage(new StringTextComponent("Counter: " + tag.getInt("counter")), true);
			}
		}

		return super.onItemRightClick(world, player, hand);
	}
}
