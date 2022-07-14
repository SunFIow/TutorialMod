package com.sunflow.tutorialmod.entity.weirdmob;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class WeirdMobModel extends EntityModel<WeirdMobEntity> {
	public static final String BODY = "body";
	public static final ModelLayerLocation WERIDMOB_LAYER = new ModelLayerLocation(new ResourceLocation(TutorialMod.MODID, "weirdmob"), BODY);

	protected final ModelPart modelBody;

	public WeirdMobModel(ModelPart root) {
		this.modelBody = root.getChild(BODY);
	}

	public static LayerDefinition createBodyLayer() {
		// HumanoidModel.createMesh(CubeDeformation.NONE, 1.0f);
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild(BODY, CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 8.0F, -8.0F, 16.0F, 16.0F, 16.0F), PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
		// pPoseStack.pushPose();
		// pPoseStack.scale(0.6f, 0.6f, 0.6f);
		// pPoseStack.translate(0.0d, 1.0d, 0.0d);
		modelBody.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
		// pPoseStack.popPose();
	}

	/** Sets this entity's model rotation angles */
	@Override
	public void setupAnim(WeirdMobEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		// No need to set rotations
		this.modelBody.xRot = pHeadPitch * ((float) Math.PI / 180F);
		this.modelBody.yRot = pNetHeadYaw * ((float) Math.PI / 180F);
	}

	/** Sets this entity's model rotation angles (with partialTicks) */
	@Override
	public void prepareMobModel(WeirdMobEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick) {
		super.prepareMobModel(pEntity, pLimbSwing, pLimbSwingAmount, pPartialTick);
	}
}
