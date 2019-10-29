package com.sunflow.tutorialmod.init;

import java.util.ArrayList;
import java.util.List;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.items.EnergyWand;
import com.sunflow.tutorialmod.items.FancySwordItem;
import com.sunflow.tutorialmod.items.FirstItem;
import com.sunflow.tutorialmod.items.GrenadeItem;
import com.sunflow.tutorialmod.items.TestItem;
import com.sunflow.tutorialmod.items.armor.ArmorBase;
import com.sunflow.tutorialmod.items.armor.ArmorSkinBase;
import com.sunflow.tutorialmod.items.armor.CustomArmorMaterial;
import com.sunflow.tutorialmod.items.armor.ItemNBTSkin;
import com.sunflow.tutorialmod.items.armor.SkinUtil.SkinType;
import com.sunflow.tutorialmod.items.base.ItemBase;
import com.sunflow.tutorialmod.items.base.MobEggBase;
import com.sunflow.tutorialmod.items.food.FoodItem;
import com.sunflow.tutorialmod.items.food.SeedItem;
import com.sunflow.tutorialmod.items.tools.ToolAxe;
import com.sunflow.tutorialmod.items.tools.ToolHoe;
import com.sunflow.tutorialmod.items.tools.ToolMaterial;
import com.sunflow.tutorialmod.items.tools.ToolPickaxe;
import com.sunflow.tutorialmod.items.tools.ToolShovel;
import com.sunflow.tutorialmod.items.tools.ToolSword;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ModItems {
	public static final List<Item> ITEMS = new ArrayList<>();
	public static final List<MobEggBase> EGGS = new ArrayList<>();

// Items
	public static final Item FIRSTITEM = new FirstItem();
	public static final Item GRENADE = new GrenadeItem();
	public static final Item TESTITEM = new TestItem();
	public static final Item ENERGYWAND = new EnergyWand();
	public static final Item FANCY_SWORD = new FancySwordItem();

	public static final Item RUBY = new ItemBase("ruby");
	public static final Item COPPER_INGOT = new ItemBase("copper_ingot");

// Wands
	public static final Item WOOD_WAND = new ItemBase("wood_wand");
	public static final Item STONE_WAND = new ItemBase("stone_wand");
	public static final Item IRON_WAND = new ItemBase("iron_wand");
	public static final Item GOLD_WAND = new ItemBase("gold_wand");
	public static final Item DIAMOND_WAND = new ItemBase("diamond_wand");
	public static final Item RUBY_WAND = new ItemBase("ruby_wand");
	public static final Item OBSIDIAN_WAND = new ItemBase("obsidian_wand");
	public static final Item EMERALD_WAND = new ItemBase("emerald_wand");

// Tools
	public static final Item RUBY_AXE = new ToolAxe("ruby_axe", 6.0F, -3.1F, ToolMaterial.RUBY);
	public static final Item RUBY_HOE = new ToolHoe("ruby_hoe", -1.0f, ToolMaterial.RUBY);
	public static final Item RUBY_PICKAXE = new ToolPickaxe("ruby_pickaxe", 1, -3.2f, ToolMaterial.RUBY);
	public static final Item RUBY_SHOVEL = new ToolShovel("ruby_shovel", 1.5f, -3.0f, ToolMaterial.RUBY);
	public static final Item RUBY_SWORD = new ToolSword("ruby_sword", 6, -3f, ToolMaterial.RUBY);

// Spawn Eggs
	public static final MobEggBase WEIRDMOB_SPAWN_EGG = new MobEggBase("weirdmob_spawn_egg", 0xff1111);
	public static final MobEggBase CENTAUR_SPAWN_EGG = new MobEggBase("centaur_spawn_egg", 0xeecc11, TutorialMod.setup.itemGroup2);

// Armor
	public static final Item RUBY_HELMET = new ArmorBase("ruby_helmet", CustomArmorMaterial.RUBY, EquipmentSlotType.HEAD);
	public static final Item RUBY_CHESTPLATE = new ArmorBase("ruby_chestplate", CustomArmorMaterial.RUBY, EquipmentSlotType.CHEST);
	public static final Item RUBY_LEGGINGS = new ArmorBase("ruby_leggings", CustomArmorMaterial.RUBY, EquipmentSlotType.LEGS);
	public static final Item RUBY_BOOTS = new ArmorBase("ruby_boots", CustomArmorMaterial.RUBY, EquipmentSlotType.FEET);

	public static final Item SUNFLOW_HELMET = new ArmorSkinBase("sunflow_helmet", SkinType.SUNFLOW, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.HEAD);
	public static final Item SUNFLOW_CHESTPLATE = new ArmorSkinBase("sunflow_chestplate", SkinType.SUNFLOW, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.CHEST);
	public static final Item SUNFLOW_LEGGINGS = new ArmorSkinBase("sunflow_leggings", SkinType.SUNFLOW, CustomArmorMaterial.RUBY, 2, EquipmentSlotType.LEGS);
	public static final Item SUNFLOW_BOOTS = new ArmorSkinBase("sunflow_boots", SkinType.SUNFLOW, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.FEET);

	public static final Item BEKE_HELMET = new ArmorSkinBase("beke_helmet", SkinType.BEKESCSABA99, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.HEAD);
	public static final Item BEKE_CHESTPLATE = new ArmorSkinBase("beke_chestplate", SkinType.BEKESCSABA99, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.CHEST);
	public static final Item BEKE_LEGGINGS = new ArmorSkinBase("beke_leggings", SkinType.BEKESCSABA99, CustomArmorMaterial.RUBY, 2, EquipmentSlotType.LEGS);
	public static final Item BEKE_BOOTS = new ArmorSkinBase("beke_boots", SkinType.BEKESCSABA99, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.FEET);

	public static final Item PHANTOME_HELMET = new ArmorSkinBase("phantom_helmet", SkinType.PHANTOM, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.HEAD);
	public static final Item PHANTOM_CHESTPLATE = new ArmorSkinBase("phantom_chestplate", SkinType.PHANTOM, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.CHEST);
	public static final Item PHANTOME_LEGGINGS = new ArmorSkinBase("phantom_leggings", SkinType.PHANTOM, CustomArmorMaterial.RUBY, 2, EquipmentSlotType.LEGS);
	public static final Item PHANTOM_BOOTS = new ArmorSkinBase("phantom_boots", SkinType.PHANTOM, CustomArmorMaterial.RUBY, 1, EquipmentSlotType.FEET);

//	public static final Item HELMET_SKIN = new ArmorSkins("helmet_skin", ARMOR_MATERIAL_PHANTOM, 1, EquipmentSlotType.HEAD);
//	public static final Item CHESTPLATE_SKIN = new ArmorSkins("chestplate_skin", ARMOR_MATERIAL_PHANTOM, 1, EquipmentSlotType.CHEST);
//	public static final Item LEGGINGS_SKIN = new ArmorSkins("leggings_skin", ARMOR_MATERIAL_PHANTOM, 2, EquipmentSlotType.LEGS);
//	public static final Item BOOTS_SKIN = new ArmorSkins("boots_skin", ARMOR_MATERIAL_PHANTOM, 1, EquipmentSlotType.FEET);

	public static final Item[] SKIN = ItemNBTSkin.create();

	public static final Item EVIL_APPLE = new FoodItem("evil_apple", 6, 4, false, false, true,
			new EffectInstance(Effects.INSTANT_HEALTH, 1, 1),
			new EffectInstance(Effects.REGENERATION, 20 * 4, 0));

	public static final Item RICE_BOWL = new FoodItem("rice_bowl", 8, 2, false, false, false);
	public static final Item RICE = new SeedItem("rice", 3);
}
