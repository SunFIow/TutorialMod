package com.sunflow.tutorialmod.block.machine.sintering_furnace;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.ScreenBase;
import com.sunflow.tutorialmod.util.Config;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class SinteringFurnaceScreen extends ScreenBase<SinteringFurnaceContainer> {

	public SinteringFurnaceScreen(SinteringFurnaceContainer container, PlayerInventory inv, ITextComponent name) {
		super(container, inv, name);
		GUI = new ResourceLocation(TutorialMod.MODID, "textures/gui/container/sintering_furnace.png");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);

		String current = Integer.toString(container.data.get(SinteringFurnaceTile.BURNTIME_ID));
		String max = Integer.toString(container.data.get(SinteringFurnaceTile.BURNTIME_TOTAL_ID));
		String text = current + " / " + max;
		int w = font.getStringWidth(text);
		font.drawString(text, 148 - w, 72, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

//		blit(xIG, yIG, xOffset, yOffset, wIG, hIG);
		int l = getProgressScaled(container.getCookTime(), Config.SINTERING_FURNACE_TICKS.get(), 24);
		this.blit(guiLeft + 79, guiTop + 34, 176, 14, l, 17);

		int k = getProgressScaled(container.getBurnTime(), container.getBurnTimeTotal(), 14);
		this.blit(guiLeft + 21, guiTop + 54 + 14 - k, 176, 14 - k, 14, k);
	}
}
