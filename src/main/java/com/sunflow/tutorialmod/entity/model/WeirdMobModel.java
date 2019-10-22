package com.sunflow.tutorialmod.entity.model;

import com.sunflow.tutorialmod.entity.WeirdMobEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class WeirdMobModel extends EntityModel<WeirdMobEntity> {
	private RendererModel body;

	public WeirdMobModel() {
		body = new RendererModel(this, 0, 0);
		body.addBox(-3, -3, -3, 6, 6, 6);
	}

	@Override
	public void render(WeirdMobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		body.render(scale);
	}
}
