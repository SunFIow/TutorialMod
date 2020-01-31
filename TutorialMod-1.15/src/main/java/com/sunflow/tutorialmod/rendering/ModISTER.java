package com.sunflow.tutorialmod.rendering;

import java.util.function.Supplier;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ModISTER extends ItemStackTileEntityRenderer {

	private Supplier<Block> block;
	private Supplier<TileEntity> tileEntity;

	public ModISTER(Supplier<Block> block, Supplier<TileEntity> tileEntity) {
		this.block = block;
		this.tileEntity = tileEntity;
	}

	@Override
	public void render(ItemStack itemStackIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		Item item = itemStackIn.getItem();
		if (item instanceof BlockItem) {
			Block block = ((BlockItem) item).getBlock();
			if (block != this.block.get()) return;
			TileEntityRendererDispatcher.instance.renderNullable(this.tileEntity.get(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
		}
	}
}
