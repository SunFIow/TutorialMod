package com.sunflow.tutorialmod.config;

import java.util.function.BiFunction;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.client.settings.IteratableOption;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.entity.player.ChatVisibility;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class InGameConfig {
	public static void RegisterExtensionPoint(FMLClientSetupEvent event) {
		BiFunction<Minecraft, Screen, Screen> bi = (mc, parent) -> {
			return new DefaultConfigScreen(TutorialMod.NAME, parent,
					CHAT_VISIBILITY, CHAT_OPACITY, RAW_MOUSE_INPUT);
		};
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> bi);
	}

	public static final IteratableOption CHAT_VISIBILITY = new IteratableOption("options.chat.visibility",
			(settings, value) -> {
				settings.chatVisibility = ChatVisibility.getValue((settings.chatVisibility.getId() + value) % 3);
			},
			(settings, option) -> {
				return option.getDisplayString() + I18n.format(settings.chatVisibility.getResourceKey());
			});
	public static final SliderPercentageOption CHAT_OPACITY = new SliderPercentageOption("options.chat.opacity", 0.0D, 1.0D, 0.0F,
			(settings) -> {
				return settings.chatOpacity;
			},
			(settings, value) -> {
				settings.chatOpacity = value;
				Minecraft.getInstance().ingameGUI.getChatGUI().refreshChat();
			},
			(settings, option) -> {
				double d0 = option.normalizeValue(option.get(settings));
				return option.getDisplayString() + (int) (d0 * 90.0D + 10.0D) + "%";
			});
	public static final BooleanOption RAW_MOUSE_INPUT = new BooleanOption("options.rawMouseInput",
			(settings) -> {
				return settings.rawMouseInput;
			},
			(settings, value) -> {
				settings.rawMouseInput = value;
				MainWindow mainwindow = Minecraft.getInstance().getMainWindow();
				if (mainwindow != null) {
					mainwindow.setRawMouseInput(value);
				}
			});
}
