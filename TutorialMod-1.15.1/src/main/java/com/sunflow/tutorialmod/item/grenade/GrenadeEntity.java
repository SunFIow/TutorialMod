package com.sunflow.tutorialmod.item.grenade;

import com.sunflow.tutorialmod.util.VersionUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class GrenadeEntity extends SnowballEntity {

	public GrenadeEntity(EntityType<? extends GrenadeEntity> type, World world) {
		super(type, world);
	}

	public GrenadeEntity(World world, LivingEntity thrower) {
		super(world, thrower);
	}

	public GrenadeEntity(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

//	@Override
//	protected Item func_213885_i() { return ModItems.GRENADE; }

	@Override
	protected void onImpact(RayTraceResult result) {
		if (this.world.isRemote) return; // .posX .posY .posZ
		this.world.createExplosion((Entity) null, VersionUtils.getX(this), VersionUtils.getY(this), VersionUtils.getZ(this), 3.0F, false, Explosion.Mode.DESTROY);
		this.remove();
	}
}