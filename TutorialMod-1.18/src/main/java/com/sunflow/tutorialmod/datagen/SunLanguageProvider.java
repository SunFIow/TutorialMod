package com.sunflow.tutorialmod.datagen;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.ModSetup;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class SunLanguageProvider extends LanguageProvider {

    public SunLanguageProvider(DataGenerator generator, String locale) { super(generator, TutorialMod.MODID, locale); }

    @Override
    protected void addTranslations() {
        add(Registration.SUN_ORE_OVERWORLD.block(), "Sun Ore");
        add(Registration.SUN_ORE_DEEPSLATE.block(), "Sun Ore");
        add(Registration.SUN_ORE_NETHER.block(), "Sun Ore");
        add(Registration.SUN_ORE_END.block(), "Sun Ore");
        add("itemGroup." + ModSetup.TAB_NAME, "Tutorial Items");
        add("message.tutorialmod.tutorial_message.tooltip", "Just a Tutorial Tooltip");
    }

    @Override
    public String getName() { return TutorialMod.MODID + " " + super.getName(); }
}
