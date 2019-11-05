package com.sunflow.tutorialmod.block.ore;

import java.util.Random;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.base.BlockBase;
import com.sunflow.tutorialmod.setup.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class CustomOreBlock extends BlockBase {

	public CustomOreBlock(String name, float hardnessAndResistance, int harvestlevel, int lightlevel) {
		this(name, TutorialMod.groups.itemGroup, hardnessAndResistance, harvestlevel, lightlevel);
	}

	public CustomOreBlock(String name, ItemGroup group, float hardnessAndResistance, int harvestlevel, int lightlevel) {
		this(name, group, Block.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(hardnessAndResistance).harvestLevel(harvestlevel).lightValue(lightlevel));
	}

	public CustomOreBlock(String name, ItemGroup group, Properties properties) {
		super(name, group, properties);
	}

	protected int getExperience(Random rand) {
		if (this == ModBlocks.RUBY_ORE) {
			return MathHelper.nextInt(rand, 0, 2);
		} else if (this == ModBlocks.COPPER_ORE_OVERWORLD || this == ModBlocks.COPPER_ORE_NETHER || this == ModBlocks.COPPER_ORE_END) {
			return MathHelper.nextInt(rand, 1, 4);
		} else if (this == ModBlocks.ALUMINIUM_ORE_OVERWORLD || this == ModBlocks.ALUMINIUM_ORE_NETHER || this == ModBlocks.ALUMINIUM_ORE_END) {
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
