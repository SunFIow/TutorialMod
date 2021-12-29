package com.sunflow.tutorialmod.item.tools;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public enum ToolMaterial implements Tier {
	// WOOD(0, 59, 2.0F, 0.0F, 15, () -> {
	// return Ingredient.of(ItemTags.PLANKS);
	// }),
	// STONE(1, 131, 4.0F, 1.0F, 5, () -> {
	// return Ingredient.of(ItemTags.STONE_TOOL_MATERIALS);
	// }),
	// IRON(2, 250, 6.0F, 2.0F, 14, () -> {
	// return Ingredient.of(Items.IRON_INGOT);
	// }),
	// DIAMOND(3, 1561, 8.0F, 3.0F, 10, () -> {
	// return Ingredient.of(Items.DIAMOND);
	// }),
	// GOLD(0, 32, 12.0F, 0.0F, 22, () -> {
	// return Ingredient.of(Items.GOLD_INGOT);
	// }),
	// NETHERITE(4, 2031, 9.0F, 4.0F, 15, () -> {
	// return Ingredient.of(Items.NETHERITE_INGOT);
	// });

	// RUBY(2, 250, 6.0F, 2.0F, 14, () -> Ingredient.of(Registration.RUBY.get()), ModTags.Blocks.NEEDS_RUBY_TOOL);
	RUBY(2, 250, 6.0F, 2.0F, 14, () -> Ingredient.of(Registration.RUBY.get()), BlockTags.NEEDS_IRON_TOOL);

	private final int level;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final LazyLoadedValue<Ingredient> repairIngredient;
	private final Tag<Block> tag;

	private ToolMaterial(int harvestLevel, int maxUses, float speed, float attackDamage, int enchantmentValue, Supplier<Ingredient> repairIngredient, Tag<Block> tag) {
		this.level = harvestLevel;
		this.uses = maxUses;
		this.speed = speed;
		this.damage = attackDamage;
		this.enchantmentValue = enchantmentValue;
		this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
		this.tag = tag;
	}

	@Override
	public int getUses() { return this.uses; }

	@Override
	public float getSpeed() { return this.speed; }

	@Override
	public float getAttackDamageBonus() { return this.damage; }

	@Override
	public int getLevel() { return this.level; }

	@Override
	public int getEnchantmentValue() { return this.enchantmentValue; }

	@Override
	public Ingredient getRepairIngredient() { return this.repairIngredient.get(); }

	@Override
	public Tag<Block> getTag() { return this.tag; }
}