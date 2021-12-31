package com.sunflow.tutorialmod.block.copper_chest;

import java.util.Calendar;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.sunflow.tutorialmod.TutorialMod;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CopperChestRenderer implements BlockEntityRenderer<CopperChestEntity> {// , BlockEntityRendererProvider<CopperChestEntity> {
	public static final ResourceLocation COPPER_CHEST_TEXTURE = new ResourceLocation(TutorialMod.MODID, "entity/chest/copper_chest");
	public static final Material COPPER_CHEST_MATERIAL = new Material(Sheets.CHEST_SHEET, COPPER_CHEST_TEXTURE);
	private boolean xmasTextures;

	private final CopperChestModel simpleChest;

	public CopperChestRenderer(BlockEntityRendererProvider.Context context) {
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
			this.xmasTextures = true;
		}

		simpleChest = new CopperChestModel(context);
	}

	@Override
	public void render(CopperChestEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
		Level level = blockEntity.getLevel();
		boolean flag = level != null;
		BlockState blockstate = flag ? blockEntity.getBlockState() : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
		Block block = blockstate.getBlock();
		if (block instanceof CopperChestBlock copperchestblock) {
			poseStack.pushPose();
			float f = blockstate.getValue(ChestBlock.FACING).toYRot();
			poseStack.translate(0.5D, 0.5D, 0.5D);
			poseStack.mulPose(Vector3f.YP.rotationDegrees(-f));
			poseStack.translate(-0.5D, -0.5D, -0.5D);
			DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> neighborcombineresult;
			if (flag) {
				neighborcombineresult = copperchestblock.combine(blockstate, level, blockEntity.getBlockPos(), true);
			} else {
				neighborcombineresult = DoubleBlockCombiner.Combiner::acceptNone;
			}

			float f1 = neighborcombineresult.<Float2FloatFunction>apply(ChestBlock.opennessCombiner(blockEntity)).get(partialTicks);
			f1 = 1.0F - f1;
			f1 = 1.0F - f1 * f1 * f1;
			int i = neighborcombineresult.<Int2IntFunction>apply(new BrightnessCombiner<>()).applyAsInt(combinedLight);
			VertexConsumer vertexconsumer = COPPER_CHEST_MATERIAL.buffer(buffer, RenderType::entityCutout);
			simpleChest.applyRotation(f1);
			simpleChest.render(vertexconsumer, poseStack, i, combinedOverlay);

			poseStack.popPose();
		}
	}

	protected Material getMaterial(CopperChestEntity blockEntity, ChestType chestType) {
		return Sheets.chooseMaterial(blockEntity, chestType, this.xmasTextures);
	}
}