package com.sunflow.tutorialmod.entity.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.blocks.CopperChestBlock;
import com.sunflow.tutorialmod.entity.model.CopperChestModel;
import com.sunflow.tutorialmod.init.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CopperChestTileRenderer<T extends TileEntity & IChestLid> extends TileEntityRenderer<T> {
	private static final ResourceLocation TEXTURE_COPPER_CHEST = new ResourceLocation(TutorialMod.MODID, "textures/entity/chest/copper_chest.png");
	private final CopperChestModel simpleChest = new CopperChestModel();

//	public CopperChestTileRenderer() {}

	@Override
	public void render(T tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.enableDepthTest();
		GlStateManager.depthFunc(515);
		GlStateManager.depthMask(true);
		BlockState blockstate = tileEntityIn.hasWorld() ? tileEntityIn.getBlockState() : ModBlocks.COPPER_CHEST.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);
		CopperChestModel chestmodel = getChestModel(tileEntityIn, destroyStage);
		if (destroyStage >= 0) {
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scalef(4.0F, 4.0F, 1.0F);
			GlStateManager.translatef(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		} else {
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		}

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.translatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
		GlStateManager.scalef(1.0F, -1.0F, -1.0F);
		float f = blockstate.get(CopperChestBlock.FACING).getHorizontalAngle();
		if (Math.abs(f) > 1.0E-5D) {
			GlStateManager.translatef(0.5F, 0.5F, 0.5F);
			GlStateManager.rotatef(f, 0.0F, 1.0F, 0.0F);
			GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
		}

		applyLidRotation(tileEntityIn, partialTicks, chestmodel);
		chestmodel.renderAll();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		if (destroyStage >= 0) {
			GlStateManager.matrixMode(5890);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}
	}

	private CopperChestModel getChestModel(T tileEntityIn, int destroyStage) {
		ResourceLocation resourcelocation;
		if (destroyStage >= 0) {
			resourcelocation = DESTROY_STAGES[destroyStage];
		} else {
			resourcelocation = TEXTURE_COPPER_CHEST;
		}

		bindTexture(resourcelocation);
		return simpleChest;
	}

	private void applyLidRotation(T tileEntityIn, float partialTicks, CopperChestModel model) {
		float f = ((IChestLid) tileEntityIn).getLidAngle(partialTicks);
		f = 1.0F - f;
		f = 1.0F - f * f * f;
		model.getLid().rotateAngleX = -(f * ((float) Math.PI / 2F));
	}
}