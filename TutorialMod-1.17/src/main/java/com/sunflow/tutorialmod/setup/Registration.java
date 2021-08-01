package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MODID);

	public static void init(IEventBus bus) {
		ITEMS.register(bus);
	}

	public static final RegistryObject<Item> TUTORIAL_ITEM = ITEMS.register("tutorial_item", () -> new Item(new Item.Properties()));
}
