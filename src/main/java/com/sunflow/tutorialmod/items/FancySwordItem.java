package com.sunflow.tutorialmod.items;

import java.util.Random;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.items.base.ItemBase;
import com.sunflow.tutorialmod.util.handlers.PlayerSkinHandler;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FancySwordItem extends ItemBase {

	public FancySwordItem() {
		super("fancy_sword");
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if (player instanceof AbstractClientPlayerEntity) {
			AbstractClientPlayerEntity clplayer = (AbstractClientPlayerEntity) player;
			ResourceLocation loc = new ResourceLocation(TutorialMod.MODID, "textures/entity/skins/customplayer_" + clplayer.getSkinType() + "_" + new Random().nextInt(3) + ".png");
			PlayerSkinHandler.changePlayerSkin(clplayer, loc);
			return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
		}
		return super.onItemRightClick(world, player, hand);
	}

//	@Override
//	public void customOnItemRightClick(World world, PlayerEntity player, Hand hand) {
//		if (!(player instanceof AbstractClientPlayerEntity)) {
//			Reference.NEKOLOGGER.warn("U are not an AbstractClientPlayer!");
//			return;
//		}
//		AbstractClientPlayerEntity clplayer = (AbstractClientPlayerEntity) player;
//		ResourceLocation loc = new ResourceLocation(Reference.MOD_ID + ":textures/entity/customplayer_" + clplayer.getSkinType() + "_" + new Random().nextInt(3) + ".png");
//		PlayerSkinHandler.changePlayerSkin(clplayer, loc);
//	}
}
