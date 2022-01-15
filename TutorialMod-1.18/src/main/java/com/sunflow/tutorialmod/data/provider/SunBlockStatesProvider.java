package com.sunflow.tutorialmod.data.provider;

import java.util.function.Function;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.multipart.ComplexMultipart;
import com.sunflow.tutorialmod.block.multipart.ComplexMultipartEntity;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
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
		// horizontalBlock(Registration.SANTA_HAT.block(), models().getExistingFile(modLoc(named(Registration.SANTA_HAT.block()))));
		horizontalBlock(Registration.SANTA_HAT.block(), models().getExistingFile(Registration.SANTA_HAT.block().getRegistryName()));

		// Food
		cropBlock(Registration.RICE.block());

		// Machines
		simpleBlock(Registration.TELEPORTER.block());
		registerComplexMultipart();

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
		// simpleBlock(Registration.MOLTEN_COPPER_BLOCK.get(), cubeAll(Blocks.LAVA));
		simpleBlock(Registration.MOLTEN_COPPER_BLOCK.get(), models().withExistingParent(named(Registration.MOLTEN_COPPER_BLOCK.get()), "block/lava"));

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

	private void registerGeneratorBlock() {
		ResourceLocation loc_side = new ResourceLocation(TutorialMod.MODID, "block/generator_side");
		ResourceLocation loc_front = new ResourceLocation(TutorialMod.MODID, "block/generator_front");
		ResourceLocation loc_powered = new ResourceLocation(TutorialMod.MODID, "block/generator_powered");
		BlockModelBuilder modelGenBlock = models().cube("generator", loc_side, loc_side, loc_front, loc_side, loc_side, loc_side);
		BlockModelBuilder modelGenBlockPowered = models().cube("generator_powered", loc_side, loc_side, loc_powered, loc_side, loc_side, loc_side);
		orientatedBlock(Registration.ALUMINIUM_BLOCK.block(), // TODO 
				state -> state.getValue(BlockStateProperties.POWERED) ? modelGenBlockPowered : modelGenBlock,
				dir -> dir.getAxis() == Direction.Axis.Y ? dir.getAxisDirection().getStep() * -90 : 0,
				dir -> dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0);
	}

	private void registerComplexMultipart() {
		MultiPartBlockStateBuilder bld = getMultipartBuilder(Registration.COMPLEX_MULTIPART.block());

		BlockModelBuilder complexMultipartFrame = makeFrame("block/complex/main", "block/complex/main", "block/complex/window", "window", 0f, 1f);
		floatingCube(complexMultipartFrame, "window", 1f, 1f, 1f, 15f, 15f, 15f);

		BlockModelBuilder singleNone = models().getBuilder("block/complex/singlenone")
				.element().from(3f, 3, 3f).to(13f, 13f, 13f).face(Direction.DOWN).texture("#single").end().end()
				.texture("single", modLoc("block/complex/main"));
		BlockModelBuilder singleIn = models().getBuilder("block/complex/singlein")
				.element().from(3f, 3f, 3f).to(13f, 13f, 13f).face(Direction.DOWN).texture("#single").end().end()
				.texture("single", modLoc("block/complex/input"));
		BlockModelBuilder singleOut = models().getBuilder("block/complex/singleout")
				.element().from(3f, 3f, 3f).to(13f, 13f, 13f).face(Direction.DOWN).texture("#single").end().end()
				.texture("single", modLoc("block/complex/output"));

		bld.part().modelFile(complexMultipartFrame).addModel();

		BlockModelBuilder[] models = new BlockModelBuilder[] { singleNone, singleIn, singleOut };
		for (ComplexMultipartEntity.Mode mode : ComplexMultipartEntity.Mode.values()) {
			BlockModelBuilder model = models[mode.ordinal()];
			bld.part().modelFile(model).addModel().condition(ComplexMultipart.DOWN, mode);
			bld.part().modelFile(model).rotationX(180).addModel().condition(ComplexMultipart.UP, mode);
			bld.part().modelFile(model).rotationX(90).addModel().condition(ComplexMultipart.SOUTH, mode);
			bld.part().modelFile(model).rotationX(270).addModel().condition(ComplexMultipart.NORTH, mode);
			bld.part().modelFile(model).rotationY(90).rotationX(90).addModel().condition(ComplexMultipart.WEST, mode);
			bld.part().modelFile(model).rotationY(270).rotationX(90).addModel().condition(ComplexMultipart.EAST, mode);
		}
	}

	private BlockModelBuilder makeFrame(String modelPath, String particlePath, String texPath, String texName, float o, float s) {
		BlockModelBuilder frame = models().getBuilder(modelPath);

		frame.texture("particle", modLoc(particlePath));
		frame.texture(texName, modLoc(texPath));

		floatingCube(frame, texName, o, o, o, o + s, 16f - o, o + s);
		floatingCube(frame, texName, 16f - o - s, o, o, 16f - o, 16f - o, o + s);
		floatingCube(frame, texName, o, o, 16f - o - s, o + s, 16f - o, 16f - o);
		floatingCube(frame, texName, 16f - o - s, o, 16f - o - s, 16f - o, 16f - o, 16f - o);

		floatingCube(frame, texName, o + s, o, o, 16f - o - s, o + s, o + s);
		floatingCube(frame, texName, o + s, 16f - o - s, o, 16f - o - s, 16f - o, o + s);
		floatingCube(frame, texName, o + s, o, 16f - o - s, 16f - o - s, o + s, 16f - o);
		floatingCube(frame, texName, o + s, 16f - o - s, 16f - o - s, 16f - o - s, 16f - o, 16f - o);

		floatingCube(frame, texName, o, o, o + s, o + s, o + s, 16f - o - s);
		floatingCube(frame, texName, 16f - o - s, o, o + s, 16f - o, o + s, 16f - o - s);
		floatingCube(frame, texName, o, 16f - o - s, o + s, o + s, 16f - o, 16f - o - s);
		floatingCube(frame, texName, 16f - o - s, 16f - o - s, o + s, 16f - o, 16f - o, 16f - o - s);

		return frame;
	}

	private void floatingCube(BlockModelBuilder builder, String texName, float fx, float fy, float fz, float tx, float ty, float tz) {
		builder.element().from(fx, fy, fz).to(tx, ty, tz).allFaces((direction, faceBuilder) -> faceBuilder.texture("#" + texName)).end();
	}

	private void orientatedBlock(Block block, Function<BlockState, ModelFile> modelFunc, Function<Direction, Integer> rotXFunc, Function<Direction, Integer> rotYFunc) {
		getVariantBuilder(block).forAllStates(state -> {
			Direction dir = state.getValue(BlockStateProperties.FACING);
			return ConfiguredModel.builder()
					.modelFile(modelFunc.apply(state))
					.rotationX(rotXFunc.apply(dir))
					.rotationY(rotYFunc.apply(dir))
					.build();
		});
	}

}
