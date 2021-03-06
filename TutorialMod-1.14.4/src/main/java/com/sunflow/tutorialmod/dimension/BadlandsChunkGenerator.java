package com.sunflow.tutorialmod.dimension;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap.Type;

public class BadlandsChunkGenerator extends ChunkGenerator<BadlandsChunkGenerator.Config> {

	public BadlandsChunkGenerator(IWorld world, BiomeProvider biomeProvider) {
		super(world, biomeProvider, Config.createDefault());
	}

	@Override
	public void generateSurface(IChunk chunk) {
		BlockState bedrock = Blocks.BEDROCK.getDefaultState();
		BlockState stone = Blocks.STONE.getDefaultState();
		ChunkPos chunkpos = chunk.getPos();

		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				chunk.setBlockState(pos.setPos(x, 0, z), bedrock, false);
			}
		}

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int realX = chunkpos.x * 16 + x;
				int realZ = chunkpos.z * 16 + z;
				int height = (int) (65 + Math.sin(realX / 20.0D) * 10 + Math.cos(realZ / 20.0D) * 10);
				for (int y = 1; y < height; y++) {
					chunk.setBlockState(pos.setPos(x, y, z), stone, false);
				}
			}
		}
	}

	@Override
	public int getGroundHeight() { return world.getSeaLevel() + 1; }

	@Override
	public void makeBase(IWorld worldIn, IChunk chunkIn) {}

	@Override
	public int func_222529_a(int p_222529_1_, int p_222529_2_, Type heightmapType) { return 0; }

	static class Config extends GenerationSettings {
		public static Config createDefault() {
			Config config = new Config();
			config.setDefaultBlock(Blocks.DIAMOND_BLOCK.getDefaultState());
			config.setDefaultFluid(Blocks.LAVA.getDefaultState());
			return config;
		}
	}
}
