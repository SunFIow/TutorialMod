package com.sunflow.tutorialmod.util.enums;

import net.minecraft.util.IStringSerializable;

public class EnumHandlerSkins {

	public static enum EnumType implements IStringSerializable {

		SUNFLOW(0, "sunflow"), BEKESCSABA99(1, "beke"), PHANTOM(1, "phantom");

		private static final EnumType[] META_LOOKUP = new EnumType[EnumType.values().length];
		private final int meta;
		private final String name, unlocalizedName;

		private EnumType(int meta, String name) {
			this(meta, name, name);
		}

		private EnumType(int meta, String name, String unlocalizedName) {
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}

		public int getMetadata() {
			return this.meta;
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
