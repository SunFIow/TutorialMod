package com.sunflow.tutorialmod.block.machine.charger;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.ScreenBase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ChargerScreen extends ScreenBase<ChargerContainer> {

	public ChargerScreen(ChargerContainer container, PlayerInventory inv, ITextComponent name) {
		super(container, inv, name);
		GUI = new ResourceLocation(TutorialMod.MODID, "textures/gui/container/charger.png");
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		addButton(new Button(guiLeft + 10, guiTop + 10 + 27 * 0, 160, 20, new TranslationTextComponent("Test"), this::bla));
	}

	private void bla(Button b) { System.out.println("bla"); }

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

		int l = getProgressScaled(container.getItemEnergy(), container.getItemEnergyMax(), 38);
		this.blit(matrixStack, guiLeft + 102, guiTop + 24, 176, 107, 16, 38 - l);

		int k = getProgressScaled(container.getEnergy(), container.getEnergyMax(), 76);
		this.blit(matrixStack, guiLeft + 152, guiTop + 5, 176, 31, 16, 76 - k);
	}
}