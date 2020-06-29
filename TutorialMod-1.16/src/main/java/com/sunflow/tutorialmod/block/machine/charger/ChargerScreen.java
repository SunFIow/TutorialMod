package com.sunflow.tutorialmod.block.machine.charger;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.ScreenBase;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ChargerScreen extends ScreenBase<ChargerContainer> {

	public ChargerScreen(ChargerContainer container, PlayerInventory inv, ITextComponent name) {
		super(container, inv, name);
		GUI = new ResourceLocation(TutorialMod.MODID, "textures/gui/container/charger.png");
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

		int l = getProgressScaled(container.getItemEnergy(), container.getItemEnergyMax(), 38);
		this.func_238474_b_(matrixStack, guiLeft + 102, guiTop + 24, 176, 107, 16, 38 - l);

		int k = getProgressScaled(container.getEnergy(), container.getEnergyMax(), 76);
		this.func_238474_b_(matrixStack, guiLeft + 152, guiTop + 5, 176, 31, 16, 76 - k);
	}
}