package com.sunflow.tutorialmod.util.enums;

import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.IStringSerializable;

public class EnumHandlerMetals {

	public static enum EnumType implements IStringSerializable {

		COPPER(0, "copper", MaterialColor.ORANGE_TERRACOTTA), ALUMINIUM(1, "aluminium", MaterialColor.LIGHT_GRAY);

		private static final EnumType[] META_LOOKUP = new EnumType[EnumType.values().length];
		private final int meta;
		private final String name, unlocalizedName;
		private final MaterialColor color;

		private EnumType(int meta, String name, MaterialColor color) {
			this(meta, name, name, color);
		}

		private EnumType(int meta, String name, String unlocalizedName, MaterialColor color) {
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
			this.color = color;
		}

		public int getMetadata() {
			return this.meta;
		}

		public MaterialColor getColor() {
			return this.color;
		}

		@Override
		public String toString() {
			return this.name();
		}

		public static EnumType byMetadata(int meta) {
			if (meta < 0 || meta >= EnumType.META_LOOKUP.length)
				meta = 0;
			return EnumType.META_LOOKUP[meta];
		}

		@Override
		public String getName() {
			return this.name;
		}

		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}

		static {
			for (final EnumType enumtype : EnumType.values())
				EnumType.META_LOOKUP[enumtype.getMetadata()] = enumtype;
		}
	}
}
