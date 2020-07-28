package com.sunflow.tutorialmod.config;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.AbstractOption;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.client.settings.IteratableOption;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.ForgeConfigSpec;

public class DefaultConfigScreen extends Screen {

	private Screen parentScreen;

	private final DefaultConfigScreen.Builder builder;

	private final Side side;

	public DefaultConfigScreen(ITextComponent title, Screen parentScreen, Builder builder, Side side) {
		super(title);
		this.parentScreen = parentScreen;
		this.builder = builder;
		this.side = side;
	}

	public void open(Minecraft mc) { mc.displayGuiScreen(this); }

	@Override
	protected void init() {
		AbstractOption[] options = builder.build(side);
		for (int i = 0; i < options.length; ++i) {
			AbstractOption abstractoption = options[i];
			int j = this.width / 2 - 155 + i % 2 * 160;
			int k = this.height / 6 + 24 * (i >> 1);
			this.addButton(abstractoption.createWidget(this.minecraft.gameSettings, j, k, 150));
		}

		this.addButton(new Button(this.width / 2 - 100, this.height / 6 + 24 * (options.length + 1) / 2, 200, 20, I18n.format("gui.done"),
				button -> onClose()));
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		this.drawCenteredString(this.font, this.title.getFormattedText(), this.width / 2, 20, 16777215);
		super.render(mouseX, mouseY, partialTicks);
	}

	@Override
	public void removed() {
		this.minecraft.gameSettings.saveOptions();
	}

	@Override
	public void onClose() {
		this.minecraft.displayGuiScreen(this.parentScreen);
	}

	public static enum Side {
		CLIENT(),
		SERVER(),
		COMMON(),
	}

	public static class Builder {

		public static DefaultConfigScreen.Builder create() { return new DefaultConfigScreen.Builder(); }

		private final List<AbstractOption> optionsClient = new ArrayList<>();
		private final List<AbstractOption> optionsServer = new ArrayList<>();
		private final List<AbstractOption> optionsCommon = new ArrayList<>();

		private List<AbstractOption> options = optionsCommon;

		public DefaultConfigScreen.Builder client() { options = optionsClient; return this; }

		public DefaultConfigScreen.Builder server() { options = optionsServer; return this; }

		public DefaultConfigScreen.Builder common() { options = optionsCommon; return this; }

		public AbstractOption[] build() { return build(Side.COMMON); }

		public AbstractOption[] build(Side side) {
			if (side == Side.CLIENT) return optionsClient.toArray(new AbstractOption[optionsClient.size()]);
			if (side == Side.SERVER) return optionsServer.toArray(new AbstractOption[optionsServer.size()]);
			if (side == Side.COMMON) {
				List<AbstractOption> options = new ArrayList<>();
				options.addAll(optionsClient);
				options.addAll(optionsServer);
				options.addAll(optionsCommon);
				return options.toArray(new AbstractOption[options.size()]);
			}
			return null;
		}

		public DefaultConfigScreen.Builder Boolean(String translationKey, ForgeConfigSpec.BooleanValue value) {
			options.add(new BooleanOption(translationKey,
					(settings) -> value.get(),
					(settings, _value) -> {
						value.set(_value);
						value.save();
					}));
			return this;
		}

		public DefaultConfigScreen.Builder Integer(String translationKey, ForgeConfigSpec.IntValue value, double minValue, double maxValue, float stepSize) {
			options.add(new SliderPercentageOption(translationKey, minValue, maxValue, stepSize,
					(settings) -> (double) value.get(),
					(settings, _value) -> {
						value.set(_value.intValue());
						value.save();
					},
					(settings, _option) -> {
						double d0 = _option.get(settings);
						String s = _option.getDisplayString();
						return s + (int) d0;
					}));
			return this;
		}

		public DefaultConfigScreen.Builder Long(String translationKey, ForgeConfigSpec.LongValue value, double minValue, double maxValue, float stepSize) {
			options.add(new SliderPercentageOption(translationKey, minValue, maxValue, stepSize,
					(settings) -> (double) value.get(),
					(settings, _value) -> {
						value.set(_value.longValue());
						value.save();
					},
					(settings, option) -> {
						double d0 = option.get(settings);
						String s = option.getDisplayString();
						return s + (long) d0;
					}));
			return this;
		}

		public DefaultConfigScreen.Builder Double(String translationKey, ForgeConfigSpec.DoubleValue value, double minValue, double maxValue, float stepSize) {
			options.add(new SliderPercentageOption(translationKey, minValue, maxValue, stepSize,
					(settings) -> (double) value.get(),
					(settings, _value) -> {
						value.set(_value.doubleValue());
						value.save();
					},
					(settings, option) -> {
						double d0 = option.get(settings);
						String s = option.getDisplayString();
						return s + d0;
					}));
			return this;
		}

		public <T> DefaultConfigScreen.Builder Value(String translationKey, ForgeConfigSpec.ConfigValue<T> value,
				double minValue, double maxValue, float stepSize,
				Function<T, Double> getter,
				Function<Double, T> setter,
				Function<Double, String> namer) {
			options.add(new SliderPercentageOption(translationKey, minValue, maxValue, stepSize,
					(settings) -> getter.apply(value.get()),
					(settings, _value) -> {
						value.set(setter.apply(_value));
						value.save();
					},
					(settings, option) -> {
						double d0 = option.get(settings);
						String s = option.getDisplayString();
						return s + namer.apply(d0);
					}));
			return this;
		}

		public <T extends Enum<T>> DefaultConfigScreen.Builder Enum(String translationKey, ForgeConfigSpec.EnumValue<T> value,
				double minValue, double maxValue, float stepSize,
				Function<T, Double> getter,
				Function<Double, T> setter,
				Function<Double, String> namer) {
			options.add(new SliderPercentageOption(translationKey, minValue, maxValue, stepSize,
					(settings) -> getter.apply(value.get()),
					(settings, _value) -> {
						value.set(setter.apply(_value));
						value.save();
					},
					(settings, option) -> {
						double d0 = option.get(settings);
						String s = option.getDisplayString();
						return s + namer.apply(d0);
					}));
			return this;
		}

		public DefaultConfigScreen.Builder Boolean(String translationKey, ForgeConfigSpec.BooleanValue value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

		public DefaultConfigScreen.Builder Integer(String translationKey, ForgeConfigSpec.IntValue value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

		public DefaultConfigScreen.Builder Long(String translationKey, ForgeConfigSpec.LongValue value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

		public DefaultConfigScreen.Builder Double(String translationKey, ForgeConfigSpec.DoubleValue value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

		public <T> DefaultConfigScreen.Builder Value(String translationKey, ForgeConfigSpec.ConfigValue<T> value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

		public <T extends Enum<T>> DefaultConfigScreen.Builder Enum(String translationKey, ForgeConfigSpec.EnumValue<T> value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

	}
}