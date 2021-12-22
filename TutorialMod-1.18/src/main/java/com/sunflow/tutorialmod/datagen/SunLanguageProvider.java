package com.sunflow.tutorialmod.datagen;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.ModGroups;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.common.data.LanguageProvider;

public class SunLanguageProvider extends LanguageProvider {

    public SunLanguageProvider(DataGenerator generator, String locale) { super(generator, TutorialMod.MODID, locale); }

    @Override
    protected void addTranslations() {
        add(Registration.SUN_ORE_OVERWORLD.block(), "Sun Ore");
        add(Registration.SUN_ORE_DEEPSLATE.block(), "Sun Ore");
        add(Registration.SUN_ORE_NETHER.block(), "Sun Ore");
        add(Registration.SUN_ORE_END.block(), "Sun Ore");
        add(((TranslatableComponent) ModGroups.ITEM_GROUP.getDisplayName()).getKey(), "Tutorial Items");
        add(((TranslatableComponent) ModGroups.ITEM_GROUP2.getDisplayName()).getKey(), "Tutorial Test Items");
        add("message.tutorialmod.tutorial_message.tooltip", "Just a Tutorial Tooltip");
        add(Registration.ENCHANTMENT_MULTIJUMP.get(), "MutliJump");
        add("container.copper_chest", "Copper Chest");
    }

    @Override
    public String getName() { return TutorialMod.MODID + " " + super.getName(); }
}
