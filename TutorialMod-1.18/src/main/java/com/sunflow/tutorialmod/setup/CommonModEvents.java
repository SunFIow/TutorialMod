package com.sunflow.tutorialmod.setup;

import com.sunflow.tutorialmod.entity.centaur.CentaurEntity;
import com.sunflow.tutorialmod.item.grenade.GrenadeEntity;
import com.sunflow.tutorialmod.network.Networking;

import net.minecraft.Util;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonModEvents {

	public static void setup(final FMLCommonSetupEvent event) {
		// event.enqueueWork(() -> {});
		Networking.registerMessages();
		DispenserBlock.registerBehavior(Registration.GRENADE.get(), new AbstractProjectileDispenseBehavior() {
			protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
				return Util.make(new GrenadeEntity(pLevel, pPosition.x(), pPosition.y(), pPosition.z()), grenade -> grenade.setItem(pStack));
			}
		});
	}

	public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
		event.put(Registration.WEIRDMOB.get(), CentaurEntity.createAttributes().build());
		event.put(Registration.CENTAUR.get(), CentaurEntity.createAttributes().build());
	}

}
