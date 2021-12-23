package com.sunflow.tutorialmod.item.armor;

import java.util.function.Supplier;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public enum CustomArmorMaterial implements ArmorMaterial {

	// LEATHER("leather", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
	// return Ingredient.of(Items.LEATHER);
	// }),
	// CHAIN("chainmail", 15, new int[]{1, 4, 5, 2}, 12, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> {
	// return Ingredient.of(Items.IRON_INGOT);
	// }),
	// IRON("iron", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
	// return Ingredient.of(Items.IRON_INGOT);
	// }),
	// GOLD("gold", 7, new int[]{1, 3, 5, 2}, 25, SoundEvents.ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> {
	// return Ingredient.of(Items.GOLD_INGOT);
	// }),
	// DIAMOND("diamond", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	// return Ingredient.of(Items.DIAMOND);
	// }),
	// TURTLE("turtle", 25, new int[]{2, 5, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_TURTLE, 0.0F, 0.0F, () -> {
	// return Ingredient.of(Items.SCUTE);
	// }),
	// NETHERITE("netherite", 37, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> {
	// return Ingredient.of(Items.NETHERITE_INGOT);
	// });

	RUBY("ruby", 15, new int[] { 2, 5, 6, 2 }, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Registration.RUBY_BLOCK.item()));

	private static final int[] HEALTH_PER_SLOT = new int[] { 13, 15, 16, 11 };
	private final String name;
	private final int durabilityMultiplier;
	private final int[] slotProtections;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyLoadedValue<Ingredient> repairIngredient;

	private CustomArmorMaterial(String nameIn, int durabilityMultiplier, int[] damageReductionAmountsIn, int enchantmentValueIn, SoundEvent equipSoundIn, float toughness, float p_i231593_9_, Supplier<Ingredient> repairIngredientSupplier) {
		this.name = nameIn;
		this.durabilityMultiplier = durabilityMultiplier;
		this.slotProtections = damageReductionAmountsIn;
		this.enchantmentValue = enchantmentValueIn;
		this.sound = equipSoundIn;
		this.toughness = toughness;
		this.knockbackResistance = p_i231593_9_;
		this.repairIngredient = new LazyLoadedValue<>(repairIngredientSupplier);
	}

	@Override
	public int getDurabilityForSlot(EquipmentSlot slotIn) {
		return HEALTH_PER_SLOT[slotIn.getIndex()] * this.durabilityMultiplier;
	}

	@Override
	public int getDefenseForSlot(EquipmentSlot slotIn) {
		return this.slotProtections[slotIn.getIndex()];
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	@Override
	public SoundEvent getEquipSound() {
		return this.sound;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}

	@Override
	public String getName() {
		return TutorialMod.MODID + ":" + this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return knockbackResistance;
	}
}
