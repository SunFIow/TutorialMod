package com.sunflow.tutorialmod.enchantment;

import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.MultiJumpPacket;
import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.util.NBTUtils;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.MovementInputUpdateEvent;

public class EnchantmentMultiJump extends Enchantment {

	public EnchantmentMultiJump() { super(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[] { EquipmentSlot.FEET }); }

	@Override
	public int getMinCost(int enchantmentLevel) { return enchantmentLevel * 15; }

	@Override
	public int getMaxCost(int enchantmentLevel) { return this.getMinCost(enchantmentLevel) + 10; }

	@Override
	public int getMaxLevel() { return 3; }

	public static void enchantmentFunction(MovementInputUpdateEvent event) {
		Player player = event.getPlayer();
		int jumpNum = NBTUtils.getModTag(player).getInt("jumpNum");
		boolean spaceStillDown = NBTUtils.getModTag(player).getBoolean("spaceStillDown");

		if (event.getInput().jumping) {
			int level = EnchantmentHelper.getEnchantmentLevel(Registration.ENCHANTMENT_MULTIJUMP.get(), player);
			if (level == 0) return;

			if (!player.isOnGround()) {
				if (!spaceStillDown) {
					if (jumpNum < level) {
						jumpNum += 1;
						player.jumpFromGround();
						player.getDeltaMovement().add(0, 0.12f, 0);
						player.fallDistance = -2.0F;
						player.hasImpulse = true;
						Networking.sendToServer(new MultiJumpPacket());
					}
				}
			} else jumpNum = 0;

			spaceStillDown = true;
		} else spaceStillDown = false;

		NBTUtils.getModTag(player).putInt("jumpNum", jumpNum);
		NBTUtils.getModTag(player).putBoolean("spaceStillDown", spaceStillDown);
	}
}
