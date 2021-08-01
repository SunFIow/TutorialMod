package com.sunflow.tutorialmod.network.packet;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class SpawnPacket extends BasePacket {

	private final String id;
	private final RegistryKey<World> type;
	private final BlockPos pos;

	public SpawnPacket(PacketBuffer buf) {
		this.id = buf.readString();
//		this.type = DimensionType.func_236022_a_(buf.readInt());
		this.type = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, buf.readResourceLocation());
		this.pos = buf.readBlockPos();
	}

	public SpawnPacket(String id, RegistryKey<World> type, BlockPos pos) {
		this.id = id;
		this.type = type;
		this.pos = pos;
	}

	@Override
	public void encode(PacketBuffer buf) {
		buf.writeString(id);
		System.out.println(type);
		buf.writeResourceLocation(type.getLocation());
		buf.writeBlockPos(pos);
	}

	@Override
	public boolean action(NetworkEvent.Context ctx) {
		ServerWorld spawnWorld = ctx.getSender().world.getServer().getWorld(type);
		EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(id));
		if (entityType == null) throw new IllegalStateException("This cannot happen! Unkown id '" + id + "'!");

		entityType.spawn(spawnWorld, null, null, pos, SpawnReason.EVENT, true, true);

		return true;
	}
}
