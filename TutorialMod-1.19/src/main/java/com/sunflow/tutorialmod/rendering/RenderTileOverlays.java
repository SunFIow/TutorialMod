package com.sunflow.tutorialmod.rendering;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.RenderLevelLastEvent;

public class RenderTileOverlays {

	public static void render(RenderLevelLastEvent event) {
		// @SuppressWarnings("resource")
		Player player = TutorialMod.proxy.getClientPlayer();
		if (player.getMainHandItem().getItem() == Items.NETHER_STAR) {
			locateTileEntities(player, event.getPoseStack());
		}
	}

	private static void blueLine(VertexConsumer builder, Matrix4f positionMatrix, BlockPos pos, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2) {
		builder.vertex(positionMatrix, pos.getX() + dx1, pos.getY() + dy1, pos.getZ() + dz1)
				.color(0.0f, 0.0f, 1.0f, 1.0f)
				.endVertex();
		builder.vertex(positionMatrix, pos.getX() + dx2, pos.getY() + dy2, pos.getZ() + dz2)
				.color(0.0f, 0.0f, 1.0f, 1.0f)
				.endVertex();
	}

	private static void locateTileEntities(Player player, PoseStack matrixStack) {
		MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();

		VertexConsumer builder = buffer.getBuffer(ModRenderTypes.getOverlayLines());

		BlockPos playerPos = player.blockPosition();
		int px = playerPos.getX();
		int py = playerPos.getY();
		int pz = playerPos.getZ();
		Level world = player.getLevel();

		matrixStack.pushPose();

		@SuppressWarnings("resource")
		// Vector3d projectedView = TutorialMod.proxy.getMinecraft().gameRenderer.getActiveRenderInfo().getProjectedView();
		// matrixStack.translate(-projectedView.x, -projectedView.y, -projectedView.z);
		Quaternion projectedView = TutorialMod.proxy.getMinecraft().gameRenderer.getMainCamera().rotation();
		matrixStack.mulPose(projectedView);

		Matrix4f positionMatrix = matrixStack.last().pose();

		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		for (int dx = -10; dx <= 10; dx++) {
			for (int dy = -10; dy <= 10; dy++) {
				for (int dz = -10; dz <= 10; dz++) {
					pos.set(px + dx, py + dy, pz + dz);
					if (world.getBlockEntity(pos) != null) {
						blueLine(builder, positionMatrix, pos, 0, 0, 0, 1, 0, 0);
						blueLine(builder, positionMatrix, pos, 0, 1, 0, 1, 1, 0);
						blueLine(builder, positionMatrix, pos, 0, 0, 1, 1, 0, 1);
						blueLine(builder, positionMatrix, pos, 0, 1, 1, 1, 1, 1);

						blueLine(builder, positionMatrix, pos, 0, 0, 0, 0, 0, 1);
						blueLine(builder, positionMatrix, pos, 1, 0, 0, 1, 0, 1);
						blueLine(builder, positionMatrix, pos, 0, 1, 0, 0, 1, 1);
						blueLine(builder, positionMatrix, pos, 1, 1, 0, 1, 1, 1);

						blueLine(builder, positionMatrix, pos, 0, 0, 0, 0, 1, 0);
						blueLine(builder, positionMatrix, pos, 1, 0, 0, 1, 1, 0);
						blueLine(builder, positionMatrix, pos, 0, 0, 1, 0, 1, 1);
						blueLine(builder, positionMatrix, pos, 1, 0, 1, 1, 1, 1);
					}
				}
			}
		}

		matrixStack.popPose();

		RenderSystem.disableDepthTest();
		buffer.endBatch(ModRenderTypes.getOverlayLines());
	}
}
