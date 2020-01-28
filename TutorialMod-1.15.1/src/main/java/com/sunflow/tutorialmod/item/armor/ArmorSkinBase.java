package com.sunflow.tutorialmod.item.armor;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.item.armor.SkinUtil.SkinType;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ArmorSkinBase extends ArmorBase {

	private String armorTexturePath;
	private String skinType;
	private int renderIndex;

	public ArmorSkinBase(SkinType type, IArmorMaterial armorMaterial, int renderIndex, EquipmentSlotType equipmentSlot) {
		super(armorMaterial, equipmentSlot);
		this.armorTexturePath = TutorialMod.MODID + ":textures/models/armor/" + type.getName() + "_model";
		this.skinType = "default";
		this.renderIndex = renderIndex;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		return this.armorTexturePath + "_" + this.skinType + "_layer_" + this.renderIndex + ".png";
	}

	@SuppressWarnings("unchecked")
	@Override
	@OnlyIn(Dist.CLIENT)
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
		if (itemStack != ItemStack.EMPTY && itemStack.getItem() instanceof ArmorSkinBase) {
			this.skinType = ((AbstractClientPlayerEntity) entityLiving).getSkinType();
			PlayerModel<PlayerEntity> model = new PlayerModel<PlayerEntity>(0f, this.skinType == "slim");

			model.bipedHead.showModel = armorSlot == EquipmentSlotType.HEAD;
			model.bipedBody.showModel = armorSlot == EquipmentSlotType.CHEST;
			model.bipedLeftArm.showModel = armorSlot == EquipmentSlotType.CHEST;
			model.bipedRightArm.showModel = armorSlot == EquipmentSlotType.CHEST;
			model.bipedLeftLeg.showModel = armorSlot == EquipmentSlotType.LEGS;
			model.bipedRightLeg.showModel = armorSlot == EquipmentSlotType.LEGS;

			model.isChild = _default.isChild;
			// .isSneak
			model.field_228270_o_ = _default.field_228270_o_;
			model.isSitting = _default.isSitting;
			model.rightArmPose = _default.rightArmPose;
			model.leftArmPose = _default.leftArmPose;
			return (A) model;
		}
		return null;
	}
}
