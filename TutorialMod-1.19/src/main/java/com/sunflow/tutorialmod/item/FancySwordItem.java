package com.sunflow.tutorialmod.item;

import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.PlayerSkinPacket;
import com.sunflow.tutorialmod.setup.ModTabs;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FancySwordItem extends Item {

	public FancySwordItem() { super(new Item.Properties().tab(ModTabs.ITEM_TAB)); }

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
		if (pLevel.isClientSide()) return super.use(pLevel, pPlayer, pUsedHand);
		int num = pLevel.getRandom().nextInt(3);
		String location = "textures/entity/skins/customplayer_" + num;
		Networking.sendToConnected(new PlayerSkinPacket(pPlayer.getUUID(), location));
		return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
	}

}
