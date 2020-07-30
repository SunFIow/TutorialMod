package com.sunflow.tutorialmod.config;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.sunflow.tutorialmod.network.Networking;

import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.AbstractOption;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.client.settings.IteratableOption;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.config.ModConfig;

public class ConfigScreen extends BaseConfigScreen {

	protected final ConfigScreen.Builder builder;

	protected final ModConfig.Type type;

	protected Button commonButton;
	protected Button clientButton;
	protected Button serverButton;
	protected Button doneButton;

	public ConfigScreen(ITextComponent title, Screen parentScreen, ModConfig.Type type, ConfigScreen.Builder builder) {
		super(title, parentScreen, 9);
		this.builder = builder;
		this.type = type;
	}

	@Override
	protected void init() {
		super.init();
		commonButton = addButton(new Button(this.width / 2 - 100, this.height / 6 + 24 * (1) / 2, 200, 20, I18n.format("options.tutorialmod.common"),
				button -> new SidedConfigScreen(title.deepCopy().appendText(" - " + button.getMessage()), this, builder.build(ModConfig.Type.COMMON)).open(minecraft)));
		clientButton = addButton(new Button(this.width / 2 - 100, this.height / 6 + 24 * (3) / 2, 200, 20, I18n.format("options.tutorialmod.client"),
				button -> new SidedConfigScreen(title.deepCopy().appendText(" - " + button.getMessage()), this, builder.build(ModConfig.Type.CLIENT)).open(minecraft)));
		serverButton = addButton(new Button(this.width / 2 - 100, this.height / 6 + 24 * (5) / 2, 200, 20, I18n.format("options.tutorialmod.server"),
				button -> new SidedConfigScreen(title.deepCopy().appendText(" - " + button.getMessage()), this, builder.build(ModConfig.Type.SERVER)).open(minecraft)));

		if (type == ModConfig.Type.CLIENT) serverButton.active = false;
		else if (type == ModConfig.Type.SERVER) clientButton.active = false;
	}

	public static class Builder {

		public static ConfigScreen.Builder create() { return new ConfigScreen.Builder(); }

		private final List<AbstractOption> optionsCommon = new ArrayList<>();
		private final List<AbstractOption> optionsClient = new ArrayList<>();
		private final List<AbstractOption> optionsServer = new ArrayList<>();

		private List<AbstractOption> getList(ModConfig.Type type) {
			switch (type) {
				case COMMON:
					return optionsCommon;
				case CLIENT:
					return optionsClient;
				case SERVER:
					return optionsServer;
				default:
					return null;
			}
		}

		private <T> void save(ConfigValue<T> value, ModConfig.Type configScreenType, SyncConfigPacket.Type optionType) {
			if (configScreenType == ModConfig.Type.SERVER) sync(value, optionType);
			value.save();
		}

		private <T> void sync(ConfigValue<T> value, SyncConfigPacket.Type type) {
			Networking.sendToServer(new SyncConfigPacket(value, type));
		}

		public AbstractOption[] build(ModConfig.Type type) {
			List<AbstractOption> options = getList(type);
			return options.toArray(new AbstractOption[options.size()]);
		}

		public ConfigScreen.Builder Boolean(ModConfig.Type type, String translationKey, ForgeConfigSpec.BooleanValue value) {
			List<AbstractOption> options = getList(type);
			options.add(new BooleanOption(translationKey,
					(settings) -> value.get(),
					(settings, _value) -> {
						value.set(_value);
						save(value, type, SyncConfigPacket.Type.BOOL);
					}));
			return this;
		}

		public ConfigScreen.Builder Integer(ModConfig.Type type, String translationKey, ForgeConfigSpec.IntValue value, double minValue, double maxValue, float stepSize) {
			List<AbstractOption> options = getList(type);
			options.add(new SliderPercentageOption(translationKey, minValue, maxValue, stepSize,
					(settings) -> (double) value.get(),
					(settings, _value) -> {
						value.set(_value.intValue());
						save(value, type, SyncConfigPacket.Type.INT);
					},
					(settings, _option) -> {
						double d0 = _option.get(settings);
						String s = _option.getDisplayString();
						return s + (int) d0;
					}));
			return this;
		}

		public ConfigScreen.Builder Long(ModConfig.Type type, String translationKey, ForgeConfigSpec.LongValue value, double minValue, double maxValue, float stepSize) {
			List<AbstractOption> options = getList(type);
			options.add(new SliderPercentageOption(translationKey, minValue, maxValue, stepSize,
					(settings) -> (double) value.get(),
					(settings, _value) -> {
						value.set(_value.longValue());
						save(value, type, SyncConfigPacket.Type.LONG);
					},
					(settings, option) -> {
						double d0 = option.get(settings);
						String s = option.getDisplayString();
						return s + (long) d0;
					}));
			return this;
		}

		public ConfigScreen.Builder Double(ModConfig.Type type, String translationKey, ForgeConfigSpec.DoubleValue value, double minValue, double maxValue, float stepSize) {
			List<AbstractOption> options = getList(type);
			options.add(new SliderPercentageOption(translationKey, minValue, maxValue, stepSize,
					(settings) -> (double) value.get(),
					(settings, _value) -> {
						value.set(_value.doubleValue());
						save(value, type, SyncConfigPacket.Type.DOUBLE);
					},
					(settings, option) -> {
						double d0 = option.get(settings);
						String s = option.getDisplayString();
						return s + d0;
					}));
			return this;
		}

		public <T> ConfigScreen.Builder Value(ModConfig.Type type, String translationKey, ForgeConfigSpec.ConfigValue<T> value,
				double minValue, double maxValue, float stepSize,
				Function<T, Double> getter,
				Function<Double, T> setter,
				Function<Double, String> namer) {
			List<AbstractOption> options = getList(type);
			options.add(new SliderPercentageOption(translationKey, minValue, maxValue, stepSize,
					(settings) -> getter.apply(value.get()),
					(settings, _value) -> {
						value.set(setter.apply(_value));
						save(value, type, SyncConfigPacket.Type.CUSTOM);
					},
					(settings, option) -> {
						double d0 = option.get(settings);
						String s = option.getDisplayString();
						return s + namer.apply(d0);
					}));
			return this;
		}

		public <T extends Enum<T>> ConfigScreen.Builder Enum(ModConfig.Type type, String translationKey, ForgeConfigSpec.EnumValue<T> value,
				double minValue, double maxValue, float stepSize,
				Function<T, Double> getter,
				Function<Double, T> setter,
				Function<Double, String> namer) {
			List<AbstractOption> options = getList(type);
			options.add(new SliderPercentageOption(translationKey, minValue, maxValue, stepSize,
					(settings) -> getter.apply(value.get()),
					(settings, _value) -> {
						value.set(setter.apply(_value));
						save(value, type, SyncConfigPacket.Type.ENUM);
					},
					(settings, option) -> {
						double d0 = option.get(settings);
						String s = option.getDisplayString();
						return s + namer.apply(d0);
					}));
			return this;
		}

		public ConfigScreen.Builder Boolean(ModConfig.Type type, String translationKey, ForgeConfigSpec.BooleanValue value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			List<AbstractOption> options = getList(type);
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

		public ConfigScreen.Builder Integer(ModConfig.Type type, String translationKey, ForgeConfigSpec.IntValue value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			List<AbstractOption> options = getList(type);
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

		public ConfigScreen.Builder Long(ModConfig.Type type, String translationKey, ForgeConfigSpec.LongValue value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			List<AbstractOption> options = getList(type);
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

		public ConfigScreen.Builder Double(ModConfig.Type type, String translationKey, ForgeConfigSpec.DoubleValue value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			List<AbstractOption> options = getList(type);
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

		public <T> ConfigScreen.Builder Value(ModConfig.Type type, String translationKey, ForgeConfigSpec.ConfigValue<T> value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			List<AbstractOption> options = getList(type);
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

		public <T extends Enum<T>> ConfigScreen.Builder Enum(ModConfig.Type type, String translationKey, ForgeConfigSpec.EnumValue<T> value,
				BiConsumer<GameSettings, Integer> setter,
				BiFunction<GameSettings, IteratableOption, String> getter) {
			List<AbstractOption> options = getList(type);
			options.add(new IteratableOption(translationKey, setter, getter));
			return this;
		}

	}
}