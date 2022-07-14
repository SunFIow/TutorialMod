package com.sunflow.tutorialmod.network.packet;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class SpawnPacket extends BasePacket {

	private final String id;
	private final ResourceKey<Level> type;
	private final BlockPos pos;

	public SpawnPacket(FriendlyByteBuf buf) {
		this.id = buf.readUtf();
		this.type = ResourceKey.create(Registry.DIMENSION_REGISTRY, buf.readResourceLocation());
		this.pos = buf.readBlockPos();
	}

	public SpawnPacket(String id, ResourceKey<Level> type, BlockPos pos) {
		this.id = id;
		this.type = type;
		this.pos = pos;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeUtf(id);
		TutorialMod.LOGGER.debug(type);
		buf.writeResourceLocation(type.location());
		buf.writeBlockPos(pos);
	}

	@Override
	public boolean action(NetworkEvent.Context ctx) {
		ServerLevel spawnWorld = ctx.getSender().level.getServer().getLevel(type);
		EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(id));
		if (entityType == null) throw new IllegalStateException("This cannot happen! Unkown id '" + id + "'!");

		entityType.spawn(spawnWorld, null, null, pos, MobSpawnType.EVENT, true, true);

		return true;
	}
}
