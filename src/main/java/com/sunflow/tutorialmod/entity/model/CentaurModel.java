package com.sunflow.tutorialmod.entity.model;

import com.sunflow.tutorialmod.entity.CentaurEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CentaurModel extends EntityModel<CentaurEntity> {
//	private RendererModel body;

	private RendererModel villagerHead;
	private RendererModel leg1;
	private RendererModel villagerArms2;
	private RendererModel villagerArms1;
	private RendererModel leg4;
	private RendererModel villagerArms0;
	private RendererModel leg2;
	private RendererModel body0;
	private RendererModel leg3;
	private RendererModel body1;
	private RendererModel villagerBody1;
	private RendererModel villagerBody0;
	private RendererModel villagerHeadChild;

	public CentaurModel() {
//		body = new RendererModel(this, 0, 0);
//		body.addBox(-3, -3, -3, 6, 6, 6);

		this.textureWidth = 64;
		this.textureHeight = 64;
		this.villagerArms2 = new RendererModel(this, 40, 38);
		this.villagerArms2.setRotationPoint(0.0F, -10.0F, -3.0F);
		this.villagerArms2.addBox(-4.0F, 5.0F, -3.0F, 8, 4, 4, 0.0F);
		this.setRotateAngle(this.villagerArms2, -0.7499679923057556F, 0.0F, 0.0F);
		this.body0 = new RendererModel(this, 18, 4);
		this.body0.setRotationPoint(0.0F, 5.0F, 2.0F);
		this.body0.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
		this.setRotateAngle(this.body0, 1.5707963705062866F, 0.0F, 0.0F);
		this.leg4 = new RendererModel(this, 0, 16);
		this.leg4.setRotationPoint(4.0F, 12.0F, -6.0F);
		this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.body1 = new RendererModel(this, 52, 0);
		this.body1.setRotationPoint(0.0F, 5.0F, 2.0F);
		this.body1.addBox(-2.0F, 2.0F, -8.0F, 4, 6, 1, 0.0F);
		this.setRotateAngle(this.body1, 1.5707963705062866F, 0.0F, 0.0F);
		this.leg3 = new RendererModel(this, 0, 16);
		this.leg3.setRotationPoint(-4.0F, 12.0F, -6.0F);
		this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.leg1 = new RendererModel(this, 0, 16);
		this.leg1.setRotationPoint(-4.0F, 12.0F, 7.0F);
		this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.villagerArms0 = new RendererModel(this, 44, 22);
		this.villagerArms0.setRotationPoint(0.0F, -10.0F, -3.0F);
		this.villagerArms0.addBox(-8.0F, 1.0F, -3.0F, 4, 8, 4, 0.0F);
		this.setRotateAngle(this.villagerArms0, -0.7499679923057556F, 0.0F, 0.0F);
		this.villagerHead = new RendererModel(this, 0, 0);
		this.villagerHead.setRotationPoint(0.0F, -10.0F, -3.0F);
		this.villagerHead.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
		this.villagerBody1 = new RendererModel(this, 0, 38);
		this.villagerBody1.setRotationPoint(0.0F, -10.0F, -3.0F);
		this.villagerBody1.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
		this.villagerBody0 = new RendererModel(this, 16, 20);
		this.villagerBody0.setRotationPoint(0.0F, -10.0F, 0.0F);
		this.villagerBody0.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
		this.villagerArms1 = new RendererModel(this, 44, 22);
		this.villagerArms1.setRotationPoint(0.0F, -10.0F, -3.0F);
		this.villagerArms1.addBox(4.0F, 1.0F, -3.0F, 4, 8, 4, 0.0F);
		this.setRotateAngle(this.villagerArms1, -0.7499679923057556F, 0.0F, 0.0F);
		this.villagerHeadChild = new RendererModel(this, 24, 0);
		this.villagerHeadChild.setRotationPoint(0.0F, -2.0F, 0.0F);
		this.villagerHeadChild.addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F);
		this.leg2 = new RendererModel(this, 0, 16);
		this.leg2.setRotationPoint(4.0F, 12.0F, 7.0F);
		this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.villagerHead.addChild(this.villagerHeadChild);
	}

	@Override
	public void render(CentaurEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
//		body.render(scale);
		this.villagerArms2.render(scale);
		this.body0.render(scale);
		this.leg4.render(scale);
		this.body1.render(scale);
		this.leg3.render(scale);
		this.leg1.render(scale);
		this.villagerArms0.render(scale);
		this.villagerHead.render(scale);
		this.villagerBody1.render(scale);
		this.villagerBody0.render(scale);
		this.villagerArms1.render(scale);
		this.leg2.render(scale);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(CentaurEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		this.villagerHead.rotateAngleX = headPitch * 0.017453292F;
		this.villagerHead.rotateAngleY = netHeadYaw * 0.017453292F;

		this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}
}
