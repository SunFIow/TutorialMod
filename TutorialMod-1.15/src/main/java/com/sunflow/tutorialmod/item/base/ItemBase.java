package com.sunflow.tutorialmod.item.base;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

import com.sunflow.tutorialmod.rendering.ModISTER;
import com.sunflow.tutorialmod.setup.ModGroups;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.Food;
import net.minecraft.item.Food.Builder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class ItemBase extends Item {

//	protected String customName;

	public ItemBase() { super(new Item.Properties().group(ModGroups.itemGroup)); }

	public ItemBase(ItemGroup group) { super(new Item.Properties().group(group)); }

	public ItemBase(Item.Properties properties) { super(properties); }

	public static Item.Properties Stackable(int maxStackSize, ItemGroup group) { return new Item.Properties().maxStackSize(maxStackSize).group(group); }

	public static Item.Properties Stackable(int maxStackSize) { return Stackable(maxStackSize, ModGroups.itemGroup); }

	public static Item.Properties Damageable(int maxDamage, ItemGroup group) { return new Item.Properties().maxDamage(maxDamage).group(group); }

	public static Item.Properties Damageable(int maxDamage) { return Damageable(maxDamage, ModGroups.itemGroup); }

	public static Item.Properties Food(int hunger, float saturation) {
		return new Item.Properties()
				.group(ModGroups.itemGroup)
				.food(new Food.Builder()
						.hunger(hunger)
						.saturation(saturation)
						.build());
	}

	public static Item.Properties Food(int hunger, float saturation, boolean meat, boolean fastEat, boolean alwaysEdible, EffectInstance... effects) {
		Builder food = new Food.Builder()
				.hunger(hunger)
				.saturation(saturation);
		if (meat) food.meat();
		if (fastEat) food.fastToEat();
		if (alwaysEdible) food.setAlwaysEdible();
		for (EffectInstance effect : effects) food.effect(effect, 1.0f);
		return new Item.Properties()
				.group(ModGroups.itemGroup)
				.food(food.build());
	}

	public static Item.Properties ISTER(Supplier<Block> block, Supplier<TileEntity> tileEntity) {
		Object tmp = DistExecutor.callWhenOn(Dist.CLIENT, () -> () -> new ModISTER(block, tileEntity));
		Supplier<Callable<ItemStackTileEntityRenderer>> ister = tmp == null ? null : () -> () -> (ItemStackTileEntityRenderer) tmp;
		return new Item.Properties().group(ModGroups.itemGroup).setISTER(ister);
	}
}