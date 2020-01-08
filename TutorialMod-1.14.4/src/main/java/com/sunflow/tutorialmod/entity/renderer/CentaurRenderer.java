package com.sunflow.tutorialmod.entity.renderer;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.entity.CentaurEntity;
import com.sunflow.tutorialmod.entity.model.CentaurModel;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CentaurRenderer extends MobRenderer<CentaurEntity, CentaurModel> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(TutorialMod.MODID, "textures/entity/centaur.png");

	public CentaurRenderer(EntityRendererManager manager) {
		super(manager, new CentaurModel(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(CentaurEntity entity) {
		return TEXTURE;
	}
//
//	@Override
//	protected void applyRotations(CentaurEntity entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
//		super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
//	}
}
