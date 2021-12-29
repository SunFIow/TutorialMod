package com.sunflow.tutorialmod.datagen;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.item.armor.ItemNBTSkin;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryObject;

public class SunItemModelProvider extends ItemModelProvider {

	public SunItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) { super(generator, TutorialMod.MODID, existingFileHelper); }

	@Override
	protected void registerModels() {
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// BLOCKS
		singleTexture(Registration.RUBY.get());
		blockItem(Registration.RUBY_BLOCK.block());
		blockItem(Registration.CONFIG_BLOCK.block());
		blockItem(Registration.MULTIBLOCK.block());

		// Ores
		blockItem(Registration.RUBY_ORE.block());
		blockItem(Registration.SUN_ORE_OVERWORLD.block());
		blockItem(Registration.SUN_ORE_NETHER.block());
		blockItem(Registration.SUN_ORE_END.block());
		blockItem(Registration.SUN_ORE_DEEPSLATE.block());

		// Furniture
		blockItem(Registration.SANTA_HAT.block());

		// Food
		singleTexture(Registration.RICE.item());
		singleTexture(Registration.RICE_BOWL.get());

		// Machines
		blockItem(Registration.TELEPORTER.block());

		// Tree
		singleTexture(Registration.COPPER_INGOT.get());
		blockItem(Registration.COPPER_BLOCK.block());
		blockItem(Registration.COPPER_ORE_OVERWORLD.block());
		blockItem(Registration.COPPER_ORE_NETHER.block());
		blockItem(Registration.COPPER_ORE_END.block());
		blockItem(Registration.COPPER_ORE_DEEPSLATE.block());
		blockItem(Registration.COPPER_LEAVES.block());
		blockItem(Registration.COPPER_LOG.block());
		blockItem(Registration.COPPER_PLANKS.block());
		blockItem(Registration.COPPER_SAPLING.block());

		singleTexture(Registration.ALUMINIUM_INGOT.get());
		blockItem(Registration.ALUMINIUM_BLOCK.block());
		blockItem(Registration.ALUMINIUM_ORE_OVERWORLD.block());
		blockItem(Registration.ALUMINIUM_ORE_NETHER.block());
		blockItem(Registration.ALUMINIUM_ORE_END.block());
		blockItem(Registration.ALUMINIUM_ORE_DEEPSLATE.block());
		blockItem(Registration.ALUMINIUM_LEAVES.block());
		blockItem(Registration.ALUMINIUM_LOG.block());
		blockItem(Registration.ALUMINIUM_PLANKS.block());
		blockItem(Registration.ALUMINIUM_SAPLING.block());

		// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// FLUIDS

		// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// ITEMS
		singleTexture(Registration.FIRSTITEM.get());
		singleTexture(Registration.TESTITEM.get());
		singleTexture(Registration.ENERGYWAND.get());
		// singleTexture(Registration.FANCY_SWORD.get()); // TODO

		// Wands
		singleTexture(Registration.WOOD_WAND.get());
		singleTexture(Registration.STONE_WAND.get());
		singleTexture(Registration.IRON_WAND.get());
		singleTexture(Registration.GOLD_WAND.get());
		singleTexture(Registration.DIAMOND_WAND.get());
		singleTexture(Registration.RUBY_WAND.get());
		singleTexture(Registration.OBSIDIAN_WAND.get());
		singleTexture(Registration.EMERALD_WAND.get());

		// Tools
		singleTexture(Registration.RUBY_AXE.get());
		singleTexture(Registration.RUBY_HOE.get());
		singleTexture(Registration.RUBY_PICKAXE.get());
		singleTexture(Registration.RUBY_SHOVEL.get());
		singleTexture(Registration.RUBY_SWORD.get());

		// Armor
		skinTexture(Registration.RUBY_HELMET.get());
		skinTexture(Registration.RUBY_CHESTPLATE.get());
		skinTexture(Registration.RUBY_LEGGINGS.get());
		skinTexture(Registration.RUBY_BOOTS.get());
		skinTexture(Registration.SUNFLOW_HELMET.get());
		skinTexture(Registration.SUNFLOW_CHESTPLATE.get());
		skinTexture(Registration.SUNFLOW_LEGGINGS.get());
		skinTexture(Registration.SUNFLOW_BOOTS.get());
		skinTexture(Registration.BEKE_HELMET.get());
		skinTexture(Registration.BEKE_CHESTPLATE.get());
		skinTexture(Registration.BEKE_LEGGINGS.get());
		skinTexture(Registration.BEKE_BOOTS.get());
		skinTexture(Registration.PHANTOM_HELMET.get());
		skinTexture(Registration.PHANTOM_CHESTPLATE.get());
		skinTexture(Registration.PHANTOM_LEGGINGS.get());
		skinTexture(Registration.PHANTOM_BOOTS.get());

		for (RegistryObject<ItemNBTSkin> skin : Registration.SKIN) singleTexture(skin.get());

		// Food
		singleTexture(Registration.EVIL_APPLE.get());

		// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// ENTITIES
		singleTexture(Registration.GRENADE.get());
		withParent(Registration.WEIRDMOB_SPAWN_EGG.get(), "item/template_spawn_egg");
		withParent(Registration.CENTAUR_SPAWN_EGG.get(), "item/template_spawn_egg");
	}

	private void singleTexture(Item item) {
		String name = item.getRegistryName().getPath();
		singleTexture(name, mcLoc("item/handheld"), "layer0", modLoc("item/" + name));
	}

	private <T extends IForgeRegistryEntry<? super T>> void withParent(T entry, String parent) {
		withExistingParent(entry.getRegistryName().getPath(), mcLoc(parent));
	}

	private void blockItem(Block block) {
		String name = block.getRegistryName().getPath();
		withExistingParent(name, modLoc("block/" + name));
	}

	private void skinTexture(Item item) {
		String name = item.getRegistryName().getPath();
		singleTexture(name, mcLoc("item/handheld"), "layer0", modLoc("item/skin_" + name.split("_")[1]));
	}

	@Override
	public String getName() { return TutorialMod.MODID + " " + super.getName(); }
}
