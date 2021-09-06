package com.sunflow.tutorialmod.datagen;

import java.util.function.Consumer;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

public class RecipesProvider extends RecipeProvider {

	public RecipesProvider(DataGenerator generatorIn) { super(generatorIn); }

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(Registration.TUTORIAL_ITEM.get())
				.pattern("#sr")
				.pattern("rs#")
				.pattern("#sr")
				.define('r', Tags.Items.DYES_RED)
				.define('#', Tags.Items.STRING)
				.define('s', Items.STICK)
				.group("tutorial")
				.unlockedBy("sticks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
				.save(consumer);

		ShapedRecipeBuilder.shaped(Registration.GENERATOR_BLOCK.get())
				.pattern("iii")
				.pattern("ici")
				.pattern("iri")
				.define('r', Items.REDSTONE)
				.define('i', Items.IRON_INGOT)
				.define('c', Tags.Items.STORAGE_BLOCKS_COAL)
				.group("tutorial")
				.unlockedBy("coals", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COAL))
				.save(consumer);

	}

	@Override
	public String getName() {
		return TutorialMod.MODID + " " + super.getName();
	}
}
