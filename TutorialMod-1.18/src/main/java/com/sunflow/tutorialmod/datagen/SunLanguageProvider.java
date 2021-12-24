package com.sunflow.tutorialmod.datagen;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.copper_chest.CopperChestBlock;
import com.sunflow.tutorialmod.config.ConfigScreen;
import com.sunflow.tutorialmod.config.ConfigScreen.SunOption;
import com.sunflow.tutorialmod.config.TutorialModConfig1;
import com.sunflow.tutorialmod.setup.ModGroups;
import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.util.enums.KeyBindings;

import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.config.ModConfig;

public class SunLanguageProvider extends LanguageProvider {

    public SunLanguageProvider(DataGenerator generator, String locale) { super(generator, TutorialMod.MODID, locale); }

    @Override
    protected void addTranslations() {
        add(((TranslatableComponent) ModGroups.ITEM_GROUP.getDisplayName()).getKey(), "Tutorial Items");
        add(((TranslatableComponent) ModGroups.ITEM_GROUP2.getDisplayName()).getKey(), "Tutorial Test Items");

        add(Registration.SUN_ORE_OVERWORLD.block(), "Sun Ore");
        add(Registration.SUN_ORE_DEEPSLATE.block(), "Sun Ore");
        add(Registration.SUN_ORE_NETHER.block(), "Sun Ore");
        add(Registration.SUN_ORE_END.block(), "Sun Ore");

        add(CopperChestBlock.CONTAINER_TITLE.getString(), "Copper Chest");
        add(Registration.COPPER_CHEST.block(), "Copper Chest");

        add(Registration.ENCHANTMENT_MULTIJUMP.get(), "MultiJump");

        add(KeyBindings.CATERGORY, TutorialMod.NAME);
        for (KeyBindings key : KeyBindings.values()) add(key.getName(), key.getTranslation());

        add(getSidedTranslationKey(ModConfig.Type.CLIENT), "Client Settings");
        add(getSidedTranslationKey(ModConfig.Type.COMMON), "Common Settings");
        add(getSidedTranslationKey(ModConfig.Type.SERVER), "Server Settings");
        ConfigScreen.Builder configBuilder = TutorialModConfig1.getBuilder();
        configBuilder.getList(ModConfig.Type.CLIENT).forEach(this::addOption);
        configBuilder.getList(ModConfig.Type.COMMON).forEach(this::addOption);
        configBuilder.getList(ModConfig.Type.SERVER).forEach(this::addOption);

        addMessage("tutorial_message.tooltip", "Just a Tutorial Tooltip");
    }

    private void addOption(SunOption option) { add(getTranslationKey(option.getType(), option.getTranslationKey()), option.getTranslation()); }

    private void addMessage(String key, String translation) { add("message." + TutorialMod.MODID + "." + key, translation); }

    @Override
    public String getName() { return TutorialMod.MODID + " " + super.getName(); }

    public static String getTranslationKey(ModConfig.Type screenType, String translationKey) { return getSidedTranslationKey(screenType) + "." + translationKey; }

    public static String getSidedTranslationKey(ModConfig.Type screenType) { return "option." + TutorialMod.MODID + "." + screenType.extension(); }
}
