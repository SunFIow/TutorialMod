package com.sunflow.tutorialmod.item.food;

import com.sunflow.tutorialmod.item.base.ItemBase;
import com.sunflow.tutorialmod.setup.ModGroups;

import net.minecraft.item.Food;
import net.minecraft.item.Food.Builder;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;

public class FoodItem extends ItemBase {

	public FoodItem(String name, int hunger, float saturation, boolean meat, boolean fastEat, boolean alwaysEdible, EffectInstance... effects) {
		super(name, getProperties(hunger, saturation, meat, fastEat, alwaysEdible, effects));
	}

	private static Properties getProperties(int hunger, float saturation, boolean meat, boolean fastEat, boolean alwaysEdible, EffectInstance... effects) {
		Builder food = new Food.Builder().hunger(hunger).saturation(saturation);
		if (meat) {
			food.meat();
		}
		if (fastEat) {
			food.fastToEat();
		}
		if (alwaysEdible) {
			food.setAlwaysEdible();
		}
		for (EffectInstance effect : effects) {
			food.effect(effect, 1.0f);
		}
		Properties prop = new Item.Properties().group(ModGroups.itemGroup).food(food.build());
		return prop;
	}
}
