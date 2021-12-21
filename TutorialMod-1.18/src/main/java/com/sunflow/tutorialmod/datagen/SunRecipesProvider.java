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

public class SunRecipesProvider extends RecipeProvider {

	public SunRecipesProvider(DataGenerator generatorIn) { super(generatorIn); }

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		// ShapedRecipeBuilder.shaped(Registration.TUTORIAL_ITEM.get())
		// .pattern("#sr")
		// .pattern("rs#")
		// .pattern("#sr")
		// .define('r', Tags.Items.DYES_RED)
		// .define('#', Tags.Items.STRING)
		// .define('s', Items.STICK)
		// .group(TutorialMod.MODID)
		// .unlockedBy("sticks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
		// .save(consumer);
	}

	@Override
	public String getName() { return TutorialMod.MODID + " " + super.getName(); }
}
