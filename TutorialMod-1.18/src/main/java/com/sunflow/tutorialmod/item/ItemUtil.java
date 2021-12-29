package com.sunflow.tutorialmod.item;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.setup.ModTabs;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class ItemUtil {

	public static Item Default() { return new Item(new Item.Properties().tab(ModTabs.ITEM_TAB)); }

	public static Item.Properties Food(CreativeModeTab tab, int nutrition, float saturation) {
		return new Item.Properties()
				.tab(tab)
				.food(new FoodProperties.Builder()
						.nutrition(nutrition)
						.saturationMod(saturation)
						.build());
	}

	public static Item.Properties Food(CreativeModeTab tab, int nutrition, float saturation, boolean meat, boolean fastEat, boolean alwaysEdible, Supplier<MobEffectInstance>... effects) {
		FoodProperties.Builder food = new FoodProperties.Builder()
				.nutrition(nutrition)
				.saturationMod(saturation);
		if (meat) food.meat();
		if (fastEat) food.fast();
		if (alwaysEdible) food.alwaysEat();
		for (Supplier<MobEffectInstance> effect : effects) food.effect(effect, 1.0f);
		return new Item.Properties()
				.tab(tab)
				.food(food.build());

	}

	public static Item.Properties ISTER(CreativeModeTab tab) {
		Log.warn("ISTER");
		Item.Properties properties = new Item.Properties().tab(tab);
		// DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> properties.setISTER(() -> () -> ModISTER.instance));
		// if (TutorialMod.proxy.isClient()) properties.setISTER(() -> () -> ModISTER.instance);
		return properties;
	}
}