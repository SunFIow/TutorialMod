package com.sunflow.tutorialmod.item;

import com.sunflow.tutorialmod.rendering.ModISTER;
import com.sunflow.tutorialmod.setup.ModGroups;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.item.Food;
import net.minecraft.item.Food.Builder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class ItemUtil {

	public static Item Default() { return new Item(new Item.Properties().group(ModGroups.itemGroup)); }

	public static Item.Properties Food(ItemGroup group, int hunger, float saturation) {
		return new Item.Properties()
				.group(group)
				.food(new Food.Builder()
						.hunger(hunger)
						.saturation(saturation)
						.build());
	}

	public static Item.Properties Food(ItemGroup group, int hunger, float saturation, boolean meat, boolean fastEat, boolean alwaysEdible, EffectInstance... effects) {
		Builder food = new Food.Builder()
				.hunger(hunger)
				.saturation(saturation);
		if (meat) food.meat();
		if (fastEat) food.fastToEat();
		if (alwaysEdible) food.setAlwaysEdible();
		for (EffectInstance effect : effects) food.effect(() -> effect, 1.0f);
		return new Item.Properties()
				.group(group)
				.food(food.build());
	}

	public static Item.Properties ISTER(ItemGroup group) {
		Log.warn("ISTER");
		Item.Properties properties = new Item.Properties().group(group);
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> properties.setISTER(() -> () -> ModISTER.instance));
//		if (TutorialMod.proxy.isClient()) properties.setISTER(() -> () -> ModISTER.instance);
		return properties;
	}
}