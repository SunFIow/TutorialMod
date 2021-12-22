package com.sunflow.tutorialmod.block.copper_chest;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CopperChestModel {
	private final ModelPart lid;
	private final ModelPart bottom;
	private final ModelPart lock;

	public CopperChestModel(BlockEntityRendererProvider.Context context) {
		ModelPart modelpart = context.bakeLayer(ModelLayers.CHEST);
		this.bottom = modelpart.getChild("bottom");
		this.lid = modelpart.getChild("lid");
		this.lock = modelpart.getChild("lock");
	}

	public void render(VertexConsumer vertexConsumer, PoseStack poseStack, int combinedLight, int combinedOverlay) {
		lid.render(poseStack, vertexConsumer, combinedLight, combinedOverlay);
		lock.render(poseStack, vertexConsumer, combinedLight, combinedOverlay);
		bottom.render(poseStack, vertexConsumer, combinedLight, combinedOverlay);
	}

	public void applyRotation(float angle) {
		lid.xRot = -(angle * ((float) Math.PI / 2F));
		lock.xRot = lid.xRot;
	}
}