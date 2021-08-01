package com.sunflow.tutorialmod.rendering;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class RenderTileOverlays {

	public static void render(RenderWorldLastEvent event) {
		@SuppressWarnings("resource")
		ClientPlayerEntity player = TutorialMod.proxy.getMinecraft().player;

		if (player.getHeldItemMainhand().getItem() == Items.NETHER_STAR) {
			locateTileEntities(player, event.getMatrixStack());
		}
	}

	private static void blueLine(IVertexBuilder builder, Matrix4f positionMatrix, BlockPos pos, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2) {
		builder.pos(positionMatrix, pos.getX() + dx1, pos.getY() + dy1, pos.getZ() + dz1)
				.color(0.0f, 0.0f, 1.0f, 1.0f)
				.endVertex();
		builder.pos(positionMatrix, pos.getX() + dx2, pos.getY() + dy2, pos.getZ() + dz2)
				.color(0.0f, 0.0f, 1.0f, 1.0f)
				.endVertex();
	}

	private static void locateTileEntities(ClientPlayerEntity player, MatrixStack matrixStack) {
		IRenderTypeBuffer.Impl buffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
		IVertexBuilder builder = buffer.getBuffer(ModRenderTypes.getOverlayLines());

		BlockPos playerPos = player.getPosition();
		int px = playerPos.getX();
		int py = playerPos.getY();
		int pz = playerPos.getZ();
		World world = player.getEntityWorld();

		matrixStack.push();

		@SuppressWarnings("resource")
		Vector3d projectedView = TutorialMod.proxy.getMinecraft().gameRenderer.getActiveRenderInfo().getProjectedView();
		matrixStack.translate(-projectedView.x, -projectedView.y, -projectedView.z);

		Matrix4f positionMatrix = matrixStack.getLast().getMatrix();

		BlockPos.Mutable pos = new BlockPos.Mutable();
		for (int dx = -10; dx <= 10; dx++) {
			for (int dy = -10; dy <= 10; dy++) {
				for (int dz = -10; dz <= 10; dz++) {
					pos.setPos(px + dx, py + dy, pz + dz);
					if (world.getTileEntity(pos) != null) {
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

		matrixStack.pop();

		RenderSystem.disableDepthTest();
		buffer.finish(ModRenderTypes.getOverlayLines());
	}
}
