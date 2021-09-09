package com.sunflow.tutorialmod.config;

import java.util.ArrayList;
import java.util.List;

import com.sunflow.tutorialmod.network.packets.BasePacket;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.EnumValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.common.ForgeConfigSpec.LongValue;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

public class SyncConfigPacket extends BasePacket {

	private ConfigValue<?> value;

	private List<String> path;
	private Type type;
	private Type listType;
	private Object val;

	public SyncConfigPacket(ConfigValue<?> value, Type type) {
		this.value = value;
		this.type = type;
	}

	public SyncConfigPacket(ConfigValue<?> value, Type type, Type listType) {
		this.value = value;
		this.type = type;
		this.listType = listType;
	}

	public SyncConfigPacket(FriendlyByteBuf buf) {
		int pathSize = buf.readInt();
		path = new ArrayList<>();
		for (int i = 0; i < pathSize; i++) path.add(buf.readUtf(1000));

		type = buf.readEnum(Type.class);

		if (type == Type.LIST) {
			listType = buf.readEnum(Type.class);
			int size = buf.readInt();
			List list = new ArrayList<>();
			for (int i = 0; i < size; i++)
				list.add(readValue(buf, listType));
			val = list;
//		} else if (type == type.CONFIG) {
		} else val = readValue(buf, type);
	}

	private Object readValue(FriendlyByteBuf buf, Type type) {
		Object val = null;
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
			case ENUM:
				String enumClazzName = buf.readUtf(1000);
				try {
					Class enumClazz = Class.forName(enumClazzName);
					val = buf.readEnum(enumClazz);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case STRING:
				val = buf.readUtf(1000);
				break;
			// TODO:
//			case CONFIG:
//				break;
			case LIST:
				Log.error("SyncConfigPacket#readValue - Lists int Lists is not supported");
				break;
//			case CUSTOM:
//				break;
			default:
				Log.error("SyncConfigPacket#decode - type unknown");
				Log.error(type);
				break;
		}
		return val;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		List<String> path = value.getPath();

		buf.writeInt(path.size());
		path.forEach((s) -> buf.writeUtf(s, 1000));

		buf.writeEnum(type);

		if (type == Type.LIST) {
			buf.writeEnum(listType);
			List<?> list = (List<?>) value.get();
			buf.writeInt(list.size());
			list.forEach(a -> {
				writeValue(buf, listType, a);
			});
//		} else if (type == type.CONFIG) {
		} else writeValue(buf, type, value.get());
	}

	private void writeValue(FriendlyByteBuf buf, Type type, Object value) {
		switch (type) {
			case BOOL:
				buf.writeBoolean((Boolean) value);
				break;
			case INT:
				buf.writeInt((Integer) value);
				break;
			case LONG:
				buf.writeLong((Long) value);
				break;
			case DOUBLE:
				buf.writeDouble((Double) value);
				break;
			case ENUM:
				buf.writeUtf(value.getClass().getName(), 1000);
				buf.writeEnum((Enum<?>) value);
				break;
			case STRING:
				buf.writeUtf((String) value, 1000);
				break;
			// TODO:
			case CONFIG:
				break;
			case LIST:
				Log.error("SyncConfigPacket#writeValue - Lists int Lists is not supported");
				break;
//			case CUSTOM:
//				break;
			default:
				Log.error("SyncConfigPacket#writeValue - type unknown");
				Log.error(type);
				break;
		}
	}

	@Override
	public boolean action(NetworkEvent.Context ctx) {
		ForgeConfigSpec spec = null;
		if (path.get(0).equals(TutorialModConfig1.Client.PATH)) {
			spec = TutorialModConfig1.clientSpec;
		} else if (path.get(0).equals(TutorialModConfig1.Server.PATH)) {
			spec = TutorialModConfig1.serverSpec;
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
			case ENUM:
				((EnumValue) value).set(val);
				break;
			case STRING:
				((ConfigValue<String>) value).set((String) val);
				break;
			// TODO:
			case CONFIG:
				break;
			case LIST:
				((ConfigValue<List<?>>) value).set((List<?>) val);
				break;
//			case CUSTOM:
//				break;
			default:
				Log.error("SyncConfigPacket#action - type unknown");
				Log.error(type);
				return false;
		}
		return true;
	}

	static enum Type {
		BOOL, INT, LONG, DOUBLE, ENUM, STRING, CONFIG, LIST,
//		CUSTOM
	}

}
