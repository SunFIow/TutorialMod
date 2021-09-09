package com.sunflow.tutorialmod.config;

import net.minecraft.client.Option;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SidedConfigScreen extends BaseConfigScreen {

	protected final Option[] options;

	public SidedConfigScreen(Component title, Screen parentScreen, Option[] options) {
		super(title, parentScreen, options.length + 1);
		this.options = options;
	}

	@Override
	protected void init() {
		super.init();
		for (int i = 0; i < options.length; ++i) {
			Option abstractoption = options[i];
			int j = this.width / 2 - 155 + i % 2 * 160;
			int k = this.height / 6 + 24 * (i >> 1);
			addRenderableWidget(abstractoption.createButton(this.minecraft.options, j, k, 150));
		}
	}
}