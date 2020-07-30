package com.sunflow.tutorialmod.config;

import java.util.ArrayList;
import java.util.List;

import com.sunflow.tutorialmod.network.packet.BasePacket;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.EnumValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.common.ForgeConfigSpec.LongValue;
import net.minecraftforge.fml.network.NetworkEvent;

public class SyncConfigPacket extends BasePacket {

	private ConfigValue<?> value;

	private List<String> path;
	private Type type;
	private Object val;

	public SyncConfigPacket(ConfigValue<?> value, Type type) {
		this.type = type;
		this.value = value;
	}

	public SyncConfigPacket(PacketBuffer buf) {
		int pathSize = buf.readInt();
		path = new ArrayList<>();
		for (int i = 0; i < pathSize; i++) path.add(buf.readString(1000));

		type = buf.readEnumValue(Type.class);

		switch (type) {
			case BOOL:
				val = buf.readBoolean();
				break;
			case INT:
				val = buf.readInt();
				break;
			case LONG:
				val = buf.readLong();
				break;
			case DOUBLE:
				val = buf.readDouble();
				break;
			case CUSTOM:
				val = buf.readCompoundTag();
				break;
			case ENUM:
				String enumClazzName = buf.readString(1000);
				try {
					Class enumClazz = Class.forName(enumClazzName);
					val = buf.readEnumValue(enumClazz);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				break;
			default:
				Log.error(type + " isnt a known Type");
				break;
		}
	}

	@Override
	public void encode(PacketBuffer buf) {
		List<String> path = value.getPath();

		buf.writeInt(path.size());
		path.forEach((s) -> buf.writeString(s));

		buf.writeEnumValue(type);

		switch (type) {
			case BOOL:
				buf.writeBoolean((Boolean) value.get());
				break;
			case INT:
				buf.writeInt((Integer) value.get());
				break;
			case LONG:
				buf.writeLong((Long) value.get());
				break;
			case DOUBLE:
				buf.writeDouble((Double) value.get());
				break;
			case CUSTOM:
				if (value.get() instanceof IConfigValue) {
					buf.writeCompoundTag(((IConfigValue) value.get()).serializeNBT());
				} else {
					Log.error("SyncConfigPacket#encode - " + value.get().getClass().getSimpleName() + " doesn't implement IConfigValue");
				}
				break;
			case ENUM:
				buf.writeString(value.get().getClass().getName());
				buf.writeEnumValue((Enum<?>) value.get());
				break;
			default:
				Log.error("SyncConfigPacket#encode - value class unknown");
				Log.error(value.getClass());
				break;
		}
	}

	@Override
	public boolean action(NetworkEvent.Context ctx) {
		ForgeConfigSpec spec = null;
		if (path.get(0).equals(TutorialModConfig.Client.PATH)) {
			spec = TutorialModConfig.clientSpec;
		} else if (path.get(0).equals(TutorialModConfig.Server.PATH)) {
			spec = TutorialModConfig.serverSpec;
		} else {
			Log.error("SyncConfigPacket#decode - path unkown");
			return false;
		}

		value = spec.getValues().get(path);

		switch (type) {
			case BOOL:
				((BooleanValue) value).set((Boolean) val);
				break;
			case INT:
				((IntValue) value).set((Integer) val);
				break;
			case LONG:
				((LongValue) value).set((Long) val);
				break;
			case DOUBLE:
				((DoubleValue) value).set((Double) val);
				break;
			case CUSTOM:
				((IConfigValue) value.get()).deserializeNBT(value, (CompoundNBT) val);
				break;
			case ENUM:
				((EnumValue) value).set(val);
				break;
			default:
				return false;
		}
		return true;
	}

	static enum Type {
		BOOL, INT, LONG, DOUBLE, CUSTOM, ENUM
	}

}
