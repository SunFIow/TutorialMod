package com.sunflow.tutorialmod.block.machine.glowstone_generator;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.ScreenBase;
import com.sunflow.tutorialmod.config.TutorialModConfig;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class GlowstoneGeneratorScreen extends ScreenBase<GlowstoneGeneratorContainer> {

	public GlowstoneGeneratorScreen(GlowstoneGeneratorContainer container, PlayerInventory inv, ITextComponent name) {
		super(container, inv, name);
		GUI = new ResourceLocation(TutorialMod.MODID, "textures/gui/container/glowstone_generator.png");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(matrixStack, mouseX, mouseY);

		String current = Integer.toString(container.getEnergy());
		String max = Integer.toString(container.getEnergyMax());
		String text = current + " / " + max;
		int w = font.getStringWidth(text);
//		field_230712_o_.func_238422_b_(matrixStack, new StringTextComponent(text), 148 - w, 72, 0x404040);
		this.font.func_243248_b(matrixStack, new StringTextComponent(text), 148 - w, 72, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(matrixStack, partialTicks, mouseX, mouseY);

		int l = getProgressScaled(container.getCookTime(), TutorialModConfig.GLOWSTONE_GENERATOR_TICKS.get(), 24);
		this.blit(matrixStack, guiLeft + 113, guiTop + 34, 176, 14, l, 17);

		int k = getProgressScaled(container.getEnergy(), container.getEnergyMax(), 76);
		this.blit(matrixStack, guiLeft + 152, guiTop + 5, 176, 31, 16, 76 - k);
	}
}
