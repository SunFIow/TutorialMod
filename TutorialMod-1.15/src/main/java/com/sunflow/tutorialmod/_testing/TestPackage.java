package com.sunflow.tutorialmod._testing;

import com.sunflow.tutorialmod.network.packet.BasePacket;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class TestPackage extends BasePacket {

	private final CompoundNBT nbt;

	public TestPackage(PacketBuffer buf) {
		nbt = buf.readCompoundTag();
	}

	public TestPackage(CompoundNBT data) {
		this.nbt = data;
	}

	@Override
	public void encode(PacketBuffer buf) {
		buf.writeCompoundTag(nbt);
	}

	@Override
	protected boolean action(Context ctx) {
//		io.netty.buffer.Unpooled
//		;Minecraft.getInstance().
		ServerPlayerEntity spe = ctx.getSender();
		@SuppressWarnings("resource")
		ClientPlayerEntity cpe = Minecraft.getInstance().player;
		Log.info(spe);
		Log.info(cpe);
		Log.info(nbt);
		Container contain = cpe.openContainer;
		Log.info(contain);
		return true;
	}

}
