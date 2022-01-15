package com.sunflow.tutorialmod.data.provider;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.item.armor.ItemNBTSkin;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryObject;

public class SunItemModelProvider extends ItemModelProvider {

	public SunItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) { super(generator, TutorialMod.MODID, existingFileHelper); }

	@Override
	protected void registerModels() {
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// BLOCKS
		singleHandheldTexture(Registration.RUBY.get());
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
		singleHandheldTexture(Registration.RICE.item());
		singleHandheldTexture(Registration.RICE_BOWL.get());

		// Machines
		blockItem(Registration.TELEPORTER.block());
		cubeAll(named(Registration.COMPLEX_MULTIPART.block()), modLoc("block/complex/main"));

		// Tree
		singleHandheldTexture(Registration.COPPER_INGOT.get());
		blockItem(Registration.COPPER_BLOCK.block());
		blockItem(Registration.COPPER_ORE_OVERWORLD.block());
		blockItem(Registration.COPPER_ORE_NETHER.block());
		blockItem(Registration.COPPER_ORE_END.block());
		blockItem(Registration.COPPER_ORE_DEEPSLATE.block());
		blockItem(Registration.COPPER_LEAVES.block());
		blockItem(Registration.COPPER_LOG.block());
		blockItem(Registration.COPPER_PLANKS.block());
		blockItem(Registration.COPPER_SAPLING.block());

		singleHandheldTexture(Registration.ALUMINIUM_INGOT.get());
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
		singleGeneratedTexture(Registration.MOLTEN_COPPER_BUCKET.get());

		// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// ITEMS
		singleHandheldTexture(Registration.FIRSTITEM.get());
		singleHandheldTexture(Registration.TESTITEM.get());
		singleHandheldTexture(Registration.ENERGYWAND.get());
		// singleTexture(Registration.FANCY_SWORD.get()); // TODO

		// Wands
		singleHandheldTexture(Registration.WOOD_WAND.get());
		singleHandheldTexture(Registration.STONE_WAND.get());
		singleHandheldTexture(Registration.IRON_WAND.get());
		singleHandheldTexture(Registration.GOLD_WAND.get());
		singleHandheldTexture(Registration.DIAMOND_WAND.get());
		singleHandheldTexture(Registration.RUBY_WAND.get());
		singleHandheldTexture(Registration.OBSIDIAN_WAND.get());
		singleHandheldTexture(Registration.EMERALD_WAND.get());

		// Tools
		singleHandheldTexture(Registration.RUBY_AXE.get());
		singleHandheldTexture(Registration.RUBY_HOE.get());
		singleHandheldTexture(Registration.RUBY_PICKAXE.get());
		singleHandheldTexture(Registration.RUBY_SHOVEL.get());
		singleHandheldTexture(Registration.RUBY_SWORD.get());

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

		for (RegistryObject<ItemNBTSkin> skin : Registration.SKIN) singleHandheldTexture(skin.get());

		// Food
		singleHandheldTexture(Registration.EVIL_APPLE.get());

		// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// ENTITIES
		singleHandheldTexture(Registration.GRENADE.get());
		withParent(Registration.WEIRDMOB_SPAWN_EGG.get(), "item/template_spawn_egg");
		withParent(Registration.CENTAUR_SPAWN_EGG.get(), "item/template_spawn_egg");

		// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// BLOCK ENTITES
		blockItem(Registration.FANCYBLOCK.block());
		singleGeneratedTexture(Registration.MAGICBLOCK.block(), "magicblock_item");
	}

	private <T extends IForgeRegistryEntry<? super T>> ItemModelBuilder singleHandheldTexture(T entry) {
		return singleHandheldTexture(entry, named(entry));
	}

	private <T extends IForgeRegistryEntry<? super T>> ItemModelBuilder singleHandheldTexture(T entry, String layer0) {
		return singleTexture(named(entry), mcLoc("item/handheld"), "layer0", modLoc("item/" + layer0));
	}

	private <T extends IForgeRegistryEntry<? super T>> ItemModelBuilder singleGeneratedTexture(T entry) {
		return singleGeneratedTexture(entry, named(entry));
	}

	private <T extends IForgeRegistryEntry<? super T>> ItemModelBuilder singleGeneratedTexture(T entry, String layer0) {
		return singleTexture(named(entry), mcLoc("item/generated"), "layer0", modLoc("item/" + layer0));
	}

	private <T extends IForgeRegistryEntry<? super T>> ItemModelBuilder withParent(T entry, String parent) {
		return withExistingParent(named(entry), mcLoc(parent));
	}

	private void blockItem(Block block) {
		String name = named(block);
		withExistingParent(name, modLoc("block/" + name));
	}

	private void skinTexture(Item item) {
		String name = named(item);
		singleTexture(name, mcLoc("item/handheld"), "layer0", modLoc("item/skin_" + name.split("_")[1]));
	}

	private <T extends IForgeRegistryEntry<? super T>> String named(T entry) { return entry.getRegistryName().getPath(); }

	@Override
	public String getName() { return TutorialMod.MODID + " " + super.getName(); }
}
