package com.sunflow.tutorialmod.block.copper_chest;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.sunflow.tutorialmod.TutorialMod;

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
//		this.passEvents = false;
		this.ySize = 256;
	}

	@Override
	public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.func_230446_a_(matrixStack);
		super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
		this.func_230459_a_(matrixStack, mouseX, mouseY);
	}

	@Override
	protected void func_230451_b_(MatrixStack matrixStack, int mouseX, int mouseY) {
		this.field_230712_o_.func_238422_b_(matrixStack, this.field_230704_d_, 8.0F, 6.0F, 4210752);
		this.field_230712_o_.func_238422_b_(matrixStack, this.playerInventory.getDisplayName(), 8.0F, this.ySize - 96 + 3, 4210752);
	}

	@Override
	protected void func_230450_a_(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.field_230706_i_.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
		func_238474_b_(matrixStack, guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}