package com.sunflow.tutorialmod.config;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.electronwill.nightconfig.core.Config;

import net.minecraft.client.CycleOption;
import net.minecraft.client.Option;
import net.minecraft.client.Options;
import net.minecraft.client.ProgressOption;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.config.ModConfig;

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
		commonButton = addRenderableWidget(new Button(this.width / 2 - 100, this.height / 6 + 24 * (1) / 2, 200, 20, new TranslatableComponent("options.tutorialmod.common"),
				button -> new SidedConfigScreen(title.copy().append(" - " + button.getMessage()), this, builder.build(ModConfig.Type.COMMON)).open(minecraft)));
		clientButton = addRenderableWidget(new Button(this.width / 2 - 100, this.height / 6 + 24 * (3) / 2, 200, 20, new TranslatableComponent("options.tutorialmod.client"),
				button -> new SidedConfigScreen(title.copy().append(" - " + button.getMessage()), this, builder.build(ModConfig.Type.CLIENT)).open(minecraft)));
		serverButton = addRenderableWidget(new Button(this.width / 2 - 100, this.height / 6 + 24 * (5) / 2, 200, 20, new TranslatableComponent("options.tutorialmod.server"),
				button -> new SidedConfigScreen(title.copy().append(" - " + button.getMessage()), this, builder.build(ModConfig.Type.SERVER)).open(minecraft)));

		if (type == ModConfig.Type.CLIENT) serverButton.active = false;
		else if (type == ModConfig.Type.SERVER) clientButton.active = false;
	}

	public static class Builder {

		public static ConfigScreen.Builder create() { return new ConfigScreen.Builder(); }

		private final List<Option> optionsCommon = new ArrayList<>();
		private final List<Option> optionsClient = new ArrayList<>();
		private final List<Option> optionsServer = new ArrayList<>();

		private List<Option> getList(ModConfig.Type screenType) {
			switch (screenType) {
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

		public Option[] build(ModConfig.Type screenType) {
			List<Option> options = getList(screenType);
			return options.toArray(new Option[options.size()]);
		}

		public ConfigScreen.Builder addOption(ModConfig.Type screenType, Option option) {
			List<Option> options = getList(screenType);
			options.add(option);
			return this;
		}

		public ConfigScreen.Builder Boolean(ModConfig.Type screenType, String translationKey, ForgeConfigSpec.BooleanValue value) {
			return addOption(screenType, CycleOption.createOnOff(translationKey,
					(settings) -> value.get(),
					(settings, _setting, _value) -> {
						value.set(_value);
						sync(value, screenType, SyncConfigPacket.Type.BOOL);
					}));
		}

		public ConfigScreen.Builder Integer(ModConfig.Type screenType, String translationKey, ForgeConfigSpec.IntValue value, double minValue, double maxValue, float stepSize) {
			return addOption(screenType, new ProgressOption(translationKey, minValue, maxValue, stepSize,
					(settings) -> (double) value.get(),
					(settings, _value) -> {
						value.set(_value.intValue());
						sync(value, screenType, SyncConfigPacket.Type.INT);
					},
					(settings, _option) -> {
						double d0 = _option.get(settings);
						return new TranslatableComponent(translationKey + (int) d0);
					}));
		}

		public ConfigScreen.Builder Long(ModConfig.Type screenType, String translationKey, ForgeConfigSpec.LongValue value, double minValue, double maxValue, float stepSize) {
			return addOption(screenType, new ProgressOption(translationKey, minValue, maxValue, stepSize,
					(settings) -> (double) value.get(),
					(settings, _value) -> {
						value.set(_value.longValue());
						sync(value, screenType, SyncConfigPacket.Type.LONG);
					},
					(settings, option) -> {
						double d0 = option.get(settings);
						return new TranslatableComponent(translationKey + (long) d0);
					}));
		}

		public ConfigScreen.Builder Double(ModConfig.Type screenType, String translationKey, ForgeConfigSpec.DoubleValue value, double minValue, double maxValue, float stepSize) {
			return addOption(screenType, new ProgressOption(translationKey, minValue, maxValue, stepSize,
					(settings) -> (double) value.get(),
					(settings, _value) -> {
						value.set(_value.doubleValue());
						sync(value, screenType, SyncConfigPacket.Type.DOUBLE);
					},
					(settings, option) -> {
						double d0 = option.get(settings);
						return new TranslatableComponent(translationKey + d0);
					}));
		}

		public <T extends Enum<T>> ConfigScreen.Builder Enum(ModConfig.Type screenType, String translationKey, ForgeConfigSpec.EnumValue<T> value,
				double minValue, double maxValue, float stepSize,
				Function<T, Double> getter,
				Function<Double, T> setter,
				Function<T, String> namer) {
			return addOption(screenType, new ProgressOption(translationKey, minValue, maxValue, stepSize,
					(settings) -> getter.apply(value.get()),
					(settings, _value) -> {
						value.set(setter.apply(_value));
						sync(value, screenType, SyncConfigPacket.Type.ENUM);
					},
					(settings, option) -> {
						return new TranslatableComponent(translationKey + namer.apply(value.get()));
					}));
		}

		public ConfigScreen.Builder String(ModConfig.Type screenType, String translationKey, ForgeConfigSpec.ConfigValue<String> value,
				double minValue, double maxValue, float stepSize,
				Function<String, Double> getter,
				Function<Double, String> setter,
				Function<String, String> namer) {
			return ConfigValue(SyncConfigPacket.Type.STRING, screenType, translationKey, value, minValue, maxValue, stepSize, getter, setter, namer);
		}

		// TODO: Config
		public ConfigScreen.Builder Config(ModConfig.Type screenType, String translationKey, ForgeConfigSpec.ConfigValue<Config> value,
				double minValue, double maxValue, float stepSize,
				Function<Config, Double> getter,
				Function<Double, Config> setter,
				Function<Config, String> namer) {
			return ConfigValue(SyncConfigPacket.Type.CONFIG, screenType, translationKey, value, minValue, maxValue, stepSize, getter, setter, namer);
		}

		// TODO: List
		public <T> ConfigScreen.Builder List(ModConfig.Type screenType, SyncConfigPacket.Type listType, String translationKey, ForgeConfigSpec.ConfigValue<List<T>> value,
				double minValue, double maxValue, float stepSize,
				Function<List<T>, Double> getter,
				Function<Double, List<T>> setter,
				Function<List<T>, String> namer) {
			return addOption(screenType, new ProgressOption(translationKey, minValue, maxValue, stepSize,
					(settings) -> getter.apply(value.get()),
					(settings, _value) -> {
						value.set(setter.apply(_value));
						syncList(value, screenType, listType);
					},
					(settings, option) -> {
						return new TranslatableComponent(translationKey + namer.apply(value.get()));
					}));
		}

		private <T> ConfigScreen.Builder ConfigValue(SyncConfigPacket.Type packetType, ModConfig.Type screenType, String translationKey, ForgeConfigSpec.ConfigValue<T> value,
				double minValue, double maxValue, float stepSize,
				Function<T, Double> getter,
				Function<Double, T> setter,
				Function<T, String> namer) {
			return addOption(screenType, new ProgressOption(translationKey, minValue, maxValue, stepSize,
					(settings) -> getter.apply(value.get()),
					(settings, _value) -> {
						value.set(setter.apply(_value));
						sync(value, screenType, packetType);
					},
					(settings, option) -> {
						return new TranslatableComponent(translationKey + namer.apply(value.get()));
					}));
		}

		private <T> void sync(ConfigValue<T> value, ModConfig.Type screenType, SyncConfigPacket.Type packetType) {
			value.save();
//			if (screenType == ModConfig.Type.SERVER) Networking.sendToServer(new SyncConfigPacket(value, packetType));
		}

		private <T> void syncList(ConfigValue<T> value, ModConfig.Type screenType, SyncConfigPacket.Type listType) {
			value.save();
//			if (screenType == ModConfig.Type.SERVER) Networking.sendToServer(new SyncConfigPacket(value, SyncConfigPacket.Type.LIST, listType));
		}

		public ConfigScreen.Builder Boolean(ModConfig.Type screenType, String translationKey, ForgeConfigSpec.BooleanValue value,
				Function<Options, Boolean> setter,
				CycleOption.OptionSetter<Boolean> getter) {
			List<Option> options = getList(screenType);
			options.add(CycleOption.createOnOff(translationKey, setter, getter));
			return this;
		}

		public ConfigScreen.Builder Integer(ModConfig.Type screenType, String translationKey, ForgeConfigSpec.IntValue value,
				List<Integer> values,
				Function<Integer, Component> namer,
				Function<Options, Integer> getter,
				CycleOption.OptionSetter<Integer> setter) {
			List<Option> options = getList(screenType);
			options.add(CycleOption.create(translationKey, values, namer, getter, setter));
			return this;
		}

		public ConfigScreen.Builder Long(ModConfig.Type screenType, String translationKey, ForgeConfigSpec.LongValue value,
				List<Long> values,
				Function<Long, Component> namer,
				Function<Options, Long> getter,
				CycleOption.OptionSetter<Long> setter) {
			List<Option> options = getList(screenType);
			options.add(CycleOption.create(translationKey, values, namer, getter, setter));
			return this;
		}

		public ConfigScreen.Builder Double(ModConfig.Type screenType, String translationKey, ForgeConfigSpec.DoubleValue value,
				List<Double> values,
				Function<Double, Component> namer,
				Function<Options, Double> getter,
				CycleOption.OptionSetter<Double> setter) {
			List<Option> options = getList(screenType);
			options.add(CycleOption.create(translationKey, values, namer, getter, setter));
			return this;
		}

		public <T> ConfigScreen.Builder ConfigValue(ModConfig.Type screenType, String translationKey, ForgeConfigSpec.ConfigValue<T> value,
				List<T> values,
				Function<T, Component> namer,
				Function<Options, T> getter,
				CycleOption.OptionSetter<T> setter) {
			List<Option> options = getList(screenType);
			options.add(CycleOption.create(translationKey, values, namer, getter, setter));
			return this;
		}

		public <T extends Enum<T>> ConfigScreen.Builder Enum(ModConfig.Type screenType, String translationKey, ForgeConfigSpec.EnumValue<T> value,
				List<T> values,
				Function<T, Component> namer,
				Function<Options, T> getter,
				CycleOption.OptionSetter<T> setter) {
			List<Option> options = getList(screenType);
			options.add(CycleOption.create(translationKey, values, namer, getter, setter));
			return this;
		}

	}
}