package com.sunflow.tutorialmod.item.armor;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.item.armor.ItemNBTSkin.SkinType;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ArmorSkinBase extends ArmorBase {

	private String armorTexturePath;
	private String skinType;
	private int renderIndex;

	public ArmorSkinBase(SkinType type, ArmorMaterial armorMaterial, int renderIndex, EquipmentSlot equipmentSlot) {
		super(armorMaterial, equipmentSlot);
		this.armorTexturePath = TutorialMod.MODID + ":textures/models/armor/" + type.getName() + "_model";
		this.skinType = "default";
		this.renderIndex = renderIndex;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		return this.armorTexturePath + "_" + this.skinType + "_layer_" + this.renderIndex + ".png";
	}

	@OnlyIn(Dist.CLIENT)
	public PlayerModel<AbstractClientPlayer> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel _default) {
		if (itemStack != ItemStack.EMPTY && itemStack.getItem() instanceof ArmorSkinBase) {
			this.skinType = ((AbstractClientPlayer) entityLiving).getModelName();
			EntityRenderer<? extends Player> p = TutorialMod.proxy.getMinecraft().getEntityRenderDispatcher().getSkinMap().get(skinType);
			if (p instanceof PlayerRenderer pr) {
				PlayerModel<AbstractClientPlayer> model = pr.getModel();
				// PlayerModel<Player> model = new PlayerModel<>(0f, this.skinType == "slim");
				// PlayerModel<AbstractClientPlayer> model = new PlayerModel<>(this.skinType == "slim" ? ModelLayers.PLAYER_SLIM : ModelLayers.PLAYER, this.skinType == "slim"), 0.5f);

				model.head.visible = armorSlot == EquipmentSlot.HEAD;
				model.body.visible = armorSlot == EquipmentSlot.CHEST;
				model.leftArm.visible = armorSlot == EquipmentSlot.CHEST;
				model.rightArm.visible = armorSlot == EquipmentSlot.CHEST;
				model.leftLeg.visible = armorSlot == EquipmentSlot.LEGS;
				model.rightLeg.visible = armorSlot == EquipmentSlot.LEGS;

				model.attackTime = _default.attackTime;
				model.crouching = _default.crouching;
				model.riding = _default.riding;
				model.rightArmPose = _default.rightArmPose;
				model.leftArmPose = _default.leftArmPose;

				return model;
			}
		}
		return null;
	}
}
