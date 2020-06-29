package com.sunflow.tutorialmod.enchantment;

import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.MultiJumpPacket;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.client.event.InputUpdateEvent;

public class EnchantmentMultiJump extends Enchantment {

	public EnchantmentMultiJump() { super(Rarity.RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[] { EquipmentSlotType.FEET }); }

	@Override
	public int getMinEnchantability(int enchantmentLevel) { return enchantmentLevel * 15; }

	@Override
	public int getMaxEnchantability(int enchantmentLevel) { return this.getMinEnchantability(enchantmentLevel) + 10; }

	@Override
	public int getMaxLevel() { return 3; }

	public static void enchantmentFunction(InputUpdateEvent event) {
		PlayerEntity player = event.getPlayer();
		int jumpNum = player.getPersistentData().getInt("jumpNum");
		boolean spaceStillDown = player.getPersistentData().getBoolean("spaceStillDown");

		if (event.getMovementInput().jump) {
			int level = EnchantmentHelper.getMaxEnchantmentLevel(Registration.ENCHANTMENT_MULTIJUMP.get(), player);
			if (level == 0) return;

			if (!player.func_233570_aj_()) {
				if (!spaceStillDown) {
					if (jumpNum < level) {
						jumpNum += 1;
						player.jump();
						player.setMotion(player.getMotion().add(0, 0.12f, 0));
						player.fallDistance = -2.0F;
						player.velocityChanged = true;
						Networking.sendToServer(new MultiJumpPacket());
					}
				}
			} else jumpNum = 0;

			spaceStillDown = true;
		} else spaceStillDown = false;

		player.getPersistentData().putInt("jumpNum", jumpNum);
		player.getPersistentData().putBoolean("spaceStillDown", spaceStillDown);
	}
}
