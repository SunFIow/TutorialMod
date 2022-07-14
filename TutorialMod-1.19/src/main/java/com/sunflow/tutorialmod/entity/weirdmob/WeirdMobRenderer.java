package com.sunflow.tutorialmod.entity.weirdmob;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WeirdMobRenderer extends MobRenderer<WeirdMobEntity, WeirdMobModel> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(TutorialMod.MODID, "textures/entity/weirdmob.png");

	public WeirdMobRenderer(EntityRendererProvider.Context context) {
		super(context, new WeirdMobModel(context.bakeLayer(WeirdMobModel.WERIDMOB_LAYER)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(WeirdMobEntity entity) { return TEXTURE; }
}
