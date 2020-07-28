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

public class ConfigScreen extends Screen {

	protected Screen parentScreen;

	protected final ConfigScreen.Builder builder;

	protected final Catergory category;

	protected Button commonButton;
	protected Button clientButton;
	protected Button serverButton;
	protected Button doneButton;

	public ConfigScreen(ITextComponent title, Screen parentScreen, ConfigScreen.Builder builder, Catergory category) {
		super(title);
		this.parentScreen = parentScreen;
		this.builder = builder;
		this.category = category;
	}

	public void open(Minecraft mc) { mc.displayGuiScreen(this); }

	@Override
	protected void init() {
		commonButton = addButton(new Button(this.width / 2 - 100, this.height / 6 + 24 * (1) / 2, 200, 20, I18n.format("options.tutorialmod.common"),
				button -> new SidedConfigScreen(title.deepCopy().appendText(" - " + button.getMessage()), this, builder.build(Catergory.GENERAL)).open(minecraft)));
		clientButton = addButton(new Button(this.width / 2 - 100, this.height / 6 + 24 * (3) / 2, 200, 20, I18n.format("options.tutorialmod.client"),
				button -> new SidedConfigScreen(title.deepCopy().appendText(" - " + button.getMessage()), this, builder.build(Catergory.CLIENT)).open(minecraft)));
		serverButton = addButton(new Button(this.width / 2 - 100, this.height / 6 + 24 * (5) / 2, 200, 20, I18n.format("options.tutorialmod.server"),
				button -> new SidedConfigScreen(title.deepCopy().appendText(" - " + button.getMessage()), this, builder.build(Catergory.SERVER)).open(minecraft)));
		doneButton = addButton(new Button(this.width / 2 - 100, this.height / 6 + 24 * (7) / 2, 200, 20, I18n.format("gui.done"),
				button -> onClose()));

		if (category == Catergory.CLIENT) serverButton.active = false;
		else if (category == Catergory.SERVER) clientButton.active = false;
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		this.drawCenteredString(this.font, this.title.getFormattedText(), this.width / 2, 20, 16777215);
		super.render(mouseX, mouseY, partialTicks);
	}

	@Override
	public void onClose() { this.minecraft.displayGuiScreen(this.parentScreen); }

	public static enum Catergory {
		GENERAL(), CLIENT(), SERVER(),
	}

	public static class Builder {

		public static ConfigScreen.Builder create() { return new ConfigScreen.Builder(); }

		private final List<AbstractOption> optionsClient = new ArrayList<>();
		private final List<AbstractOption> optionsServer = new ArrayList<>();
		private final List<AbstractOption> optionsGeneral = new ArrayList<>();

		private List<AbstractOption> options = optionsGeneral;

		public ConfigScreen.Builder general() { options = optionsGeneral; return this; }

		public ConfigScreen.Builder client() { options = optionsClient; return this; }

		public ConfigScreen.Builder server() { options = optionsServer; return this; }

		public AbstractOption[] build() { return build(Catergory.GENERAL); }

		public AbstractOption[] build(Catergory side) {
			if (side == Catergory.CLIENT) return optionsClient.toArray(new AbstractOption[optionsClient.size()]);
			if (side == Catergory.SERVER) return optionsServer.toArray(new AbstractOption[optionsServer.size()]);
			if (side == Catergory.GENERAL) return optionsGeneral.toArray(new AbstractOption[optionsGeneral.size()]);
			return null;
		}

		public ConfigScreen.Builder Boolean(String translationKey, ForgeConfigSpec.BooleanValue value) {
			options.add(new BooleanOption(translationKey,
					(settings) -> value.get(),
					(settings, _value) -> {
						value.set(_value);
						value.save();
					}));
			return this;
		}

		public ConfigScreen.Builder Integer(String translationKey, ForgeConfigSpec.IntValue value, double minValue, double maxValue, float stepSize) {
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

		public ConfigScreen.Builder Long(String translationKey, ForgeConfigSpec.LongValue value, double minValue, double maxValue, float stepSize) {
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

		public ConfigScreen.Builder Double(String translationKey, ForgeConfigSpec.DoubleValue value, double minValue, double maxValue, float stepSize) {
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

		public <T> ConfigScreen.Builder Value(String translationKey, ForgeConfigSpec.ConfigValue<T> value,
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

		public <T extends Enum<T>> ConfigScreen.Builder Enum(String translationKey, ForgeConfigSpec.EnumValue<T> value,
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

		public ConfigScreen.Builder Boolean(String translationKey, ForgeConfigSpec.BooleanValue value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

		public ConfigScreen.Builder Integer(String translationKey, ForgeConfigSpec.IntValue value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

		public ConfigScreen.Builder Long(String translationKey, ForgeConfigSpec.LongValue value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

		public ConfigScreen.Builder Double(String translationKey, ForgeConfigSpec.DoubleValue value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

		public <T> ConfigScreen.Builder Value(String translationKey, ForgeConfigSpec.ConfigValue<T> value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

		public <T extends Enum<T>> ConfigScreen.Builder Enum(String translationKey, ForgeConfigSpec.EnumValue<T> value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

	}
}