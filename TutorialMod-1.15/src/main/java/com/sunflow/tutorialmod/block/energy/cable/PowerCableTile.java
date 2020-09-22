package com.sunflow.tutorialmod.block.energy.cable;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.util.Constants;

public class PowerCableTile extends TileEntity {

	private Mode modes[] = new Mode[] { Mode.MODE_NONE, Mode.MODE_NONE, Mode.MODE_NONE, Mode.MODE_NONE, Mode.MODE_NONE, Mode.MODE_NONE };

	public PowerCableTile() {
		super(Registration.POWER_CABLE_TILE.get());
	}

	public void changeMode(Direction side, Mode newMode) {
		modes[side.ordinal()] = newMode;
		updateState(side);
	}

	private void updateState(Direction side) {
		world.setBlockState(pos, world.getBlockState(pos)
				.with(PowerCableBlock.getProp(side), getMode(side)),
				Constants.BlockFlags.BLOCK_UPDATE | Constants.BlockFlags.NOTIFY_NEIGHBORS);
		markDirty();
	}

	private Mode getMode(Direction side) { return modes[side.ordinal()]; }

	@Override
	public void read(CompoundNBT tag) {
		readModes(tag);
		super.read(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		writeModes(tag);
		return super.write(tag);
	}

	public void readModes(CompoundNBT tag) {
		modes[0] = Mode.values()[tag.getByte("m0")];
		modes[1] = Mode.values()[tag.getByte("m1")];
		modes[2] = Mode.values()[tag.getByte("m2")];
		modes[3] = Mode.values()[tag.getByte("m3")];
		modes[4] = Mode.values()[tag.getByte("m4")];
		modes[5] = Mode.values()[tag.getByte("m5")];
	}

	public CompoundNBT writeModes(CompoundNBT tag) {
		tag.putByte("m0", (byte) modes[0].ordinal());
		tag.putByte("m1", (byte) modes[1].ordinal());
		tag.putByte("m2", (byte) modes[2].ordinal());
		tag.putByte("m3", (byte) modes[3].ordinal());
		tag.putByte("m4", (byte) modes[4].ordinal());
		tag.putByte("m5", (byte) modes[5].ordinal());
		return tag;
	}

	public enum Mode implements IStringSerializable {
		MODE_NONE("none"),
		MODE_CABLE("cable"), // Blue
		MODE_CONNECTION("connection"); // Yellow

		private final String name;

		Mode(String name) { this.name = name; }

		@Override
		public String getName() { return name; }

		@Override
		public String toString() { return getName(); }
	}
}
