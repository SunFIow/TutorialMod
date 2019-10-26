package com.sunflow.tutorialmod.entity.model;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CopperChestModel extends Model {
	protected RendererModel chestLid;
	protected RendererModel chestBelow;
	protected RendererModel chestKnob;

	public CopperChestModel() {
		chestLid = new RendererModel(this, 0, 0).setTextureSize(64, 64);
		chestLid.addBox(0.0F, -5.0F, -14.0F, 14, 5, 14, 0.0F);
		chestLid.rotationPointX = 1.0F;
		chestLid.rotationPointY = 7.0F;
		chestLid.rotationPointZ = 15.0F;
		chestKnob = new RendererModel(this, 0, 0).setTextureSize(64, 64);
		chestKnob.addBox(-1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F);
		chestKnob.rotationPointX = 8.0F;
		chestKnob.rotationPointY = 7.0F;
		chestKnob.rotationPointZ = 15.0F;
		chestBelow = new RendererModel(this, 0, 19).setTextureSize(64, 64);
		chestBelow.addBox(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);
		chestBelow.rotationPointX = 1.0F;
		chestBelow.rotationPointY = 6.0F;
		chestBelow.rotationPointZ = 1.0F;
	}

	public void renderAll() {
		chestKnob.rotateAngleX = chestLid.rotateAngleX;
		chestLid.render(0.0625F);
		chestKnob.render(0.0625F);
		chestBelow.render(0.0625F);
	}

	public RendererModel getLid() {
		return chestLid;
	}
}