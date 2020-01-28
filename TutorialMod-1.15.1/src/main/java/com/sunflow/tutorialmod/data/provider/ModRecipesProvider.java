package com.sunflow.tutorialmod.data.provider;

import java.util.function.Consumer;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.registration.Registration;

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
		ShapedRecipeBuilder.shapedRecipe(Registration.FIRSTBLOCK.get())
				.patternLine(" R ")
				.patternLine("R#R")
				.patternLine(" R ")
				.key('#', Blocks.COBBLESTONE)
				.key('R', Tags.Items.DYES_RED)
				.setGroup("tutorialmod")
				.addCriterion("has_cobblestone", hasItem(Blocks.COBBLESTONE))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(Registration.COPPER_BLOCK.get())
				.patternLine("###")
				.patternLine("###")
				.patternLine("###")
				.key('#', Registration.COPPER_INGOT.get())
				.setGroup("tutorialmod")
				.addCriterion("has_copper_ingot", hasItem(Registration.COPPER_INGOT.get()))
				.build(consumer);
		ShapelessRecipeBuilder.shapelessRecipe(Registration.COPPER_INGOT.get(), 9)
				.addIngredient(Registration.COPPER_BLOCK.get())
				.addCriterion("has_copper_block", hasItem(Registration.COPPER_BLOCK.get()))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(Registration.RUBY_BLOCK.get())
				.patternLine("###")
				.patternLine("###")
				.patternLine("###")
				.key('#', Registration.RUBY.get())
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(Registration.RUBY.get()))
				.build(consumer);
		ShapelessRecipeBuilder.shapelessRecipe(Registration.RUBY.get(), 9)
				.addIngredient(Registration.RUBY_BLOCK.get())
				.addCriterion("has_ruby_block", hasItem(Registration.RUBY_BLOCK.get()))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(Registration.RUBY_AXE.get())
				.patternLine("##")
				.patternLine("#S")
				.patternLine(" S")
				.key('#', Registration.RUBY.get())
				.key('S', Items.STICK)
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(Registration.RUBY.get()))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(Registration.RUBY_HOE.get())
				.patternLine("##")
				.patternLine(" S")
				.patternLine(" S")
				.key('#', Registration.RUBY.get())
				.key('S', Items.STICK)
				.setGroup("has_tutorialmod")
				.addCriterion("ruby", hasItem(Registration.RUBY.get()))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(Registration.RUBY_PICKAXE.get())
				.patternLine("###")
				.patternLine(" S ")
				.patternLine(" S ")
				.key('#', Registration.RUBY.get())
				.key('S', Items.STICK)
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(Registration.RUBY.get()))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(Registration.RUBY_SHOVEL.get())
				.patternLine("#")
				.patternLine("S")
				.patternLine("S")
				.key('#', Registration.RUBY.get())
				.key('S', Items.STICK)
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(Registration.RUBY.get()))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(Registration.RUBY_SWORD.get())
				.patternLine("#")
				.patternLine("#")
				.patternLine("S")
				.key('#', Registration.RUBY.get())
				.key('S', Items.STICK)
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(Registration.RUBY.get()))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(Registration.RUBY_HELMET.get())
				.patternLine("###")
				.patternLine("# #")
				.key('#', Registration.RUBY.get())
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(Registration.RUBY.get()))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(Registration.RUBY_CHESTPLATE.get())
				.patternLine("# #")
				.patternLine("###")
				.patternLine("###")
				.key('#', Registration.RUBY.get())
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(Registration.RUBY.get()))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(Registration.RUBY_LEGGINGS.get())
				.patternLine("###")
				.patternLine("# #")
				.patternLine("# #")
				.key('#', Registration.RUBY.get())
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(Registration.RUBY.get()))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(Registration.RUBY_BOOTS.get())
				.patternLine("# #")
				.patternLine("# #")
				.key('#', Registration.RUBY.get())
				.setGroup("tutorialmod")
				.addCriterion("has_ruby", hasItem(Registration.RUBY.get()))
				.build(consumer);
	}

	@Override
	public String getName() { return TutorialMod.NAME + super.getName(); }
}
