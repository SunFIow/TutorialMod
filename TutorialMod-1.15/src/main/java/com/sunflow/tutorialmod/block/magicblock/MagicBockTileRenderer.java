package com.sunflow.tutorialmod.block.magicblock;

import java.util.Random;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.data.EmptyModelData;

public class MagicBockTileRenderer extends TileEntityRenderer<MagicBlockTile> {

	public static final ResourceLocation MAGICBLOCK_TEXTURE = new ResourceLocation(TutorialMod.MODID, "block/magicblock");

	public MagicBockTileRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	private void add(IVertexBuilder renderer, MatrixStack stack, float x, float y, float z, float u, float v) {
		renderer.pos(stack.getLast().getMatrix(), x, y, z)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.tex(u, v)
				.lightmap(0, 240)
				.normal(1, 0, 0)
				.endVertex();
	}

	private static float diffFunction(long time, long delta, float scale) {
		long dt = time % (delta * 2);
		if (dt > delta) {
			dt = 2 * delta - dt;
		}
		return dt * scale;
	}

	@Override
	public void render(MagicBlockTile tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
		@SuppressWarnings("deprecation")
		TextureAtlasSprite sprite = TutorialMod.proxy.getMinecraft().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(MAGICBLOCK_TEXTURE);
		IVertexBuilder builder = buffer.getBuffer(RenderType.getTranslucent());

		Random rnd = new Random(tileEntity.getPos().getX() * 337L + tileEntity.getPos().getY() * 37L + tileEntity.getPos().getZ() * 13L);

		long time = System.currentTimeMillis();
		float dx1 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
		float dx2 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
		float dx3 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
		float dx4 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
		float dy1 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
		float dy2 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
		float dy3 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
		float dy4 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);

		float angle = (time / 100) % 360;
		Quaternion rotation = Vector3f.YP.rotationDegrees(angle);
		float scale = 1.0f + diffFunction(time, 900 + rnd.nextInt(800), 0.0001f + rnd.nextFloat() * 0.001f);

		matrixStack.push();
		matrixStack.translate(.5, .5, .5);
		matrixStack.rotate(rotation);
		matrixStack.scale(scale, scale, scale);
		matrixStack.translate(-.5, -.5, -.5);

		// Add a Quad facing to the front
		add(builder, matrixStack, 0 + dx1, 0 + dy1, .5f, sprite.getMinU(), sprite.getMinV());
		add(builder, matrixStack, 1 - dx2, 0 + dy2, .5f, sprite.getMaxU(), sprite.getMinV());
		add(builder, matrixStack, 1 - dx3, 1 - dy3, .5f, sprite.getMaxU(), sprite.getMaxV());
		add(builder, matrixStack, 0 + dx4, 1 - dy4, .5f, sprite.getMinU(), sprite.getMaxV());

		// Add a Quad facing to the back
		add(builder, matrixStack, 0 + dx4, 1 - dy4, .5f, sprite.getMinU(), sprite.getMaxV());
		add(builder, matrixStack, 1 - dx3, 1 - dy3, .5f, sprite.getMaxU(), sprite.getMaxV());
		add(builder, matrixStack, 1 - dx2, 0 + dy2, .5f, sprite.getMaxU(), sprite.getMinV());
		add(builder, matrixStack, 0 + dx1, 0 + dy1, .5f, sprite.getMinU(), sprite.getMinV());

		matrixStack.pop();

		matrixStack.push();

		// Render a Diamond above the Block
		matrixStack.translate(0.5, 1.5, 0.5);
		ItemRenderer itemRenderer = TutorialMod.proxy.getMinecraft().getItemRenderer();
		ItemStack stack = new ItemStack(Items.DIAMOND);
		IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(stack, tileEntity.getWorld(), null);
		itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStack, buffer, combinedLight, combinedOverlay, ibakedmodel);

		// Render a EnderChest above the Block
		matrixStack.translate(-.5, 1, -.5);
		BlockRendererDispatcher blockRenderer = TutorialMod.proxy.getMinecraft().getBlockRendererDispatcher();
		BlockState state = Blocks.ENDER_CHEST.getDefaultState();
		blockRenderer.renderBlock(state, matrixStack, buffer, combinedLight, combinedOverlay, EmptyModelData.INSTANCE);

		matrixStack.pop();
	}
}
