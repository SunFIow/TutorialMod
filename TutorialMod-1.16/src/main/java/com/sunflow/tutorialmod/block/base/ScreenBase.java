package com.sunflow.tutorialmod.block.base;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ScreenBase<T extends ContainerBase> extends ContainerScreen<T> {

	protected ResourceLocation GUI = new ResourceLocation(TutorialMod.MODID, "textures/gui/gui_base.png");

	public ScreenBase(T container, PlayerInventory inv, ITextComponent name) {
		super(container, inv, name);
	}

	@Override
//	public void render(int mouseX, int mouseY, float partialTicks) {
	public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
//		this.renderBackground();
		this.func_230446_a_(matrixStack);
//		super.redner(mouseX, mouseY, partialTicks);
		super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
//		this.renderHoveredToolTip(mouseX, mouseY);
		this.func_230459_a_(matrixStack, mouseX, mouseY);
	}

	@Override
//	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
	protected void func_230451_b_(MatrixStack matrixStack, int mouseX, int mouseY) {
		super.func_230451_b_(matrixStack, mouseX, mouseY);
//		font.drawString(title.getFormattedText(), xSize / 2 - font.getStringWidth(title.getFormattedText()) / 2, 6.0F, 0x404040);
//		font.drawString(playerInventory.getDisplayName().getFormattedText(), 8.0F, ySize - 96 + 2, 0x404040);
//		this.field_230712_o_.func_238422_b_(matrixStack, this.field_230704_d_, this.field_238742_p_, this.field_238743_q_, 4210752);
//		this.field_230712_o_.func_238422_b_(matrixStack, this.playerInventory.getDisplayName(), this.field_238744_r_, this.field_238745_s_, 4210752);
	}

	@Override
//	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
	protected void func_230450_a_(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

//		minecraft.getTextureManager().bindTexture(GUI);
		this.field_230706_i_.getTextureManager().bindTexture(GUI);

//		blit(xIG, yIG, xOffset, yOffset, wIG, hIG);
//		blit(guiLeft, guiTop, 0, 0, xSize, ySize);
		func_238474_b_(matrixStack, guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	protected int getProgressScaled(int current, int max, int pixels) {
		return max != 0 && current != 0 ? current * pixels / max : 0;
	}

}
