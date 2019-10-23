package com.sunflow.tutorialmod.enchantments;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EnchantmentMultiJump extends EnchantmentBase {

	private int jumpNum = 0;
	private boolean spaceStillDown = false;

	public EnchantmentMultiJump() {
		super("multijump", Rarity.RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[] { EquipmentSlotType.FEET });
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

	@SubscribeEvent
	public void enchantmentFunction(InputUpdateEvent event) {
		if (event.getMovementInput().jump) {
			PlayerEntity player = event.getPlayer();
			int level = EnchantmentHelper.getMaxEnchantmentLevel(this, player);
			if (level == 0) {
				return;
			}
			player.sendStatusMessage(new StringTextComponent("Fall Distance: " + player.fallDistance), true);
			if (!player.onGround) {
				if (!this.spaceStillDown) {
					if (this.jumpNum < level) {
						ClientPlayerEntity cplayer = (ClientPlayerEntity) player;
						cplayer.jump();
						cplayer.setMotion(player.getMotion().add(0, 0.12f, 0));
						cplayer.fallDistance = 0.0F;
						cplayer.velocityChanged = true;
						jumpNum += 1;

//						System.out.println(player);
//						net.minecraft.network.play.server.SPlayerAbilitiesPacket
//						cplayer.connection.sendPacket(new SEntityVelocityPacket(player));
//						cplayer.connection.sendPacket(new SPlayerAbilitiesPacket());
//						cplayer.connection.sendPacket(new CInputPacket(cplayer.moveStrafing, cplayer.moveForward, cplayer.movementInput.jump, cplayer.movementInput.sneak));
						cplayer.tick();
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

//	private void onUpdateWalkingPlayer(ClientPlayerEntity player) {
//		boolean flag = player.isSprinting();
//		if (flag != player.isSprinting()) {
//			CEntityActionPacket.Action centityactionpacket$action = flag ? CEntityActionPacket.Action.START_SPRINTING : CEntityActionPacket.Action.STOP_SPRINTING;
//			player.connection.sendPacket(new CEntityActionPacket(player, centityactionpacket$action));
//			player.serverSprintState = flag;
//		}
//
//		boolean flag3 = this.hasSneakingInput();
//		if (flag3 != this.serverSneakState) {
//			CEntityActionPacket.Action centityactionpacket$action1 = flag3 ? CEntityActionPacket.Action.START_SNEAKING : CEntityActionPacket.Action.STOP_SNEAKING;
//			this.connection.sendPacket(new CEntityActionPacket(this, centityactionpacket$action1));
//			this.serverSneakState = flag3;
//		}
//
//		if (this.isCurrentViewEntity()) {
//			AxisAlignedBB axisalignedbb = this.getBoundingBox();
//			double d0 = this.posX - this.lastReportedPosX;
//			double d1 = axisalignedbb.minY - this.lastReportedPosY;
//			double d2 = this.posZ - this.lastReportedPosZ;
//			double d3 = (double) (this.rotationYaw - this.lastReportedYaw);
//			double d4 = (double) (this.rotationPitch - this.lastReportedPitch);
//			++this.positionUpdateTicks;
//			boolean flag1 = d0 * d0 + d1 * d1 + d2 * d2 > 9.0E-4D || this.positionUpdateTicks >= 20;
//			boolean flag2 = d3 != 0.0D || d4 != 0.0D;
//			if (this.isPassenger()) {
//				Vec3d vec3d = this.getMotion();
//				this.connection.sendPacket(new CPlayerPacket.PositionRotationPacket(vec3d.x, -999.0D, vec3d.z, this.rotationYaw, this.rotationPitch, this.onGround));
//				flag1 = false;
//			} else if (flag1 && flag2) {
//				this.connection.sendPacket(new CPlayerPacket.PositionRotationPacket(this.posX, axisalignedbb.minY, this.posZ, this.rotationYaw, this.rotationPitch, this.onGround));
//			} else if (flag1) {
//				this.connection.sendPacket(new CPlayerPacket.PositionPacket(this.posX, axisalignedbb.minY, this.posZ, this.onGround));
//			} else if (flag2) {
//				this.connection.sendPacket(new CPlayerPacket.RotationPacket(this.rotationYaw, this.rotationPitch, this.onGround));
//			} else if (this.prevOnGround != this.onGround) {
//				this.connection.sendPacket(new CPlayerPacket(this.onGround));
//			}
//
//			if (flag1) {
//				this.lastReportedPosX = this.posX;
//				this.lastReportedPosY = axisalignedbb.minY;
//				this.lastReportedPosZ = this.posZ;
//				this.positionUpdateTicks = 0;
//			}
//
//			if (flag2) {
//				this.lastReportedYaw = this.rotationYaw;
//				this.lastReportedPitch = this.rotationPitch;
//			}
//
//			this.prevOnGround = this.onGround;
//			this.autoJumpEnabled = this.mc.gameSettings.autoJump;
//		}
//
//	}
}
