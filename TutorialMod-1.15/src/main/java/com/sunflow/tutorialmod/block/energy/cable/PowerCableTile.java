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
		if (modes[side.ordinal()] == newMode) return;
		modes[side.ordinal()] = newMode;
		updateState(side);
	}

	private void updateState(Direction side) {
		world.setBlockState(pos, world.getBlockState(pos)
				.with(PowerCableBlock.getProperty(side), modes[side.ordinal()]),
				Constants.BlockFlags.BLOCK_UPDATE | Constants.BlockFlags.NOTIFY_NEIGHBORS);
		markDirty();
	}

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
		CompoundNBT modesNBT = tag.getCompound("modes");
		modes[0] = Mode.values()[modesNBT.getByte("down")];
		modes[1] = Mode.values()[modesNBT.getByte("up")];
		modes[2] = Mode.values()[modesNBT.getByte("north")];
		modes[3] = Mode.values()[modesNBT.getByte("south")];
		modes[4] = Mode.values()[modesNBT.getByte("west")];
		modes[5] = Mode.values()[modesNBT.getByte("east")];
	}

	public CompoundNBT writeModes(CompoundNBT tag) {
		CompoundNBT modesNBT = new CompoundNBT();
		modesNBT.putByte("down", (byte) modes[0].ordinal());
		modesNBT.putByte("up", (byte) modes[1].ordinal());
		modesNBT.putByte("north", (byte) modes[2].ordinal());
		modesNBT.putByte("south", (byte) modes[3].ordinal());
		modesNBT.putByte("west", (byte) modes[4].ordinal());
		modesNBT.putByte("east", (byte) modes[5].ordinal());
		tag.put("modes", modesNBT);
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
