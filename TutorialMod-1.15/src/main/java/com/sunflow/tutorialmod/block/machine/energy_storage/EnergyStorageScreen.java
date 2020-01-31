package com.sunflow.tutorialmod.block.machine.energy_storage;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.ScreenBase;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class EnergyStorageScreen extends ScreenBase<EnergyStorageContainer> {

	public EnergyStorageScreen(EnergyStorageContainer container, PlayerInventory inv, ITextComponent name) {
		super(container, inv, name);
		GUI = new ResourceLocation(TutorialMod.MODID, "textures/gui/container/energy_storage.png");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
//		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		String name = " Energy      Storage";
		font.drawString(name, xSize / 2.0F - font.getStringWidth(name) / 2.0F, 6.0F, 0x404040);

		font.drawString(playerInventory.getDisplayName().getFormattedText(), 8.0F, ySize - 96 + 2, 0x404040);

		String current = Integer.toString(container.data.get(EnergyStorageTile.ENERGY_ID));
		String max = Integer.toString(container.data.get(EnergyStorageTile.ENERGY_MAX_ID));
		int wCurrent = font.getStringWidth(current);
		int wMax = font.getStringWidth(max);
		font.drawString(current, xSize / 4.0F - wCurrent / 2.0F, ySize / 4.0F, 0x404040);
		font.drawString(max, xSize * (3.0F / 4.0F) - wMax / 2.0F, ySize / 4.0F, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

		int k = getProgressScaled(container.data.get(EnergyStorageTile.ENERGY_ID), container.data.get(EnergyStorageTile.ENERGY_MAX_ID), 75);
		this.blit(guiLeft + 80, guiTop + 5, 176, 31, 16, 76 - k);
	}
}
