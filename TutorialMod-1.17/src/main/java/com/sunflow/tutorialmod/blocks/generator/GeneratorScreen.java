package com.sunflow.tutorialmod.blocks.generator;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class GeneratorScreen extends AbstractContainerScreen<GeneratorContainer> {

	private static final ResourceLocation GUI = new ResourceLocation(TutorialMod.MODID, "textures/gui/generator_gui.png");

	public GeneratorScreen(GeneratorContainer container, Inventory inventory, Component name) { super(container, inventory, name); }

	@Override
	protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
//		super.renderLabels(matrixStack, mouseX, mouseY);
		drawString(matrixStack, Minecraft.getInstance().font, "Energy: " + menu.getEnergy(), 10, 10, 0xffFFff);
	}

	@Override
	protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderTexture(0, GUI);
		int relX = (this.width - this.imageWidth) / 2;
		int relY = (this.height - this.imageHeight) / 2;
		this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
	}

}
