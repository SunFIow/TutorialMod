package com.sunflow.tutorialmod.enchantment;

import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.MultiJumpPacket;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EnchantmentMultiJump extends Enchantment {

	private int jumpNum = 0;
	private boolean spaceStillDown = false;

	public EnchantmentMultiJump() {
		super(Rarity.RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[] { EquipmentSlotType.FEET });
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return enchantmentLevel * 15;
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return this.getMinEnchantability(enchantmentLevel) + 10;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void enchantmentFunction(InputUpdateEvent event) {
		if (event.getMovementInput().jump) {
			PlayerEntity player = event.getPlayer();
			int level = EnchantmentHelper.getMaxEnchantmentLevel(this, player);
			if (level == 0) {
				return;
			}
			if (!player.onGround) {
				if (!this.spaceStillDown) {
					if (this.jumpNum < level) {
						jumpNum += 1;
						player.jump();
						player.setMotion(player.getMotion().add(0, 0.12f, 0));
						player.fallDistance = -2.0F;
						player.velocityChanged = true;
						Networking.sendToServer(new MultiJumpPacket());
					}
				}
			} else {
				this.jumpNum = 0;
			}
			this.spaceStillDown = true;
		} else {
			this.spaceStillDown = false;
		}
	}
}
