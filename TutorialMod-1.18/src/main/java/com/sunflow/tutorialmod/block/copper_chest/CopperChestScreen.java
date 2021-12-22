package com.sunflow.tutorialmod.block.copper_chest;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CopperChestScreen extends AbstractContainerScreen<CopperChestContainer> {
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(TutorialMod.MODID, "textures/gui/container/copper_chest.png");

	public CopperChestScreen(CopperChestContainer container, Inventory playerInv, Component title) {
		super(container, playerInv, title);
		// this.passEvents = false;
		this.imageHeight = 256;
		// this.inventoryLabelY = this.imageHeight - 94;
	}

	@Override
	public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderTooltip(matrixStack, mouseX, mouseY);
	}

	@Override
	protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
		super.renderLabels(matrixStack, mouseX, mouseY);

		this.font.draw(matrixStack, this.title + "_T", 8.0F, 6.0F, 4210752);
		this.font.draw(matrixStack, this.playerInventoryTitle + "_T", 8.0F, this.imageHeight - 96 + 3, 4210752);
	}

	@Override
	protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, CHEST_GUI_TEXTURE);
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);

		this.blit(matrixStack, getGuiLeft(), getGuiTop(), 0, 0, getXSize(), getYSize());
	}

}