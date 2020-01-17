package com.sunflow.tutorialmod.entity.weirdmob;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class WeirdMobModel extends EntityModel<WeirdMobEntity> {
	private ModelRenderer body;

	public WeirdMobModel() {
		body = new ModelRenderer(this, 0, 0);
		body.func_228300_a_(-3, -3, -3, 6, 6, 6);
	}

//	@Override
//	public void render(WeirdMobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
//		body.render(scale);
//	}

	// render
	@Override
	public void func_225597_a_(WeirdMobEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
	}

	@Override
	public void func_225598_a_(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
		body.func_228308_a_(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_);
	}
}
