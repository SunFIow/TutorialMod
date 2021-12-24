package com.sunflow.tutorialmod.config;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.electronwill.nightconfig.core.Config;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.datagen.SunLanguageProvider;

import net.minecraft.client.CycleOption;
import net.minecraft.client.Option;
import net.minecraft.client.Options;
import net.minecraft.client.ProgressOption;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.config.ModConfig.Type;

public class ConfigScreen extends BaseConfigScreen {

	protected final ConfigScreen.Builder builder;

	protected final ModConfig.Type type;

	protected Button commonButton;
	protected Button clientButton;
	protected Button serverButton;

	public ConfigScreen(Component title, Screen parentScreen, ModConfig.Type type, ConfigScreen.Builder builder) {
		super(title, parentScreen, 9);
		this.builder = builder;
		this.type = type;
	}

	@Override
	protected void init() {
		super.init();
		clientButton = addRenderableWidget(new Button(this.width / 2 - 100, this.height / 6 + 24 * (3) / 2, 200, 20, new TranslatableComponent(SunLanguageProvider.getSidedTranslationKey(ModConfig.Type.CLIENT)),
				button -> new SidedConfigScreen(title.copy().append(" - ").append(button.getMessage()), this, builder.getList(ModConfig.Type.CLIENT)).open(minecraft)));
		commonButton = addRenderableWidget(new Button(this.width / 2 - 100, this.height / 6 + 24 * (1) / 2, 200, 20, new TranslatableComponent(SunLanguageProvider.getSidedTranslationKey(ModConfig.Type.COMMON)),
				button -> new SidedConfigScreen(title.copy().append(" - ").append(button.getMessage()), this, builder.getList(ModConfig.Type.COMMON)).open(minecraft)));
		serverButton = addRenderableWidget(new Button(this.width / 2 - 100, this.height / 6 + 24 * (5) / 2, 200, 20, new TranslatableComponent(SunLanguageProvider.getSidedTranslationKey(ModConfig.Type.SERVER)),
				button -> new SidedConfigScreen(title.copy().append(" - ").append(button.getMessage()), this, builder.getList(ModConfig.Type.SERVER)).open(minecraft)));

		if (type == ModConfig.Type.CLIENT) serverButton.active = false;
		else if (type == ModConfig.Type.SERVER) clientButton.active = false;
	}

	public static class Builder {

		public static ConfigScreen.Builder create() { return new ConfigScreen.Builder(); }

		private final List<SunOption> optionsClient = new ArrayList<>();
		private final List<SunOption> optionsCommon = new ArrayList<>();
		private final List<SunOption> optionsServer = new ArrayList<>();

		public List<SunOption> getList(ModConfig.Type screenType) {
			switch (screenType) {
				case CLIENT:
					return optionsClient;
				case COMMON:
					return optionsCommon;
				case SERVER:
					return optionsServer;
				default:
					return null;
			}
		}

		public ConfigScreen.Builder addOption(ModConfig.Type screenType, SunOption option) {
			getList(screenType).add(option);
			return this;
		}

		public ConfigScreen.Builder Boolean(ModConfig.Type screenType, String translationKey, String translation, ForgeConfigSpec.BooleanValue value) {
			return addOption(screenType, new SunOption(screenType, translationKey, translation, CycleOption.createOnOff(SunLanguageProvider.getTranslationKey(screenType, translationKey),
					(settings) -> value.get(),
					(settings, _setting, _value) -> {
						value.set(_value);
						sync(value, screenType, SyncConfigPacket.Type.BOOL);
					})));
		}

		public ConfigScreen.Builder Integer(ModConfig.Type screenType, String translationKey, String translation, ForgeConfigSpec.IntValue value, double minValue, double maxValue, float stepSize) {
			return addOption(screenType, new SunOption(screenType, translationKey, translation, new ProgressOption(SunLanguageProvider.getTranslationKey(screenType, translationKey), minValue, maxValue, stepSize,
					(settings) -> (double) value.get(),
					(settings, _value) -> {
						value.set(_value.intValue());
						sync(value, screenType, SyncConfigPacket.Type.INT);
					},
					(settings, _option) -> {
						double d0 = _option.get(settings);
						return new TranslatableComponent(translationKey + "." + (int) d0);
					})));
		}

		public ConfigScreen.Builder Long(ModConfig.Type screenType, String translationKey, String translation, ForgeConfigSpec.LongValue value, double minValue, double maxValue, float stepSize) {
			return addOption(screenType, new SunOption(screenType, translationKey, translation, new ProgressOption(SunLanguageProvider.getTranslationKey(screenType, translationKey), minValue, maxValue, stepSize,
					(settings) -> (double) value.get(),
					(settings, _value) -> {
						value.set(_value.longValue());
						sync(value, screenType, SyncConfigPacket.Type.LONG);
					},
					(settings, option) -> {
						double d0 = option.get(settings);
						return new TranslatableComponent(translationKey + "." + (long) d0);
					})));
		}

		public ConfigScreen.Builder Double(ModConfig.Type screenType, String translationKey, String translation, ForgeConfigSpec.DoubleValue value, double minValue, double maxValue, float stepSize) {
			return addOption(screenType, new SunOption(screenType, translationKey, translation, new ProgressOption(SunLanguageProvider.getTranslationKey(screenType, translationKey), minValue, maxValue, stepSize,
					(settings) -> (double) value.get(),
					(settings, _value) -> {
						value.set(_value.doubleValue());
						sync(value, screenType, SyncConfigPacket.Type.DOUBLE);
					},
					(settings, option) -> {
						double d0 = option.get(settings);
						return new TranslatableComponent(translationKey + "." + d0);
					})));
		}

		public <T extends Enum<T>> ConfigScreen.Builder Enum(ModConfig.Type screenType, String translationKey, String translation, ForgeConfigSpec.EnumValue<T> value,
				double minValue, double maxValue, float stepSize,
				Function<T, Double> getter,
				Function<Double, T> setter,
				Function<T, String> namer) {
			return addOption(screenType, new SunOption(screenType, translationKey, translation, new ProgressOption(SunLanguageProvider.getTranslationKey(screenType, translationKey), minValue, maxValue, stepSize,
					(settings) -> getter.apply(value.get()),
					(settings, _value) -> {
						value.set(setter.apply(_value));
						sync(value, screenType, SyncConfigPacket.Type.ENUM);
					},
					(settings, option) -> {
						return new TranslatableComponent(translationKey + "." + namer.apply(value.get()));
					})));
		}

		public ConfigScreen.Builder String(ModConfig.Type screenType, String translationKey, String translation, ForgeConfigSpec.ConfigValue<String> value,
				double minValue, double maxValue, float stepSize,
				Function<String, Double> getter,
				Function<Double, String> setter,
				Function<String, String> namer) {
			return ConfigValue(SyncConfigPacket.Type.STRING, screenType, translationKey, translation, value, minValue, maxValue, stepSize, getter, setter, namer);
		}

		// TODO: Config
		public ConfigScreen.Builder Config(ModConfig.Type screenType, String translationKey, String translation, ForgeConfigSpec.ConfigValue<Config> value,
				double minValue, double maxValue, float stepSize,
				Function<Config, Double> getter,
				Function<Double, Config> setter,
				Function<Config, String> namer) {
			return ConfigValue(SyncConfigPacket.Type.CONFIG, screenType, translationKey, translation, value, minValue, maxValue, stepSize, getter, setter, namer);
		}

		// TODO: List
		public <T> ConfigScreen.Builder List(ModConfig.Type screenType, SyncConfigPacket.Type listType, String translationKey, String translation, ForgeConfigSpec.ConfigValue<List<T>> value,
				double minValue, double maxValue, float stepSize,
				Function<List<T>, Double> getter,
				Function<Double, List<T>> setter,
				Function<List<T>, String> namer) {
			return addOption(screenType, new SunOption(screenType, translationKey, translation, new ProgressOption(SunLanguageProvider.getTranslationKey(screenType, translationKey), minValue, maxValue, stepSize,
					(settings) -> getter.apply(value.get()),
					(settings, _value) -> {
						value.set(setter.apply(_value));
						syncList(value, screenType, listType);
					},
					(settings, option) -> {
						return new TranslatableComponent(translationKey + "." + namer.apply(value.get()));
					})));
		}

		private <T> ConfigScreen.Builder ConfigValue(SyncConfigPacket.Type packetType, ModConfig.Type screenType, String translationKey, String translation, ForgeConfigSpec.ConfigValue<T> value,
				double minValue, double maxValue, float stepSize,
				Function<T, Double> getter,
				Function<Double, T> setter,
				Function<T, String> namer) {
			return addOption(screenType, new SunOption(screenType, translationKey, translation, new ProgressOption(SunLanguageProvider.getTranslationKey(screenType, translationKey), minValue, maxValue, stepSize,
					(settings) -> getter.apply(value.get()),
					(settings, _value) -> {
						value.set(setter.apply(_value));
						sync(value, screenType, packetType);
					},
					(settings, option) -> {
						return new TranslatableComponent(translationKey + "." + namer.apply(value.get()));
					})));
		}

		private <T> void sync(ConfigValue<T> value, ModConfig.Type screenType, SyncConfigPacket.Type packetType) {
			value.save();
			// if (screenType == ModConfig.Type.SERVER) Networking.sendToServer(new SyncConfigPacket(value, packetType)); // TODO
		}

		private <T> void syncList(ConfigValue<T> value, ModConfig.Type screenType, SyncConfigPacket.Type listType) {
			value.save();
			// if (screenType == ModConfig.Type.SERVER) Networking.sendToServer(new SyncConfigPacket(value, SyncConfigPacket.Type.LIST, listType));
		}

		public ConfigScreen.Builder Boolean(ModConfig.Type screenType, String translationKey, String translation, ForgeConfigSpec.BooleanValue value,
				Function<Options, Boolean> setter,
				CycleOption.OptionSetter<Boolean> getter) {
			addOption(screenType, new SunOption(screenType, translationKey, translation, CycleOption.createOnOff(SunLanguageProvider.getTranslationKey(screenType, translationKey), setter, getter)));
			return this;
		}

		public ConfigScreen.Builder Integer(ModConfig.Type screenType, String translationKey, String translation, ForgeConfigSpec.IntValue value,
				List<Integer> values,
				Function<Integer, Component> namer,
				Function<Options, Integer> getter,
				CycleOption.OptionSetter<Integer> setter) {
			addOption(screenType, new SunOption(screenType, translationKey, translation, CycleOption.create(SunLanguageProvider.getTranslationKey(screenType, translationKey), values, namer, getter, setter)));
			return this;
		}

		public ConfigScreen.Builder Long(ModConfig.Type screenType, String translationKey, String translation, ForgeConfigSpec.LongValue value,
				List<Long> values,
				Function<Long, Component> namer,
				Function<Options, Long> getter,
				CycleOption.OptionSetter<Long> setter) {
			addOption(screenType, new SunOption(screenType, translationKey, translation, CycleOption.create(SunLanguageProvider.getTranslationKey(screenType, translationKey), values, namer, getter, setter)));
			return this;
		}

		public ConfigScreen.Builder Double(ModConfig.Type screenType, String translationKey, String translation, ForgeConfigSpec.DoubleValue value,
				List<Double> values,
				Function<Double, Component> namer,
				Function<Options, Double> getter,
				CycleOption.OptionSetter<Double> setter) {
			addOption(screenType, new SunOption(screenType, translationKey, translation, CycleOption.create(SunLanguageProvider.getTranslationKey(screenType, translationKey), values, namer, getter, setter)));
			return this;
		}

		public <T> ConfigScreen.Builder ConfigValue(ModConfig.Type screenType, String translationKey, String translation, ForgeConfigSpec.ConfigValue<T> value,
				List<T> values,
				Function<T, Component> namer,
				Function<Options, T> getter,
				CycleOption.OptionSetter<T> setter) {
			addOption(screenType, new SunOption(screenType, translationKey, translation, CycleOption.create(SunLanguageProvider.getTranslationKey(screenType, translationKey), values, namer, getter, setter)));
			return this;
		}

		public <T extends Enum<T>> ConfigScreen.Builder Enum(ModConfig.Type screenType, String translationKey, String translation, ForgeConfigSpec.EnumValue<T> value,
				List<T> values,
				Function<T, Component> namer,
				Function<Options, T> getter,
				CycleOption.OptionSetter<T> setter) {
			addOption(screenType, new SunOption(screenType, translationKey, translation, CycleOption.create(SunLanguageProvider.getTranslationKey(screenType, translationKey), values, namer, getter, setter)));
			return this;
		}

	}

	public static class SunOption {
		private final ModConfig.Type screenType;
		private final String translationKey;
		private final String translation;
		private final Option option;

		public SunOption(ModConfig.Type screenType, String translationKey, String translation, Option option) {
			this.screenType = screenType;
			this.translationKey = translationKey;
			this.translation = translation;
			this.option = option;
		}

		public AbstractWidget createButton(Options pOptions, int pX, int pY, int pWidth) {
			return option.createButton(pOptions, pX, pY, pWidth);
		}

		public ModConfig.Type getType() { return screenType; }

		public String getTranslationKey() { return translationKey; }

		public String getTranslation() { return translation; }
	}
}