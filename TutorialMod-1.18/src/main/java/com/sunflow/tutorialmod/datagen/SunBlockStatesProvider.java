package com.sunflow.tutorialmod.datagen;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SunBlockStatesProvider extends BlockStateProvider {

	public SunBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) { super(gen, TutorialMod.MODID, exFileHelper); }

	@Override
	protected void registerStatesAndModels() {
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// BLOCKS
		simpleBlock(Registration.RUBY_BLOCK.block());
		simpleBlock(Registration.CONFIG_BLOCK.block());
		simpleBlock(Registration.MULTIBLOCK.block());

		// Ores
		simpleBlock(Registration.RUBY_ORE.block());
		simpleBlock(Registration.SUN_ORE_OVERWORLD.block());
		simpleBlock(Registration.SUN_ORE_NETHER.block());
		simpleBlock(Registration.SUN_ORE_END.block());
		simpleBlock(Registration.SUN_ORE_DEEPSLATE.block());

		// Furniture
		horizontalBlock(Registration.SANTA_HAT.block(), models().getExistingFile(modLoc(named(Registration.SANTA_HAT.block()))));

		// Food
		cropBlock(Registration.RICE.block());

		// Machines
		simpleBlock(Registration.TELEPORTER.block());

		// Tree
		simpleBlock(Registration.COPPER_BLOCK.block());
		simpleBlock(Registration.COPPER_ORE_OVERWORLD.block());
		simpleBlock(Registration.COPPER_ORE_NETHER.block());
		simpleBlock(Registration.COPPER_ORE_END.block());
		simpleBlock(Registration.COPPER_ORE_DEEPSLATE.block());
		simpleBlock(Registration.COPPER_LEAVES.block());
		columBlock(Registration.COPPER_LOG.block());
		simpleBlock(Registration.COPPER_PLANKS.block());
		crossBlock(Registration.COPPER_SAPLING.block());

		simpleBlock(Registration.ALUMINIUM_BLOCK.block());
		simpleBlock(Registration.ALUMINIUM_ORE_OVERWORLD.block());
		simpleBlock(Registration.ALUMINIUM_ORE_NETHER.block());
		simpleBlock(Registration.ALUMINIUM_ORE_END.block());
		simpleBlock(Registration.ALUMINIUM_ORE_DEEPSLATE.block());
		simpleBlock(Registration.ALUMINIUM_LEAVES.block());
		columBlock(Registration.ALUMINIUM_LOG.block());
		simpleBlock(Registration.ALUMINIUM_PLANKS.block());
		crossBlock(Registration.ALUMINIUM_SAPLING.block());

		// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// FLUIDS

		// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// BLOCK ENTITES
		customLoaderBlock(Registration.FANCYBLOCK.block());
		simpleBlock(Registration.MAGICBLOCK.block(), models().getBuilder(named(Registration.MAGICBLOCK.block())).texture("particle", modLoc("block/" + named(Registration.MAGICBLOCK.block()))));
	}

	private void customLoaderBlock(Block block) {
		simpleBlock(block, models().getBuilder(named(block)).customLoader((builder, helper) -> {
			return new CustomLoaderBuilder<BlockModelBuilder>(modLoc("customloader"), builder, helper) {};
		}).end());
	}

	private void cropBlock(Block block) {
		VariantBlockStateBuilder builder = getVariantBuilder(block);
		for (int i = 0; i <= CropBlock.MAX_AGE; i++) {
			String stage = named(block) + "_stage_" + i;
			builder.partialState().with(CropBlock.AGE, i).modelForState().modelFile(models().crop(stage, modLoc("block/" + stage))).addModel();
		}
	}

	private void crossBlock(Block block) {
		simpleBlock(block, models().cross(block.getRegistryName().getPath(), modLoc("block/" + block.getRegistryName().getPath())));
	}

	private void columBlock(Block block) {
		String name = named(block);
		simpleBlock(block, models().cubeColumn(name, modLoc("block/" + name), modLoc("block/" + name + "_top")));
	}

	private String named(ForgeRegistryEntry<?> entry) { return entry.getRegistryName().getPath(); }

	@Override
	public String getName() { return TutorialMod.MODID + " " + super.getName(); }

	// private void registerGeneratorBlock() {
	// 	ResourceLocation loc_side = new ResourceLocation(TutorialMod.MODID, "block/generator_side");
	// 	ResourceLocation loc_front = new ResourceLocation(TutorialMod.MODID, "block/generator_front");
	// 	ResourceLocation loc_powered = new ResourceLocation(TutorialMod.MODID, "block/generator_powered");
	// 	BlockModelBuilder modelGenBlock = models().cube("generator", loc_side, loc_side, loc_front, loc_side, loc_side, loc_side);
	// 	BlockModelBuilder modelGenBlockPowered = models().cube("generator_powered", loc_side, loc_side, loc_powered, loc_side, loc_side, loc_side);
	// 	orientatedBlock(Registration.GENERATOR_BLOCK.get(),
	// 			state -> state.getValue(BlockStateProperties.POWERED) ? modelGenBlockPowered : modelGenBlock,
	// 			dir -> dir.getAxis() == Direction.Axis.Y ? dir.getAxisDirection().getStep() * -90 : 0,
	// 			dir -> dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0);
	// }

	// private void orientatedBlock(Block block, Function<BlockState, ModelFile> modelFunc, Function<Direction, Integer> rotXFunc, Function<Direction, Integer> rotYFunc) {
	// 	getVariantBuilder(block).forAllStates(state -> {
	// 		Direction dir = state.getValue(BlockStateProperties.FACING);
	// 		return ConfiguredModel.builder()
	// 				.modelFile(modelFunc.apply(state))
	// 				.rotationX(rotXFunc.apply(dir))
	// 				.rotationY(rotYFunc.apply(dir))
	// 				.build();
	// 	});
	// }

}
