package com.sunflow.tutorialmod.entity.renderer;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.entity.WeirdMobEntity;
import com.sunflow.tutorialmod.entity.model.WeirdMobModel;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WeirdMobRenderer extends MobRenderer<WeirdMobEntity, WeirdMobModel> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(TutorialMod.MODID, "textures/entity/weirdmob.png");

	public WeirdMobRenderer(EntityRendererManager manager) {
		super(manager, new WeirdMobModel(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(WeirdMobEntity entity) {

		return TEXTURE;
	}

}
