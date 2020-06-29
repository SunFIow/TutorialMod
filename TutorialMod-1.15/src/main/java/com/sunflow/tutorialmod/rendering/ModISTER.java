package com.sunflow.tutorialmod.rendering;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestBlock;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestTile;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModISTER extends ItemStackTileEntityRenderer {

	public static final ItemStackTileEntityRenderer instance = new ModISTER();

	private CopperChestTile chestCopper;

	public static void setup() {
		((ModISTER) instance).chestCopper = new CopperChestTile();
	}

	@Override
	public void render(ItemStack itemStackIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		Item item = itemStackIn.getItem();
		if (item instanceof BlockItem) {
			Block block = ((BlockItem) item).getBlock();
			TileEntity tileentity;
			if (block instanceof CopperChestBlock) {
				tileentity = this.chestCopper;
			} else {
				return;
			}
			TileEntityRendererDispatcher.instance.renderItem(tileentity, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
		}
	}
}
