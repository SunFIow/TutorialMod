package com.sunflow.tutorialmod.entity.centaur;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class CentaurEntity extends CowEntity {

	public CentaurEntity(EntityType<? extends CentaurEntity> type, World world) {
		super(type, world);
//		getAttribute(Attributes.MAX_HEALTH).setBaseValue(40.0D);
	}

	public static AttributeModifierMap.MutableAttribute prepareAttributes() {
		return LivingEntity.registerAttributes()
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.22D)
				.createMutableAttribute(Attributes.MAX_HEALTH, 40.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(Registration.RUBY.get()), false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
	}

//	@Override
//	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
//		if (spawnDataIn == null) {
//			spawnDataIn = new AgeableEntity.AgeableData(0.2F);
//		}
//
//		this.func_230273_eI_();
//		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
//	}
//
//	protected void func_230273_eI_() {
//		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(40.0D);
//		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.22D);
//	}

	@Override
	public float getEyeHeight(Pose pose) { return 2.6f; }

	@Override
	protected SoundEvent getAmbientSound() { return Registration.ENTITY_CENTAUR_AMBIENT.get(); }

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) { return Registration.ENTITY_CENTAUR_HURT.get(); }

	@Override
	protected SoundEvent getDeathSound() { return Registration.ENTITY_CENTAUR_DEATH.get(); }

	@Override
//	createChild
	public CentaurEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity ageable) { return Registration.CENTAUR.get().create(p_241840_1_); }

//	@Override
//	protected ResourceLocation getLootTable(){ return LootTableHandler.CENTAUR; }
}
