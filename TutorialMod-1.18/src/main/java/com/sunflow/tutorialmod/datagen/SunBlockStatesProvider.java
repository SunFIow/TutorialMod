package com.sunflow.tutorialmod.datagen;

import java.util.function.Function;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SunBlockStatesProvider extends BlockStateProvider {

	public SunBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) { super(gen, TutorialMod.MODID, exFileHelper); }

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(Registration.SUN_ORE_OVERWORLD.block());
		simpleBlock(Registration.SUN_ORE_DEEPSLATE.block());
		simpleBlock(Registration.SUN_ORE_NETHER.block());
		simpleBlock(Registration.SUN_ORE_END.block());
	}

	// private void registerGeneratorBlock() {
	// ResourceLocation loc_side = new ResourceLocation(TutorialMod.MODID, "block/generator_side");
	// ResourceLocation loc_front = new ResourceLocation(TutorialMod.MODID, "block/generator_front");
	// ResourceLocation loc_powered = new ResourceLocation(TutorialMod.MODID, "block/generator_powered");
	// BlockModelBuilder modelGenBlock = models().cube("generator", loc_side, loc_side, loc_front, loc_side, loc_side, loc_side);
	// BlockModelBuilder modelGenBlockPowered = models().cube("generator_powered", loc_side, loc_side, loc_powered, loc_side, loc_side, loc_side);
	// orientatedBlock(Registration.GENERATOR_BLOCK.get(),
	// state -> state.getValue(BlockStateProperties.POWERED) ? modelGenBlockPowered : modelGenBlock,
	// dir -> dir.getAxis() == Direction.Axis.Y ? dir.getAxisDirection().getStep() * -90 : 0,
	// dir -> dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0);
	// }

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

	@Override
	public String getName() { return TutorialMod.MODID + " " + super.getName(); }
}
