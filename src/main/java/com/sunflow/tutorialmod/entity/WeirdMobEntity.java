package com.sunflow.tutorialmod.entity;

import com.sunflow.tutorialmod.init.ModEntities;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

public class WeirdMobEntity extends AnimalEntity {

	public WeirdMobEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public WeirdMobEntity createChild(AgeableEntity ageable) {
		return ModEntities.WEIRDMOB.create(this.world);
	}
}
