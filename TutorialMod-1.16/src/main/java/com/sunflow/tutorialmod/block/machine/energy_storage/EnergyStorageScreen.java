package com.sunflow.tutorialmod.block.machine.energy_storage;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.ScreenBase;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class EnergyStorageScreen extends ScreenBase<EnergyStorageContainer> {

	public EnergyStorageScreen(EnergyStorageContainer container, PlayerInventory inv, ITextComponent name) {
		super(container, inv, name);
		GUI = new ResourceLocation(TutorialMod.MODID, "textures/gui/container/energy_storage.png");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
//		super.drawGuiContainerForegroundLayer(matrixStack, mouseX, mouseY);
		String name = " Energy      Storage";
//		int wName = field_230712_o_.getStringWidth(name);
		int wName = font.getStringWidth(name);
//		field_230712_o_.func_238422_b_(matrixStack, new StringTextComponent(name), xSize / 2.0F - wName / 2.0F, 6.0F, 0x404040);
		this.font.func_243248_b(matrixStack, new StringTextComponent(name), xSize / 2.0F - wName / 2.0F, 6.0F, 0x404040);

//		field_230712_o_.func_238422_b_(matrixStack, playerInventory.getDisplayName(), 8.0F, ySize - 96 + 2, 0x404040);
		this.font.func_243248_b(matrixStack, playerInventory.getDisplayName(), 8.0F, ySize - 96 + 2, 0x404040);

		String current = Integer.toString(container.data.get(EnergyStorageTile.ENERGY_ID));
		String max = Integer.toString(container.data.get(EnergyStorageTile.ENERGY_MAX_ID));
		int wCurrent = font.getStringWidth(current);
		int wMax = font.getStringWidth(max);
//		field_230712_o_.func_238422_b_(matrixStack, new StringTextComponent(current), xSize / 4.0F - wCurrent / 2.0F, ySize / 4.0F, 0x404040);
		this.font.func_243248_b(matrixStack, new StringTextComponent(current), xSize / 4.0F - wCurrent / 2.0F, ySize / 4.0F, 0x404040);
//		field_230712_o_.func_238422_b_(matrixStack, new StringTextComponent(max), xSize * (3.0F / 4.0F) - wMax / 2.0F, ySize / 4.0F, 0x404040);
		this.font.func_243248_b(matrixStack, new StringTextComponent(max), xSize * (3.0F / 4.0F) - wMax / 2.0F, ySize / 4.0F, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(matrixStack, partialTicks, mouseX, mouseY);

		int k = getProgressScaled(container.data.get(EnergyStorageTile.ENERGY_ID), container.data.get(EnergyStorageTile.ENERGY_MAX_ID), 75);
		this.blit(matrixStack, guiLeft + 80, guiTop + 5, 176, 31, 16, 76 - k);
	}
}
