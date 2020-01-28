package com.sunflow.tutorialmod.block.ore;

import java.util.Random;

import com.sunflow.tutorialmod.block.base.BlockBase;
import com.sunflow.tutorialmod.setup.registration.Registration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class CustomOreBlock extends BlockBase {

	public CustomOreBlock(float hardnessAndResistance, int harvestlevel, int lightlevel) {
		this(Block.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(hardnessAndResistance).harvestLevel(harvestlevel).lightValue(lightlevel));
	}

	public CustomOreBlock(Properties properties) {
		super(properties);
	}

	protected int getExperience(Random rand) {
		if (this == Registration.RUBY_ORE.get()) {
			return MathHelper.nextInt(rand, 0, 2);
		} else if (this == Registration.COPPER_ORE_OVERWORLD.get() || this == Registration.COPPER_ORE_NETHER.get() || this == Registration.COPPER_ORE_END.get()) {
			return MathHelper.nextInt(rand, 1, 4);
		} else if (this == Registration.ALUMINIUM_ORE_OVERWORLD.get() || this == Registration.ALUMINIUM_ORE_NETHER.get() || this == Registration.ALUMINIUM_ORE_END.get()) {
			return MathHelper.nextInt(rand, 1, 4);
		} else {
			return 0;
		}
	}

	@Override
	public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
		return silktouch == 0 ? this.getExperience(RANDOM) : 0;
	}
}
