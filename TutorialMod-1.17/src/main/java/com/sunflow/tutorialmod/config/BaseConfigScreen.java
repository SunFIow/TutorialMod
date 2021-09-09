package com.sunflow.tutorialmod.config;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class BaseConfigScreen extends Screen {

	private Screen parentScreen;
	protected Button doneButton;

	private int i;

	public BaseConfigScreen(Component title, Screen parentScreen, int i) {
		super(title);
		this.parentScreen = parentScreen;
		this.i = i;
	}

	public void open(Minecraft mc) { mc.setScreen(this); }

	@Override
	protected void init() {
		doneButton = addRenderableWidget(new Button(this.width / 2 - 100, this.height / 6 + 24 * (i) / 2, 200, 20, new TranslatableComponent("gui.done"), button -> onClose()));

	}

	@Override
	public void render(PoseStack pMatrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(pMatrixStack);
		drawCenteredString(pMatrixStack, this.font, this.title, this.width / 2, 20, 16777215);
		super.render(pMatrixStack, mouseX, mouseY, partialTicks);
	}

	@Override
	public void onClose() { this.minecraft.setScreen(this.parentScreen); }
}