package com.sunflow.tutorialmod.entity.centaur;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CentaurModel extends EntityModel<CentaurEntity> {
//	private RendererModel body;

	private ModelRenderer villagerHead;
	private ModelRenderer leg1;
	private ModelRenderer villagerArms2;
	private ModelRenderer villagerArms1;
	private ModelRenderer leg4;
	private ModelRenderer villagerArms0;
	private ModelRenderer leg2;
	private ModelRenderer body0;
	private ModelRenderer leg3;
	private ModelRenderer body1;
	private ModelRenderer villagerBody1;
	private ModelRenderer villagerBody0;
	private ModelRenderer villagerHeadChild;

	public CentaurModel() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.villagerArms2 = new ModelRenderer(this, 40, 38);
		this.villagerArms2.setRotationPoint(0.0F, -10.0F, -3.0F);
		this.villagerArms2.addBox(-4.0F, 5.0F, -3.0F, 8, 4, 4, 0.0F);
		this.setRotateAngle(this.villagerArms2, -0.7499679923057556F, 0.0F, 0.0F);
		this.body0 = new ModelRenderer(this, 18, 4);
		this.body0.setRotationPoint(0.0F, 5.0F, 2.0F);
		this.body0.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
		this.setRotateAngle(this.body0, 1.5707963705062866F, 0.0F, 0.0F);
		this.leg4 = new ModelRenderer(this, 0, 16);
		this.leg4.setRotationPoint(4.0F, 12.0F, -6.0F);
		this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.body1 = new ModelRenderer(this, 52, 0);
		this.body1.setRotationPoint(0.0F, 5.0F, 2.0F);
		this.body1.addBox(-2.0F, 2.0F, -8.0F, 4, 6, 1, 0.0F);
		this.setRotateAngle(this.body1, 1.5707963705062866F, 0.0F, 0.0F);
		this.leg3 = new ModelRenderer(this, 0, 16);
		this.leg3.setRotationPoint(-4.0F, 12.0F, -6.0F);
		this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.leg1 = new ModelRenderer(this, 0, 16);
		this.leg1.setRotationPoint(-4.0F, 12.0F, 7.0F);
		this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.villagerArms0 = new ModelRenderer(this, 44, 22);
		this.villagerArms0.setRotationPoint(0.0F, -10.0F, -3.0F);
		this.villagerArms0.addBox(-8.0F, 1.0F, -3.0F, 4, 8, 4, 0.0F);
		this.setRotateAngle(this.villagerArms0, -0.7499679923057556F, 0.0F, 0.0F);
		this.villagerHead = new ModelRenderer(this, 0, 0);
		this.villagerHead.setRotationPoint(0.0F, -10.0F, -3.0F);
		this.villagerHead.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
		this.villagerBody1 = new ModelRenderer(this, 0, 38);
		this.villagerBody1.setRotationPoint(0.0F, -10.0F, -3.0F);
		this.villagerBody1.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
		this.villagerBody0 = new ModelRenderer(this, 16, 20);
		this.villagerBody0.setRotationPoint(0.0F, -10.0F, 0.0F);
		this.villagerBody0.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
		this.villagerArms1 = new ModelRenderer(this, 44, 22);
		this.villagerArms1.setRotationPoint(0.0F, -10.0F, -3.0F);
		this.villagerArms1.addBox(4.0F, 1.0F, -3.0F, 4, 8, 4, 0.0F);
		this.setRotateAngle(this.villagerArms1, -0.7499679923057556F, 0.0F, 0.0F);
		this.villagerHeadChild = new ModelRenderer(this, 24, 0);
		this.villagerHeadChild.setRotationPoint(0.0F, -2.0F, 0.0F);
		this.villagerHeadChild.addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F);
		this.leg2 = new ModelRenderer(this, 0, 16);
		this.leg2.setRotationPoint(4.0F, 12.0F, 7.0F);
		this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.villagerHead.addChild(this.villagerHeadChild);
	}

	/**
	 * Sets this entity's model rotation angles
	 */
	@Override
	public void setRotationAngles(CentaurEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.villagerHead.rotateAngleX = headPitch * 0.017453292F;
		this.villagerHead.rotateAngleY = netHeadYaw * 0.017453292F;

		this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.villagerArms2.render(matrixStack, buffer, packedLight, packedOverlay);
		this.body0.render(matrixStack, buffer, packedLight, packedOverlay);
		this.leg4.render(matrixStack, buffer, packedLight, packedOverlay);
		this.body1.render(matrixStack, buffer, packedLight, packedOverlay);
		this.leg3.render(matrixStack, buffer, packedLight, packedOverlay);
		this.leg1.render(matrixStack, buffer, packedLight, packedOverlay);
		this.villagerArms0.render(matrixStack, buffer, packedLight, packedOverlay);
		this.villagerHead.render(matrixStack, buffer, packedLight, packedOverlay);
		this.villagerBody1.render(matrixStack, buffer, packedLight, packedOverlay);
		this.villagerBody0.render(matrixStack, buffer, packedLight, packedOverlay);
		this.villagerArms1.render(matrixStack, buffer, packedLight, packedOverlay);
		this.leg2.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
