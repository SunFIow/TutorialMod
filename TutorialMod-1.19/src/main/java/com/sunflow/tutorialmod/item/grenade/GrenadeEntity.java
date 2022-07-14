package com.sunflow.tutorialmod.item.grenade;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion.BlockInteraction;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class GrenadeEntity extends ThrowableItemProjectile {

	public GrenadeEntity(EntityType<? extends GrenadeEntity> type, Level world) {
		super(type, world);
	}

	public GrenadeEntity(Level world, LivingEntity thrower) {
		super(Registration.GRENADE_ENTITY.get(), thrower, world);
	}

	public GrenadeEntity(Level world, double x, double y, double z) {
		super(Registration.GRENADE_ENTITY.get(), x, y, z, world);
	}

	@Override
	protected Item getDefaultItem() { return Registration.GRENADE.get(); }

	private ParticleOptions getParticle() {
		ItemStack itemstack = this.getItemRaw();
		return itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, itemstack);
	}

	/**
	 * Handler for {@link World#setEntityState}
	 */
	@Override
	public void handleEntityEvent(byte pId) {
		if (pId == 3) {
			ParticleOptions particleoptions = this.getParticle();
			for (int i = 0; i < 8; ++i) {
				this.level.addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	protected void onHit(HitResult pResult) {
		if (level.isClientSide()) return;
		level.explode(this, getX(), getY(), getZ(), 3.0F, false, BlockInteraction.DESTROY);
		discard();
	}
}