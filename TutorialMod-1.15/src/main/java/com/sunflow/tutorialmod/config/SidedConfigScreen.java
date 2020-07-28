package com.sunflow.tutorialmod.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.AbstractOption;
import net.minecraft.util.text.ITextComponent;

public class SidedConfigScreen extends Screen {

	protected Screen parentScreen;

	protected final AbstractOption[] options;

	public SidedConfigScreen(ITextComponent title, Screen parentScreen, AbstractOption[] options) {
		super(title);
		this.parentScreen = parentScreen;
		this.options = options;
	}

	public void open(Minecraft mc) { mc.displayGuiScreen(this); }

	@Override
	protected void init() {
		for (int i = 0; i < options.length; ++i) {
			AbstractOption abstractoption = options[i];
			int j = this.width / 2 - 155 + i % 2 * 160;
			int k = this.height / 6 + 24 * (i >> 1);
			addButton(abstractoption.createWidget(this.minecraft.gameSettings, j, k, 150));
		}

		addButton(new Button(this.width / 2 - 100, this.height / 6 + 24 * (options.length + 1) / 2, 200, 20, I18n.format("gui.done"),
				button -> onClose()));
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