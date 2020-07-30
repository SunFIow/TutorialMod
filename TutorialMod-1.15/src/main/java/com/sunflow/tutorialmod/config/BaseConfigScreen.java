package com.sunflow.tutorialmod.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;

public class BaseConfigScreen extends Screen {

	private Screen parentScreen;

	private int i;

	public BaseConfigScreen(ITextComponent title, Screen parentScreen, int i) {
		super(title);
		this.parentScreen = parentScreen;
		this.i = i;
	}

	public void open(Minecraft mc) { mc.displayGuiScreen(this); }

	@Override
	protected void init() {
		addButton(new Button(this.width / 2 - 100, this.height / 6 + 24 * (i) / 2, 200, 20, I18n.format("gui.done"), button -> onClose()));
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		this.drawCenteredString(this.font, this.title.getFormattedText(), this.width / 2, 20, 16777215);
		super.render(mouseX, mouseY, partialTicks);
	}

	@Override
	public void onClose() { this.minecraft.displayGuiScreen(this.parentScreen); }
}