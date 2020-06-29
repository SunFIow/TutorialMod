package com.sunflow.tutorialmod.block.machine.electric_sintering_furnace;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.ScreenBase;
import com.sunflow.tutorialmod.config.TutorialModConfig;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ElectricSinteringFurnaceScreen extends ScreenBase<ElectricSinteringFurnaceContainer> {

	public ElectricSinteringFurnaceScreen(ElectricSinteringFurnaceContainer container, PlayerInventory inv, ITextComponent name) {
		super(container, inv, name);
		GUI = new ResourceLocation(TutorialMod.MODID, "textures/gui/container/electric_sintering_furnace.png");
	}

	@Override
	protected void func_230451_b_(MatrixStack matrixStack, int mouseX, int mouseY) {
		super.func_230451_b_(matrixStack, mouseX, mouseY);

		String current = Integer.toString(container.getEnergy());
		String max = Integer.toString(container.getEnergyMax());
		String text = current + " / " + max;
		int w = field_230712_o_.getStringWidth(text);
		field_230712_o_.func_238422_b_(matrixStack, new StringTextComponent(text), 148 - w, 72, 0x404040);
	}

	@Override
	protected void func_230450_a_(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		super.func_230450_a_(matrixStack, partialTicks, mouseX, mouseY);

		int l = getProgressScaled(container.getCookTime(), TutorialModConfig.ELECTRIC_SINTERING_FURNACE_TICKS.get(), 24);
		this.func_238474_b_(matrixStack, guiLeft + 79, guiTop + 34, 176, 14, l, 17);

		int k = getProgressScaled(container.getEnergy(), container.getEnergyMax(), 76);
		this.func_238474_b_(matrixStack, guiLeft + 152, guiTop + 5, 176, 31, 16, 76 - k);
	}
}