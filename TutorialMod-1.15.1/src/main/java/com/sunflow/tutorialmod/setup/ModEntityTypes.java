package com.sunflow.tutorialmod.setup;

import java.util.ArrayList;
import java.util.List;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.item.base.MobEggBase;
import com.sunflow.tutorialmod.item.grenade.GrenadeEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

public class ModEntityTypes {
	public static final List<EntityType<? extends Entity>> ENTITY_TYPES = new ArrayList<EntityType<? extends Entity>>();

//	@ObjectHolder("tutorialmod:weirdmob")
//	public static final EntityType<WeirdMobEntity> WEIRDMOB = createEntityType("weirdmob", 1.0F, 1.0F, false, EntityClassification.CREATURE, WeirdMobEntity::new, ModItems.WEIRDMOB_SPAWN_EGG);

//	@ObjectHolder("tutorialmod:centaur")
//	public static final EntityType<CentaurEntity> CENTAUR = createEntityType("centaur", 0.9F, 2.8F, false, EntityClassification.CREATURE, CentaurEntity::new, ModItems.CENTAUR_SPAWN_EGG);

//	@ObjectHolder("tutorialmod:grenade")
	public static final EntityType<GrenadeEntity> GRENADE_ENTITY = createEntityType("grenade", 0.5F, 0.5F, true, EntityClassification.MISC, GrenadeEntity::new);

	private static <T extends Entity> EntityType<T> createEntityType(String name, float w, float h, boolean receiveVelocityUpdates, EntityClassification classification, EntityType.IFactory<T> factory, MobEggBase... eggs) {
		EntityType<T> type = EntityType.Builder.<T>create(factory, classification)
				.size(w, h)
				.setShouldReceiveVelocityUpdates(receiveVelocityUpdates)
				.build(name);
		type.setRegistryName(TutorialMod.MODID, name);
		for (MobEggBase egg : eggs) {
			egg.setEntityType(type);
		}
		ENTITY_TYPES.add(type);
		return type;
	}
}
