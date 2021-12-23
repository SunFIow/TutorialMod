package com.sunflow.tutorialmod.datagen;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.item.armor.ItemNBTSkin;
import com.sunflow.tutorialmod.item.armor.SkinUtil.SkinType;
import com.sunflow.tutorialmod.setup.Registration;

import org.stringtemplate.v4.compiler.CodeGenerator.region_return;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class SunItemModelProvider extends ItemModelProvider {

	public SunItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) { super(generator, TutorialMod.MODID, existingFileHelper); }

	@Override
	protected void registerModels() {
		blockItem(Registration.SUN_ORE_OVERWORLD.block());
		blockItem(Registration.SUN_ORE_DEEPSLATE.block());
		blockItem(Registration.SUN_ORE_NETHER.block());
		blockItem(Registration.SUN_ORE_END.block());
		blockItem(Registration.RUBY_BLOCK.block());

		skinTexture(Registration.RUBY_BOOTS.get());
		skinTexture(Registration.RUBY_CHESTPLATE.get());
		skinTexture(Registration.RUBY_HELMET.get());
		skinTexture(Registration.RUBY_LEGGINGS.get());
		skinTexture(Registration.BEKE_BOOTS.get());
		skinTexture(Registration.BEKE_CHESTPLATE.get());
		skinTexture(Registration.BEKE_HELMET.get());
		skinTexture(Registration.BEKE_LEGGINGS.get());
		skinTexture(Registration.PHANTOM_HELMET.get());
		skinTexture(Registration.PHANTOM_LEGGINGS.get());
		skinTexture(Registration.PHANTOM_BOOTS.get());
		skinTexture(Registration.PHANTOM_CHESTPLATE.get());
		skinTexture(Registration.SUNFLOW_BOOTS.get());
		skinTexture(Registration.SUNFLOW_CHESTPLATE.get());
		skinTexture(Registration.SUNFLOW_HELMET.get());
		skinTexture(Registration.SUNFLOW_LEGGINGS.get());
		for (RegistryObject<ItemNBTSkin> skin : Registration.SKIN) singleTexture(skin.get());
	}

	private void skinTexture(Item item) {
		String name = item.getRegistryName().getPath();
		singleTexture(name, mcLoc("item/handheld"), "layer0", modLoc("item/skin_" + name.split("_")[1]));
	}

	private void singleTexture(Item item) {
		String name = item.getRegistryName().getPath();
		singleTexture(name, mcLoc("item/handheld"), "layer0", modLoc("item/" + name));
	}

	private void blockItem(Block block) {
		String name = block.getRegistryName().getPath();
		withExistingParent(name, modLoc("block/" + name));
	}

	@Override
	public String getName() { return TutorialMod.MODID + " " + super.getName(); }
}
