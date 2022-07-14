package com.sunflow.tutorialmod.item;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.sunflow.tutorialmod.rendering.ModBEWLR;
import com.sunflow.tutorialmod.setup.ModTabs;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.IItemRenderProperties;

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

	public static Item BEWLR(Item.Properties properties) {
		Log.warn("Item BEWLR");
		return new Item(properties) {
			@Override
			public void initializeClient(Consumer<IItemRenderProperties> consumer) {
				consumer.accept(new IItemRenderProperties() {
					@Override
					public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
						return ModBEWLR.instance;
					}
				});
			}
		};
	}

	public static BlockItem BEWLR(Block block, Item.Properties properties) {
		Log.warn("BlockItem BEWLR");
		return new BlockItem(block, properties) {
			@Override
			public void initializeClient(Consumer<IItemRenderProperties> consumer) {
				consumer.accept(new IItemRenderProperties() {
					@Override
					public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
						return ModBEWLR.instance;
					}
				});
			}
		};
	}
}