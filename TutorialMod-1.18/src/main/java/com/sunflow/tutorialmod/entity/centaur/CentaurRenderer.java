package com.sunflow.tutorialmod.entity.centaur;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CentaurRenderer extends MobRenderer<CentaurEntity, CentaurModel> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(TutorialMod.MODID, "textures/entity/centaur.png");

	public CentaurRenderer(EntityRendererProvider.Context context) {
		super(context, new CentaurModel(context.bakeLayer(CentaurModel.CENTAUR_LAYER)), 0.7f);
	}

	@Override
	public ResourceLocation getTextureLocation(CentaurEntity entity) { return TEXTURE; }

}
