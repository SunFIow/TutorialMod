package com.sunflow.tutorialmod.network.packet;

import java.util.function.Supplier;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class SpawnPacket {

	private final String id;
	private final DimensionType type;
	private final BlockPos pos;

	public SpawnPacket(PacketBuffer buf) {
		this.id = buf.readString();
		this.type = DimensionType.getById(buf.readInt());
		this.pos = buf.readBlockPos();
	}

	public SpawnPacket(String id, DimensionType type, BlockPos pos) {
		this.id = id;
		this.type = type;
		this.pos = pos;
	}

	public void encode(PacketBuffer buf) {
		buf.writeString(id);
		buf.writeInt(type.getId());
		buf.writeBlockPos(pos);
	}

	public void onMessage(Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ServerWorld spawnWorld = ctx.get().getSender().world.getServer().getWorld(type);
			EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(id));
			if (entityType == null) {
				throw new IllegalStateException("This cannot happen! Unkown id '" + id + "'!");
			}
			entityType.spawn(spawnWorld, null, null, pos, SpawnReason.EVENT, true, true);
		});
		ctx.get().setPacketHandled(true);
	}
}
