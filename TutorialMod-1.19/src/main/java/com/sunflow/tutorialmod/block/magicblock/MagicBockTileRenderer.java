package com.sunflow.tutorialmod.block.magicblock;

import java.util.Random;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.sunflow.tutorialmod.TutorialMod;

import org.lwjgl.system.CallbackI.V;
import org.lwjgl.system.CallbackI.Z;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.EmptyModelData;

public class MagicBockTileRenderer implements BlockEntityRenderer<MagicBlockEntity> {

	public static final ResourceLocation MAGICBLOCK_TEXTURE = new ResourceLocation(TutorialMod.MODID, "block/magicblock");

	public MagicBockTileRenderer(BlockEntityRendererProvider.Context context) {}

	private void add(VertexConsumer renderer, PoseStack stack, float x, float y, float z, float u, float v) {
		renderer.vertex(stack.last().pose(), x, y, z)
				.color(1.0f, 1.0f, 1.0f, 1.0f)
				.uv(u, v)
				.uv2(0, 240)
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
	// public void render(MagicBlockTile tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
	public void render(MagicBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
		TextureAtlasSprite sprite = TutorialMod.proxy.getMinecraft().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(MAGICBLOCK_TEXTURE);
		VertexConsumer builder = buffer.getBuffer(RenderType.translucent());

		Random rnd = new Random(blockEntity.getBlockPos().getX() * 337L + blockEntity.getBlockPos().getY() * 37L + blockEntity.getBlockPos().getZ() * 13L);

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

		poseStack.pushPose();
		poseStack.translate(.5, .5, .5);
		poseStack.mulPose(rotation);
		poseStack.scale(scale, scale, scale);
		poseStack.translate(-.5, -.5, -.5);

		// Add a Quad facing to the front
		add(builder, poseStack, 0 + dx1, 0 + dy1, .5f, sprite.getU0(), sprite.getV0());
		add(builder, poseStack, 1 - dx2, 0 + dy2, .5f, sprite.getU1(), sprite.getV0());
		add(builder, poseStack, 1 - dx3, 1 - dy3, .5f, sprite.getU1(), sprite.getV1());
		add(builder, poseStack, 0 + dx4, 1 - dy4, .5f, sprite.getU0(), sprite.getV1());

		// Add a Quad facing to the back
		add(builder, poseStack, 0 + dx4, 1 - dy4, .5f, sprite.getU0(), sprite.getV1());
		add(builder, poseStack, 1 - dx3, 1 - dy3, .5f, sprite.getU1(), sprite.getV1());
		add(builder, poseStack, 1 - dx2, 0 + dy2, .5f, sprite.getU1(), sprite.getV0());
		add(builder, poseStack, 0 + dx1, 0 + dy1, .5f, sprite.getU0(), sprite.getV0());

		poseStack.popPose();

		poseStack.pushPose();

		// Render a Diamond above the Block
		poseStack.translate(0.5, 1.5, 0.5);
		ItemRenderer itemRenderer = TutorialMod.proxy.getMinecraft().getItemRenderer();
		ItemStack stack = new ItemStack(Items.DIAMOND);
		BakedModel ibakedmodel = itemRenderer.getModel(stack, blockEntity.getLevel(), (LivingEntity) null, 0);
		itemRenderer.render(stack, ItemTransforms.TransformType.FIXED, true, poseStack, buffer, combinedLight, combinedOverlay, ibakedmodel);

		// Render a EnderChest above the Block
		poseStack.translate(-.5, 1, -.5);
		BlockRenderDispatcher blockRenderer = TutorialMod.proxy.getMinecraft().getBlockRenderer();
		BlockState state = Blocks.ENDER_CHEST.defaultBlockState();
		blockRenderer.renderSingleBlock(state, poseStack, buffer, combinedLight, combinedOverlay, EmptyModelData.INSTANCE);

		poseStack.popPose();
	}
}
