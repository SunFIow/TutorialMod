package com.sunflow.tutorialmod.data.generator.provider;

import java.util.function.Function;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.energy.cable.PowerCableBlock;
import com.sunflow.tutorialmod.block.energy.cable.PowerCableTile;
import com.sunflow.tutorialmod.block.multipart.ComplexMultipartBlock;
import com.sunflow.tutorialmod.block.multipart.ComplexMultipartTile;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;

public class ModBlockStatesProvider extends BlockStateProvider {

	public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, TutorialMod.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(Registration.MAGICBLOCK.get());
		registerGlowstoneGenerator();
		registerComplexMultipart();
		registerPowerCable();
	}

	private void registerGlowstoneGenerator() {
//		ResourceLocation normal = new ResourceLocation(TutorialMod.MODID, "block/glowstone_generator");
		ResourceLocation top = new ResourceLocation(TutorialMod.MODID, "block/glowstone_generator_top");
		ResourceLocation front = new ResourceLocation(TutorialMod.MODID, "block/glowstone_generator_front");
		ResourceLocation front_powered = new ResourceLocation(TutorialMod.MODID, "block/glowstone_generator_front_powered");
		ResourceLocation side = new ResourceLocation(TutorialMod.MODID, "block/glowstone_generator_side");
		BlockModelBuilder modelGlowstoneGenerator = models().cube("glowstone_generator", top, top, front, side, side, side);
		BlockModelBuilder modelGlowstoneGeneratorPowered = models().cube("glowstone_generator_powered", top, top, front_powered, side, side, side);
		orientedBlock(Registration.GLOWSTONE_GENERATOR_BLOCK.get(), state -> {
			if (state.get(BlockStateProperties.POWERED)) {
				return modelGlowstoneGeneratorPowered;
			} else {
				return modelGlowstoneGenerator;
			}
		});
	}

	private void registerComplexMultipart() {
		MultiPartBlockStateBuilder bld = getMultipartBuilder(Registration.COMPLEX_MULTIPART_BLOCK.get());

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
		for (ComplexMultipartTile.Mode mode : ComplexMultipartTile.Mode.values()) {
			BlockModelBuilder model = models[mode.ordinal()];
			bld.part().modelFile(model).addModel().condition(ComplexMultipartBlock.DOWN, mode);
			bld.part().modelFile(model).rotationX(180).addModel().condition(ComplexMultipartBlock.UP, mode);
			bld.part().modelFile(model).rotationX(90).addModel().condition(ComplexMultipartBlock.SOUTH, mode);
			bld.part().modelFile(model).rotationX(270).addModel().condition(ComplexMultipartBlock.NORTH, mode);
			bld.part().modelFile(model).rotationY(90).rotationX(90).addModel().condition(ComplexMultipartBlock.WEST, mode);
			bld.part().modelFile(model).rotationY(270).rotationX(90).addModel().condition(ComplexMultipartBlock.EAST, mode);
		}
	}

	private void registerPowerCable() {
		BlockModelBuilder powerCableItemModel = makeFrame("block/power_cable/main", "block/power_cable/main", "block/power_cable/main", "center", 5f, 1f);
		floatingCube(powerCableItemModel, "center", 6f, 6f, 6f, 10f, 10f, 10f);

		MultiPartBlockStateBuilder bld = getMultipartBuilder(Registration.POWER_CABLE_BLOCK.get());

		BlockModelBuilder singleNone = models().getBuilder("block/power_cable/singlenone")
				.texture("particle", modLoc("block/power_cable/main"))
				.texture("single", modLoc("block/power_cable/none"))
				.element().from(6f, 6f, 6f).to(10f, 7f, 10f).face(Direction.DOWN).texture("#single").end()
				.end();
		floatingCube(singleNone, "single", 7f, 5.75f, 7f, 9f, 6f, 9f);

		BlockModelBuilder singleCable = models().getBuilder("block/power_cable/singlecable")
				.texture("single", modLoc("block/power_cable/cable"))

				.element().from(6f, 0f, 6f).to(10, 6f, 10f)
				.face(Direction.NORTH).texture("#single").end()
				.face(Direction.SOUTH).texture("#single").end()
				.face(Direction.EAST).texture("#single").end()
				.face(Direction.WEST).texture("#single").end()
				.end();

		BlockModelBuilder singleConnection = models().getBuilder("block/power_cable/singleconnection")
				.texture("single", modLoc("block/power_cable/connection"))
				.texture("frame", modLoc("block/power_cable/frame"))

				.element().from(6f, 0f, 6f).to(10f, 6f, 10f)
				.face(Direction.NORTH).texture("#single").end()
				.face(Direction.SOUTH).texture("#single").end()
				.face(Direction.EAST).texture("#single").end()
				.face(Direction.WEST).texture("#single").end()
				.end()

				.element().from(5f, 0f, 5f).to(11f, 1f, 11f)
				.allFaces((direction, faceBuilder) -> faceBuilder.texture("#frame"))
				.end();

		BlockModelBuilder[] models = new BlockModelBuilder[] { singleNone, singleCable, singleConnection };
		for (PowerCableTile.Mode mode : PowerCableTile.Mode.values()) {
			BlockModelBuilder model = models[mode.ordinal()];
			bld.part().modelFile(model).addModel().condition(PowerCableBlock.DOWN, mode);
			bld.part().modelFile(model).rotationX(180).addModel().condition(PowerCableBlock.UP, mode);
			bld.part().modelFile(model).rotationX(90).addModel().condition(PowerCableBlock.SOUTH, mode);
			bld.part().modelFile(model).rotationX(270).addModel().condition(PowerCableBlock.NORTH, mode);
			bld.part().modelFile(model).rotationY(90).rotationX(90).addModel().condition(PowerCableBlock.WEST, mode);
			bld.part().modelFile(model).rotationY(270).rotationX(90).addModel().condition(PowerCableBlock.EAST, mode);
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

	private void orientedBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
		getVariantBuilder(block)
				.forAllStates(state -> {
					Direction dir = state.get(BlockStateProperties.FACING);
					return ConfiguredModel.builder()
							.modelFile(modelFunc.apply(state))
							.rotationX(dir.getAxis() == Direction.Axis.Y ? dir.getAxisDirection().getOffset() * -90 : 0)
							.rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.getHorizontalIndex() + 2) % 4) * 90 : 0)
							.build();
				});
	}

}
