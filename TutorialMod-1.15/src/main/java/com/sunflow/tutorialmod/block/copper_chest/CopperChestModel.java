package com.sunflow.tutorialmod.block.copper_chest;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CopperChestModel {
	protected ModelRenderer chestBelow; // a
	protected ModelRenderer chestLid; // d
	protected ModelRenderer chestKnob; // c

	public CopperChestModel() {
		this.chestBelow = new ModelRenderer(64, 64, 0, 19);
		this.chestBelow.addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F, 0.0F);

		this.chestLid = new ModelRenderer(64, 64, 0, 0);
		this.chestLid.addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F, 0.0F);
		this.chestLid.rotationPointY = 9.0F;
		this.chestLid.rotationPointZ = 1.0F;

		this.chestKnob = new ModelRenderer(64, 64, 0, 0);
		this.chestKnob.addBox(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F, 0.0F);
		this.chestKnob.rotationPointY = 8.0F;
	}

	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay) {

//		this.chestBelow = new ModelRenderer(64, 64, 0, 19);
//		this.chestBelow.addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F, 0.0F);
//
//		this.chestLid = new ModelRenderer(64, 64, 0, 0);
//		this.chestLid.addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F, 0.0F);
//		this.chestLid.rotationPointY = 9.0F;
//		this.chestLid.rotationPointZ = 1.0F;
//
//		this.chestKnob = new ModelRenderer(64, 64, 0, 0);
//		this.chestKnob.addBox(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F, 0.0F);
//		this.chestKnob.rotationPointY = 8.0F;

		chestLid.render(matrixStack, buffer, packedLight, packedOverlay);
		chestKnob.render(matrixStack, buffer, packedLight, packedOverlay);
		chestBelow.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void applyRotation(float angle) {
		chestLid.rotateAngleX = -(angle * ((float) Math.PI / 2F));
		chestKnob.rotateAngleX = chestLid.rotateAngleX;
	}
}