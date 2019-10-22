package com.sunflow.tutorialmod.blocks.screen;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.blocks.base.ScreenBase;
import com.sunflow.tutorialmod.blocks.container.FirstBlockContainer;
import com.sunflow.tutorialmod.util.Config;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class FirstBlockScreen extends ScreenBase {

	FirstBlockContainer cont;

	public FirstBlockScreen(Container container, PlayerInventory inv, ITextComponent name) {
		super(container, inv, name);
		cont = (FirstBlockContainer) container;
		GUI = new ResourceLocation(TutorialMod.MODID, "textures/gui/container/glowstone_generator.png");
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

//		FirstBlockContainer cont = (FirstBlockContainer) container;
		int l = getProgressScaled(cont.getCookTime(), Config.FIRSTBLOCK_TICKS.get(), 24);
		this.blit(guiLeft + 113, guiTop + 34, 176, 14, l, 17);

		int k = getProgressScaled(cont.getEnergy(), cont.getEnergyMax(), 76);
		this.blit(guiLeft + 152, guiTop + 5, 176, 31, 16, 76 - k);
	}
}
