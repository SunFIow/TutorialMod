package com.sunflow.tutorialmod.config;

import java.util.List;

import com.sunflow.tutorialmod.config.ConfigScreen.SunOption;

import net.minecraft.client.Option;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SidedConfigScreen extends BaseConfigScreen {

	protected final List<SunOption> options;

	public SidedConfigScreen(Component title, Screen parentScreen, List<SunOption> options) {
		super(title, parentScreen, options.size() + 1);
		this.options = options;
	}

	@Override
	protected void init() {
		super.init();
		for (int i = 0; i < options.size(); ++i) {
			int j = this.width / 2 - 155 + i % 2 * 160;
			int k = this.height / 6 + 24 * (i >> 1);
			AbstractWidget widget = options.get(i).createButton(this.minecraft.options, j, k, 150);
			addRenderableWidget(widget);
		}
	}
}