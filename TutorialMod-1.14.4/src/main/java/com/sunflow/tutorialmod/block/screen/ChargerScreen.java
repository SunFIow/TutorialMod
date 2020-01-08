package com.sunflow.tutorialmod.block.screen;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.ScreenBase;
import com.sunflow.tutorialmod.block.container.ChargerContainer;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ChargerScreen extends ScreenBase {

	ChargerContainer cont;

	public ChargerScreen(Container container, PlayerInventory inv, ITextComponent name) {
		super(container, inv, name);
		cont = (ChargerContainer) container;
		GUI = new ResourceLocation(TutorialMod.MODID, "textures/gui/container/charger.png");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);

		String current = Integer.toString(cont.getEnergy());
		String max = Integer.toString(cont.getEnergyMax());
		String text = current + " / " + max;
		int w = font.getStringWidth(text);
		font.drawString(text, 148 - w, 72, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

		int l = getProgressScaled(cont.getItemEnergy(), cont.getItemEnergyMax(), 38);
		this.blit(guiLeft + 102, guiTop + 24, 176, 107, 16, 38 - l);

		int k = getProgressScaled(cont.getEnergy(), cont.getEnergyMax(), 76);
		this.blit(guiLeft + 152, guiTop + 5, 176, 31, 16, 76 - k);
	}
}