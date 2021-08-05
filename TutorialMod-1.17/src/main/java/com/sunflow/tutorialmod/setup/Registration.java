package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.item.TutorialItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {

	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MODID);

	public static void init(IEventBus bus) {
		ITEMS.register(bus);
	}

	public static final RegistryObject<TutorialItem> TUTORIAL_ITEM = ITEMS.register("tutorial_item", TutorialItem::new);
}
