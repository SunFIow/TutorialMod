package com.sunflow.tutorialmod._testing;

import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.setup.ModGroups;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class PackageItem extends Item {
	public PackageItem() { super(new Item.Properties().group(ModGroups.itemGroup)); }

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if (world.isRemote) return super.onItemRightClick(world, player, hand);
		ItemStack stack = player.getHeldItem(hand);
		CompoundNBT testtag = stack.getOrCreateChildTag("testtag");
		testtag.putString("teststring", "this is a test string");
		Networking.sendToClient((ServerPlayerEntity) player, new TestPackage(testtag));
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
}
