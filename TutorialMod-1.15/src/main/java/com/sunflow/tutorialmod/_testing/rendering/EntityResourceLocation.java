package com.sunflow.tutorialmod._testing.rendering;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

public class EntityResourceLocation {
	private static final Map<EntityType<?>, ResourceLocation> LOCATIONS = Util.make(Maps.newHashMap(), (map) -> {
		map.put(EntityType.SKELETON, new ResourceLocation("textures/entity/skeleton/skeleton.png"));
		map.put(EntityType.WITHER_SKELETON, new ResourceLocation("textures/entity/skeleton/wither_skeleton.png"));
		map.put(EntityType.ZOMBIE, new ResourceLocation("textures/entity/zombie/zombie.png"));
		map.put(EntityType.CREEPER, new ResourceLocation("textures/entity/creeper/creeper.png"));
		map.put(EntityType.ENDER_DRAGON, new ResourceLocation("textures/entity/enderdragon/dragon.png"));
		map.put(EntityType.PLAYER, DefaultPlayerSkin.getDefaultSkinLegacy());
	});

	public static ResourceLocation getLocation(Entity entity) {
		EntityType<?> type = entity.getType();
		ResourceLocation location = LOCATIONS.get(type);
		return location;
	}
}
