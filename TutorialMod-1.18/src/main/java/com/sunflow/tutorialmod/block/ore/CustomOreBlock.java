package com.sunflow.tutorialmod.block.ore;

import java.util.Random;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CustomOreBlock extends Block {

	public CustomOreBlock(Properties properties) { super(properties); }

	protected int getExperience(Random rand) {
		if (this == Registration.RUBY_ORE.block()) {
			return Mth.nextInt(rand, 0, 2);
		} else if (this == Registration.COPPER_ORE_OVERWORLD.block() || this == Registration.COPPER_ORE_NETHER.block() || this == Registration.COPPER_ORE_END.block()) {
			return Mth.nextInt(rand, 1, 4);
		} else if (this == Registration.ALUMINIUM_ORE_OVERWORLD.block() || this == Registration.ALUMINIUM_ORE_NETHER.block() || this == Registration.ALUMINIUM_ORE_END.block()) {
			return Mth.nextInt(rand, 1, 4);
		} else {
			return 0;
		}
	}

	@Override
	public int getExpDrop(BlockState state, LevelReader reader, BlockPos pos, int fortune, int silktouch) {
		return silktouch == 0 ? this.getExperience(RANDOM) : 0;
	}
}
