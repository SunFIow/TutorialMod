package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {
    public static final String TAB_NAME = "tutorialmod";
    public static final CreativeModeTab ITEM_GROUP = (new CreativeModeTab(TAB_NAME) {
        @Override
        public ItemStack makeIcon() { return new ItemStack(Registration.SUN_ORE_OVERWORLD.block()); }

    }).setBackgroundImage(new ResourceLocation(TutorialMod.MODID, "textures/gui/container/creative_inventory/tab_tutorial.png"));

    private ModSetup() {}

    public static void init(FMLCommonSetupEvent event) {

    }
}
