package com.sunflow.tutorialmod.block.machine.firstblock;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.ScreenBase;
import com.sunflow.tutorialmod.util.Config;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class FirstBlockScreen extends ScreenBase<FirstBlockContainer> {

	public FirstBlockScreen(FirstBlockContainer container, PlayerInventory inv, ITextComponent name) {
		super(container, inv, name);
		GUI = new ResourceLocation(TutorialMod.MODID, "textures/gui/container/glowstone_generator.png");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);

		String current = Integer.toString(container.getEnergy());
		String max = Integer.toString(container.getEnergyMax());
		String text = current + " / " + max;
		int w = font.getStringWidth(text);
		font.drawString(text, 148 - w, 72, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

		int l = getProgressScaled(container.getCookTime(), Config.FIRSTBLOCK_TICKS.get(), 24);
		this.blit(guiLeft + 113, guiTop + 34, 176, 14, l, 17);

		int k = getProgressScaled(container.getEnergy(), container.getEnergyMax(), 76);
		this.blit(guiLeft + 152, guiTop + 5, 176, 31, 16, 76 - k);
	}
}
