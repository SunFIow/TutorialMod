package com.sunflow.tutorialmod.item.armor;

import com.sunflow.tutorialmod.item.armor.SkinUtil.SkinType;
import com.sunflow.tutorialmod.item.base.ItemBase;
import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.PlayerSkinPacket;
import com.sunflow.tutorialmod.setup.ModGroups;

import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemNBTSkin extends ItemBase {
	private final SkinType skin;

	public ItemNBTSkin(SkinType skin, Item.Properties properties) {
		super(skin.getName() + "_skin", properties);
		this.skin = skin;
	}

	public static Item[] create() {
		Item[] skins = new Item[SkinType.values().length];
		int i = 0;
		for (SkinType skin : SkinType.values()) skins[i++] = new ItemNBTSkin(skin, new Item.Properties().group(ModGroups.itemGroup));
		return skins;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if (skin == SkinType.DEFAULT) {
			if (!world.isRemote) return super.onItemRightClick(world, player, hand);
			ResourceLocation location = DefaultPlayerSkin.getDefaultSkin(player.getGameProfile().getId());
			Networking.sendToServer(new PlayerSkinPacket(player.getUniqueID(), location));
			return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
		}
		if (world.isRemote) return super.onItemRightClick(world, player, hand);
		String path = "textures/entity/skins/" + skin.getName();
		Networking.sendToConnected(new PlayerSkinPacket(player.getUniqueID(), path));
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
}
