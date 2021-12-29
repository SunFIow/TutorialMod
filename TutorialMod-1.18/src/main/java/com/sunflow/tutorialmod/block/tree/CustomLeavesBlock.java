package com.sunflow.tutorialmod.block.tree;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class CustomLeavesBlock extends LeavesBlock {

	public CustomLeavesBlock() {
		super(Block.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion().isValidSpawn((state, getter, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT).isSuffocating((state, getter, pos) -> false).isViewBlocking((state, getter, pos) -> false));
	}
}
