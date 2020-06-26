package com.sunflow.tutorialmod.item;

import java.util.Random;

import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.PlayerSkinPacket;
import com.sunflow.tutorialmod.setup.ModGroups;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class FancySwordItem extends Item {

	public FancySwordItem() { super(new Item.Properties().group(ModGroups.itemGroup)); }

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if (world.isRemote) return super.onItemRightClick(world, player, hand);
		int num = new Random().nextInt(3);
		String location = "textures/entity/skins/customplayer_" + num;
		Networking.sendToConnected(new PlayerSkinPacket(player.getUniqueID(), location));
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
}
