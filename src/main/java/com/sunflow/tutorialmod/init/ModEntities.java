package com.sunflow.tutorialmod.init;

import com.sunflow.tutorialmod.entity.CentaurEntity;
import com.sunflow.tutorialmod.entity.WeirdMobEntity;

import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModEntities {
	@ObjectHolder("tutorialmod:weirdmob")
	public static EntityType<WeirdMobEntity> WEIRDMOB;

	@ObjectHolder("tutorialmod:centaur")
	public static EntityType<CentaurEntity> CENTAUR;
}
