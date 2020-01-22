package com.sunflow.tutorialmod.setup;

import java.util.ArrayList;
import java.util.List;

import com.sunflow.tutorialmod.entity.centaur.CentaurEntity;
import com.sunflow.tutorialmod.entity.weirdmob.WeirdMobEntity;
import com.sunflow.tutorialmod.item.grenade.GrenadeEntity;
import com.sunflow.tutorialmod.util.ModTypeUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModEntityTypes {
	public static final List<EntityType<? extends Entity>> ENTITY_TYPES = new ArrayList<EntityType<? extends Entity>>();

	@ObjectHolder("tutorialmod:weirdmob")
	public static final EntityType<WeirdMobEntity> WEIRDMOB = ModTypeUtils.createType("weirdmob", 1.0F, 1.0F, false, EntityClassification.CREATURE, WeirdMobEntity::new, ModItems.WEIRDMOB_SPAWN_EGG);

	@ObjectHolder("tutorialmod:centaur")
	public static final EntityType<CentaurEntity> CENTAUR = ModTypeUtils.createType("centaur", 0.9F, 2.8F, false, EntityClassification.CREATURE, CentaurEntity::new, ModItems.CENTAUR_SPAWN_EGG);
//
	@ObjectHolder("tutorialmod:grenade")
	public static final EntityType<GrenadeEntity> GRENADE_ENTITY = ModTypeUtils.createType("grenade", 0.5F, 0.5F, true, EntityClassification.MISC, GrenadeEntity::new);
}
