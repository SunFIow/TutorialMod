package com.sunflow.tutorialmod.config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.settings.AbstractOption;
import net.minecraft.util.text.ITextComponent;

public class SidedConfigScreen extends BaseConfigScreen {

	protected final AbstractOption[] options;

	public SidedConfigScreen(ITextComponent title, Screen parentScreen, AbstractOption[] options) {
		super(title, parentScreen, options.length + 1);
		this.options = options;
	}

	@Override
	protected void init() {
		super.init();
		for (int i = 0; i < options.length; ++i) {
			AbstractOption abstractoption = options[i];
			int j = this.width / 2 - 155 + i % 2 * 160;
			int k = this.height / 6 + 24 * (i >> 1);
			addButton(abstractoption.createWidget(this.minecraft.gameSettings, j, k, 150));
		}
	}
}