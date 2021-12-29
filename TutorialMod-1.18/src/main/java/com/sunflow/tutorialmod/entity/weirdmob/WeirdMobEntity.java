package com.sunflow.tutorialmod.entity.weirdmob;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

public class WeirdMobEntity extends Animal {

	public WeirdMobEntity(EntityType<? extends Animal> type, Level worldIn) { super(type, worldIn); }

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.ATTACK_DAMAGE, 3.0)
				.add(Attributes.FOLLOW_RANGE, 20.0F)
				.add(Attributes.MAX_HEALTH, 40);
	}

	@Override
	public WeirdMobEntity getBreedOffspring(ServerLevel pLevel, AgeableMob parentMob) { return Registration.WEIRDMOB.get().create(pLevel); }

}
