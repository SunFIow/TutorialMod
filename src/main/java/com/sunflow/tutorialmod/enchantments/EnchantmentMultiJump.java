package com.sunflow.tutorialmod.enchantments;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.InputUpdateEvent;

public class EnchantmentMultiJump extends EnchantmentBase {

	private int jumpNum = 0;
	private boolean spaceStillDown = false;

	public EnchantmentMultiJump() {
		super("multijump", Rarity.RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[] { EquipmentSlotType.FEET });
	}

	public void enchantmentFunction(InputUpdateEvent event) {
		if (event.getMovementInput().jump) {
			PlayerEntity player = event.getPlayer();
			player.sendStatusMessage(new StringTextComponent("Fall Distance: " + player.fallDistance), true);
			if (!player.onGround) {
				if (!this.spaceStillDown) {
					int level = EnchantmentHelper.getMaxEnchantmentLevel(this, player);
					if (this.jumpNum < level) {
//						ClientPlayerEntity cplayer = (ClientPlayerEntity) player;
						player.jump();
						player.setMotion(player.getMotion().add(0, 0.12f, 0));
						player.fallDistance = 0.0F;
						player.velocityChanged = true;
//						System.out.println(player);
//						net.minecraft.network.play.server.SPlayerAbilitiesPacket
//						((ClientPlayerEntity) player).connection.sendPacket(new SEntityVelocityPacket(player));
//						((ClientPlayerEntity) player).connection.sendPacket(new SPlayerAbilitiesPacket());
						jumpNum += 1;
//						player.sendMessage(new TranslationTextComponent("Jumping: {}", cplayer.movementInput.jump));
//						cplayer.movementInput.jump = true;
//						cplayer.connection.sendPacket(new CInputPacket(cplayer.moveStrafing, cplayer.moveForward, cplayer.movementInput.jump, cplayer.movementInput.sneak));
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
}
