package com.sunflow.tutorialmod.entity.weirdmob;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class WeirdMobEntity extends AnimalEntity {

	public WeirdMobEntity(EntityType<? extends AnimalEntity> type, World worldIn) { super(type, worldIn); }

	@Override
//	createChild
	public WeirdMobEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity ageable) { return Registration.WEIRDMOB.get().create(p_241840_1_); }

	public static AttributeModifierMap.MutableAttribute prepareAttributes() {
		return LivingEntity.registerAttributes()
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 20.0F)
				.createMutableAttribute(Attributes.MAX_HEALTH, 40);
	}

}
