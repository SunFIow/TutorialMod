package com.sunflow.tutorialmod.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.RenderLivingEvent;

public class RenderMobPositions {

	public static void render(RenderLivingEvent.Post<?, ?> event) {
		@SuppressWarnings("resource")
		LocalPlayer player = TutorialMod.proxy.getMinecraft().player;

		if (player.getMainHandItem().getItem() == Items.GHAST_TEAR)
			showMobs(event.getPoseStack(), event.getEntity());
	}

	private static void showMobs(PoseStack matrixStack, LivingEntity entity) {
		MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
		VertexConsumer builder = buffer.getBuffer(ModRenderTypes.getOverlayLines());

		Matrix4f positionMatrix = matrixStack.last().pose();

		if (entity instanceof Enemy) {
			redLine(builder, positionMatrix, 0, .5f, 0, 0, 6, 0);
		} else {
			greenLine(builder, positionMatrix, 0, .5f, 0, 0, 6, 0);
		}
	}

	private static void greenLine(VertexConsumer builder, Matrix4f positionMatrix, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2) {
		builder.vertex(positionMatrix, dx1, dy1, dz1)
				.color(0.0f, 1.0f, 0.0f, 1.0f)
				.endVertex();
		builder.vertex(positionMatrix, dx2, dy2, dz2)
				.color(0.0f, 1.0f, 0.0f, 1.0f)
				.endVertex();
	}

	private static void redLine(VertexConsumer builder, Matrix4f positionMatrix, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2) {
		builder.vertex(positionMatrix, dx1, dy1, dz1)
				.color(1.0f, 0.0f, 0.0f, 1.0f)
				.endVertex();
		builder.vertex(positionMatrix, dx2, dy2, dz2)
				.color(1.0f, 0.0f, 0.0f, 1.0f)
				.endVertex();
	}

}
