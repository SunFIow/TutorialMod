package com.sunflow.tutorialmod.block.energy.cable;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.util.Constants;

public class PowerCableTile extends TileEntity {

	private Mode modes[] = new Mode[] { Mode.MODE_NONE, Mode.MODE_NONE, Mode.MODE_NONE, Mode.MODE_NONE, Mode.MODE_NONE, Mode.MODE_NONE };

	public PowerCableTile() {
		super(Registration.COMPLEX_MULTIPART_TILE.get());
	}

	public void changeMode(Direction side, Mode newMode) {
		if (modes[side.ordinal()] != newMode) {
			modes[side.ordinal()] = newMode;
			updateState(side);
		}
	}

	private void updateState(Direction side) {
//		Mode north = getMode(Direction.NORTH);
//		Mode south = getMode(Direction.SOUTH);
//		Mode west = getMode(Direction.WEST);
//		Mode east = getMode(Direction.EAST);
//		Mode up = getMode(Direction.UP);
//		Mode down = getMode(Direction.DOWN);		
		BlockState state = world.getBlockState(pos);
		world.setBlockState(pos, state.with(PowerCableBlock.getProp(side), getMode(side)),
				Constants.BlockFlags.BLOCK_UPDATE | Constants.BlockFlags.NOTIFY_NEIGHBORS);
		markDirty();
	}

	private Mode getMode(Direction side) { return modes[side.ordinal()]; }

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		modes[0] = Mode.values()[compound.getByte("m0")];
		modes[1] = Mode.values()[compound.getByte("m1")];
		modes[2] = Mode.values()[compound.getByte("m2")];
		modes[3] = Mode.values()[compound.getByte("m3")];
		modes[4] = Mode.values()[compound.getByte("m4")];
		modes[5] = Mode.values()[compound.getByte("m5")];
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putByte("m0", (byte) modes[0].ordinal());
		compound.putByte("m1", (byte) modes[1].ordinal());
		compound.putByte("m2", (byte) modes[2].ordinal());
		compound.putByte("m3", (byte) modes[3].ordinal());
		compound.putByte("m4", (byte) modes[4].ordinal());
		compound.putByte("m5", (byte) modes[5].ordinal());
		return super.write(compound);
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
