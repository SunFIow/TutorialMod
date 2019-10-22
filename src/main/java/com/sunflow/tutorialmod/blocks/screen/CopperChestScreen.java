package com.sunflow.tutorialmod.blocks.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.blocks.container.CopperChestContainer;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CopperChestScreen extends ContainerScreen<CopperChestContainer> { // implements IHasContainer<CopperChestContainer> {
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(TutorialMod.MODID, "textures/gui/container/copper_chest.png");

	public CopperChestScreen(CopperChestContainer container, PlayerInventory playerInv, ITextComponent title) {
		super(container, playerInv, title);
		this.passEvents = false;
		this.ySize = 256;
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.font.drawString(this.title.getFormattedText(), 8.0F, 6.0F, 4210752);
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, this.ySize - 96 + 3, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
		blit(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}