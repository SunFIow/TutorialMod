package com.sunflow.tutorialmod.rendering;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class RenderPlayerFire {
//	public static final Material LOCATION_FIRE_0 = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(TutorialMod.MODID, "block/ffire_0"));
//	public static final Material LOCATION_FIRE_1 = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(TutorialMod.MODID, "block/ffire_1"));
	public static final ResourceLocation LOCATION_FIRE_0 = new ResourceLocation(TutorialMod.MODID, "block/ffire_0");
	public static final ResourceLocation LOCATION_FIRE_1 = new ResourceLocation(TutorialMod.MODID, "block/ffire_1");

	public static void render(RenderPlayerEvent.Post event) {
		renderEntityStatic(event.getMatrixStack(), event.getBuffers(), event.getPlayer(), event.getRenderer().getRenderManager().info);
	}

	// EntityRendererManager.renderEntityStatic
	private static void renderEntityStatic(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, Entity entityIn, ActiveRenderInfo info) {
		if (entityIn.isSneaking()) {
			renderFire(matrixStackIn, bufferIn, entityIn, info);
		}
	}

	// EntityRendererManager.renderFire
	private static void renderFire(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, Entity entityIn, ActiveRenderInfo info) {
//		TextureAtlasSprite textureatlassprite = ModelBakery.LOCATION_FIRE_0.getSprite();
//		TextureAtlasSprite textureatlassprite1 = ModelBakery.LOCATION_FIRE_1.getSprite();
		TextureAtlasSprite textureatlassprite = TutorialMod.proxy.getMinecraft().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(LOCATION_FIRE_0);
		TextureAtlasSprite textureatlassprite1 = TutorialMod.proxy.getMinecraft().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(LOCATION_FIRE_1);
		matrixStackIn.push();
		float f = entityIn.getWidth() * 1.4F;
		matrixStackIn.scale(f, f, f);
		float f1 = 0.5F;
		float f2 = 0.0F;
		float f3 = entityIn.getHeight() / f;
		float f4 = 0.0F;
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-info.getYaw()));
		matrixStackIn.translate(0.0D, 0.0D, -0.3F + ((int) f3) * 0.02F);
		float f5 = 0.0F;
		int i = 0;
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(Atlases.getCutoutBlockType());

		for (MatrixStack.Entry matrixstack$entry = matrixStackIn.getLast(); f3 > 0.0F; ++i) {
			TextureAtlasSprite textureatlassprite2 = i % 2 == 0 ? textureatlassprite : textureatlassprite1;
			float f6 = textureatlassprite2.getMinU();
			float f7 = textureatlassprite2.getMinV();
			float f8 = textureatlassprite2.getMaxU();
			float f9 = textureatlassprite2.getMaxV();
			if (i / 2 % 2 == 0) {
				float f10 = f8;
				f8 = f6;
				f6 = f10;
			}

			fireVertex(matrixstack$entry, ivertexbuilder, f1 - 0.0F, 0.0F - f4, f5, f8, f9);
			fireVertex(matrixstack$entry, ivertexbuilder, -f1 - 0.0F, 0.0F - f4, f5, f6, f9);
			fireVertex(matrixstack$entry, ivertexbuilder, -f1 - 0.0F, 1.4F - f4, f5, f6, f7);
			fireVertex(matrixstack$entry, ivertexbuilder, f1 - 0.0F, 1.4F - f4, f5, f8, f7);
			f3 -= 0.45F;
			f4 -= 0.45F;
			f1 *= 0.9F;
			f5 += 0.03F;
		}

		matrixStackIn.pop();
	}

	// EntityRendererManager.fireVertex
	private static void fireVertex(MatrixStack.Entry matrixEntryIn, IVertexBuilder bufferIn, float x, float y, float z, float texU, float texV) {
		bufferIn.pos(matrixEntryIn.getMatrix(), x, y, z).color(255, 255, 255, 255).tex(texU, texV).overlay(0, 10).lightmap(240).normal(matrixEntryIn.getNormal(), 0.0F, 1.0F, 0.0F).endVertex();
	}
}
