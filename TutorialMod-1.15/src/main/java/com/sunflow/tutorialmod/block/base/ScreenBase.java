package com.sunflow.tutorialmod.block.base;

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
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		font.drawString(title.getFormattedText(), xSize / 2 - font.getStringWidth(title.getFormattedText()) / 2, 6.0F, 0x404040);

		font.drawString(playerInventory.getDisplayName().getFormattedText(), 8.0F, ySize - 96 + 2, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		minecraft.getTextureManager().bindTexture(GUI);

//		blit(xIG, yIG, xOffset, yOffset, wIG, hIG);
		blit(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	protected int getProgressScaled(int current, int max, int pixels) {
		return max != 0 && current != 0 ? current * pixels / max : 0;
	}
}
