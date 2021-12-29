package com.sunflow.tutorialmod.entity.centaur;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CentaurEntity extends Cow {

	public CentaurEntity(EntityType<? extends CentaurEntity> type, Level world) {
		super(type, world);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Registration.RUBY.get()), false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MOVEMENT_SPEED, 0.22D)
				.add(Attributes.MAX_HEALTH, 40.0D);
	}

	@Override
	protected SoundEvent getAmbientSound() { return Registration.ENTITY_CENTAUR_AMBIENT.get(); }

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) { return Registration.ENTITY_CENTAUR_HURT.get(); }

	@Override
	protected SoundEvent getDeathSound() { return Registration.ENTITY_CENTAUR_DEATH.get(); }

	@Override
	protected void playStepSound(BlockPos pPos, BlockState pBlock) { super.playStepSound(pPos, pBlock); }

	@Override
	protected float getSoundVolume() { return super.getSoundVolume(); }

	@Override
	public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) { return super.mobInteract(pPlayer, pHand); }

	@Override
	public CentaurEntity getBreedOffspring(ServerLevel pLevel, AgeableMob parentMob) { return Registration.CENTAUR.get().create(pLevel); }

	// @Override
	// public float getEyeHeight(Pose pose) { return 2.6f; }

	@Override
	protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
		return super.getStandingEyeHeight(pPose, pSize); // 2.6f;
	}

	// @Override
	// protected ResourceLocation getLootTable(){ return LootTableHandler.CENTAUR; }

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return super.getDefaultLootTable();
	}

}
