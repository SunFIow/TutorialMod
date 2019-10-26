package com.sunflow.tutorialmod.entity.model;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FancyModel extends Model {
	protected RendererModel block;

	public FancyModel() {
		block = new RendererModel(this, 0, 0).setTextureSize(64, 64);
		block.addBox(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);
		block.rotationPointX = 1.0F;
		block.rotationPointY = 6.0F;
		block.rotationPointZ = 1.0F;
	}

	public void renderAll() {
		block.render(0.0625F);
	}
}