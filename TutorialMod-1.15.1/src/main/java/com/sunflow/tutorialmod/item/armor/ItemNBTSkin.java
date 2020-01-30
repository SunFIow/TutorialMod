package com.sunflow.tutorialmod.item.armor;

import com.sunflow.tutorialmod.item.armor.SkinUtil.SkinType;
import com.sunflow.tutorialmod.item.base.ItemBase;
import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.PlayerSkinPacket;

import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class ItemNBTSkin extends ItemBase {
	private final SkinType skin;

	public ItemNBTSkin(SkinType skin) { this.skin = skin; }

	public static RegistryObject<Item>[] create(DeferredRegister<Item> ITEMS) {
		@SuppressWarnings("unchecked")
		RegistryObject<Item>[] skins = new RegistryObject[SkinType.values().length];
		int i = 0;
		for (SkinType skin : SkinType.values()) skins[i++] = ITEMS.register(skin.getName() + "_skin", () -> new ItemNBTSkin(skin));
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
