package com.sunflow.tutorialmod.data.provider;

import java.util.function.Consumer;

import com.sunflow.tutorialmod.setup.ModBlocks;
import com.sunflow.tutorialmod.setup.ModItems;

import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

public class ModRecipesProvider extends RecipeProvider {

	public ModRecipesProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		ShapedRecipeBuilder.shapedRecipe(ModBlocks.FIRSTBLOCK)
				.patternLine(" R ")
				.patternLine("R#R")
				.patternLine(" R ")
				.key('#', Blocks.COBBLESTONE)
				.key('R', Tags.Items.DYES_RED)
				.setGroup("tutorialmod")
				.addCriterion("has_cobblestone", hasItem(Blocks.COBBLESTONE))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModBlocks.COPPER_BLOCK)
				.patternLine("###")
				.patternLine("###")
				.patternLine("###")
				.key('#', ModItems.COPPER_INGOT)
				.setGroup("tutorialmod")
				.addCriterion("has_copper_ingot", hasItem(ModItems.COPPER_INGOT))
				.build(consumer);
		ShapelessRecipeBuilder.shapelessRecipe(ModItems.COPPER_INGOT, 9)
				.addIngredient(ModBlocks.COPPER_BLOCK)
				.addCriterion("has_copper_block", hasItem(ModBlocks.COPPER_BLOCK))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModBlocks.RUBY_BLOCK)
				.patternLine("###")
				.patternLine("###")
				.patternLine("###")
				.key('#', ModItems.RUBY)
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(ModItems.RUBY))
				.build(consumer);
		ShapelessRecipeBuilder.shapelessRecipe(ModItems.RUBY, 9)
				.addIngredient(ModBlocks.RUBY_BLOCK)
				.addCriterion("has_ruby_block", hasItem(ModBlocks.RUBY_BLOCK))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.RUBY_AXE)
				.patternLine("##")
				.patternLine("#S")
				.patternLine(" S")
				.key('#', ModItems.RUBY)
				.key('S', Items.STICK)
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(ModItems.RUBY))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.RUBY_HOE)
				.patternLine("##")
				.patternLine(" S")
				.patternLine(" S")
				.key('#', ModItems.RUBY)
				.key('S', Items.STICK)
				.setGroup("has_tutorialmod")
				.addCriterion("ruby", hasItem(ModItems.RUBY))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.RUBY_PICKAXE)
				.patternLine("###")
				.patternLine(" S ")
				.patternLine(" S ")
				.key('#', ModItems.RUBY)
				.key('S', Items.STICK)
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(ModItems.RUBY))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.RUBY_SHOVEL)
				.patternLine("#")
				.patternLine("S")
				.patternLine("S")
				.key('#', ModItems.RUBY)
				.key('S', Items.STICK)
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(ModItems.RUBY))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.RUBY_SWORD)
				.patternLine("#")
				.patternLine("#")
				.patternLine("S")
				.key('#', ModItems.RUBY)
				.key('S', Items.STICK)
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(ModItems.RUBY))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.RUBY_HELMET)
				.patternLine("###")
				.patternLine("# #")
				.key('#', ModItems.RUBY)
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(ModItems.RUBY))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.RUBY_CHESTPLATE)
				.patternLine("# #")
				.patternLine("###")
				.patternLine("###")
				.key('#', ModItems.RUBY)
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(ModItems.RUBY))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.RUBY_LEGGINGS)
				.patternLine("###")
				.patternLine("# #")
				.patternLine("# #")
				.key('#', ModItems.RUBY)
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(ModItems.RUBY))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.RUBY_BOOTS)
				.patternLine("# #")
				.patternLine("# #")
				.key('#', ModItems.RUBY)
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(ModItems.RUBY))
				.build(consumer);
	}

	@Override
	public String getName() {
		return "TutorialMod " + super.getName();
	}
}
