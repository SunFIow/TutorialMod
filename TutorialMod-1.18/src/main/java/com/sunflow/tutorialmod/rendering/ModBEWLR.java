package com.sunflow.tutorialmod.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestBlock;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestEntity;
import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.util.Log;

import org.apache.logging.log4j.MarkerManager;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModBEWLR extends BlockEntityWithoutLevelRenderer {

	private final BlockEntityRenderDispatcher dispatcher;

	public ModBEWLR() {
		super(TutorialMod.proxy.getMinecraft().getBlockEntityRenderDispatcher(), TutorialMod.proxy.getMinecraft().getEntityModels());
		this.dispatcher = TutorialMod.proxy.getMinecraft().getBlockEntityRenderDispatcher();
	}

	public static final ModBEWLR instance = new ModBEWLR();

	private CopperChestEntity chestCopper;

	public void setup() {
		chestCopper = new CopperChestEntity(BlockPos.ZERO, Registration.COPPER_CHEST.block().defaultBlockState());
	}

	@Override
	public void renderByItem(ItemStack itemStackIn, ItemTransforms.TransformType transformType, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
		Item item = itemStackIn.getItem();
		if (item instanceof BlockItem blockItem) {
			Block block = blockItem.getBlock();
			BlockEntity tileentity;
			if (block instanceof CopperChestBlock) {
				tileentity = this.chestCopper;
				dispatcher.renderItem(tileentity, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
			}
		}
		super.renderByItem(itemStackIn, transformType, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
	}
}
