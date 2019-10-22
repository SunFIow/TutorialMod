package com.sunflow.tutorialmod.items;

import com.sunflow.tutorialmod.entity.GrenadeEntity;
import com.sunflow.tutorialmod.items.base.ItemBase;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class GrenadeItem extends ItemBase {

	public GrenadeItem() {
		super("grenade");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		if (!player.abilities.isCreativeMode) {
			itemstack.shrink(1);
		}

		world.playSound((PlayerEntity) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		if (!world.isRemote) {
			GrenadeEntity grenadeentity = new GrenadeEntity(world, player);
			grenadeentity.func_213884_b(itemstack);
			grenadeentity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
			world.addEntity(grenadeentity);
		}

		player.addStat(Stats.ITEM_USED.get(this));
		return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
	}
}
